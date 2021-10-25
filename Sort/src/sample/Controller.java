package sample;

        import java.net.URL;
        import java.util.ResourceBundle;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.SplitMenuButton;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButtonIDClear;

    @FXML
    private TextField TimerIDElapsed;

    @FXML
    private  TextArea TextArrayID;
    //public static void TextArrayIDSetter(String text){TextArrayID.setText(text);}

    @FXML
    private SplitMenuButton ButtonIDSortList;

    @FXML
    private Button ButtonIDSort;

    @FXML
    private TextArea TextArrayIDSorted;

    @FXML
    private Button ButtonIDGenerate;

    @FXML
    public void ButtonGeneratePressed(ActionEvent event) {
        TextArrayID.setText(MySort.printArray(400));
    }

    @FXML
    public void ButtonSortPressed(ActionEvent event) {
    }

    @FXML
    public void ButtonClearPressed(ActionEvent event) {

    }
    @FXML
    public void ButtonSortListPressed(ActionEvent event) {
    }

    @FXML
    void initialize() {
//        assert ButtonIDClear != null : "fx:id=\"ButtonIDClear\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TimerIDElapsed != null : "fx:id=\"TimerIDElapsed\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TextArrayID != null : "fx:id=\"TextArrayID\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDSortList != null : "fx:id=\"ButtonIDSortList\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDSort != null : "fx:id=\"ButtonIDSort\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TextArrayIDSorted != null : "fx:id=\"TextArrayIDSorted\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDGenerate != null : "fx:id=\"ButtonIDGenerate\" was not injected: check your FXML file 'sample.fxml'.";

    }

    //public void ButtonGeneratePressed(ActionEvent actionEvent) {    }
}
