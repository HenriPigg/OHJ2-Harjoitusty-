package Levyyhtio.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Levyyhtio.*;
import java.util.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.29 15:08:01 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LevyyhtiotTest {



  // Generated by ComTest BEGIN
  /** testIterator50 */
  @Test
  public void testIterator50() {    // Levyyhtiot: 50
    Levyyhtiot ar = new Levyyhtiot(); 
    Levyyhtio a1 = new Levyyhtio(); 
    Levyyhtio a2 = new Levyyhtio(); 
    ar.lisaa(a1); 
    ar.lisaa(a2); 
    Iterator<Levyyhtio> i2 = ar.iterator(); 
    assertEquals("From: Levyyhtiot line: 61", a1, i2.next()); 
    assertEquals("From: Levyyhtiot line: 62", a2, i2.next()); 
  } // Generated by ComTest END
}