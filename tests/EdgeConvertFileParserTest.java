import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;

class EdgeConvertFileParserTest {
  private EdgeConvertFileParser parser;
  private EdgeConvertFileParser mock;

  @BeforeEach
  void setUp() {
    File file = new File("./Courses.edg");
    parser = new EdgeConvertFileParser(file);

    mock = mock(EdgeConvertFileParser.class);
  }

  //region parseEdgeFile
  /**
   * Test parseEdgeFile given an entity
   */
  @Test
  void parseEdgeFileFigureEntity() {
  }

  /**
   * Test parseEdgeFile given an attribute
   */
  @Test
  void parseEdgeFileFigureAttribute() {
  }

  /**
   * Test parseEdgeFile given an underlined attribute
   */
  @Test
  void parseEdgeFileFigureAttributeUnderlined() {
  }

  /**
   * Test parseEdgeFile given an entity with an existing name
   */
  @Test
  void parseEdgeFileFigureEntityExistingTable() {
  }

  /**
   * Test parseEdgeFile given a figure with a blank name
   */
  @Test
  void parseEdgeFileFigureBlankName() {
  }

  /**
   * Test parseEdgeFile given a figure with no line break in the text parameter
   */
  @Test
  void parseEdgeFileFigureWithoutLineBreak() {
  }

  /**
   * Test parseEdgeFile given an figure that isn't an entity or an attribute
   */
  @Test
  void parseEdgeFileFigureNotEntityOrAttr() {
  }

  /**
   * Test parseEdgeFile given a figure with no style
   */
  @Test
  void parseEdgeFileFigureNoStyle() {
  }

  /**
   * Test parseEdgeFile given an figure with Relations in it
   */
  @Test
  void parseEdgeFileFigureWithRelation() {
  }

  /**
   * Test parseEdgeFile given a Connector
   */
  @Test
  void parseEdgeFileConnector() {
  }
  //endregion

  //region parseSaveFile
  /**
   * Tests parseSaveFile
   */
  @Test
  void parseSaveFile() {
  }

  /**
   * Tests parseSaveFile with an undefined next token
   */
  @Test
  void parseSaveFileTokenNotDefined() {
  }
  //endregion

  //region getEdgeTables
  /**
   * Test getEdgeTables
   */
  @Test
  void getEdgeTables() {
  }
  //endregion

  //region getEdgeFields
  /**
   * Test getEdgeFields
   */
  @Test
  void getEdgeFields() {
  }
  //endregion

  //region openFile
  /**
   * Test getEdgeTables with an EdgeDiagrammer file
   */
  @Test
  void openFileEdgeDiagrammer() {
  }

  /**
   * Test getEdgeTables with a Save file
   */
  @Test
  void openFileSave() {
  }

  /**
   * Test getEdgeTables with a file other than a Save or EdgeDiagrammer file
   */
  @Test
  void openFileOther() {
  }

  /**
   * Test getEdgeTables with a FileNotFoundException
   */
  @Test
  void openFileFileNotFound() {
  }

  /**
   * Test getEdgeTables with an IOException
   */
  @Test
  void openFileIO() {
  }
  //endregion
}