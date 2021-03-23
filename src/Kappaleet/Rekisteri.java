package Kappaleet;


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
       private final Kappaleet kappaleet = new Kappaleet();
       private final Artistit artistit = new Artistit();
       private final Levyyhtiot yhtiot = new Levyyhtiot();
       
       
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
     *  int id1 = k1.getArtistiID();
     *  int id2 = 20;
     *  
     *  Artisti a1 = new Artisti(id1); r.lisaa(a1);
     *  Artisti a2 = new Artisti(id2); r.lisaa(a2);
     *  
     *  List<Artisti> loytyneet;
     *  loytyneet = r.annaArtistit(k1);
     *  loytyneet.size() === 1;
     *  loytyneet = r.annaArtistit(k2);
     *  loytyneet.size() === 1;
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

            int id1 = sicko1.getArtistiID();
            int id2 = wonder.getArtistiID();
            
            Artisti travis = new Artisti(id1);
            Artisti oasis = new Artisti(id2);
            
            travis.vastaaTravisScott(10);
            oasis.vastaaOasis(20);

            rekisteri.lisaa(travis);
            rekisteri.lisaa(oasis);
            
            int yID1 = travis.getLevyyhtioID();
            
            Levyyhtio creation = new Levyyhtio(yID1);
            
            creation.vastaaCreation(1000);
            
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
