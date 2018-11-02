import java.util.ArrayList;

/**
 * Created by Lauren on 10/31/18.
 */
public class MainTester {

    public void runEdgeFieldTest(){
        EdgeFieldTest test = new EdgeFieldTest();
        try {
            test.setUp(1, "name");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void cmdParser(String[] items){
        for(String item:items){
            switch(item){
                case "-h":
                    System.out.println("Help call");
                case "-n":
                    System.out.println("what follows is a test object");
                case "-f":
                    System.out.println("what follows is the name of a test object file, containing one or more test object");
            }
        }
    }

    public static void main(String[] args) {
        MainTester mainTester = new MainTester();
        mainTester.cmdParser(args);
    }

}
