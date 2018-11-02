import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Lauren and Will on 11/02/18.
 */
public class MainTester {

    public void runEdgeFieldTest(int numFigure,String name){
        EdgeField edgeField = new EdgeField(numFigure+"|"+name);
        EdgeFieldTest edgeFieldTest = new EdgeFieldTest();
        try{
            JUnitCore junit = new JUnitCore();
            EdgeFieldTest.prepare(edgeField);
            Result result = junit.run(EdgeFieldTest.class);
            System.out.printf("EdgeFieldTest ran: %s, Failed: %s%n",
                    result.getRunCount(), result.getFailureCount());
            //edgeFieldTest.setUp(edgeField);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void runEdgeTableTest(int numFigure,String name){
        EdgeTable edgeTable = new EdgeTable(numFigure+"|"+name);
        EdgeTableTest edgeTableTest = new EdgeTableTest();
        try{
            JUnitCore junit = new JUnitCore();
            EdgeTableTest.prepare(edgeTable);
            Result result = junit.run(EdgeTableTest.class);
            System.out.printf("EdgeTableTest ran: %s, Failed: %s%n",
                    result.getRunCount(), result.getFailureCount());
            //edgeTableTest.setUp(edgeTable);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /*
     * reads the data file
     */
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

    /*
     * checks to see if there are two types of objects
     */
    public boolean check(String[] items){
        boolean f = false;
        boolean n = false;
        for (int i = 0; i < items.length; i++) {
            if(items[i].equals("-f")){
                f = true;
            }else if(items[i].equals("-n")){
                n = true;
            }
        }
        return !(f && n);
    }

    /*
     * Parses command line objects
     */
    public void cmdParser(String[] items){
        if(items.length == 0){
            //default if there is no command line arguments
            runEdgeFieldTest(1, "field1");
            runEdgeTableTest(2, "table1");
        }else {
            //check to see if -n and -f
            if (check(items)) {
                for (int i = 0; i < items.length; i++) {
                    switch (items[i]) {
                        case "-h":
                            //help call
                            System.out.println("Help \n-n : following this command is a test object, use 'f' at the" +
                                    " beginning to identify as an EdgeField object or a 't; to identify as a EdgeTable object." +
                                    " For example 'f,1,field' or 't,2,table'." +
                                    "\n-f : following this command is the name of a test object file containing one or more test" +
                                    " objects" +
                                    "\n\n Note: -n and -f cannot be used in the same command");
                            break;
                        case "-n":
                            //what follows is a test object
                            //f = FieldTest obj, t = TableTest obj
                            String object = items[i + 1];
                            String[] objectArray = object.split(",");
                            if (objectArray[0].equals("f")) {
                                runEdgeFieldTest(Integer.parseInt(objectArray[1]), objectArray[2]);
                            } else if (objectArray[0].equals("t")) {
                                runEdgeTableTest(Integer.parseInt(objectArray[1]), objectArray[2]);
                            } else {
                                System.out.println("The test object argument was supplied incorrectly, use -h for help. ");
                            }
                            break;
                        case "-f":
                            //what follows is the name of a test object file, containing one or more test object
                            readFile(items[i + 1]);
                            break;
                    }
                }
            } else {
                System.out.println("Cannot have an command line object and a test file object, use -h for help.");
            }
        }
    }

    public static void main(String[] args) {
        MainTester mainTester = new MainTester();
        mainTester.cmdParser(args);
    }

}
