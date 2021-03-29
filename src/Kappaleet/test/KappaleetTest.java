package Kappaleet.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import Kappaleet.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.03.23 11:35:02 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class KappaleetTest {


  // Generated by ComTest BEGIN
  /** 
   * testLisaa39 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa39() throws SailoException {    // Kappaleet: 39
    Kappaleet kt = new Kappaleet(); 
    Kappale k1 = new Kappale(), k2 = new Kappale(); 
    assertEquals("From: Kappaleet line: 43", 0, kt.getLkm()); 
    kt.lisaa(k1); assertEquals("From: Kappaleet line: 44", 1, kt.getLkm()); 
    kt.lisaa(k2); assertEquals("From: Kappaleet line: 45", 2, kt.getLkm()); 
  } // Generated by ComTest END
}