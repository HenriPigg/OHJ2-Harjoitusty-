package Artistit.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Artistit.*;
import java.util.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.15 09:56:23 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ArtistitTest {



  // Generated by ComTest BEGIN
  /** testIterator171 */
  @Test
  public void testIterator171() {    // Artistit: 171
    Artistit ar = new Artistit(); 
    Artisti a1 = new Artisti(); 
    Artisti a2 = new Artisti(); 
    ar.lisaa(a1); 
    ar.lisaa(a2); 
    Iterator<Artisti> i2 = ar.iterator(); 
    assertEquals("From: Artistit line: 182", a1, i2.next()); 
    assertEquals("From: Artistit line: 183", a2, i2.next()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaArtistit212 */
  @Test
  public void testAnnaArtistit212() {    // Artistit: 212
    Artistit ar = new Artistit(); 
    Artisti a1 = new Artisti(); ar.lisaa(a1); 
    Artisti a2 = new Artisti(); ar.lisaa(a2); 
    List<Artisti> loytyneet; 
    loytyneet = ar.annaArtistit(); 
    assertEquals("From: Artistit line: 221", 2, loytyneet.size()); 
  } // Generated by ComTest END
}