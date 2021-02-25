package biisirekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 29.1.2021
 *
 */
public class BiisitGUIController implements Initializable{
    
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;

    
    private String listannimi = "Hittibiisit";

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //      
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
    void handleLisaaBiisi() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä :(");
    }
    
    
    @FXML
    void handleApua() {
        Dialogs.showMessageDialog("Ei osata vielä antaa apua :(");
    }
    
    
    @FXML
    void handlePoistaBiisi() {
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
    
    
    //------------------------- TÄSTÄ ETEENPÄIN EI LIITY KÄYTTÖLIITTYMÄÄN ----------------------------
    
    
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
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
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