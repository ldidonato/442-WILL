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

    public static void main(String[] args) {
        for(String item:args){
            System.out.println(item);
        }
    }

}
