/*
  Created by Isabella Sturm
  WILL
  10/24/2018
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeTableTest {

    private static EdgeTable testObj;

    public static void prepare(EdgeTable testObj){
        EdgeTableTest.testObj = testObj;
    }

    @Before
    public void setUp() throws Exception {
        //runTests();
    }


    private void runTests() {
        testGetNumFigure();
        testGetName();
        testAddRelatedTable();
        testGetRelatedTablesArray();
        testGetSetRelatedFieldsArray();
        testAddGetNativeFieldsArray();
        testMoveFieldUp();
        testMoveFieldUpIndex0();
        testMoveFieldDown();
        testMoveFieldDownLast();
        testMakeArrays();
        testToString();
    }

    private void reset() {
        testObj = new EdgeTable("1|table1");
    }

    @Test
    public void testGetNumFigure() {
        assertEquals("Num Figure of the Table should be 1.", 1, testObj.getNumFigure());
        reset();
    }

    @Test
    public void testGetName() {
        assertEquals("The name of the table should be 'table1'", "table1", testObj.getName());
        reset();
    }

    @Test
    public void testAddRelatedTable() {
        int[] testRelatedTables = {3};
        testObj.addRelatedTable(3);
        testObj.makeArrays();

        assertArrayEquals("Table num 3 was added, so there should be value 3 in the related tables array list", testRelatedTables, testObj.getRelatedTablesArray());
        reset();
    }

    @Test
    public void testGetRelatedTablesArray() {
        int [] testRelatedTables = {2};
        testObj.addRelatedTable(2);
        testObj.makeArrays();

        assertArrayEquals("Table num 2 was added, so the related tables array should include 2.", testRelatedTables, testObj.getRelatedTablesArray());
        reset();
    }

    @Test
    public void testGetSetRelatedFieldsArrayExeptionThrown() {
        // tests both set and get related fields
        int[] testRelatedFields = new int[2];
        boolean thrown = false;

        testRelatedFields[0] = 1;
        testObj.makeArrays();

        // error is expected to be thrown when there is no native fields ArrayList because array size=0
        try {
            testObj.setRelatedField(0, 1);
        } catch (IndexOutOfBoundsException e){
            thrown = true;
        }

        //assertArrayEquals("Related field 1 should be set at index 0 of the related fields array.", testRelatedFields, testObj.getRelatedFieldsArray());
        assertTrue(thrown);
        reset();
    }

    @Test
    public void testGetSetRelatedFieldsArray() {
        int[] testRelatedFields = new int[3];

        // test when related fields exist
        testRelatedFields[1] = 1;
        testObj.addNativeField(1);
        testObj.addNativeField(2);
        testObj.addNativeField(3);
        testObj.makeArrays();

        testObj.setRelatedField(1,1);

        assertArrayEquals("Related field 1 should be set at index 1 of the related fields array.", testRelatedFields, testObj.getRelatedFieldsArray());

    }

    @Test
    public void testAddGetNativeFieldsArray() {
        // tests both add and get native fields array
        int[] testNativeFields = {1};
        testObj.addNativeField(1);
        testObj.makeArrays();

        assertArrayEquals("Native field one should be added to the native fields array.", testNativeFields, testObj.getNativeFieldsArray());
        reset();
    }

    @Test
    public void testMoveFieldUpIndex0() {
        int [] testNativeFields = {0,1,2};
        int []  testRelatedFields = {2,1,0};

        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.addNativeField(2);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,1);
        testObj.setRelatedField(2,0);

        testObj.moveFieldUp(0);

        assertArrayEquals("Input index was 0, so no move should have occurred to native fields array.", testNativeFields, testObj.getNativeFieldsArray());
        assertArrayEquals("Input index was 0, so no move should have occurred to related fields array", testRelatedFields, testObj.getRelatedFieldsArray());

        reset();

    }

    @Test
    public void testMoveFieldUp() {
        int [] testNativeFields = {0,2,1};
        int []  testRelatedFields = {2,0,1};

        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.addNativeField(2);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,1);
        testObj.setRelatedField(2,0);

        testObj.moveFieldUp(2);

        assertArrayEquals("Input index was 2, so value at index 2 (2) should have moved up an index in native fields array.", testNativeFields, testObj.getNativeFieldsArray());
        assertArrayEquals("Input index was 2, so value at index 2 (0) should have moved up and index in related fields array", testRelatedFields, testObj.getRelatedFieldsArray());

        reset();
    }

    @Test
    public void testMoveFieldDown() {
        int [] testNativeFields = {0,1,2};
        int []  testRelatedFields = {2,1,0};

        testObj.addNativeField(0);
        testObj.addNativeField(2);
        testObj.addNativeField(1);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,0);
        testObj.setRelatedField(2,1);

        testObj.moveFieldDown(1);

        assertArrayEquals("Input index was 1, so value at index 1 (2) should have moved down an index in native fields array.", testNativeFields, testObj.getNativeFieldsArray());
        assertArrayEquals("Input index was 1, so value at index 1 (0) should have moved down and index in related fields array", testRelatedFields, testObj.getRelatedFieldsArray());

        reset();
    }

    @Test
    public void testMoveFieldDownLast() {
        int [] testNativeFields = {0,1,2};
        int []  testRelatedFields = {2,1,0};

        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.addNativeField(2);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,1);
        testObj.setRelatedField(2,0);

        testObj.moveFieldDown(2);

        assertArrayEquals("Input index was 2 (the last element), so no move should have occurred to native fields array.", testNativeFields, testObj.getNativeFieldsArray());
        assertArrayEquals("Input index was 2 (the last element), so no move should have occurred to related fields array", testRelatedFields, testObj.getRelatedFieldsArray());

        reset();
    }

    @Test
    public void testMakeArrays() {
        int [] testNativeFields = {0,1,2};
        int []  testRelatedFields = {2,1,0};

        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.addNativeField(2);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,1);
        testObj.setRelatedField(2,0);

        assertArrayEquals("Native fields arraylist should have been made into an array", testNativeFields, testObj.getNativeFieldsArray());
        assertArrayEquals("Related fields arraylist should have been made into an array", testRelatedFields, testObj.getRelatedFieldsArray());

        reset();
    }

    @Test
    public void testToString() {
        String expected = "Table: 1\r\n" +
                "{\r\nTableName: table1\r\n" +
                "NativeFields: 0|1|2\r\n" +
                "RelatedTables: 2\r\n" +
                "RelatedFields: 2|1|0\r\n}\r\n";

        testObj.addRelatedTable(2);

        testObj.addNativeField(0);
        testObj.addNativeField(1);
        testObj.addNativeField(2);

        testObj.makeArrays();

        testObj.setRelatedField(0,2);
        testObj.setRelatedField(1,1);
        testObj.setRelatedField(2,0);

        assertEquals("Actual toString should reflect 1 related table, 3 native fields, and 3 related fields to 1:table1", expected, testObj.toString());

        reset();

    }
}
