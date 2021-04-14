/**
 * 
 */
package fxBiisirekisteri;

import java.net.URL;
import java.util.ResourceBundle;

import Artistit.Artisti;
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
public class ArtistiDialogController implements ModalControllerInterface<Artisti>, Initializable{

    @FXML private TextField editArtisti;
    @FXML private TextField editAloitusvuosi;

    @FXML private Label labelVirhe;

    
    @FXML private void handleOK() {
        if (artistiKohdalla != null && artistiKohdalla.getArtistiNimi().trim().equals("") ) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }

    
    @FXML private void handleCancel() {
        artistiKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    
// ----------------------------------------------------------------------------    
    private Artisti artistiKohdalla;
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
    
    
    @Override
    public Artisti getResult() {
        return artistiKohdalla;
    }

    @Override
    public void handleShown() {
        editArtisti.requestFocus();
    }

    @Override
    public void setDefault(Artisti oletus) {
        artistiKohdalla = oletus;
        naytaArtisti(edits, artistiKohdalla);
        
    }
    
    
    /**
     * Näytetään kappaleen tiedot TextField komponentteihin
     * @param edits Tekstikenttä taulukko
     * @param artisti näytettävä jäsen
     */
    public static void naytaArtisti(TextField[] edits, Artisti artisti) {
        if (artisti == null) return;
        edits[0].setText(artisti.getArtistiNimi());
        edits[1].setText(artisti.getAloitusvuosi());
        
    }
    
    
    /**
     * Tarvittavat alustukset
     */
    public void alusta() {
        edits = new TextField[] { 
                editArtisti, 
                editAloitusvuosi };
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased( e -> kasitteleMuutosArtistiin(k, (TextField)(e.getSource())));
        }
    }
    
    
    /**
     * Käsitellään jäseneen tullut muutos
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosArtistiin(int k, TextField edit) {
        if (artistiKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
           case 1 : virhe = artistiKohdalla.setArtisti(s); break;
           case 2 : virhe = artistiKohdalla.setAloitusvuosi(s); break;
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
     * @param modalityStage Mille ollaan modaalisia
     * @param oletus Artisti jota näytetään oletuksena
     * @return null muutoin artisti muokkaussivu
     */
    public static Artisti kysyArtisti(Stage modalityStage, Artisti oletus) {
        return ModalController.<Artisti, ArtistiDialogController>showModal(
                ArtistiDialogController.class.getResource("LisaaArtisti.fxml"),
                "Hittibiisit",
                modalityStage, oletus, null 
            );
    }

}
