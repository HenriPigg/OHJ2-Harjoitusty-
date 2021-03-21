/**
 * 
 */
package Levyyhtio;

import Kappaleet.SailoException;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 19.3.2021
 *
 */
public class Levyyhtiot {
    
    private static final int MAX_LEVYYHTIOT = 10;
    private int lkm = 0;
    
    private Levyyhtio alkiot[] = new Levyyhtio[MAX_LEVYYHTIOT];
    
    
    /**
     * Oletusmuodostaja
     */
    public Levyyhtiot() {
        
    }

    
    /**
     * @param levyyhtio Lisättävän levy-yhtiön viite
     * @throws SailoException jos tietorakenne on täynnä
     */
    public void lisaa(Levyyhtio levyyhtio) throws SailoException {
        if (lkm >= alkiot.length) throw new SailoException("Liikaa alkioita");
        alkiot[lkm] = levyyhtio;
        lkm++;
    }
    
    
    /**
     * @param i Monennes menossa
     * @return viite levy-yhtiöön indeksissä i
     * @throws IndexOutOfBoundsException Jos i on yli sallitun rajan
     */
    public Levyyhtio anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Ei sallittu indeksi: " + i);
        return alkiot[i];
    }
    
    
    /**
     * @return lkm
     */
    public int getLkm() {
        return this.lkm;
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Levyyhtiot levyyhtiot = new Levyyhtiot();
        
        Levyyhtio creation = new Levyyhtio(); 
        Levyyhtio creation2 = new Levyyhtio();
        
        creation.rekisteroi();
        creation2.rekisteroi();
        
        creation.vastaaCreation();
        creation2.vastaaCreation();
        
        try {
            levyyhtiot.lisaa(creation);
            levyyhtiot.lisaa(creation2);
            System.out.println("============= Levy-yhtiöt testi =================");
            
            for (int i = 0; i < levyyhtiot.getLkm(); i++) {
                Levyyhtio levyyhtio = levyyhtiot.anna(i);
                System.out.println("Levy-yhtiö numero " + i);
                levyyhtio.tulosta(System.out);
            }

        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    
    }

}
