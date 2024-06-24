import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import com.simplyflashcards.FileHandler;
import com.simplyflashcards.FlashCardSet;


public class TestFileHandler {

    @Test
    public void testEmptyCardsetCannotLoad() throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());

        assertNull(fcs);

        f.delete();

       
    }

    @Test
    public void testMetaDataOnlyCardsetCanLoad() throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

       FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
       
       assertEquals(fcs.getMetaData().get("name"), "cardset 1");

       assertEquals(0, fcs.getFlashCardSet().size());


       f.delete();
    }
    

    @Test 
    public void testMultipleCardsCanLoad() throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        "fimg\r\n" + 
                        "bimg\r\n" + 
                        ";\r\n" + 
                        "f\r\n" + 
                        "b\r\n" + 
                        "1\r\n" + 
                        "2\r\n" + 
                        ";");

        fw.flush();
        fw.close();

       FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
       
       assertEquals(fcs.getMetaData().get("name"), "cardset 1");

       assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
       assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
       assertEquals("fimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
       assertEquals("bimg", fcs.getFlashCardSet().get(0).getBackImagePath());

       assertEquals("f", fcs.getFlashCardSet().get(1).getFrontText());
       assertEquals("b", fcs.getFlashCardSet().get(1).getBackText());
       assertEquals("1", fcs.getFlashCardSet().get(1).getFrontImagePath());
       assertEquals("2", fcs.getFlashCardSet().get(1).getBackImagePath());


       f.delete();

        
    }


    @Test 
    public void testImglessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testFrontImglessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        "\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testBackImglessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        "frontimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testTextlessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "\r\n" + 
                        "\r\n" + 
                        "frontimg\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testFrontlessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "\r\n" + 
                        "back\r\n" + 
                        "\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testBacktlessCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "\r\n" + 
                        "frontimg\r\n" + 
                        "\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testEmptyCardCanLoad()  throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testMultipleIncompleteCardsCanLoad() throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset 1\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        ";\r\n" + 
                        "f\r\n" + 
                        "b\r\n" + 
                        "1\r\n" + 
                        ";\r\n" + 
                        "f\r\n" + 
                        "b\r\n" + 
                        "\r\n" + 
                        "2\r\n" + 
                        ";");

        fw.flush();
        fw.close();

       FlashCardSet fcs = FileHandler.loadFlashCardSet(f.getAbsolutePath());
       
       assertEquals(fcs.getMetaData().get("name"), "cardset 1");

       assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
       assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
       assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
       assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());

       assertEquals("f", fcs.getFlashCardSet().get(1).getFrontText());
       assertEquals("b", fcs.getFlashCardSet().get(1).getBackText());
       assertEquals("1", fcs.getFlashCardSet().get(1).getFrontImagePath());
       assertEquals("", fcs.getFlashCardSet().get(1).getBackImagePath());

       assertEquals("f", fcs.getFlashCardSet().get(2).getFrontText());
       assertEquals("b", fcs.getFlashCardSet().get(2).getBackText());
       assertEquals("", fcs.getFlashCardSet().get(2).getFrontImagePath());
       assertEquals("2", fcs.getFlashCardSet().get(2).getBackImagePath());


       f.delete();

        
    }
}
