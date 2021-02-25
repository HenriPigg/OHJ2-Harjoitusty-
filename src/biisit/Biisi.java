/**
 * 
 */
package biisit;

import java.io.*;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 22.2.2021
 *
 */
public class Biisi {

    
    private int kappaleId;
    private int artistiId;
    private String kappaleenNimi = "";
    // private int julkaisuvuosi;
    // private int kuuntelukerrat;
    
    private static int seuraavaNro = 1;
    
    /**
     * Tulostetaan henkilön tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", kappaleId, 3) + "  " + String.format("%03d", artistiId, 3) + "  "
                + kappaleenNimi);
        
    }
    
    
    /**
     * @return Generoidun kappaleId:n
     */
    public int rekisteroi() {
        this.kappaleId = seuraavaNro;
        seuraavaNro++;
        
        return this.kappaleId;
    }
    
    
    /**
     * @return Haetun kappaleen id numeron
     */
    public int getKappaleId() {
        return kappaleId;
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Biisi eka = new Biisi();
        Biisi toka = new Biisi();
        
        //eka.rekisteroi();
        //toka.rekisteroi();
        
        eka.tulosta(System.out);
        //eka.vastaaEkaBiisi();
        eka.tulosta(System.out);
        
        toka.tulosta(System.out);
        //toka.vastaaEkaBiisi();
        toka.tulosta(System.out);
        
    }

}
