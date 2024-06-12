import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.simplyflashcards.FileHandler;
import com.simplyflashcards.FlashCardSet;


public class FileHandlerTests {
    

    @Test 
    public void testNameCorrect(){
       String dir = System.getProperty("user.dir"); 
       String newDir = dir + "\\src\\test\\testfiles\\cardset_size1.txt";      
       FlashCardSet fcs = FileHandler.loadFlashCardSet(newDir);


       assertEquals(fcs.getMetaData().get("name"), "cardset size 1");

       assertEquals("front", fcs.getFlashCardSet().get(0).getFrontText());

        
    }
}
