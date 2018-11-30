/**
 * Created by Lauren on 10/24/18.
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EdgeFieldTest {
    //EdgeField testObj;

    private static EdgeField testObj;

    public static void prepare(EdgeField testObj){
        EdgeFieldTest.testObj = testObj;
    }

    @Before
    public void setUp() {
        // "numFigure|name"
        runner();
    }

    public void runner(){
        testGetNumFigure();
        testGetName();
        testGetTableID();
        testSetTableID();
        testGetTableBound();
        testSetTableBound();
        testGetFieldBound();
        testSetFieldBound();
        testGetDisallowNull();
        testSetDisallowNull();
        testGetIsPrimaryKey();
        testSetIsPrimaryKey();
        testGetDefaultValue();
        testSetDefaultValue();
        testGetVarcharValue();
        testSetVarcharValue();
        testGetDataType();
        testSetDataType();
    }
    public void resetObj(){
        testObj = new EdgeField("1|Name");
    }

    /*
    * NumFigure is int
    * */
    @Test
    public void testGetNumFigure(){
        assertEquals("numFigure was initialized as 1",1,testObj.getNumFigure());
        resetObj();
    }

    /*
    * Name is string
    * */
    @Test
    public void testGetName(){
        assertEquals("getName was initialized as \"Name\"","Name",testObj.getName());
        resetObj();
    }

    /*
    * TableID is int
    * */
    @Test
    public void testGetTableID(){
        assertEquals("TableID should be 0",0,testObj.getTableID());
        resetObj();
    }

    /*
    * TableID is int
    * */
    @Test
    public void testSetTableID(){
        testObj.setTableID(1);
        assertEquals("TableID should be 1",1,testObj.getTableID());
        resetObj();
    }

    /*
    * TableBound is int
    * */
    @Test
    public void testGetTableBound() {
        assertEquals("TableBound should be 0", 0, testObj.getTableBound());
        resetObj();
    }

    /*
    * TableBound is int
    * */
    @Test
    public void testSetTableBound() {
        testObj.setTableBound(1);
        assertEquals("TableBound should be 1", 1, testObj.getTableBound());
        resetObj();
    }

    /*
    * FieldBound is int
    * */
    @Test
    public void testGetFieldBound(){
        assertEquals("FieldBound should be 0", 0, testObj.getFieldBound());
        resetObj();
    }

    /*
    * FieldBound is int
    * */
    @Test
    public void testSetFieldBound(){
        testObj.setFieldBound(1);
        assertEquals("FieldBound should be 1", 1, testObj.getFieldBound());
        resetObj();
    }

    /*
    * DisallowNull is boolean
    * */
    @Test
    public void testGetDisallowNull(){
        assertEquals("DisallowNull should be false", false, testObj.getDisallowNull());
        resetObj();
    }

    /*
    * DisallowNull is boolean
    */
    @Test
    public void testSetDisallowNull(){
        testObj.setDisallowNull(true);
        assertEquals("DisallowNull should be true", true, testObj.getDisallowNull());
        resetObj();
    }

    /*
    * IsPrimary is boolean
    */
    @Test
    public void testGetIsPrimaryKey(){
        assertEquals("IsPrimaryKey should be false", false, testObj.getIsPrimaryKey());
        resetObj();
    }

    /*
    * IsPrimary is boolean
    */
    @Test
    public void testSetIsPrimaryKey(){
        testObj.setIsPrimaryKey(true);
        assertEquals("IsPrimaryKey should be true", true, testObj.getIsPrimaryKey());
        resetObj();
    }

    /*
    * Default Value is string
    * */
    @Test
    public void testGetDefaultValue(){
        assertEquals("DefaultValue should be \"\"", "", testObj.getDefaultValue());
        resetObj();
    }

    /*
    * DefaultValue is string
    * */
    @Test
    public void testSetDefaultValue(){
        testObj.setDefaultValue("DefaultValue");
        assertEquals("DefaultValue should be \"DefaultValue\"", "DefaultValue", testObj.getDefaultValue());
        resetObj();
    }

    /*
    * VarcharValue is int
    * */
    @Test
    public void testGetVarcharValue(){
        assertEquals("VarcharValue should be 1", 1, testObj.getVarcharValue());
        resetObj();
    }

    /*
    * VarcharValue is int
    * */
    @Test
    public void testSetVarcharValue(){
        testObj.setVarcharValue(2);
        assertEquals("VarcharValue should be 2", 2, testObj.getVarcharValue());
        resetObj();
    }

    /*
    * DataType is int
    */
    @Test
    public void testGetDataType(){
        assertEquals("DataType should be 0", 0, testObj.getDataType());
        resetObj();
    }

    /*
    * DataType is int
    */
    @Test
    public void testSetDataType(){
        testObj.setDataType(1);
        assertEquals("DataType should be 1", 1, testObj.getDataType());
        resetObj();
    }


}







