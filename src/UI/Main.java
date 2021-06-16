package UI;

import Model.Automata;
import Model.CFG;
import XML.XMLParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main extends Application {

    FXMLLoader loader;

    Automata automata;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();

        automata = new Automata();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();

        controller.convert_to_cfg_button.setOnAction(actionEvent -> {
            String path = controller.path_text_field.getText();
            try {
                XMLParser.parse(automata, path);
            } catch (ParserConfigurationException | IOException | SAXException e) {
                e.printStackTrace();
            }
            CFG.convertToCFG(automata);
            System.out.println("parsed!");
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
