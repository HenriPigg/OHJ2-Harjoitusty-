/**
 * 
 */
package fxArtistit;

import fxKappaleet.SailoException;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 19.3.2021
 *
 */
public class Artistit {

    private static final int MAX_ARTISTIT = 10;
    private int lkm = 0;
    
    private Artisti alkiot[] = new Artisti[MAX_ARTISTIT];
    
    /**
     * Oletusmuodostaja
     */
    public Artistit() {
        //
    }
    
    
    /**
     * @return Lkm
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
   /**
     * @param artisti Lisättävän artistin viite
     * @throws SailoException jos tietorakenne on täynnä
     */
    public void lisaa(Artisti artisti) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = artisti;
        lkm++;
    }
    
    
    /**
     * @param i Monennes menossa
     * @return viite artistiin indeksissä i
     * @throws IndexOutOfBoundsException Jos i on yli sallitun rajan
     */
    public Artisti anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Ei sallittu indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Artistit artistit = new Artistit();
        
        Artisti travis = new Artisti(), travis2 = new Artisti();
        
        travis.rekisteroi();
        travis.vastaaTravisScott();
        travis2.rekisteroi();
        travis2.vastaaTravisScott();
        
        try {
            artistit.lisaa(travis);
            artistit.lisaa(travis2);
            
            System.out.println(" ____________ Artistit testi ____________");
            
            
            for (int i = 0; i < artistit.getLkm(); i++) {
                Artisti artisti = artistit.anna(i);
                System.out.println("Artisti numero: " + i);
                artisti.tulosta(System.out);
            }
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
    
    }

}
