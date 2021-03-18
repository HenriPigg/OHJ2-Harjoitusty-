/**
 * 
 */
package fxKappaleet;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 22.2.2021
 *
 */
public class Kappaleet {
    
    private static final int MAX_KAPPALEET = 10;
    private int lkm = 0;
    //private String tiedostonNimi = "";
    private Kappale alkiot[] = new Kappale[MAX_KAPPALEET];
    
    
    /**
     * Oletusmuodostaja
     */
    public Kappaleet() {
        //
    }
    
    
    /**
     * @return Palauttaa rekisterin kappaleiden lukumäärän
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * @param kappale Lisättävän kappaleen viite
     * @throws SailoException jos tietorakenne on täynnä
     */
    public void lisaa(Kappale kappale) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = kappale;
        lkm++;
    }
    
    
    /**
     * @param i Monennes menossa
     * @return viite kappaleeseen indeksissä i
     * @throws IndexOutOfBoundsException Jos i on yli sallitun rajan
     */
    public Kappale anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Ei sallittu indeksi: " + i);
        return alkiot[i];
    }

    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kappaleet kappaleet = new Kappaleet();
        
        Kappale sicko1 = new Kappale(), sicko2 = new Kappale();
        sicko1.rekisteroi();
        sicko1.vastaaSickoMode();
        sicko2.rekisteroi();
        sicko2.vastaaWonderwall();
        
        
        try {
            kappaleet.lisaa(sicko1);
            kappaleet.lisaa(sicko2);
            
            System.out.println("============= Kappaleet testi =================");
            
            for (int i = 0; i < kappaleet.getLkm(); i++) {
                Kappale kappale = kappaleet.anna(i);
                System.out.println("Kappale numero: " + i);
                kappale.tulosta(System.out);
            }
            
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
