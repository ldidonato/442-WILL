import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

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
    URL url = getClass().getResource("testFiles/figureTestEntity.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeFields().length);
    assertEquals(1, parser.getEdgeTables().length);
  }

  /**
   * Test parseEdgeFile given an attribute
   */
  @Test
  void parseEdgeFileFigureAttribute() {
    URL url = getClass().getResource("testFiles/figureTestAttr.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(1, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given an underlined attribute
   */
  @Test
  void parseEdgeFileFigureAttributeUnderlined() {
    URL url = getClass().getResource("testFiles/figureTestAttrUnderl.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(1, parser.getEdgeFields().length);
    assertEquals(true, parser.getEdgeFields()[0].getIsPrimaryKey());
  }

  /**
   * Test parseEdgeFile given an entity with an existing name
   */
  @Test
  void parseEdgeFileFigureEntityExistingTable() {
    URL url = getClass().getResource("testFiles/figureTestEntityExistingName.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(1, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given a figure with a blank name
   */
  @Test
  void parseEdgeFileFigureBlankName() {
    URL url = getClass().getResource("testFiles/figureTestBlankName.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given a figure with a line break in the text parameter
   */
  @Test
  void parseEdgeFileFigureWithLineBreak() {
    URL url = getClass().getResource("testFiles/figureTestNameWithLineBreak.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeFields().length);
    assertEquals(1, parser.getEdgeTables().length);
    assertEquals("pies", parser.getEdgeTables()[0].getName());
  }

  /**
   * Test parseEdgeFile given an figure that isn't an entity or an attribute
   */
  @Test
  void parseEdgeFileFigureNotEntityOrAttr() {
    URL url = getClass().getResource("testFiles/figureTestNotEntityOrAttr.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given a figure with no style
   */
  @Test
  void parseEdgeFileFigureNoStyle() {
    URL url = getClass().getResource("testFiles/figureTestNoStyle.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given an figure with Relations in it
   */
  @Test
  void parseEdgeFileFigureWithRelation() {
    URL url = getClass().getResource("testFiles/figureTestWithRelation.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given a Connector
   */
  @Test
  void parseEdgeFileConnector() {
    URL url = getClass().getResource("testFiles/connectorTest.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(0, parser.getEdgeTables().length);
    assertEquals(0, parser.getEdgeFields().length);
  }

  /**
   * Test parseEdgeFile given a Connector connected to actual tables
   */
  @Test
  void parseEdgeFileConnectorWithTables() {
    URL url = getClass().getResource("testFiles/connectorTestTables.edg");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertEquals(2, parser.getEdgeTables().length);
    assertEquals(1, parser.getEdgeFields().length);
  }
  //endregion

  //region parseSaveFile
  /**
   * Tests parseSaveFile
   */
  @Test
  void parseSaveFile() {
    URL url = getClass().getResource("testFiles/Test.edg.sav");
    file = new File(url.getPath());

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
    URL url = getClass().getResource("testFiles/other.txt");
    file = new File(url.getPath());

    parser = new EdgeConvertFileParser(file);
    assertNull(parser.getEdgeTables());
    assertNull(parser.getEdgeFields());
  }
  //endregion
}