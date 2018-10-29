/**
 * Created by Will on 10/28/18.
 * Tests the abstract class EdgeConvertCreateDDL
 * Uses the CreateDDLMySQL to test the abstract class since it extends the abstract class
 */
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class EdgeConvertCreateDDLTest {

    CreateDDLMySQL testObj;
    EdgeField[] fields;
    EdgeTable[] tables;


    @Before
    public void setUp() throws Exception {
        fields = new EdgeField[3];
        EdgeField field1 = new EdgeField("1|field1");
        EdgeField field2 = new EdgeField("2|field2");
        EdgeField field3 = new EdgeField("3|field3");
        fields[0] = field1;
        fields[1] = field2;
        fields[2] = field3;

        tables = new EdgeTable[1];
        EdgeTable table1 = new EdgeTable("1|table1");
        tables[0] = table1;
        table1.addNativeField(1);
        table1.addNativeField(2);
        table1.addNativeField(2);
        table1.makeArrays();

        testObj = new CreateDDLMySQL(tables, fields);
        runner();
    }

    public void runner() {
//        convertStrBooleanToIntTrue();
//        convertStrBooleanToIntFalse();
//        generateDatabaseName();
//        getDatabaseName();
//        getProductName();
//        getSQLString();
    }

    @Test
    public void getTable() {
        assertEquals("Table should be first table in array", tables[0], testObj.getTable(1));
    }

    @Test
    public void getField() {
        assertEquals("Table should be third field in array", fields[2], testObj.getField(3));
    }

    @Test
    public void getDatabaseName() {
        Method methodToFind = null;
        try {
            methodToFind = CreateDDLMySQL.class.getMethod("getDatabaseName");
        } catch (NoSuchMethodException | SecurityException e) {
            fail();
        }

        if (methodToFind != null) {
            assertEquals("Return type of getDataBaseName should be a string", String.class, methodToFind.getReturnType());
        } else {
            fail();
        }
    }

    @Test
    public void getProductName() {
        Method methodToFind = null;
        try {
            methodToFind = CreateDDLMySQL.class.getMethod("getProductName");
        } catch (NoSuchMethodException | SecurityException e) {
            fail();
        }

        if (methodToFind != null) {
            assertEquals("Return type of getProductName should be a string", String.class, methodToFind.getReturnType());
        } else {
            fail();
        }
    }

    @Test
    public void getSQLString() {
        Method methodToFind = null;
        try {
            methodToFind = CreateDDLMySQL.class.getMethod("getSQLString");
        } catch (NoSuchMethodException | SecurityException e) {
            fail();
        }

        if (methodToFind != null) {
            assertEquals("Return type of getSQLString should be a string", String.class, methodToFind.getReturnType());
        } else {
            fail();
        }
    }

    @Test
    public void createDDL() {
        Method methodToFind = null;
        try {
            methodToFind = CreateDDLMySQL.class.getMethod("createDDL");
        } catch (NoSuchMethodException | SecurityException e) {
            fail();
        }

        if (methodToFind != null) {
            assertEquals("Return type of createDDL should be void", Void.TYPE, methodToFind.getReturnType());
        } else {
            fail();
        }
    }
}