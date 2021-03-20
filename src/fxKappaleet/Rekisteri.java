package fxKappaleet;


import java.util.List;

import fxArtistit.Artisti;
import fxArtistit.Artistit;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 25.2.2021
 *
 */
public class Rekisteri {
       private final Kappaleet kappaleet = new Kappaleet();
       private final Artistit artistit = new Artistit();
       
       
       /**
     * @return Palauttaa rekisterin kappaleiden lukumäärän
     */
    public int getKappaleet() {
           return kappaleet.getLkm();
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
     */
    public void lisaa(Kappale kappale) throws SailoException {
        kappaleet.lisaa(kappale);
    }
    
    
    /**
     * @param kappale Kappale, jolle artisti haetaan
     * @return Kappaleen artisti
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

            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
