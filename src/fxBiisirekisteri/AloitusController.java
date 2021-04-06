/**
 * 
 */
package fxBiisirekisteri;


import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 15.2.2021
 *
 */
public class AloitusController implements ModalControllerInterface<String>{

    @FXML private TextField textVastaus;
    private String vastaus = null;
    
    
    @Override
    public void handleShown() {
        textVastaus.requestFocus();
    }
    
    @FXML private void handleOK() {          
        vastaus = textVastaus.getText();
        ModalController.closeStage(textVastaus);
    }
          
              
    @FXML private void handleCancel() {
        ModalController.closeStage(textVastaus);
    }
    
    
    @Override
    public String getResult() {
        return vastaus;
    }
    
    
    @Override
    public void setDefault(String oletus) {
        textVastaus.setText(oletus);
    }
    

    
    
    /**
     * @param modalityStage Modaalisuus sovellukselle
     * @param oletus Oletusnimi
     * @return Null jos cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                AloitusController.class.getResource("Aloitus.fxml"),
                "Hittibiisit",
                modalityStage, oletus);
    }

}
