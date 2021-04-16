/**
 * 
 */
package Kappaleet;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import fi.jyu.mit.ohj2.WildChars;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 22.2.2021
 *
 */
public class Kappaleet implements Iterable<Kappale> {
    
    private static final int MAX_KAPPALEET = 10;
    private boolean muutettu = false;
    private int lkm = 0;
    private String kokoNimi = "";
    private String tiedostonNimi = "kappaleet";
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
     * @param kappale Kappale viite
     * @throws SailoException virheilmoitus
     */
    public void korvaaTaiLisaa(Kappale kappale) throws SailoException {
        int id = kappale.getKappaleId();
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getKappaleId() == id) {
                alkiot[i] = kappale;
                muutettu = true;
                return;
            }
        }
        lisaa(kappale);
    }
    
    
    /**
     * @param kappale Lisättävän kappaleen viite
     * @throws SailoException jos tietorakenne on täynnä
     * @example
     * <pre name="test">
     *  #THROWS SailoException 
     *  Kappaleet kt = new Kappaleet();
     *  Kappale k1 = new Kappale(), k2 = new Kappale();
     *  kt.getLkm() === 0;
     *  kt.lisaa(k1); kt.getLkm() === 1; 
     *  kt.lisaa(k2); kt.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Kappale kappale) throws SailoException {
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, lkm + 10);
        alkiot[lkm] = kappale;
        lkm++;
        muutettu = true;
    }
    
    
    /**
     * @param i Monennes menossa
     * @return viite kappaleeseen indeksissä i
     * @throws IndexOutOfBoundsException Jos i on yli sallitun rajan
     */
    protected Kappale anna(int i) throws IndexOutOfBoundsException{
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Ei sallittu indeksi: " + i);
        return alkiot[i];
    }

    
    /** 
     * Etsii kappaleen id:n perusteella 
     * @param id id, jonka mukaan etsitään 
     * @return kappale jolla etsittävä id tai null 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kappaleet kappaleet = new Kappaleet(); 
     * Kappale eka = new Kappale(), toka = new Kappale(), kolmas = new Kappale(); 
     * eka.rekisteroi(); toka.rekisteroi(); kolmas.rekisteroi();  
     * int id1 = eka.getKappaleId(); 
     * kappaleet.lisaa(eka); kappaleet.lisaa(toka); kappaleet.lisaa(kolmas); 
     * kappaleet.annaId(id1  ) == eka === true;
     * kappaleet.annaId(id1+1) == toka === true; 
     * kappaleet.annaId(id1+2) == kolmas === true;
     * </pre> 
     */ 
    public Kappale annaId(int id) { 
        for (Kappale kappale : this) { 
            if (id == kappale.getKappaleId()) return kappale; 
        } 
        return null; 
    } 

    
    
    /** 
     * Poistaa kappaleen jolla on valittu id
     * @param id poistettavan kappaleen id 
     * @return 1 jos poistettiin, 0 jos ei löydy 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kappaleet kappaleet = new Kappaleet(); 
     * Kappale eka = new Kappale(), toka = new Kappale(), kolmas = new Kappale(); 
     * eka.rekisteroi(); toka.rekisteroi(); kolmas.rekisteroi(); 
     * int id1 = eka.getKappaleId(); 
     * kappaleet.lisaa(eka); kappaleet.lisaa(toka); kappaleet.lisaa(kolmas); 
     * kappaleet.poista(id1+1) === 1; 
     * kappaleet.annaId(id1+1) === null; kappaleet.getLkm() === 2; 
     * kappaleet.poista(id1) === 1; kappaleet.getLkm() === 1; 
     * kappaleet.poista(id1+3) === 0; kappaleet.getLkm() === 1; 
     * </pre> 
     *  
     */ 
    public int poista(int id) { 
        int ind = etsiId(id); 
        if (ind < 0) return 0; 
        lkm--; 
        for (int i = ind; i < lkm; i++) 
            alkiot[i] = alkiot[i + 1]; 
        alkiot[lkm] = null; 
        muutettu = true; 
        return 1; 
    } 


    /** 
     * Etsii jäsenen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen jäsenen indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kappaleet kappaleet = new Kappaleet(); 
     * Kappale eka = new Kappale(), toka = new Kappale(), kolmas = new Kappale(); 
     * eka.rekisteroi(); toka.rekisteroi(); kolmas.rekisteroi(); 
     * int id1 = eka.getKappaleId(); 
     * kappaleet.lisaa(eka); kappaleet.lisaa(toka); kappaleet.lisaa(kolmas); 
     * kappaleet.etsiId(id1+1) === 1; 
     * kappaleet.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == alkiot[i].getKappaleId()) return i; 
        return -1; 
    } 

    
    /**
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return this.tiedostonNimi;
    }
    
    
    /**
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return this.getTiedostonPerusNimi() + ".txt";
    }
    
    
    /**
     * @param s Tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String s) {
        this.tiedostonNimi = s;
    }
    
    
    /**
     * @param tied tiedoston nimi
     * @throws SailoException jos lukeminen epäonnistuu
     */
    public void lueTiedostosta(String tied) throws SailoException {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {
            kokoNimi = fi.readLine();
            if ( kokoNimi == null ) throw new SailoException("Kerhon nimi puuttuu");
            String rivi = fi.readLine();
            if ( rivi == null ) throw new SailoException("Maksimikoko puuttuu");
            // int maxKoko = Mjonot.erotaInt(rivi,10); // tehdään jotakin

            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Kappale jasen = new Kappale();
                jasen.parse(rivi); // voisi olla virhekäsittely
                lisaa(jasen);
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
     * Tallentaa jäsenistön tiedostoon.  
     * Tiedoston muoto:
     * <pre>
     * Hitit
     * 20
     * ; kommenttirivi
     * 1|1|SICKO MODE|2018|100
     * 2|2|Wonderwall|2000|101
     * </pre>
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        if ( !muutettu ) return;

        File fbak = new File(getBakNimi());
        File ftied = new File(getTiedostonNimi());
        fbak.delete(); // if .. System.err.println("Ei voi tuhota");
        ftied.renameTo(fbak); // if .. System.err.println("Ei voi nimetä");

        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(getKokoNimi());
            fo.println(alkiot.length);
            for (Kappale kappale : this) {
                fo.println(kappale.toString());
            }
            //} catch ( IOException e ) { // ei heitä poikkeusta
            //  throw new SailoException("Tallettamisessa ongelmia: " + e.getMessage());
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new SailoException("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }

        muutettu = false;
    }

    
    /**
     * @return Varatiedoston nimi
     */
    public String getBakNimi() {
        return this.tiedostonNimi + ".bak";
    }
    
    
    /**
     * @return Kerhon koko nimi merkkijonona
     */
    public String getKokoNimi() {
        return this.kokoNimi;
    }
    
    
    /**
     * @author Joonas Ruuth & Henri Pigg
     * @version 29.3.2021
     *
     */
    public class KappaleetIterator implements Iterator<Kappale> {
        private int kohdalla = 0;

        @Override
        public boolean hasNext() {
            return kohdalla < getLkm();
        }

        @Override
        public Kappale next() throws NoSuchElementException{
            if(!hasNext() ) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
        }
        
        
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Ei poisteta");
        
    }    
  }
    
    
    @Override
    public Iterator<Kappale> iterator(){
         return new KappaleetIterator();
    }
    
    
    /**
     * @param hakuehto Hakuehto
     * @param k etsittävän kentän indeksi
     * @return tietorakenne löytyneistä kappaleista
     */
    public Collection<Kappale> etsi(String hakuehto, int k) {
        String ehto = "*"; 
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto; 
        int hk = k; 
        if ( hk < 0 ) hk = 1;
        Collection<Kappale> loytyneet = new ArrayList<Kappale>(); 
        for (Kappale kappale : this) { 
            if (WildChars.onkoSamat(kappale.anna(hk), ehto)) loytyneet.add(kappale);   
        } 
        //  TODO: lajittelua varten vertailija  
        return loytyneet; 

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
