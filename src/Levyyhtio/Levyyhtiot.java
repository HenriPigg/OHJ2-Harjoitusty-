/**
 * 
 */
package Levyyhtio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 19.3.2021
 *
 */
public class Levyyhtiot implements Iterable<Levyyhtio> {
    
    
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
     *  Levyyhtio a1 = new Levyyhtio(1000);
     *  Levyyhtio a2 = new Levyyhtio(1001);
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
