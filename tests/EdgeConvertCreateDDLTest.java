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
        fields = new EdgeField[4];
        EdgeField field1 = new EdgeField("1|field1");
        EdgeField field2 = new EdgeField("2|field2");
        EdgeField field3 = new EdgeField("3|field3");
        EdgeField field4 = new EdgeField("4|field4");
        fields[0] = field1;
        fields[1] = field2;
        fields[2] = field3;

        tables = new EdgeTable[2];
        EdgeTable table1 = new EdgeTable("1|table1");
        EdgeTable table2 = new EdgeTable("2|table2");
        tables[0] = table1;
        table1.addNativeField(1);
        table1.addNativeField(2);
        table1.addNativeField(3);
        table1.makeArrays();
        tables[1] = table2;
        table2.addNativeField(4);
        table2.makeArrays();

        testObj = new CreateDDLMySQL(tables, fields);
        runner();
    }

    public void runner() {
        getTable1();
        getTable2();
        getField1();
        getField2();
        getDatabaseName();
        getProductName();
        getSQLString();
        createDDL();
    }

    /**
     * Gets a table from the object tables by figure value
     */
    @Test
    public void getTable1() {
        assertEquals("Table should be first table in array", tables[0], testObj.getTable(1));
    }

    /**
     * Gets a table from the object tables by figure value
     */
    @Test
    public void getTable2() {
        assertEquals("Table should be second table in array", tables[1], testObj.getTable(2));
    }

    /**
     * Gets a field from the object fields by figure value
     */
    @Test
    public void getField1() {
        assertEquals("Field should be third field in array", fields[2], testObj.getField(3));
    }

    /**
     * Gets a field from the object fields by figure value
     */
    @Test
    public void getField2() {
        assertEquals("Field should be first field in array", fields[0], testObj.getField(1));
    }

    /**
     * Tests that getDatabaseName exists and the return type is string
     */
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

    /**
     * Tests that getProductName exists and the return type is string
     */
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

    /**
     * Tests that getSQLString exists and the return type is string
     */
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

    /**
     * Tests that createDDL exists and the return type is void
     */
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