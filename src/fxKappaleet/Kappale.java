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
    private int artistiID = 0;
    private String kappaleenNimi = "";
    private String albumi = "";
    private int julkaisuvuosi = 0;
    private String genre = "";
    private int kuuntelukerrat = 0;
    
  



    
    private static int seuraavaNro = 1;
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(kappaleenNimi);
        out.println(albumi);
        out.println(String.format("%d", julkaisuvuosi));
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
     * @return Palauttaa kappaleen artisti id:n
     */
    public int getArtistiID() {
        return this.artistiID;
    }
    
    
    /**
     * Luodaan testiarvot kappaleelle.
     */
    public void vastaaSickoMode() {
        this.kappaleId = getKappaleId();
        this.artistiID = 10;
        this.albumi = "ASTROWORLD";
        this.genre = "Hip hop";
        this.kappaleenNimi = "SICKO MODE";
        this.julkaisuvuosi = 2018;
        this.kuuntelukerrat = randomi(2000,100000);
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
        this.kappaleId = getKappaleId();
        this.artistiID = 20;
        this.albumi = "Jotain";
        this.genre = "Jotain";
        this.kappaleenNimi = "Wonderwall";
        this.julkaisuvuosi = 2012;
        this.kuuntelukerrat = randomi(200, 10000000);
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
