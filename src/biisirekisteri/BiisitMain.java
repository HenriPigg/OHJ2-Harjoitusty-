package biisirekisteri;

import Kappaleet.Rekisteri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

/**
 * @author Joonas Ruuth & Henri Pigg
 * @version 29.1.2021
 *
 */
public class BiisitMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader ldr = new FXMLLoader(getClass().getResource("BiisitGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final BiisitGUIController biisitCtrl = (BiisitGUIController) ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("kuvat.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hittibiisit");
            
            primaryStage.setOnCloseRequest((event) -> {
                if ( !biisitCtrl.voikoSulkea() ) event.consume();
            });
            
            Rekisteri rekisteri = new Rekisteri();
            biisitCtrl.setRekisteri(rekisteri);
        
        primaryStage.show();
        if ( !biisitCtrl.avaa() ) Platform.exit();
    } catch(Exception e) {
        e.printStackTrace();
    }
}


    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}