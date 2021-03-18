package fxKappaleet;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 25.2.2021
 *
 */
public class Rekisteri {
       private final Kappaleet kappaleet = new Kappaleet();
       
       
       /**
     * @return Palauttaa rekisterin kappaleiden lukumäärän
     */
    public int getKappaleet() {
           return kappaleet.getLkm();
       }
    
    
    /**
     * @param kappale Lisättävä kappale
     * @throws SailoException Jos kappaletta ei voida lisätä
     */
    public void lisaa(Kappale kappale) throws SailoException {
        kappaleet.lisaa(kappale);
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
            // kerho.lueTiedostosta("");

            Kappale sicko1 = new Kappale(), sicko2 = new Kappale();
            sicko1.rekisteroi();
            sicko1.vastaaSickoMode();
            sicko2.rekisteroi();
            sicko2.vastaaWonderwall();

            rekisteri.lisaa(sicko1);
            rekisteri.lisaa(sicko2);

            System.out.println("============= Rekisterin testi =================");

            for (int i = 0; i < rekisteri.getKappaleet(); i++) {
                Kappale kappale = rekisteri.annaKappale(i);
                System.out.println("Kappale paikassa: " + i);
                kappale.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
