import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ReadChoice extends Application{

    static String[] options;
    static int selection;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Motel Management System");
        options = new String[]{"one","two","three"};
        ChoiceBox<String> choiceBox = new ChoiceBox<String>();
        for (String option : options) {
            choiceBox.getItems().add(option);
        }

        HBox hbox = new HBox(choiceBox);

        Scene scene = new Scene(hbox, 200, 100);
        stage.setScene(scene);
        stage.show();
        choiceBox.setOnAction((event) -> {
            selection = choiceBox.getSelectionModel().getSelectedIndex();
        });
    }

    public static int returnIndex()
    {
        return selection;
    }

    public static void main(String[] args) {
        Application.launch(args);
        System.out.println(ReadChoice.returnIndex());
    }
}