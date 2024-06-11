package tests;
import static org.junit.Assert.assertEquals;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class SimpleTest {
    
    @Test 
    public void test() {
        assertEquals(0,0);
        assertThat(0).isEqualTo(0);
    }

}