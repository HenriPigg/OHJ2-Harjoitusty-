/**
 * 
 */
package fxArtistit;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 18.3.2021
 *
 */
public class Artisti {

    private int artistiID;
    private int levyyhtioID;
    private String artistiNimi;
    private int aloitusvuosi;
    
    private static int seuraavaNro = 99;

    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Artisti eka = new Artisti();
        eka.rekisteroi();
        
        eka.vastaaTravisScott();
        eka.tulosta(System.out);
    
    }

    
    /**
     * @return Artistin nimi
     */
    public String getArtisti() {
        return this.artistiNimi;
    }
    
    
    /**
     * @return Haetun artistin ID
     */
    public int getArtistiID() {
        return this.artistiID;
    }
    

    /**
     * @return rekisteröidyn artistin
     */
    public int rekisteroi() {
        this.artistiID = seuraavaNro;
        seuraavaNro++;
        
        return this.artistiID;
        
    }
    
    
    /**
     * Luodaan testiarvot artistille.
     */
    public void vastaaTravisScott() {
        this.artistiID = getArtistiID();
        this.levyyhtioID = 1000;
        this.artistiNimi = "Travis Scott";
        this.aloitusvuosi = 2018;
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", this.artistiID));
        out.println(String.format("%d", this.levyyhtioID));
        out.println(this.artistiNimi);
        out.println(String.format("%d", this.aloitusvuosi));
    }

}
