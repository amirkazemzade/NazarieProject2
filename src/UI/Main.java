package UI;

import Model.Automata;
import Model.CFG;
import Model.Grammar;
import XML.XMLParser;
import com.sun.jdi.ArrayReference;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    FXMLLoader loader;

    Automata automata;

    ArrayList<Grammar> grammars;

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
            grammars = CFG.convertToCFG(automata);
            try {
                CFG.PrintGrammar(grammars, "output.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("parsed!");
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
