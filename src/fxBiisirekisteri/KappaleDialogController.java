package fxBiisirekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import Kappaleet.Kappale;
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
 * @version 15.2.2021
 *
 */
public class KappaleDialogController implements ModalControllerInterface<Kappale>, Initializable{
    
    @FXML private TextField editKappaleenNimi;
    @FXML private TextField editAlbumi;
    @FXML private TextField editJulkaisuvuosi;
    @FXML private TextField editGenre;
    @FXML private TextField editKuuntelukerrat;
    
    @FXML private Label labelVirhe;

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleOK() {
        if (kappaleKohdalla != null && kappaleKohdalla.getKappaleenNimi().trim().equals("") ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        kappaleKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

  //---------------------------------------------------------------------------
    private Kappale kappaleKohdalla;
    private TextField edits[];
    
    
    /**
     * Tyhjentää halutut tekstikentät
     * @param edits Tyhjättävät kentät
     */
    public static void tyhjenna(TextField[] edits) {
        for (TextField edit : edits)
            edit.setText("");
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
    private void kasitteleMuutosKappaleeseen(int k, TextField edit) {
        if (kappaleKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
           case 1 : virhe = kappaleKohdalla.setKappaleenNimi(s); break;
           case 2 : virhe = kappaleKohdalla.setAlbumi(s); break;
           case 3 : virhe = kappaleKohdalla.setJulkaisuvuosi(s); break;
           case 4 : virhe = kappaleKohdalla.setGenre(s); break;
           case 5 : virhe = kappaleKohdalla.setKuuntelukerrat(s); break;
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
     * Tarvittavat alustukset
     */
    public void alusta() {
        edits = new TextField[] { 
                editKappaleenNimi, 
                editAlbumi, 
                editJulkaisuvuosi, 
                editGenre, 
                editKuuntelukerrat };
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased( e -> kasitteleMuutosKappaleeseen(k, (TextField)(e.getSource())));
        }
    }
    
    
    @Override
    public void setDefault(Kappale oletus) {
        kappaleKohdalla = oletus;
        naytaKappale(edits, kappaleKohdalla);
        
    }
    
    
    /**
     * Näytetään kappaleen tiedot TextField komponentteihin
     * @param edits Tekstikenttä taulukko
     * @param kappale näytettävä jäsen
     */
    public static void naytaKappale(TextField[] edits, Kappale kappale) {
        if (kappale == null) return;
        edits[0].setText(kappale.getKappaleenNimi());
        edits[1].setText(kappale.getAlbumi());
        edits[2].setText(kappale.getVuosi());
        edits[3].setText(kappale.getGenre());
        edits[4].setText(kappale.getKuuntelukerrat());
    }

    
    
    
    
    /**
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Kappale, jota näytetään oletuksena
     * @return cancel, palautuu null, muutoin kappale muokkaussivu
     */
    public static Kappale kysyKappale(Stage modalityStage, Kappale oletus) {
        return ModalController.<Kappale, KappaleDialogController>showModal(
                KappaleDialogController.class.getResource("KappaleDialogView.fxml"),
                "Hittibiisit",
                modalityStage, oletus, null 
            );

    }
    

    @Override
    public Kappale getResult() {
        return kappaleKohdalla;
    }

    @Override
    public void handleShown() {
        editKappaleenNimi.requestFocus();
    }


}
