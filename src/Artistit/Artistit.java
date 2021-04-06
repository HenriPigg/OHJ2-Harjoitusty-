package Artistit;

import java.io.*;
import java.util.*;

import Kappaleet.SailoException;


/**
 * Kappaleen artisti, joka osaa mm. lisätä uuden artistin
 *
 * @author Joonas Ruuth & Henri Pigg
 * @version 1.0, 22.02.2003
 */
public class Artistit implements Iterable<Artisti> {

    private boolean muutettu = false;
    private String tiedostonNimi = "";

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
                Artisti artisti = new Artisti();
                artisti.parse(rivi); // voisi olla virhekäsittely
                lisaa(artisti);
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
            for (Artisti artisti : this) {
                fo.println(artisti.toString());
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }

    
    
    /**
     * Palauttaa Rekisterin artistien lukumäärän
     * @return harrastusten lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
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
     * Iteraattori kaikkien artistien läpikäymiseen
     * @return artistiiteraattori
     * @example
     * <pre name="test">
     *  #PACKAGEIMPORT
     *  #import java.util.*;
     *  
     *  Artistit ar = new Artistit();
     *  Artisti a1 = new Artisti();
     *  Artisti a2 = new Artisti();
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
     *  Artisti a1 = new Artisti(); ar.lisaa(a1);
     *  Artisti a2 = new Artisti(); ar.lisaa(a2);
     *  
     *  List<Artisti> loytyneet;
     *  loytyneet = ar.annaArtistit(1);
     *  loytyneet.size() === 0;
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
        artisti1.vastaaTravisScott();
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
