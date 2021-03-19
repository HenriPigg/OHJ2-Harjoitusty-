package biisirekisteri;

import java.net.URL;
import java.util.ResourceBundle;
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


import fxKappaleet.Kappale;
//import fxKappaleet.Kappaleet;
import fxKappaleet.SailoException;
import fxKappaleet.Rekisteri;




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


    
    private String listannimi = "Hittibiisit";

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();      
    }

    
    @FXML
    void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa :(");
    }
    
    
    @FXML private void handleHakuehto() {
        String hakukentta = cbKentat.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + hakukentta + ": " + ehto);
    }

    
    @FXML private void handleSulje() {
        tallenna();
        Platform.exit();
    } 
    
    
    @FXML private void handleAvaa() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä :(");
    }

    
    @FXML
    void handleLisaaKappale() {
        uusiKappale();
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
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaArtisti.fxml"), listannimi, null, "");
    }
    
    
    @FXML private void handleNaytaYhtiot() {
        ModalController.showModal(BiisitGUIController.class.getResource("LisaaYhtio.fxml"), listannimi, null, "");
    }
    
    
    @FXML private void handleMuokkausSivu() {
        ModalController.showModal(BiisitGUIController.class.getResource("Muokkaus.fxml"), listannimi, null, "");
    }
    
    
    //------------------------- TÄSTÄ ETEENPÄIN EI LIITY SUORAAN KÄYTTÖLIITTYMÄÄN ----------------------------
    
    
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
        
        if(kappaleKohdalla == null) return;
        
        areaKappale.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaKappale)){
            kappaleKohdalla.tulosta(os);
        }     
    }
    
    
    /**
     * @param nro Kappaleen numero
     */
    private void hae(int nro) {
        chooserKappaleet.clear();
        
        int index = 0;
        for(int i = 0; i < rekisteri.getKappaleet(); i++) {
            Kappale kappale = rekisteri.annaKappale(i);
            if (kappale.getKappaleId() == nro) index = i;
            chooserKappaleet.add(kappale.getKappaleenNimi(), kappale);
        }
        chooserKappaleet.setSelectedIndex(index);
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
     */
    protected void lueTiedosto(String nimi) {
        listannimi = nimi;
        setTitle("Hittibiisit - " + listannimi);
        //String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            //Dialogs.showMessageDialog(virhe);
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
    }
    
    
    /**
     * Tulostaa listassa olevat kappaleet tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki jäsenet");
            for (int i = 0; i < rekisteri.getKappaleet(); i++) {
                Kappale kappale = rekisteri.annaKappale(i);
                tulosta(os, kappale);
                os.println("\n\n");
            }
        }
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


    private void tallenna() {
        Dialogs.showMessageDialog("Ei osata tallentaa.");
    }
}