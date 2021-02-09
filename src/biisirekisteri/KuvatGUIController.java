package biisirekisteri;

import javafx.fxml.FXML;
import fi.jyu.mit.fxgui.Dialogs;

/**
 * @author OMISTAJA
 * @version 29.1.2021
 *
 */
public class KuvatGUIController {
    
    @FXML
    void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa :(");
    }
}