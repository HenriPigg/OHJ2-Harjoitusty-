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
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
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


    
    //private String listannimi = "Hittibiisit";

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }

    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handleHakuehto() {
        if ( kappaleKohdalla != null )
            hae(kappaleKohdalla.getKappaleId()); 

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
        Dialogs.showMessageDialog("Ei osata vielä antaa apua :(");
    }
    
    
    @FXML
    void handlePoistaKappale() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa :(");
    }


    @FXML private void handleNaytaArtistit() {
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaArtisti.fxml"), "Artistit", null, "");
    }
    
    
    @FXML private void handleNaytaYhtiot() {
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaYhtio.fxml"), "Levy-yhtiöt", null, "");
    }
    
    
    @FXML private void handleMuokkausSivu() {
        ModalController.showModal(BiisitGUIController.class.getResource("Muokkaus.fxml"), "Kappale", null, "");
    }
    
    
    //------------------------- TÄSTÄ ETEENPÄIN EI LIITY SUORAAN KÄYTTÖLIITTYMÄÄN ----------------------------
    
    private String listannimi = "Hittibiisit";
    private Rekisteri rekisteri;
    private Kappale kappaleKohdalla;
    private TextArea areaKappale = new TextArea();
    
    
    /**
     * Tarvittavat alustukset
     */
    protected void alusta() {
        panelKappale.setContent(areaKappale);
        areaKappale.setFont(new Font("Courier New", 12));
        panelKappale.setFitToHeight(true);
        
        chooserKappaleet.clear();
        chooserKappaleet.addSelectionListener(e -> naytaKappale());

    }
    
    
    /**
     * Näyttää valitun kappaleen ja sen tiedot
     */
    private void naytaKappale() {
        kappaleKohdalla = chooserKappaleet.getSelectedObject();
        
        if(kappaleKohdalla == null) {
            areaKappale.clear();
            return;
        }
        
        areaKappale.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKappale)){
            tulosta(os, kappaleKohdalla);
        }     
    }
    
    
    /**
     * Hakee kappaleen tiedot listaan
     * @param nro Kappaleen numero
     */
    private void hae(int nro) {
        int k = cbKentat.getSelectionModel().getSelectedIndex();
        String ehto = hakuehto.getText();
        if (k > 0 || ehto.length() > 0)
                naytaVirhe(String.format("Ei osata hakea (kenttä: %d, ehto %s)",  k, ehto));
        else
            naytaVirhe(null);
        
        chooserKappaleet.clear();
        
        int index = 0;
        Collection<Kappale> kappaleet;
        try {
            kappaleet = rekisteri.etsi(ehto, k);
            int i = 0;
            for (Kappale kappale : kappaleet) {
                if (kappale.getKappaleId() == nro) index = i;
                chooserKappaleet.add(kappale.getKappaleenNimi(), kappale);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Kappaleen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserKappaleet.setSelectedIndex(index);
    }
    
    
       private void uusiYhtio() {
        if(kappaleKohdalla == null) return;
        Levyyhtio levyyhtio = new Levyyhtio();
        levyyhtio.rekisteroi();
        levyyhtio.vastaaCreation();
        rekisteri.lisaa(levyyhtio);
        Artisti ar = rekisteri.annaArtisti(kappaleKohdalla);
        hae(ar.getLevyyhtioID()); //TODO: HAE ARTISTIN LEVYYHTIO ID
        
    }    
 
    
    private void uusiArtisti() {
        if(kappaleKohdalla == null) return;
        Artisti artisti = new Artisti();
        artisti.rekisteroi();
        artisti.vastaaTravisScott(kappaleKohdalla.getArtistiID());
            rekisteri.lisaa(artisti);
        
        hae(kappaleKohdalla.getArtistiID());       
    }

    
    /**
     * 
     */
    private void uusiKappale() {
        Kappale uusi = new Kappale();
        uusi.rekisteroi();
        uusi.vastaaSickoMode();
        
        try {
            rekisteri.lisaa(uusi);
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Ongelmia uuden kappaleen luomisessa " + ex.getMessage());
            return;
        }
        hae(uusi.getKappaleId());
    }
    
    
    /**
     * @param rekisteri Rekisteri, jota käytetään tässä ohjelmassa
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
     * Alustaa listan lukemalla sen valitun nimisestä tiedostosta
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

        Levyyhtio levyhtio = rekisteri.annaYhtio(artisti);
        levyhtio.tulosta(os);
    }
    
    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }


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