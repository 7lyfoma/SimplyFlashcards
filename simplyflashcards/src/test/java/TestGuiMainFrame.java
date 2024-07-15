import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import com.simplyflashcards.GuiMainFrame;

//Skeleton for tests taken from
//https://joel-costigliola.github.io/assertj/assertj-swing-basics.html
//Last accessed [2024-07-15]


public class TestGuiMainFrame extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
  
    @Override
    protected void onSetUp() {
      GuiMainFrame frame = GuiActionRunner.execute(() -> new GuiMainFrame());
      // IMPORTANT: note the call to 'robot()'
      // we must use the Robot from AssertJSwingJUnitTestCase
      window = new FrameFixture(robot(), frame);
      window.show(); // shows the frame to test
    }
  
    @Test
    public void testErrorLabelEmptyAtStart() {
      window.label("ErrorLabel").requireText("");
    }
  }
  