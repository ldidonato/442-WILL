import java.io.*;
import java.util.*;
import javax.swing.*;

public class EdgeConvertFileParser {
   //private String filename = "test.edg";
   private File parseFile;
   private FileReader fr;
   private BufferedReader br;
   private String currentLine;
   private ArrayList alTables, alFields, alConnectors;
   private EdgeTable[] tables;
   private EdgeField[] fields;
   private EdgeField tempField;
   private EdgeConnector[] connectors;
   private String style;
   private String text;
   private String tableName;
   private String fieldName;
   private boolean isEntity, isAttribute, isUnderlined = false;
   private int numFigure, numConnector, numFields, numTables, numNativeRelatedFields;
   private int endPoint1, endPoint2;
   private int numLine;
   private String endStyle1, endStyle2;
   public static final String EDGE_ID = "EDGE Diagram File"; //first line of .edg files should be this
   public static final String SAVE_ID = "EdgeConvert Save File"; //first line of save files should be this
   public static final String DELIM = "|";
   
   public EdgeConvertFileParser(File constructorFile) {
      numFigure = 0;
      numConnector = 0;
      alTables = new ArrayList();
      alFields = new ArrayList();
      alConnectors = new ArrayList();
      isEntity = false;
      isAttribute = false;
      parseFile = constructorFile;
      numLine = 0;
      this.openFile(parseFile);
   }

   public void parseEdgeFile() throws IOException {
      //read whole file
      while ((currentLine = br.readLine()) != null) {
         currentLine = currentLine.trim();

         //collect Figure data
         if (currentLine.startsWith("Figure ")) { //this is the start of a Figure entry

            //get the Figure number
            numFigure = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));

            //throw away line
            currentLine = br.readLine().trim(); // this should be "{"

            //real line
            currentLine = br.readLine().trim();

            // this is to weed out other Figures, like Labels
            if (!currentLine.startsWith("Style")) {
               continue;
            } else {
               //configure styles
               style = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\"")); //get the Style parameter

               //presence of Relations implies lack of normalization
               if (style.startsWith("Relation")) {
                  JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + parseFile + "\ncontains relations.  Please resolve them and try again.");
                  EdgeConvertGUI.setReadSuccess(false);
                  break;
               }

               //determine type
               if (style.startsWith("Entity")) {
                  isEntity = true;
               }
               if (style.startsWith("Attribute")) {
                  isAttribute = true;
               }

               //these are the only Figures we're interested in
               if (!(isEntity || isAttribute)) {
                  continue;
               }

               //this should be Text
               currentLine = br.readLine().trim();

               //get the Text parameter
               text = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\"")).replaceAll(" ", "");

               //blank name
               if (text.equals("")) {
                  JOptionPane.showMessageDialog(null, "There are entities or attributes with blank names in this diagram.\nPlease provide names for them and try again.");
                  EdgeConvertGUI.setReadSuccess(false);
                  break;
               }

               //find line break
               int escape = text.indexOf("\\");
               if (escape > 0) { //Edge denotes a line break as "\line", disregard anything after a backslash
                  text = text.substring(0, escape);
               }

               //advance to end of record, look for whether the text is underlined
               do {
                  currentLine = br.readLine().trim();
                  if (currentLine.startsWith("TypeUnderl")) {
                     isUnderlined = true;
                  }
               } while (!currentLine.equals("}")); // this is the end of a Figure entry

               //create a new EdgeTable object and add it to the alTables ArrayList
               if (isEntity) {
                  //catch existing tables
                  if (isTableDup(text)) {
                     JOptionPane.showMessageDialog(null, "There are multiple tables called " + text + " in this diagram.\nPlease rename all but one of them and try again.");
                     EdgeConvertGUI.setReadSuccess(false);
                     break;
                  }
                  alTables.add(new EdgeTable(numFigure + DELIM + text));
               }

               if (isAttribute) {
                  //create a new EdgeField object and add it to the alFields ArrayList
                  tempField = new EdgeField(numFigure + DELIM + text);
                  tempField.setIsPrimaryKey(isUnderlined);
                  alFields.add(tempField);
               }

               //reset flags
               isEntity = false;
               isAttribute = false;
               isUnderlined = false;
            }
         } // if("Figure")

         //this is the start of a Connector entry
         if (currentLine.startsWith("Connector ")) {
            //get the Connector number
            numConnector = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));

            //unimportant lines
            currentLine = br.readLine().trim(); // this should be "{"
            currentLine = br.readLine().trim(); // not interested in Style

            // Figure1
            currentLine = br.readLine().trim();
            endPoint1 = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));

            // Figure2
            currentLine = br.readLine().trim();
            endPoint2 = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1));

            //unimportant lines
            currentLine = br.readLine().trim(); // EndPoint1
            currentLine = br.readLine().trim(); // EndPoint2
            currentLine = br.readLine().trim(); // SuppressEnd1
            currentLine = br.readLine().trim(); // SuppressEnd2

            // End1
            currentLine = br.readLine().trim();
            endStyle1 = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\"")); //get the End1 parameter

            // End2
            currentLine = br.readLine().trim();
            endStyle2 = currentLine.substring(currentLine.indexOf("\"") + 1, currentLine.lastIndexOf("\"")); //get the End2 parameter

            do { //advance to end of record
               currentLine = br.readLine().trim();
            } while (!currentLine.equals("}")); // this is the end of a Connector entry
            
            alConnectors.add(new EdgeConnector(numConnector + DELIM + endPoint1 + DELIM + endPoint2 + DELIM + endStyle1 + DELIM + endStyle2));
         } // if("Connector")
      } // while()
   } // parseEdgeFile()
   
   private void resolveConnectors() { //Identify nature of Connector endpoints
      //initialize variables
      int endPoint1, endPoint2;
      int fieldIndex = 0, table1Index = 0, table2Index = 0;

      //loop through connectors
      for (int cIndex = 0; cIndex < connectors.length; cIndex++) {

         //gather data and initialize flag
         endPoint1 = connectors[cIndex].getEndPoint1();
         endPoint2 = connectors[cIndex].getEndPoint2();
         fieldIndex = -1;

         //search fields array for endpoints
         for (int fIndex = 0; fIndex < fields.length; fIndex++) {
            //found endPoint1 in fields array
            if (endPoint1 == fields[fIndex].getNumFigure()) {
               connectors[cIndex].setIsEP1Field(true); //set appropriate flag
               fieldIndex = fIndex; //identify which element of the fields array that endPoint1 was found in
            }

            //found endPoint2 in fields array
            if (endPoint2 == fields[fIndex].getNumFigure()) {
               connectors[cIndex].setIsEP2Field(true); //set appropriate flag
               fieldIndex = fIndex; //identify which element of the fields array that endPoint2 was found in
            }
         }

         //search tables array for endpoints
         for (int tIndex = 0; tIndex < tables.length; tIndex++) {
            //found endPoint1 in tables array
            if (endPoint1 == tables[tIndex].getNumFigure()) {
               connectors[cIndex].setIsEP1Table(true); //set appropriate flag
               table1Index = tIndex; //identify which element of the tables array that endPoint1 was found in
            }

            //found endPoint1 in tables array
            if (endPoint2 == tables[tIndex].getNumFigure()) {
               connectors[cIndex].setIsEP2Table(true); //set appropriate flag
               table2Index = tIndex; //identify which element of the tables array that endPoint2 was found in
            }
         }

         //both endpoints are fields, implies lack of normalization
         if (connectors[cIndex].getIsEP1Field() && connectors[cIndex].getIsEP2Field()) {
            JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + parseFile + "\ncontains composite attributes. Please resolve them and try again.");
            EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
            break; //stop processing list of Connectors
         }

         //both endpoints are tables
         if (connectors[cIndex].getIsEP1Table() && connectors[cIndex].getIsEP2Table()) {
            //the connector represents a many-many relationship, implies lack of normalization
            if ((connectors[cIndex].getEndStyle1().indexOf("many") >= 0) &&
                (connectors[cIndex].getEndStyle2().indexOf("many") >= 0)) {
               JOptionPane.showMessageDialog(null, "There is a many-many relationship between tables\n\"" + tables[table1Index].getName() + "\" and \"" + tables[table2Index].getName() + "\"" + "\nPlease resolve this and try again.");
               EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
               break; //stop processing list of Connectors
            } else { //add Figure number to each table's list of related tables
               tables[table1Index].addRelatedTable(tables[table2Index].getNumFigure());
               tables[table2Index].addRelatedTable(tables[table1Index].getNumFigure());
               continue; //next Connector
            }
         }

         //field has not been assigned to a table yet
         if (fieldIndex >=0 && fields[fieldIndex].getTableID() == 0) {
            //endpoint1 is the table
            if (connectors[cIndex].getIsEP1Table()) {
               tables[table1Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
               fields[fieldIndex].setTableID(tables[table1Index].getNumFigure()); //tell the field what table it belongs to
            } else { //endpoint2 is the table
               tables[table2Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
               fields[fieldIndex].setTableID(tables[table2Index].getNumFigure()); //tell the field what table it belongs to
            }
         } else if (fieldIndex >=0) { //field has already been assigned to a table
            JOptionPane.showMessageDialog(null, "The attribute " + fields[fieldIndex].getName() + " is connected to multiple tables.\nPlease resolve this and try again.");
            EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
            break; //stop processing list of Connectors
         }
      } // connectors for() loop
   } // resolveConnectors()
   
   public void parseSaveFile() throws IOException { //this method is not good
      //initialize variables
      StringTokenizer stTables, stNatFields, stRelFields, stNatRelFields, stField;
      EdgeTable tempTable;
      EdgeField tempField;

      //unnecessary lines
      currentLine = br.readLine();
      currentLine = br.readLine(); //this should be "Table: "

      //should be an if statement?
      while (currentLine.startsWith("Table: ")) {
         //collect data
         numFigure = Integer.parseInt(currentLine.substring(currentLine.indexOf(" ") + 1)); //get the Table number
         currentLine = br.readLine(); //this should be "{"
         currentLine = br.readLine(); //this should be "TableName"
         tableName = currentLine.substring(currentLine.indexOf(" ") + 1);
         tempTable = new EdgeTable(numFigure + DELIM + tableName);
         
         currentLine = br.readLine(); //this should be the NativeFields list
         stNatFields = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numFields = stNatFields.countTokens();

         //loop through fields
         for (int i = 0; i < numFields; i++) {
            tempTable.addNativeField(Integer.parseInt(stNatFields.nextToken()));
         }

         //gather more data
         currentLine = br.readLine(); //this should be the RelatedTables list
         stTables = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numTables = stTables.countTokens();

         //loop through tables
         for (int i = 0; i < numTables; i++) {
            tempTable.addRelatedTable(Integer.parseInt(stTables.nextToken()));
         }
         tempTable.makeArrays();

         //gather more data
         currentLine = br.readLine(); //this should be the RelatedFields list
         stRelFields = new StringTokenizer(currentLine.substring(currentLine.indexOf(" ") + 1), DELIM);
         numFields = stRelFields.countTokens();

         //loop through fields again...
         for (int i = 0; i < numFields; i++) {
            tempTable.setRelatedField(i, Integer.parseInt(stRelFields.nextToken()));
         }

         //create the table
         alTables.add(tempTable);

         //read unnecessary lines
         currentLine = br.readLine(); //this should be "}"
         currentLine = br.readLine(); //this should be "\n"
         currentLine = br.readLine(); //this should be either the next "Table: ", #Fields#
      }

      //while there are non-null lines
      while ((currentLine = br.readLine()) != null) {
         //get data
         stField = new StringTokenizer(currentLine, DELIM);
         numFigure = Integer.parseInt(stField.nextToken());
         fieldName = stField.nextToken();
         tempField = new EdgeField(numFigure + DELIM + fieldName);
         tempField.setTableID(Integer.parseInt(stField.nextToken()));
         tempField.setTableBound(Integer.parseInt(stField.nextToken()));
         tempField.setFieldBound(Integer.parseInt(stField.nextToken()));
         tempField.setDataType(Integer.parseInt(stField.nextToken()));
         tempField.setVarcharValue(Integer.parseInt(stField.nextToken()));
         tempField.setIsPrimaryKey(Boolean.valueOf(stField.nextToken()).booleanValue());
         tempField.setDisallowNull(Boolean.valueOf(stField.nextToken()).booleanValue());

         //Default Value may not be defined
         if (stField.hasMoreTokens()) {
            tempField.setDefaultValue(stField.nextToken());
         }

         //create field
         alFields.add(tempField);
      }
   } // parseSaveFile()

   //convert ArrayList objects into arrays of the appropriate Class type
   private void makeArrays() {
      if (alTables != null) {
         tables = (EdgeTable[])alTables.toArray(new EdgeTable[alTables.size()]);
      }
      if (alFields != null) {
         fields = (EdgeField[])alFields.toArray(new EdgeField[alFields.size()]);
      }
      if (alConnectors != null) {
         connectors = (EdgeConnector[])alConnectors.toArray(new EdgeConnector[alConnectors.size()]);
      }
   }

   //Determines if a table is a duplicate
   private boolean isTableDup(String testTableName) {
      for (int i = 0; i < alTables.size(); i++) {
         EdgeTable tempTable = (EdgeTable)alTables.get(i);
         if (tempTable.getName().equals(testTableName)) {
            return true;
         }
      }
      return false;
   }

   //region accessors
   public EdgeTable[] getEdgeTables() {
      return tables;
   }
   
   public EdgeField[] getEdgeFields() {
      return fields;
   }
   //endregion
   
   public void openFile(File inputFile) {
      try {
         fr = new FileReader(inputFile);
         br = new BufferedReader(fr);

         //test for what kind of file we have
         currentLine = br.readLine().trim();
         numLine++;

         //the file chosen is an Edge Diagrammer file
         if (currentLine.startsWith(EDGE_ID)) {
            this.parseEdgeFile(); //parse the file
            br.close();
            this.makeArrays(); //convert ArrayList objects into arrays of the appropriate Class type
            this.resolveConnectors(); //Identify nature of Connector endpoints
         } else {
            //the file chosen is a Save file created by this application
            if (currentLine.startsWith(SAVE_ID)) {
               this.parseSaveFile(); //parse the file
               br.close();
               this.makeArrays(); //convert ArrayList objects into arrays of the appropriate Class type
            } else { //the file chosen is something else
               JOptionPane.showMessageDialog(null, "Unrecognized file format");
            }
         }
      } // try
      catch (FileNotFoundException fnfe) {
         System.out.println("Cannot find \"" + inputFile.getName() + "\".");
         System.exit(0);
      } // catch FileNotFoundException
      catch (IOException ioe) {
         System.out.println(ioe);
         System.exit(0);
      } // catch IOException
   } // openFile()
} // EdgeConvertFileHandler
