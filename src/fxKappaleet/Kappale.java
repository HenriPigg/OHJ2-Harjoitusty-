/**
 * 
 */
package fxKappaleet;

import java.io.*;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 22.2.2021
 *
 */
public class Kappale {

    private int kappaleId = 0;
    private int artistiId = 0;
    private int levyyhtioID = 0;
    private String kappaleenNimi = "";
    private int julkaisuvuosi = 0;
    private int kuuntelukerrat = 0;
    
    //----Poistoon?-----
    private String artistiNimi = "";
    private String albumi = "";
    private String levyyhtio = "";
    private String genre = "";



    
    private static int seuraavaNro = 1;
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", kappaleId) + "  " + kappaleenNimi);
        out.println(String.format("%02d", artistiId) + " " + artistiNimi);
        out.println(albumi);
        out.println(String.format("%d", julkaisuvuosi));
        out.println(String.format("%d", levyyhtioID) + " " + levyyhtio);
        out.println(genre);
        out.println(String.format("%d", kuuntelukerrat));
    }
    
    
    /**
     * @return Kappaleen nimi
     */
    public String getKappaleenNimi() {
        return this.kappaleenNimi;
    }
    
    
    /**
     * @return Haetun kappaleen id numeron
     */
    public int getKappaleId() {
        return kappaleId;
    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }

    
    /**
     * Luodaan testiarvot kappaleelle.
     */
    public void vastaaSickoMode() {
        this.kappaleId = this.rekisteroi();
        this.artistiId = 10;
        this.artistiNimi = " Travis Scott";
        this.albumi = "ASTROWORLD";
        this.genre = "Hip hop";
        this.levyyhtio = "Creation Records";
        this.kappaleenNimi = "SICKO MODE";
        this.julkaisuvuosi = 2018;
        this.kuuntelukerrat = randomi(2000,100000);
        this.levyyhtioID = 100;
    }
    
    
    /**
     * @param yla alaraja
     * @param ala yläraja
     * @return Satunnainen luku halutulta väliltä
     */
    public static int randomi(int yla, int ala) {
        double n = (yla-ala)*Math.random() + ala;
        
        return (int)Math.round(n);
    }
    
    
    /**
     * Luodaan testiarvot kappaleelle.
     */
    public void vastaaWonderwall() {
        this.kappaleId = 2;
        this.artistiId = 20;
        this.artistiNimi = " Oasis";
        this.albumi = "Jotain";
        this.genre = "Jotain";
        this.levyyhtio = "En muista";
        this.kappaleenNimi = "Wonderwall";
        this.julkaisuvuosi = 2012;
        this.kuuntelukerrat = 129787999;
        this.levyyhtioID = 101;
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Kappale eka = new Kappale();
        Kappale toka = new Kappale();
        
        eka.rekisteroi();
        toka.rekisteroi();
        
        //eka.tulosta(System.out);
        eka.vastaaSickoMode();
        toka.vastaaWonderwall();
        eka.tulosta(System.out);
        toka.tulosta(System.out);
        
    }

}
