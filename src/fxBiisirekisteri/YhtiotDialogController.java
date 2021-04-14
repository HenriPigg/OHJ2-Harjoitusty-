package fxBiisirekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import Levyyhtio.Levyyhtio;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 14.4.2021
 *
 */
public class YhtiotDialogController implements ModalControllerInterface<Levyyhtio>, Initializable{

    @FXML private TextField editYhtio;
    @FXML private TextField editPerustamisvuosi;
    
    @FXML private Label labelVirhe;

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleOK() {
        if (yhtioKohdalla != null && yhtioKohdalla.getLevyyhtio().trim().equals("") ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        yhtioKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    
// ----------------------------------------------------------------------------------
    
    private Levyyhtio yhtioKohdalla;
    private TextField edits[];
    
    
    /**
     * Tyhjentää halutut tekstikentät
     * @param edits Tyhjättävät kentät
     */
    public static void tyhjenna(TextField[] edits) {
        for (TextField edit : edits)
            edit.setText("");
    }
    
    
    /**
     * Tarvittavat alustukset
     */
    public void alusta() {
        edits = new TextField[] { 
                editYhtio, 
                editPerustamisvuosi };
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased( e -> kasitteleMuutosYhtioon(k, (TextField)(e.getSource())));
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
    
    
    /**
     * Käsitellään jäseneen tullut muutos
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosYhtioon(int k, TextField edit) {
        if (yhtioKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
           case 1 : virhe = yhtioKohdalla.setYhtio(s); break;
           case 2 : virhe = yhtioKohdalla.setPerustamisvuosi(s); break;
           default:
        }
        if (virhe == null) {
            Dialogs.setToolTipText(edit,"");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit,virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /**
     * Näytetään kappaleen tiedot TextField komponentteihin
     * @param edits Tekstikenttä taulukko
     * @param yhtio näytettävä jäsen
     */
    public static void naytaYhtio(TextField[] edits, Levyyhtio yhtio) {
        if (yhtio == null) return;
        edits[0].setText(yhtio.getLevyyhtio());
        edits[1].setText(yhtio.getPerustamisvuosi());
    }
    
    
    /**
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Kappale, jota näytetään oletuksena
     * @return cancel, palautuu null, muutoin kappale muokkaussivu
     */
    public static Levyyhtio kysyYhtio(Stage modalityStage, Levyyhtio oletus) {
        return ModalController.<Levyyhtio, YhtiotDialogController>showModal(
                YhtiotDialogController.class.getResource("LisaaYhtio.fxml"),
                "Hittibiisit",
                modalityStage, oletus, null 
            );

    }
    
    
    @Override
    public Levyyhtio getResult() {
        return yhtioKohdalla;
    }

    @Override
    public void handleShown() {
        editYhtio.requestFocus();
    }

    @Override
    public void setDefault(Levyyhtio oletus) {
        yhtioKohdalla = oletus;
        naytaYhtio(edits, yhtioKohdalla);
        
    }

}
