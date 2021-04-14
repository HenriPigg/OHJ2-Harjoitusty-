/**
 * 
 */
package Artistit;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 18.3.2021
 *
 */
public class Artisti implements Cloneable{

    private int artistiID;
    private int levyyhtioID;
    private String artistiNimi = "";
    private String aloitusvuosi = "";
    
    private static int seuraavaNro = 1;

    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Artisti eka = new Artisti();
        eka.rekisteroi();
        
        eka.vastaaTravisScott(1);
        eka.tulosta(System.out);
    
    }
    

    /**
     * Oletusmuodostaja
     */
    public Artisti() {
        //
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
     * @param s Artistille annettava nimi
     * @return null jos kaikki ok
     */
    public String setArtisti(String s) {
        this.artistiNimi = s;
        return null;
    }
    
    
    /**
     * @param s Artistille annettava nimi
     * @return null jos kaikki ok
     */
    public String setAloitusvuosi(String s) {
        if (!s.matches("[0-9]*") ) return "Aloitusvuoden pitää olla numeerinen";
        this.aloitusvuosi = s;
        return null;
    }
    
    
    /**
     * @return rekisteröidyn artistin
     * @example
     * <pre name="test">
     *  Artisti ar = new Artisti();
     *  ar.getArtistiID() === 0;
     *  ar.rekisteroi();
     *  ar.getArtistiID() === 1;
     * </pre>
     */
    public int rekisteroi() {
        this.artistiID = seuraavaNro;
        seuraavaNro++;
        
        return this.artistiID;
        
    }
    
    
    /**
     * Luodaan testiarvot artistille.
     * @param nro Viite artistin kappaleeseen
     */
    public void vastaaTravisScott(int nro) {
        this.artistiID = nro;
        this.levyyhtioID = 1;
        this.artistiNimi = "Travis Scott";
        this.aloitusvuosi = "2013";
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
     * Luodaan testiarvot artistille.
     * @param nro -//-
     */
    public void vastaaOasis(int nro) {
        this.artistiID = nro;
        this.levyyhtioID = 1001;
        this.artistiNimi = "Oasis";
        this.aloitusvuosi = "2000";
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

    @Override
    public String toString() {
        return "" + getArtistiID() + "|" + this.levyyhtioID + "|" + this.artistiNimi + "|" + this.aloitusvuosi;
    }
    
    
    /**
     * Selvitää harrastuksen tiedot | erotellusta merkkijonosta.
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusnro.
     * @param rivi josta harrastuksen tiedot otetaan
     * @example
     * <pre name="test">
     *   Artisti artisti = new Artisti();
     *   artisti.parse("   1   |  10  |   Travis Scott  | 2013");
     *   artisti.getArtistiID() === 1;
     *   artisti.toString()    === "1|10|Travis Scott|2013";
     *   
     *   artisti.rekisteroi();
     *   int n = artisti.getArtistiID();
     *   artisti.parse(""+(n+20));
     *   artisti.rekisteroi();
     *   artisti.getArtistiID() === n+20+1;
     *   artisti.toString()     === "" + (n+20+1) + "|10|Travis Scott|2013";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setArtistiID(Mjonot.erota(sb, '|', getArtistiID()));
        this.levyyhtioID = Mjonot.erota(sb, '|', levyyhtioID);
        this.artistiNimi = Mjonot.erota(sb, '|', artistiNimi);
        aloitusvuosi = Mjonot.erota(sb, '|', aloitusvuosi);
    }
    
    
    private void setArtistiID(int nro) {
        this.artistiID = nro;
        if (artistiID >= seuraavaNro) seuraavaNro = artistiID + 1;
    }
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    
    @Override
    public int hashCode() {
        return this.artistiID;
    }


    /**
     * @return Aloitusvuoden
     */
    public String getAloitusvuosi() {
        return this.aloitusvuosi;
    }
    
    
    /**
     * Tehdään identtinen klooni kappaleesta
     * @return Object kloonattu kappale
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Artisti kappale = new Artisti();
     *   kappale.parse("   1  |  1  | Travis Scott");
     *   Artisti kopio = kappale.clone();
     *   kopio.toString() === kappale.toString();
     *   kappale.parse("   2  |  1   | 123");
     *   kopio.toString().equals(kappale.toString()) === false;
     * </pre>
     */
    @Override
    public Artisti clone() throws CloneNotSupportedException {
        Artisti uusi;
        uusi = (Artisti) super.clone();
        return uusi;
    }
    
    
}
