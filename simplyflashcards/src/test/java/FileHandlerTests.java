import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.simplyflashcards.FileHandler;
import com.simplyflashcards.FlashCardSet;


public class FileHandlerTests {
    

    @Test 
    public void testNameCorrect() throws IOException{
        File f = File.createTempFile("temp", ".txt");
        FileWriter fw = new FileWriter(f);

        fw.write("name:cardset size 1\r\n" +
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
       
       assertEquals(fcs.getMetaData().get("name"), "cardset size 1");

       assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());


       f.delete();

        
    }
}
