/**
 * 
 */
package Kappaleet;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 25.2.2021
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Poikkeuksen muodostaja jolle tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti Poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }

}
