package Artistit.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Artistit.*;
import java.util.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.29 15:06:07 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ArtistitTest {



  // Generated by ComTest BEGIN
  /** testIterator155 */
  @Test
  public void testIterator155() {    // Artistit: 155
    Artistit ar = new Artistit(); 
    Artisti a1 = new Artisti(); 
    Artisti a2 = new Artisti(); 
    ar.lisaa(a1); 
    ar.lisaa(a2); 
    Iterator<Artisti> i2 = ar.iterator(); 
    assertEquals("From: Artistit line: 166", a1, i2.next()); 
    assertEquals("From: Artistit line: 167", a2, i2.next()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaArtistit197 */
  @Test
  public void testAnnaArtistit197() {    // Artistit: 197
    Artistit ar = new Artistit(); 
    Artisti a1 = new Artisti(); ar.lisaa(a1); 
    Artisti a2 = new Artisti(); ar.lisaa(a2); 
    List<Artisti> loytyneet; 
    loytyneet = ar.annaArtistit(1); 
    assertEquals("From: Artistit line: 206", 0, loytyneet.size()); 
  } // Generated by ComTest END
}