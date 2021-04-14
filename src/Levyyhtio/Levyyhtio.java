/**
 * 
 */
package Levyyhtio;

import java.io.OutputStream;
import java.io.PrintStream;

import Kappaleet.Kappale;
import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 18.3.2021
 *
 */
public class Levyyhtio implements Cloneable {

    private int levyyhtioID = 0;
    private String levyyhtio = "";
    private String perustamisvuosi = "";
    
    private static int seuraavaNro = 1;
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {

        Levyyhtio eka = new Levyyhtio();
        
        eka.rekisteroi();
        eka.vastaaCreation();
        eka.tulosta(System.out);
        
    }

    
    /**
     *  Oletusmuodostaja
     */
    public Levyyhtio() {
        //
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
     * @return Perustamisvuosi
     */
    public String getPerustamisvuosi() {
        return this.perustamisvuosi;
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
     * @example
     * <pre name="test">
     * Levyyhtio l = new Levyyhtio();
     * l.getLevyyhtioID() === 0;
     * l.rekisteroi();
     * l.getLevyyhtioID() === 1;
     * </pre>
     */
    public int rekisteroi() {
        this.levyyhtioID = seuraavaNro;
        seuraavaNro++;
        
        return this.levyyhtioID;
        
    }
    
    /**
     * Testiarvot
     */
    public void vastaaCreation() {
        this.levyyhtioID = getLevyyhtioID();
        this.levyyhtio = "Creation Records";
        this.perustamisvuosi = "2013";
    }

    
    @Override
    public String toString() {
        return "" + getLevyyhtioID() + "|" + this.levyyhtio + "|" + this.perustamisvuosi;
    }
    
    
    /**
     * Selvitää harrastuksen tiedot | erotellusta merkkijonosta.
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusnro.
     * @param rivi josta harrastuksen tiedot otetaan
     * @example
     * <pre name="test">
     *   Levyyhtio yhtio = new Levyyhtio();
     *   yhtio.parse("   1   |  Creation Records  | 2013");
     *   yhtio.getLevyyhtioID() === 1;
     *   yhtio.toString()    === "1|Creation Records|2013";
     *   
     *   yhtio.rekisteroi();
     *   int n = yhtio.getLevyyhtioID();
     *   yhtio.parse(""+(n+20));
     *   yhtio.rekisteroi();
     *   yhtio.getLevyyhtioID() === n+20+1;
     *   yhtio.toString()     === "" + (n+20+1) + "|Creation Records|2013";
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        setLevyyhtioID(Mjonot.erota(sb, '|', getLevyyhtioID()));
        this.levyyhtio = Mjonot.erota(sb, '|', levyyhtio);
        this.perustamisvuosi = Mjonot.erota(sb, '|', perustamisvuosi);
    }
    
    
    private void setLevyyhtioID(int nro) {
        this.levyyhtioID = nro;
        if (this.levyyhtioID >= seuraavaNro) seuraavaNro = levyyhtioID + 1;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return this.toString().equals(obj.toString());
    }
    
    
    @Override
    public int hashCode() {
        return this.levyyhtioID;
    }


    /**
     * @param s Yhtiön nimi
     * @return null jos kaikki ok
     */
    public String setYhtio(String s) {
        this.levyyhtio = s;
        return null;
    }
    
    
    /**
     * @param s Perustamisvuosi
     * @return null jos kaikki ok
     */
    public String setPerustamisvuosi(String s) {
        this.perustamisvuosi = s;
        return null;
    }
    
    
    /**
     * Tehdään identtinen klooni kappaleesta
     * @return Object kloonattu kappale
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Levyyhtio kappale = new Levyyhtio();
     *   kappale.parse("   1  |  JOTAIN  | 2323");
     *   Levyyhtio kopio = kappale.clone();
     *   kopio.toString() === kappale.toString();
     *   kappale.parse("   2  |  Creation   | 2010");
     *   kopio.toString().equals(kappale.toString()) === false;
     * </pre>
     */
    @Override
    public Kappale clone() throws CloneNotSupportedException {
        Kappale uusi;
        uusi = (Kappale) super.clone();
        return uusi;
    }
}
