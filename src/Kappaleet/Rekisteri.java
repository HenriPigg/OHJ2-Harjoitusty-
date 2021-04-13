package Kappaleet;


import java.io.*;
import java.util.Collection;
import java.util.List;

import Artistit.Artisti;
import Artistit.Artistit;
import Levyyhtio.Levyyhtio;
import Levyyhtio.Levyyhtiot;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 25.2.2021
 *
 */
public class Rekisteri {
       private Kappaleet kappaleet = new Kappaleet();
       private Artistit artistit = new Artistit();
       private Levyyhtiot yhtiot = new Levyyhtiot();
       
       
       
    /**
     * Poistaa kappaleista ja artisteista ne joilla on nro. KESKEN!!
     * @param nro viitenumero, jonka mukaan poistetaan.
     * @return montako kappaletta poistettiin
     */
    public int poista(@SuppressWarnings("unused") int nro) {
        return 0;
    }
    
       
     /**
     * @return Palauttaa rekisterin kappaleiden lukumäärän
     */
    public int getKappaleet() {
           return kappaleet.getLkm();
       }
    
    
    /**
     * @param yhtio Lisättävä levy-yhtiö
     */
    public void lisaa(Levyyhtio yhtio) {
        yhtiot.lisaa(yhtio);
    }
    
    
    
    /**
     * @param artisti Lisättävä artisti
     */
    public void lisaa(Artisti artisti) {
        artistit.lisaa(artisti);

    }
    
    
    /**
     * @param kappale Lisättävän kappaleen viite
     * @throws SailoException jos täynnä
     */
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
        kappaleet.korvaaTaiLisaa(kappale);
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kappaleiden viitteet 
     * @param hakuehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä kappaleista 
     * @throws SailoException Jos jotakin menee väärin
     */ 
    public Collection<Kappale> etsi(String hakuehto, int k) throws SailoException {
        return kappaleet.etsi(hakuehto, k);
    }
    
    
    /**
     * @param kappale Lisättävä kappale
     * @throws SailoException Jos kappaletta ei voida lisätä
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Rekisteri r = new Rekisteri();
     *  Kappale k1 = new Kappale(); 
     *  Kappale k2 = new Kappale();
     *  k1.rekisteroi(); k2.rekisteroi();
     *  r.getKappaleet() === 0;
     *  r.lisaa(k1); r.getKappaleet() === 1;
     *  r.lisaa(k2); r.getKappaleet() === 2;
     * </pre>
     */
    public void lisaa(Kappale kappale) throws SailoException {
        kappaleet.lisaa(kappale);
    }
    
    
    /**
     * Asettaa tiedostojen perusnimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        kappaleet.setTiedostonPerusNimi(hakemistonNimi + "kappaleet");
        artistit.setTiedostonPerusNimi(hakemistonNimi + "artistit");
        yhtiot.setTiedostonPerusNimi(hakemistonNimi + "levyyhtiot");
    }

    
    /**
     * @param nimi jota käytetään lukemisessa
     * @throws SailoException Jos jotain menee väärin
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kappaleet = new Kappaleet(); // jos luetaan olemassa olevaan niin helpoin tyhjentää näin
        artistit = new Artistit();
        yhtiot = new Levyyhtiot();

        setTiedosto(nimi);
        kappaleet.lueTiedostosta();
        artistit.lueTiedostosta();
        yhtiot.lueTiedostosta();
    }

    
    /**
     * Tallenttaa kerhon tiedot tiedostoon.  
     * Vaikka jäsenten tallettamien epäonistuisi, niin yritetään silti tallettaa
     * harrastuksia ennen poikkeuksen heittämistä.
     * @throws SailoException jos tallettamisessa ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            kappaleet.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }

        try {
            artistit.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        
        try {
            yhtiot.tallenna();
        } catch ( SailoException ex ) {
            virhe += ex.getMessage();
        }
        if ( !"".equals(virhe) ) throw new SailoException(virhe);
    }

    
    
    /**
     * @param artisti Artisti, jolta levy-yhtio id haetaan
     * @return Artistin levy-yhtiö
     */
    public Levyyhtio annaYhtio(Artisti artisti) {
        return yhtiot.annaYhtio(artisti.getLevyyhtioID());
    }
    
    
    /**
     * @param artisti Artisti, jonka levy-yhtiötä etsitään
     * @return Artistin levy-yhtio
     */
    public List<Levyyhtio> annaYhtiot(Artisti artisti) {
        return yhtiot.annaYhtiot(artisti.getLevyyhtioID());
    }
    
    
    /**
     * @param kappale Kappale, josta artisti id haetaan
     * @return artisti id
     */
    public Artisti annaArtisti(Kappale kappale) {
        return artistit.annaArtisti(kappale.getArtistiID());
    }
    
    
    
    /**
     * @param kappale Kappale, jolle artisti haetaan
     * @return Kappaleen artisti
     * @example
     * <pre name="test">
     *  #import java.util.*;
     *  #import Artistit.Artisti;
     *  Rekisteri r = new Rekisteri();
     *  Kappale k1 = new Kappale(), k2 = new Kappale();
     *  k1.rekisteroi(); k2.rekisteroi();
     *  Artisti a1 = new Artisti(); r.lisaa(a1);
     *  Artisti a2 = new Artisti(); r.lisaa(a2);
     *  
     *  List<Artisti> loytyneet;
     *  loytyneet = r.annaArtistit(k1);
     *  loytyneet.size() === 2;
     * </pre>
     */
    public List<Artisti> annaArtistit(Kappale kappale) {
      return artistit.annaArtistit(kappale.getArtistiID());
    }
    
    
    /**
     * @param i indeksissä i oleva kappale
     * @return viite indeksin i kappaleeseen
     * @throws IndexOutOfBoundsException jos i laiton
     */
    public Kappale annaKappale(int i) throws IndexOutOfBoundsException {
        return kappaleet.anna(i);
    }


    /**
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Rekisteri rekisteri = new Rekisteri();

        try {

            Kappale sicko1 = new Kappale(); 
            Kappale wonder = new Kappale();
            sicko1.rekisteroi();
            sicko1.vastaaSickoMode();
            wonder.rekisteroi();
            wonder.vastaaWonderwall();

            rekisteri.lisaa(sicko1);
            rekisteri.lisaa(wonder);

          
            Artisti travis = new Artisti();
            Artisti oasis = new Artisti();
            
            travis.vastaaTravisScott(travis.getArtistiID());
            oasis.vastaaOasis(20);

            rekisteri.lisaa(travis);
            rekisteri.lisaa(oasis);
            
            
            Levyyhtio creation = new Levyyhtio();
            
            creation.vastaaCreation();
            
            rekisteri.lisaa(creation);
            
            System.out.println("============= Rekisterin testi =================");
            
            
            for(int i = 0; i < rekisteri.getKappaleet(); i++) {
                Kappale kappale = rekisteri.annaKappale(i);
                System.out.println("-------------------------------------");
                System.out.println("Kappale paikassa: " + i);
                System.out.println("-------------------------------------");
                System.out.println();
                System.out.println();
                kappale.tulosta(System.out);
                System.out.println("---------- Tästä eteenpäin artistin tulostus ----------");
                List<Artisti> artistit = rekisteri.annaArtistit(kappale);
                for (Artisti ar : artistit)
                    ar.tulosta(System.out);

                System.out.println("---------- Tästä eteenpäin levy-yhtiön tulostus ----------");
                List<Levyyhtio> yhtiot = rekisteri.annaYhtiot(travis);
                for(Levyyhtio yhtio : yhtiot)
                    yhtio.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
