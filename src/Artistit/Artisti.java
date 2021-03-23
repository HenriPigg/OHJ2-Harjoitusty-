/**
 * 
 */
package Artistit;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 18.3.2021
 *
 */
public class Artisti {

    private int artistiID = 0;
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
        
        eka.vastaaTravisScott(10);
        eka.tulosta(System.out);
    
    }
    

    /**
     * Oletusmuodostaja
     */
    public Artisti() {
        //
    }
    
    
    /**
     * id muodostaja
     * @param id artistin id
     */
    public Artisti(int id) {
        this.artistiID = id;
    }
    
    
    /**
     * @return Artistin nimi
     */
    public String getArtistiNimi() {
        return this.artistiNimi;
    }
    
    
    /**
     * @return palauttaa levy-yhtiön id:n
     */
    public int getLevyyhtioID() {
        return this.levyyhtioID;
    }
    
    
    
    /**
     * @return Haetun artistin ID
     */
    public int getArtistiID() {
        return this.artistiID;
    }
    

    /**
     * @return rekisteröidyn artistin
     * @example
     * <pre name="test">
     *  Artisti ar = new Artisti();
     *  ar.getArtistiID() === 0;
     *  ar.rekisteroi();
     *  ar.getArtistiID() === 99;
     * </pre>
     */
    public int rekisteroi() {
        this.artistiID = seuraavaNro;
        seuraavaNro++;
        
        return this.artistiID;
        
    }
    
    
    /**
     * Luodaan testiarvot artistille.
     * @param nro Viite, jonka kappale saa
     */
    public void vastaaTravisScott(int nro) {
        this.artistiID = nro;
        this.levyyhtioID = 1000;
        this.artistiNimi = "Travis Scott";
        this.aloitusvuosi = 2018;
    }
    
    
    /**
     * Luodaan testiarvot artistille.
     * @param nro -//-
     */
    public void vastaaOasis(int nro) {
        this.artistiID = nro;
        this.levyyhtioID = 1001;
        this.artistiNimi = "Oasis";
        this.aloitusvuosi = 2000;
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
