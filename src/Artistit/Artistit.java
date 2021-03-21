package Artistit;

import java.util.*;


/**
 * Kerhon harrastukset, joka osaa mm. lisätä uuden harrastuksen
 *
 * @author Vesa Lappalainen
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
     * 
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
     */
    public List<Artisti> annaArtistit(int nro) {
        List<Artisti> loydetyt = new ArrayList<Artisti>();
        for (Artisti artisti : alkiot)
            if(artisti.getArtistiID() == nro) loydetyt.add(artisti);
        return loydetyt;
    }


    /**
     * Testiohjelma harrastuksille
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
