/**
 * 
 */
package Levyyhtio;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 18.3.2021
 *
 */
public class Levyyhtio {

    private int levyyhtioID;
    private String levyyhtio;
    private int perustamisvuosi;
    
    private static int seuraavaNro = 1000;
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {

        Levyyhtio eka = new Levyyhtio();
        
        eka.rekisteroi();
        eka.vastaaCreation(1000);
        eka.tulosta(System.out);
        
    }

    
    /**
     *  Oletusmuodostaja
     */
    public Levyyhtio() {
        //
    }
    
    
    /**
     *  Oletusmuodostaja
     * @param id Levy-yhtiölle annettava id
     */
    public Levyyhtio(int id) {
        this.levyyhtioID = id;
    }   
    
    
    /**
     * @return Levy-yhtiön nimi
     */
    public String getLevyyhtio() {
        return this.levyyhtio;
    }
    
    
    /**
     * @return get metodi levy-yhtiön ID:lle
     */
    public int getLevyyhtioID() {
        return this.levyyhtioID;
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", this.levyyhtioID) + "  " + this.levyyhtio);
        out.println(String.format("%d", this.perustamisvuosi));
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    /**
     * @return rekisterointi
     */
    public int rekisteroi() {
        this.levyyhtioID = seuraavaNro;
        seuraavaNro++;
        
        return this.levyyhtioID;
        
    }
    
    /**
     * Testiarvot
     * @param nro Annettava esimerkki id
     */
    public void vastaaCreation(int nro) {
        this.levyyhtioID = nro;
        this.levyyhtio = "Creation Records";
        this.perustamisvuosi = 2013;
    }

}
