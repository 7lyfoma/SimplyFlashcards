import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.simplyflashcards.FileHandler;
import com.simplyflashcards.FlashCard;
import com.simplyflashcards.FlashCardSet;


public class TestFileHandler {

    @Test
    public void testEmptyCardsetCannotLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());

        assertNull(fcs);

        f.delete();

       
    }

    @Test
    public void testMissingFilenameFieldCantLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

       FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());

       assertNull(fcs);

       f.delete();
    }

    @Test
    public void testMissingNameFieldCantLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("filename|temp.txt\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

       FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());

       assertNull(fcs);

       f.delete();
    }

    @Test
    public void testMetaDataOnlyCardsetCanLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

       FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
       
       assertEquals(fcs.getMetaData().get("name"), "cardset 1");

       assertEquals(0, fcs.getFlashCardSet().size());


       f.delete();
    }
    

    @Test 
    public void testMultipleCardsCanLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
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

       FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
       
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
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testFrontImglessCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        "\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testBackImglessCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "back\r\n" + 
                        "frontimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testTextlessCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "\r\n" + 
                        "\r\n" + 
                        "frontimg\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testFrontlessCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "\r\n" + 
                        "back\r\n" + 
                        "\r\n" + 
                        "backimg\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("back", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("backimg", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testBacktlessCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        "front\r\n" + 
                        "\r\n" + 
                        "frontimg\r\n" + 
                        "\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("frontimg", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testEmptyCardCanLoad()  throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n" + 
                        ";");

        fw.flush();
        fw.close();

        FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
        
        assertEquals(fcs.getMetaData().get("name"), "cardset 1");

        assertEquals("", fcs.getFlashCardSet().get(0).getFrontText());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackText());
        assertEquals("", fcs.getFlashCardSet().get(0).getFrontImagePath());
        assertEquals("", fcs.getFlashCardSet().get(0).getBackImagePath());
    }

    @Test 
    public void testMultipleIncompleteCardsCanLoad() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
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

       FlashCardSet fcs = fh.loadFlashCardSet(f.getAbsolutePath());
       
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

    @Test
    public void testCantSaveEmptyFcs(){
        FileHandler fh = new FileHandler();
        FlashCardSet fcs = new FlashCardSet();
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertFalse(success);
    }

    @Test
    public void testCantSaveMissingNameFcs() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("filename", f.getAbsolutePath());
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertFalse(success);

        f.delete();
    }

    @Test
    public void testCantSaveMissingFilenameameFcs(){
        FileHandler fh = new FileHandler();
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertFalse(success);
    }
    
    @Test
    public void testCanSaveMinimumFcs() throws IOException{
        FileHandler fh = new FileHandler();

        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        f.delete();
    }

    @Test
    public void testSaveMinimumFcsSavesCorrectValues() throws IOException{
        FileHandler fh = new FileHandler();

        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        FlashCardSet fcsCheck = fh.loadFlashCardSet(f.getAbsolutePath());

        assertEquals(fcs.getMetaData().get("name"), fcsCheck.getMetaData().get("name"));
        assertEquals(fcs.getMetaData().get("filename"), fcsCheck.getMetaData().get("filename"));

        

        f.delete();
    }

    @Test
    public void testTextOnlyCardSaved() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        fcs.addFlashCard(new FlashCard("1", "2", "",""));
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        FlashCardSet fcsCheck = fh.loadFlashCardSet(f.getAbsolutePath());

        assertEquals(fcs.getMetaData().get("name"), fcsCheck.getMetaData().get("name"));
        assertEquals(fcs.getMetaData().get("filename"), fcsCheck.getMetaData().get("filename"));

        assertEquals(fcs.getFlashCardSet().get(0), fcsCheck.getFlashCardSet().get(0));

    }

    @Test
    public void testFullCardSaved() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        fcs.addFlashCard(new FlashCard("1", "2", "3","4"));
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        FlashCardSet fcsCheck = fh.loadFlashCardSet(f.getAbsolutePath());

        assertEquals(fcs.getMetaData().get("name"), fcsCheck.getMetaData().get("name"));
        assertEquals(fcs.getMetaData().get("filename"), fcsCheck.getMetaData().get("filename"));

        assertEquals(fcs.getFlashCardSet().get(0), fcsCheck.getFlashCardSet().get(0));

    }


    @Test
    public void testManyCardsSaved() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");

        
        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        fcs.addFlashCard(new FlashCard("1", "2", "3","4"));
        fcs.addFlashCard(new FlashCard("11", "21", "31","41"));
        fcs.addFlashCard(new FlashCard("12", "22", "32","42"));
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        FlashCardSet fcsCheck = fh.loadFlashCardSet(f.getAbsolutePath());

        assertEquals(fcs.getMetaData().get("name"), fcsCheck.getMetaData().get("name"));
        assertEquals(fcs.getMetaData().get("filename"), fcsCheck.getMetaData().get("filename"));

        assertEquals(fcs.getFlashCardSet().get(0), fcsCheck.getFlashCardSet().get(0));
        assertEquals(fcs.getFlashCardSet().get(1), fcsCheck.getFlashCardSet().get(1));
        assertEquals(fcs.getFlashCardSet().get(2), fcsCheck.getFlashCardSet().get(2));

    }

    @Test
    public void testCanDeleteCardset() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");

        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        fcs.addFlashCard(new FlashCard("1", "2", "3","4"));
        fcs.addFlashCard(new FlashCard("11", "21", "31","41"));
        fcs.addFlashCard(new FlashCard("12", "22", "32","42"));
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        Boolean deleteSuccess = fh.deleteFlashCardSet(fcs);

        assertTrue(deleteSuccess);

        assertFalse(f.exists());

    }

    @Test
    public void testDeletedCardSetBecomesInactive() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");

        FlashCardSet fcs = new FlashCardSet();

        fcs.addMetaData("name", "temp");
        fcs.addMetaData("filename", f.getAbsolutePath());

        fcs.addFlashCard(new FlashCard("1", "2", "3","4"));
        fcs.addFlashCard(new FlashCard("11", "21", "31","41"));
        fcs.addFlashCard(new FlashCard("12", "22", "32","42"));
        
        Boolean success = fh.saveFlashCardSet(fcs);

        assertTrue(success);

        fh.deleteFlashCardSet(fcs);

        assertFalse(fcs.getIsActive());
   }

   @Test
    public void testEmptyCardsetCannotLoadMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.flush();
        fw.close();

        HashMap<String, String> metadata = fh.loadFlashCardSetMetadata(f.getAbsolutePath());

        assertNull(metadata);

        f.delete();

       
    }

    @Test
    public void testMissingFilenameFieldCantLoadMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

        HashMap<String, String> metadata = fh.loadFlashCardSetMetadata(f.getAbsolutePath());

        assertNull(metadata);

        f.delete();
    }

    @Test
    public void testMissingNameFieldCantLoadMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("filename|temp.txt\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

        HashMap<String, String> metadata = fh.loadFlashCardSetMetadata(f.getAbsolutePath());

        assertNull(metadata);

        f.delete();
    }

    @Test
    public void testMetaDataOnlyCardsetCanLoadMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
                        ";\r\n"
                        );

        fw.flush();
        fw.close();

        HashMap<String, String> metadata = fh.loadFlashCardSetMetadata(f.getAbsolutePath());

       
       assertEquals("cardset 1",metadata.get("name"));
       assertEquals("temp.txt", metadata.get("filename"));

       f.delete();
    }
    

    @Test 
    public void testMultipleCardsCanLoadMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name|cardset 1\r\n" +
                        "filename|temp.txt\r\n" +
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

        HashMap<String, String> metadata = fh.loadFlashCardSetMetadata(f.getAbsolutePath());
       
        assertEquals("cardset 1",metadata.get("name"));
        assertEquals("temp.txt", metadata.get("filename"));

        f.delete();

        
    }

    @Test
    public void testEmptyDirectoryLoadsNothing() throws IOException{
        FileHandler fh = new FileHandler();
        File d = Files.createTempDirectory("tempd").toFile();
        
        ArrayList<HashMap<String, String>> metadatas = fh.getMetadatasFromDirectory(d.getAbsolutePath());

        assertEquals(0, metadatas.size());
    }

    @Test
    public void testMultipleFileDirectoryLoadsMetadata() throws IOException{
        FileHandler fh = new FileHandler();
        File d = Files.createTempDirectory("tempd").toFile();
        

        File f1=  File.createTempFile("temp1", ".txt", d);

        File f2 = File.createTempFile("temp2", ".txt", d);

        FileWriter fw1 = new FileWriter(f1);
        FileWriter fw2 = new FileWriter(f2);


        fw1.write("name|cardset 1\r\n" +
                        "filename|temp1.txt\r\n" +
                        ";");

        fw2.write("name|cardset 2\r\n" +
        "filename|temp2.txt\r\n" +
        ";");

        fw1.flush();
        fw1.close();

        fw2.flush();
        fw2.close();

        ArrayList<HashMap<String, String>> metadatas = fh.getMetadatasFromDirectory(d.getAbsolutePath());

        assertEquals(2, metadatas.size());

        assertEquals("cardset 1", metadatas.get(0).get("name"));
        assertEquals("temp1.txt", metadatas.get(0).get("filename"));

        
        assertEquals("cardset 2", metadatas.get(1).get("name"));
        assertEquals("temp2.txt", metadatas.get(1).get("filename"));


    }

    
    



}
