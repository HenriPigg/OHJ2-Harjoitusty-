/**
 * 
 */
package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 23.3.2021
 *
 */

@RunWith(Suite.class)
@SuiteClasses({
   Kappaleet.test.KappaleTest.class,
   Kappaleet.test.KappaleetTest.class,
   Kappaleet.test.RekisteriTest.class,
   
   Artistit.test.ArtistiTest.class,
   Artistit.test.ArtistitTest.class,
   
   Levyyhtio.test.LevyyhtioTest.class,
   Levyyhtio.test.LevyyhtiotTest.class,
})
public class AllTests {
//
}
