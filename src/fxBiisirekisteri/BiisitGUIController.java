package fxBiisirekisteri;

import java.net.URL;
import java.util.Collection;
//import java.util.List;
import java.util.ResourceBundle;

import Artistit.Artisti;
import Kappaleet.Kappale;
import Kappaleet.Rekisteri;
import Kappaleet.SailoException;
import Levyyhtio.Levyyhtio;

import java.io.PrintStream;


import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import fi.jyu.mit.fxgui.ListChooser;
import javafx.scene.control.ScrollPane;




/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 29.1.2021
 *
 */
public class BiisitGUIController implements Initializable{
    
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    @FXML private ScrollPane panelKappale;
    @FXML private ListChooser<Kappale> chooserKappaleet;
    
    @FXML private TextField editKappaleenNimi;
    @FXML private TextField editArtisti;
    @FXML private TextField editAlbumi;
    @FXML private TextField editJulkaisuvuosi;
    @FXML private TextField editYhtio;
    @FXML private TextField editGenre;
    @FXML private TextField editKuuntelukerrat;


    
    //private String listannimi = "Hittibiisit";

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }

    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handleHakuehto() {
       hae(0);

    }


    
    @FXML private void handleSulje() {
        tallenna();
        Platform.exit();
    } 
    
    
    @FXML private void handleAvaa() {
        avaa();
    }

    
    @FXML
    void handleLisaaKappale() {
        uusiKappale();
    }
 

    @FXML
    void handleLisaaArtisti() {
        uusiArtisti();
    }
    
    
    @FXML
    void handleLisaaYhtio() {
        uusiYhtio();
    }
    
    @FXML
    void handleApua() {
        Dialogs.showMessageDialog("Ei osata viel?? antaa apua :(");
    }
    
    
    @FXML
    void handlePoistaKappale() {
        poistaKappale();
    }


    @FXML private void handleNaytaArtistit() {
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaArtisti.fxml"), "Artistit", null, "");
    }
    
    
    @FXML private void handleNaytaYhtiot() {
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaYhtio.fxml"), "Levy-yhti??t", null, "");
    }
    
    
    @FXML private void handleMuokkausSivu() {
        muokkaa();
    }
    
    
    //------------------------- T??ST?? ETEENP??IN EI LIITY SUORAAN K??YTT??LIITTYM????N ----------------------------
    
    private String listannimi = "Hittibiisit";
    private Rekisteri rekisteri;
    private Kappale kappaleKohdalla;
    private static Kappale apuKappale = new Kappale();
    
    private TextField edits[];
    
    /**
     * Tarvittavat alustukset
     */
    protected void alusta() {
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());
        
        cbKentat.clear();
        for (int i = apuKappale.ekaKentta(); i < apuKappale.getKenttia(); i++)
            cbKentat.add(apuKappale.getKysymys(i), null);
        cbKentat.getSelectionModel().select(0);
        
        edits = new TextField[] { 
                editKappaleenNimi, 
                editArtisti,
                editAlbumi, 
                editJulkaisuvuosi,
                editYhtio,
                editGenre, 
                editKuuntelukerrat };
    }
    
    
    /**
     * N??ytt???? valitun kappaleen ja sen tiedot
     */
    private void naytaKappale() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();
        
        if(kappaleKohdalla == null) return;
        
        KappaleDialogController.naytaKappale(edits, kappaleKohdalla);
        KappaleDialogController.naytaLoput(edits, kappaleKohdalla, rekisteri);

    }
    
    
    /**
     * Hakee kappaleen tiedot listaan
     * @param nro Kappaleen numero
     */
    private void hae(int nro) {
        int knro = nro; // jnro j??senen numero, joka aktivoidaan haun j??lkeen 
        if ( knro <= 0 ) { 
            Kappale kohdalla = kappaleKohdalla; 
            if ( kohdalla != null ) knro = kohdalla.getKappaleId(); 
        }
        
        int k = cbKentat.getSelectionModel().getSelectedIndex() + apuKappale.ekaKentta(); 
        String ehto = hakuehto.getText(); 
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 
        
        chooserKappaleet.clear();

        int index = 0;
        Collection<Kappale> kappaleet;
        try {
            kappaleet = rekisteri.etsi(ehto, k);
            int i = 0;
            for (Kappale kappale : kappaleet) {
                if (kappale.getKappaleId() == knro) index = i;
                chooserKappaleet.add(kappale.getKappaleenNimi(), kappale);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("J??senen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserKappaleet.setSelectedIndex(index); // t??st?? tulee muutosviesti joka n??ytt???? j??senen

    }
    
    
    /*
     * Poistetaan listalta valittu j??sen
     */
    private void poistaKappale() {
        Kappale kappale = kappaleKohdalla;
        if ( kappale == null ) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko kappale: " + kappale.getKappaleenNimi(), "Kyll??", "Ei") )
            return;
        rekisteri.poista(kappale);
        int index = chooserKappaleet.getSelectedIndex();
        hae(0);
        chooserKappaleet.setSelectedIndex(index);
    }

    
    
    private void muokkaa() { 
        if ( kappaleKohdalla == null ) return; 
        try { 
            Kappale kappale; 
            kappale = KappaleDialogController.kysyKappale(null, kappaleKohdalla.clone(), rekisteri); 
            if ( kappale == null ) return; 
            rekisteri.korvaaTaiLisaa(kappale); 
            hae(kappale.getKappaleId()); 
        } catch (CloneNotSupportedException e) { 
            // 
        } catch (SailoException e) { 
            Dialogs.showMessageDialog(e.getMessage()); 
        } 
    }     

    
    
       private void uusiYhtio() {
        Levyyhtio uusi = new Levyyhtio();
        uusi = YhtiotDialogController.kysyYhtio(null, uusi, rekisteri);
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaa(uusi);
    }    
 
    
    private void uusiArtisti() {
        Artisti uusi = new Artisti();
        uusi = ArtistiDialogController.kysyArtisti(null, uusi, rekisteri);
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaa(uusi);
    }

    
    /**
     * 
     */
    private void uusiKappale() {
        try {
            Kappale uusi = new Kappale();
            uusi = KappaleDialogController.kysyKappale(null, uusi, rekisteri);  
            if ( uusi == null ) return;
            uusi.rekisteroi();
            rekisteri.lisaa(uusi);
            hae(uusi.getKappaleId());
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }

    }

    
    
    /**
     * @param rekisteri Rekisteri, jota k??ytet????n t??ss?? ohjelmassa
     */
    public void setRekisteri(Rekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaKappale();
    }
    
    
    /**
     * @return true niin suljetaan, false niin ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    
    /**
     * Alustaa listan lukemalla sen valitun nimisest?? tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, jos ei niin palautuu virhe
     */
    protected String lueTiedosto(String nimi) {
        listannimi = nimi;
        setTitle("Hittibiisit - " + listannimi);
        try {
            rekisteri.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage();
            if (virhe != null) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
    }

    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * @return true jos onnistui, false ei
     */
    public boolean avaa() {
        String nimi = AloitusController.kysyNimi(null, listannimi);
        if(nimi == null) return false;
        lueTiedosto(nimi);
        return true;
    }
    
    
    /**
     * Tulostaa kappaleen tiedot
     * @param os tietovirta johon tulostetaan
     * @param kappale tulostettava kappale
     */
    public void tulosta(PrintStream os, final Kappale kappale) {
        os.println("----------------------------------------------");
        kappale.tulosta(os);
        os.println("----------------------------------------------");
        Artisti artisti = rekisteri.annaArtisti(kappale);
        artisti.tulosta(os);
        os.println("----------------------------------------------");

        Levyyhtio levyhtio = rekisteri.annaYhtio(kappale);
        levyhtio.tulosta(os);
    }
    
    
//    private void naytaVirhe(String virhe) {
//        if ( virhe == null || virhe.isEmpty() ) {
//            labelVirhe.setText("");
//            labelVirhe.getStyleClass().removeAll("virhe");
//            return;
//        }
//        labelVirhe.setText(virhe);
//        labelVirhe.getStyleClass().add("virhe");
//    }


    private String tallenna() {
        try {
            rekisteri.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }
}