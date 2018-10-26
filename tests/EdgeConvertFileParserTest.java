import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class EdgeConvertFileParserTest {
  private EdgeConvertFileParser parser;
  private File file;

  //region parseEdgeFile
  /**
   * Test parseEdgeFile given an entity
   */
  @Test
  void parseEdgeFileFigureEntity() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestEntity.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeFields());
    assertEquals(1, parser.getEdgeTables().length);
  }

  /**
   * Test parseEdgeFile given an attribute
   */
  @Test
  void parseEdgeFileFigureAttribute() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestAttr.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertEquals(1, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given an underlined attribute
   */
  @Test
  void parseEdgeFileFigureAttributeUnderlined() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestAttrUnderl.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertEquals(1, parser.getEdgeFields().length);
    assertEquals(true, parser.getEdgeFields()[0].getIsPrimaryKey());
  }

  /**
   * Test parseEdgeFile given an entity with an existing name
   */
  @Test
  void parseEdgeFileFigureEntityExistingTable() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestEntityExistingName.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }

  /**
   * Test parseEdgeFile given a figure with a blank name
   */
  @Test
  void parseEdgeFileFigureBlankName() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestBlankName.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }

  /**
   * Test parseEdgeFile given a figure with a line break in the text parameter
   */
  @Test
  void parseEdgeFileFigureWithLineBreak() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestNameWithLineBreak.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeFields());
    assertEquals(1, parser.getEdgeTables().length);
    assertEquals("pies", parser.getEdgeTables()[0].getName());
  }

  /**
   * Test parseEdgeFile given an figure that isn't an entity or an attribute
   */
  @Test
  void parseEdgeFileFigureNotEntityOrAttr() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestNotEntityOrAttr.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }

  /**
   * Test parseEdgeFile given a figure with no style
   */
  @Test
  void parseEdgeFileFigureNoStyle() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestNoStyle.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }

  /**
   * Test parseEdgeFile given an figure with Relations in it
   */
  @Test
  void parseEdgeFileFigureWithRelation() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestWithRelation.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }

  /**
   * Test parseEdgeFile given a Connector
   */
  //TODO: finish this and make more of them for different types of connectors
  @Test
  void parseEdgeFileConnector() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/connectorTest.edg");

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }
  //endregion

  //region parseSaveFile
  /**
   * Tests parseSaveFile
   */
  @Test
  void parseSaveFile() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/Test.edg.sav");
    parser = new EdgeConvertFileParser(file);
    assertEquals(7, parser.getEdgeFields().length);
    assertEquals(3, parser.getEdgeTables().length);
  }
  //endregion

  //region openFile

  /**
   * Test getEdgeTables with a file other than a Save or EdgeDiagrammer file
   */
  @Test
  void openFileOther() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/other.txt");
    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }
  //endregion
}