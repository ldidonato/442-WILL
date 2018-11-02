import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lauren on 10/31/18.
 */
public class MainTester {

    public void runEdgeFieldTest(int numFigure,String name){
        EdgeField edgeField = new EdgeField(numFigure+"|"+name);
        EdgeFieldTest edgeFieldTest = new EdgeFieldTest();
        try{
            edgeFieldTest.setUp(edgeField);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void runEdgeTableTest(int numFigure,String name){
        EdgeTable edgeTable = new EdgeTable(numFigure+"|"+name);
        EdgeTableTest edgeTableTest = new EdgeTableTest();
        try{
            edgeTableTest.setUp(edgeTable);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void readFile(String fileName){
        ArrayList<String[]> edgeFields = new ArrayList<>();
        ArrayList<String[]> edgeTables = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {
            //br = new BufferedReader(new FileReader("./tests/data.csv"));
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] args = line.split(cvsSplitBy);
                if (args[0].equals("f")) {
                    //edgeFields.add(new EdgeField(args[1] + "|" + args[2]));
                    edgeFields.add(new String[]{args[1],args[2]});
                } else if (args[0].equals("t")) {
                    //edgeTables.add(new EdgeTable(args[1] + "|" + args[2]));
                    edgeTables.add(new String[]{args[1],args[2]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0; i<edgeFields.size();i++){
            runEdgeFieldTest(Integer.parseInt(edgeFields.get(i)[0]),edgeFields.get(i)[1]);
        }
        for(int i=0; i<edgeTables.size();i++){
            runEdgeTableTest(Integer.parseInt(edgeTables.get(i)[0]),edgeTables.get(i)[1]);
        }

    }

    public void cmdParser(String[] items){
        for(int i=0; i<items.length; i++){

            switch(items[i]){
                case "-h":
                    System.out.println("Help call");
                    break;
                case "-n":
                    //f = FieldTest obj, t = TableTest obj
                    String object = items[i+1];
                    String[] objectArray = object.split(",");
                    if(objectArray[0].equals("f")){
                        runEdgeFieldTest(Integer.parseInt(objectArray[1]),objectArray[2]);
                    }else if(objectArray[0].equals("t")){
                        runEdgeTableTest(Integer.parseInt(objectArray[1]),objectArray[2]);
                    }else{
                        System.out.println("The test object argument was supplied incorrectly, use -h for help. ");
                    }
                    break;
                case "-f":
                    //System.out.println("what follows is the name of a test object file, containing one or more test object");
                    readFile(items[i+1]);
                    break;
                
            }
        }
    }

    public static void main(String[] args) {
        MainTester mainTester = new MainTester();
        mainTester.cmdParser(args);
    }

}
