package Artistit;

import java.util.*;


/**
 * Kappaleen artisti, joka osaa mm. lisätä uuden artistin
 *
 * @author Joonas Ruuth & Henri Pigg
 * @version 1.0, 22.02.2003
 */
public class Artistit implements Iterable<Artisti> {

    //private String                      tiedostonNimi = "";

    /** Taulukko artisteista */
    private final Collection<Artisti> alkiot        = new ArrayList<Artisti>();


    /**
     * Artistin alustaminen
     */
    public Artistit() {
        // toistaiseksi ei tarvitse tehdä mitään
    }


    /**
     * Lisää uuden artistin tietorakenteeseen.
     * @param artisti lisättävä artisti.  Huom tietorakenne muuttuu omistajaksi
     */
    public void lisaa(Artisti artisti) {
        alkiot.add(artisti);
    }


    /**
     * Palauttaa Rekisterin artistien lukumäärän
     * @return harrastusten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }


    /**
     * Iteraattori kaikkien artistien läpikäymiseen
     * @return artistiiteraattori
     * @example
     * <pre name="test">
     *  #PACKAGEIMPORT
     *  #import java.util.*;
     *  
     *  Artistit ar = new Artistit();
     *  Artisti a1 = new Artisti(10);
     *  Artisti a2 = new Artisti(11);
     *  ar.lisaa(a1);
     *  ar.lisaa(a2);
     *  
     *  Iterator<Artisti> i2 = ar.iterator();
     *  i2.next() === a1;
     *  i2.next() === a2;
     *  
     * </pre>
     */
    @Override
    public Iterator<Artisti> iterator() {
        return alkiot.iterator();
    }


    /**
     * @param nro Viite jota haetaan
     * @return Artistin oikealla id:llä
     */
     
    public Artisti annaArtisti(int nro) {
        Artisti oikea = new Artisti();
        for(Artisti artisti : alkiot) {                         // TODO: TSEKKAA ONKO MIHINKÄÄN
            if(artisti.getArtistiID() == nro) oikea = artisti;
        }      
        return oikea;
    }

    
    /**
     * 
     * @param nro Numero, jota verrataan artisti id:seen 
     * @return tietorakenne jossa viiteet löydetteyihin artisteihin
     * 
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  
     *  Artistit ar = new Artistit();
     *  Artisti a1 = new Artisti(10); ar.lisaa(a1);
     *  Artisti a2 = new Artisti(10); ar.lisaa(a2);
     *  
     *  List<Artisti> loytyneet;
     *  loytyneet = ar.annaArtistit(10);
     *  loytyneet.size() === 2;
     * </pre>
     */
    public List<Artisti> annaArtistit(int nro) {
        List<Artisti> loydetyt = new ArrayList<Artisti>();
        for (Artisti artisti : alkiot)
            if(artisti.getArtistiID() == nro) loydetyt.add(artisti);
        return loydetyt;
    }


    /**
     * Testiohjelma artisteille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Artistit harrasteet = new Artistit();
        Artisti artisti1 = new Artisti();
        Artisti artisti2 = new Artisti();

        harrasteet.lisaa(artisti1);
        artisti1.vastaaTravisScott(10);
        harrasteet.lisaa(artisti2);
        artisti2.vastaaOasis(20);

        System.out.println("============= Artistit testi =================");

        
        List<Artisti> artistit2 = harrasteet.annaArtistit(10);
        List<Artisti> artistit3 = harrasteet.annaArtistit(20);


        for (Artisti ar : artistit2) {
            System.out.print(ar);
            ar.tulosta(System.out);
        }
        System.out.println("---------------------------------------");
        for (Artisti ar : artistit3) {
            System.out.print(ar);
            ar.tulosta(System.out);
        }

    }


    
}
