package Artistit.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Artistit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.16 10:31:53 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ArtistiTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi96 */
  @Test
  public void testRekisteroi96() {    // Artisti: 96
    Artisti ar = new Artisti(); 
    assertEquals("From: Artisti line: 98", 0, ar.getArtistiID()); 
    ar.rekisteroi(); 
    assertEquals("From: Artisti line: 100", 1, ar.getArtistiID()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse179 */
  @Test
  public void testParse179() {    // Artisti: 179
    Artisti artisti = new Artisti(); 
    artisti.parse("   1   |  10  |   Travis Scott  | 2013"); 
    assertEquals("From: Artisti line: 182", 1, artisti.getArtistiID()); 
    assertEquals("From: Artisti line: 183", "1|10|Travis Scott|2013", artisti.toString()); 
    artisti.rekisteroi(); 
    int n = artisti.getArtistiID(); 
    artisti.parse(""+(n+20)); 
    artisti.rekisteroi(); 
    assertEquals("From: Artisti line: 189", n+20+1, artisti.getArtistiID()); 
    assertEquals("From: Artisti line: 190", "" + (n+20+1) + "|10|Travis Scott|2013", artisti.toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone233 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone233() throws CloneNotSupportedException {    // Artisti: 233
    Artisti kappale = new Artisti(); 
    kappale.parse("   1  |  1  | Travis Scott"); 
    Artisti kopio = kappale.clone(); 
    assertEquals("From: Artisti line: 238", kappale.toString(), kopio.toString()); 
    kappale.parse("   2  |  1   | 123"); 
    assertEquals("From: Artisti line: 240", false, kopio.toString().equals(kappale.toString())); 
  } // Generated by ComTest END
}