import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;

class EdgeConvertFileParserTest {
  private EdgeConvertFileParser parser;
  private File mock;
  private File file;

  @BeforeEach
  void setUp() {
  }

  //region parseEdgeFile
  /**
   * Test parseEdgeFile given an entity
   */
  @Test
  void parseEdgeFileFigureEntity() {
    file = new File("/Users/lauren/Documents/GitHub/442-WILL/tests/figureTestAttr.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
      assertNull(parser.getEdgeFields());
      assertEquals(1, parser.getEdgeTables().length);
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given an attribute
   */
  @Test
  void parseEdgeFileFigureAttribute() {
    file = new File("./figureTestAttr.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given an underlined attribute
   */
  @Test
  void parseEdgeFileFigureAttributeUnderlined() {
    file = new File("./figureTestAttrUnderl.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given an entity with an existing name
   */
  @Test
  void parseEdgeFileFigureEntityExistingTable() {
    file = new File("./figureTestEntityExistingName.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given a figure with a blank name
   */
  @Test
  void parseEdgeFileFigureBlankName() {
    file = new File("./figureTestBlankName.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given a figure with a line break in the text parameter
   */
  @Test
  void parseEdgeFileFigureWithLineBreak() {
    file = new File("./figureTestNameWithLineBreak.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given an figure that isn't an entity or an attribute
   */
  @Test
  void parseEdgeFileFigureNotEntityOrAttr() {
    file = new File("./figureTestAttrNotEntityOrAttr.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given a figure with no style
   */
  @Test
  void parseEdgeFileFigureNoStyle() {
    file = new File("./figureTestNoStyle.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given an figure with Relations in it
   */
  @Test
  void parseEdgeFileFigureWithRelation() {
    file = new File("./figureTestWithRelation.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
  }

  /**
   * Test parseEdgeFile given a Connector
   */
  @Test
  void parseEdgeFileConnector() {
    file = new File("./connectorTest.edg");
    parser = new EdgeConvertFileParser(file);

    try
    {
      parser.parseEdgeFile();
    }
    catch(IOException e)
    {
      fail("IOException");
    }
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
    //when(mock).thenReturn();
    //assertTrue();
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