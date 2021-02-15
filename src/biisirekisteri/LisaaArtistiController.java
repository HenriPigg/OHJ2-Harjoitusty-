package biisirekisteri;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 15.2.2021
 *
 */
public class LisaaArtistiController implements ModalControllerInterface<String>{
    
    @FXML
    private TextField textLisattava;
    
    @FXML
    void handleLisays() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä :(");

    }

    @FXML
    void handlePoista() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa :(");

    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }

}
