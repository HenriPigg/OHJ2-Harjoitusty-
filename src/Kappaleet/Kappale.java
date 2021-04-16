/**
 * 
 */
package Kappaleet;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;


/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 22.2.2021
 *
 */
public class Kappale implements Cloneable{

    private int kappaleId = 0;
    private int artistiID = 0;
    private int yhtioID = 0;
    private String kappaleenNimi = "";
    private String albumi = "";
    private String julkaisuvuosi = "";
    private String genre = "";
    private String kuuntelukerrat = "";
    
    private static int seuraavaNro = 1;
    private static int seuraavaYhtioNro = 1;
    
    
    /**
     * @return Kenttien lukumäärä
     */
    public int getKenttia() {
        return 8;
    }
    
    
    /**
     * @return Ensimmäinen "hyvä" kenttä
     */
    public int ekaKentta() {
        return 3;
    }
    
    
    /**
     * @param k kenttää vastaava kysymys
     * @return kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Kappale id";
        case 1: return "Artisti id";
        case 2: return "Levy-yhtiö id";
        case 3: return "Kappaleen nimi";
        case 4: return "albumi";
        case 5: return "julkaisuvuosi";
        case 6: return "genre";
        case 7: return "kuuntelukerrat";
        default: return "Äääliö";
        }

    }
    
    
    /**
     * Tulostetaan kappaleen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%d", this.kappaleId));
        out.println(kappaleenNimi);
        out.println(albumi);
        out.println(julkaisuvuosi);
        out.println(genre);
        out.println(kuuntelukerrat);
    }
    
    
    /**
     * @param k Monesko kenttä
     * @return Monennen kentän sisältö annetaan
     */
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + kappaleId;
        case 1: return "" + artistiID;
        case 2: return "" + yhtioID;
        case 3: return "" + kappaleenNimi;
        case 4: return "" + albumi;
        case 5: return "" + julkaisuvuosi;
        case 6: return "" + genre;
        case 7: return "" + kuuntelukerrat;
        default: return "Äääliö";
        }

    }
    
    
    /**
     * @return Kappaleen nimi
     * @example
     * <pre name="test">
     * Kappale kappale = new Kappale();
     * kappale.vastaaSickoMode();
     * kappale.getKappaleenNimi() === "SICKO MODE";
     * </pre>
     */
    public String getKappaleenNimi() {
        return this.kappaleenNimi;
    }
    
    
    /**
     * @return Kappaleen albumin nimi
     */
    public String getAlbumi() {
        return this.albumi;
    }
    
    
    /**
     * @return Kappaleen julkaisuvuosi
     */
    public String getVuosi() {
        return this.julkaisuvuosi;
    }
    
    
    /**
     * @return Haetun kappaleen id numeron
     *
     */
    public int getKappaleId() {
        return this.kappaleId;
    }
    
    
    /**
     * @return Levy-yhtiön id
     */
    public int getYhtioID() {
        return this.yhtioID;
    }
    
    
    /**
     * @return Kappaleen genre
     */
    public String getGenre() {
        return this.genre;
    }
    
    
    /**
     * @return Kappaleen kuuntelukerrat
     */
    public String getKuuntelukerrat() {
        return this.kuuntelukerrat;
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
     * @param s Kappaleelle annettava nimi
     * @return null jos kaikki ok
     */
    public String setKappaleenNimi(String s) { 
        this.kappaleenNimi = s;
        return null;
    }
    
    
    /**
     * @param s Albumille annettava nimi
     * @return null jos kaikki ok
     */
    public String setAlbumi(String s) {
        this.albumi = s;
        return null;
    }
    
    
    /**
     * @param s Annettava julkaisuvuosi
     * @return null jos kaikki ok
     */
    public String setJulkaisuvuosi(String s) {
        if (!s.matches("[0-9]*") ) return "Julkaisuvuoden pitää olla numeerinen";
        this.julkaisuvuosi = s;
        return null;
    }
    
    
    /**
     * @param s Annettava genre
     * @return null jos kaikki ok
     */
    public String setGenre(String s) {
        this.genre = s;
        return null;
    }
    
    
    /**
     * @param s Annettavat kuuntelukerrat
     * @return null jos kaikki ok
     */
    public String setKuuntelukerrat(String s) {
        if (!s.matches("[0-9]*") ) return "Kuuntelukertojen pitää olla numeerinen";
        this.kuuntelukerrat = s;
        return null;
    }
    
    
    /**
     * @param s asetettava artisti id
     * @return null jos kaikki ok
     */
    public String setArtistiID(int s) {
        this.artistiID = s;
        return null;
    }
    
    
    /**
     * @param n Asetettava id
     * @return null jos kaikki ok
     */
    public String setYhtioID(int n) {
        this.yhtioID = n;
        return null;
    }
    
    
    /**
     * Luodaan testiarvot kappaleelle.
     */
    public void vastaaSickoMode() {
        this.kappaleId = getKappaleId();
        this.artistiID = 1;
        this.albumi = "ASTROWORLD";
        this.genre = "Hip hop";
        this.kappaleenNimi = "SICKO MODE";
        this.julkaisuvuosi = "2018";
        this.kuuntelukerrat = "202022";
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
        //this.julkaisuvuosi = 2012;
        //this.kuuntelukerrat = randomi(200, 10000000);
    }
    
    
    /**
     * @return Generoidun kappaleId:n
     * @example
     * <pre name="test">
     *  Kappale k = new Kappale();
     *  k.getKappaleId() === 0;
     *  k.rekisteroi();
     *  Kappale k2 = new Kappale();
     *  k2.rekisteroi();
     *  int eka = k.getKappaleId();
     *  int toka = k2.getKappaleId();
     *  eka === toka-1;
     * </pre>
     */
    public int rekisteroi() {
        this.kappaleId = seuraavaNro;
        seuraavaNro++;
        
        return this.kappaleId;
    }
    
    
    /**
     * @return Generoidun levy-yhtiö id:n
     */
    public int rekisteroiYhtio() {
        this.yhtioID = seuraavaYhtioNro;
        seuraavaYhtioNro++;
        
        return this.yhtioID;
    }
    
    
    /**
     * Palauttaa kappaleen tiedot merkkijonona jonka voi tallentaa tiedostoon.
     * @return kappale tolppaeroteltuna merkkijonona 
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  1   | SICKO MODE");
     *   kappale.toString().startsWith("3|1|SICKO MODE|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     * </pre>  
     */
    @Override
    public String toString() {
        return "" +
                getKappaleId() + "|" +
                this.artistiID + "|" +
                this.yhtioID + "|" +
                this.kappaleenNimi + "|" +
                this.albumi + "|" +
                this.julkaisuvuosi + "|" +
                this.genre + "|" +
                this.kuuntelukerrat;
    }


    
    private void setKappaleId(int nro) {
        this.kappaleId = nro;
        if(this.kappaleId >= seuraavaNro) seuraavaNro = this.kappaleId + 1;
    }
    
    
    
    /**
     * Selvittää kappaleen tiedot
     * @param rivi rivi josta kappaleen tiedot otetaan
     * 
     * 
     * @example
     * <pre name="test">
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   3  |  1 | SICKO MODE");
     *   kappale.getKappaleId() === 3;
     *   kappale.toString().startsWith("3|1|SICKO MODE|") === true; // on enemmäkin kuin 3 kenttää, siksi loppu |
     *
     *   kappale.rekisteroi();
     *   int n = kappale.getKappaleId();
     *   kappale.parse(""+(n+20));       // Otetaan merkkijonosta vain tunnusnumero
     *   kappale.rekisteroi();           // ja tarkistetaan että seuraavalla kertaa tulee yhtä isompi
     *   kappale.getKappaleId() === n+20+1;
     *     
     * </pre>

     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setKappaleId(Mjonot.erota(sb, '|', getKappaleId()));
        artistiID = Mjonot.erota(sb, '|', artistiID);
        yhtioID = Mjonot.erota(sb, '|', yhtioID);
        kappaleenNimi = Mjonot.erota(sb, '|', kappaleenNimi);
        albumi = Mjonot.erota(sb, '|', albumi);
        julkaisuvuosi = Mjonot.erota(sb, '|', julkaisuvuosi);
        genre = Mjonot.erota(sb, '|', genre);
        kuuntelukerrat = Mjonot.erota(sb, '|', kuuntelukerrat);
    }

    
    /**
     * Tehdään identtinen klooni kappaleesta
     * @return Object kloonattu kappale
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kappale kappale = new Kappale();
     *   kappale.parse("   1  |  1  | SICKO MODE");
     *   Kappale kopio = kappale.clone();
     *   kopio.toString() === kappale.toString();
     *   kappale.parse("   2  |  Ankka Tupu   | 123");
     *   kopio.toString().equals(kappale.toString()) === false;
     * </pre>
     */
    @Override
    public Kappale clone() throws CloneNotSupportedException {
        Kappale uusi;
        uusi = (Kappale) super.clone();
        return uusi;
    }
    
    
    @Override
    public boolean equals(Object kappale) {
        if(kappale == null) return false;
        return this.toString().equals(kappale.toString());
    }
    
    
    @Override
    public int  hashCode() {
        return this.kappaleId;
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        
        Kappale eka = new Kappale();
        Kappale toka = new Kappale();
        
        //eka.rekisteroi();
        //toka.rekisteroi();
        
        //eka.tulosta(System.out);
        eka.vastaaSickoMode();
        toka.vastaaWonderwall();
        eka.tulosta(System.out);
        toka.tulosta(System.out);
        
        Kappale k = new Kappale();
        System.out.println(k.getKappaleId());
        k.rekisteroi();
        System.out.println(k.getKappaleId());
    }

}
