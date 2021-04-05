/**
 * 
 */
package Levyyhtio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import Kappaleet.SailoException;



/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 19.3.2021
 *
 */
public class Levyyhtiot implements Iterable<Levyyhtio> {
    
    private boolean muutettu = false;
    private String tiedostonNimi = "";

    private final Collection<Levyyhtio> alkiot = new ArrayList<Levyyhtio>();

    /**
     * Oletusmuodostaja
     */
    public Levyyhtiot() {
        
    }

    
    /**
     * @param yhtio Lisättävä levy-yhtiö
     */
    public void lisaa(Levyyhtio yhtio) {
        alkiot.add(yhtio);
        muutettu = true;
    }
    
    
    /**
     * Lukee harrastukset tiedostosta.
     * @param tied tiedoston nimen alkuosa
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Levyyhtio yhtio = new Levyyhtio();
                yhtio.parse(rivi); // voisi olla virhekäsittely
                lisaa(yhtio);
            }
            muutettu = false;

        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new SailoException("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws SailoException jos tulee poikkeus
     */
    public void lueTiedostosta() throws SailoException {
        lueTiedostosta(getTiedostonPerusNimi());
    }
    
    
    /**
     * Tallentaa artistit tiedostoon.
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); //  if ... System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); //  if ... System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            for (Levyyhtio yhtio : this) {
                fo.println(yhtio.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }
    
    
    /**
     * Asettaa tiedoston perusnimen ilan tarkenninta
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        this.tiedostonNimi = tied;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return this.tiedostonNimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return this.tiedostonNimi + ".txt";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return this.tiedostonNimi + ".bak";
    }
    
    
    /**
     * @return Levy-yhtiöiden lukumäärä tietorakenteessa
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    
    /**
     * Iteraattori kaikkien artistien läpikäymiseen
     * @return artistiiteraattori
     * * <pre name="test">
     *  #PACKAGEIMPORT
     *  #import java.util.*;
     *  
     *  Levyyhtiot ar = new Levyyhtiot();
     *  Levyyhtio a1 = new Levyyhtio();
     *  Levyyhtio a2 = new Levyyhtio();
     *  ar.lisaa(a1);
     *  ar.lisaa(a2);
     *  
     *  Iterator<Levyyhtio> i2 = ar.iterator();
     *  i2.next() === a1;
     *  i2.next() === a2;
     *  
     * </pre>
     * 
     */
    @Override
    public Iterator<Levyyhtio> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * @param nro Id jota haetaan
     * @return oikean id:n omaava levy-yhtiö
     */
    public Levyyhtio annaYhtio(int nro) {
        Levyyhtio oikea = new Levyyhtio();
        for(Levyyhtio yhtio : alkiot) {
            if(yhtio.getLevyyhtioID() == nro) oikea = yhtio;
        }
        
        return oikea;
    }
    
    
    /**
     * 
     * @param nro Numero, jota verrataan levy-yhtiön id:seen 
     * @return tietorakenne jossa viiteet löydetteyihin levy-yhtiöihin
     */
    public List<Levyyhtio> annaYhtiot(int nro) {
        List<Levyyhtio> loydetyt = new ArrayList<Levyyhtio>();
        for (Levyyhtio yhtio : alkiot)
            if(yhtio.getLevyyhtioID() == nro) loydetyt.add(yhtio);
        return loydetyt;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Levyyhtiot levyyhtiot = new Levyyhtiot();
        
        Levyyhtio creation = new Levyyhtio(); 
        
        
        levyyhtiot.lisaa(creation);
        
        
        creation.vastaaCreation(1000);
        
        
            System.out.println("============= Levy-yhtiöt testi =================");
            
        List<Levyyhtio> yhtiot = levyyhtiot.annaYhtiot(1000);
        
        for (Levyyhtio ly : yhtiot) {
            System.out.print(ly);
            ly.tulosta(System.out);
        }
        
    
    }

}
