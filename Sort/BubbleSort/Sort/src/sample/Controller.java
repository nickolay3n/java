package sample;

        import java.net.URL;
        import java.time.*;
        import java.time.temporal.*;
        import java.util.Date;
        import java.util.ResourceBundle;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;

        import static java.lang.System.currentTimeMillis;

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

    @FXML
    private SplitMenuButton ButtonIDSortList;

    @FXML
    private Button ButtonIDSort;

    @FXML
    private TextArea TextArrayIDSorted;

    @FXML
    private Button ButtonIDGenerate;

    @FXML//сгенерировать массив рандомов
    public void ButtonGeneratePressed(ActionEvent event) {
        TextArrayID.setText(MySort.printArray(400));
    }

    @FXML//отсортировать
    public void ButtonSortPressed(ActionEvent event) throws InterruptedException {
        long tmp = currentTimeMillis();
        TextArrayIDSorted.setText(MySort.sort(ButtonIDSortList.getText()));//Thread.sleep(7);
        TimerIDElapsed.setText("Прошло " + (currentTimeMillis()-tmp) + " Миллисекунд");
    }

    @FXML//очистить формы
    public void ButtonClearPressed(ActionEvent event) {
        TextArrayID.setText("");
        TextArrayIDSorted.setText("");
    }

    @FXML//кнопка выборки типа сортировки
    public void ButtonSortListPressed(ActionEvent event) {
        //TextArrayIDSorted.setText(ButtonIDSortList.toString());
        TextArrayIDSorted.setText( ButtonIDSortList.getItems().toString());
    }

    @FXML
    void initialize() {
        //отрисовка кнопки сортировки
        ButtonIDSortList.getItems().clear();
        MenuItem itemSortBubble = new MenuItem("Сортировка пузырьком");
        MenuItem itemSortPiramid = new MenuItem("Сортировка пирамидой");

        boolean b = ButtonIDSortList.getItems().addAll(itemSortBubble,itemSortPiramid);

        itemSortBubble.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                ButtonIDSortList.setText(itemSortBubble.getText());
            }
        });

        itemSortPiramid.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent){
                ButtonIDSortList.setText(itemSortPiramid.getText());
            }
        });

//        assert ButtonIDClear != null : "fx:id=\"ButtonIDClear\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TimerIDElapsed != null : "fx:id=\"TimerIDElapsed\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TextArrayID != null : "fx:id=\"TextArrayID\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDSortList != null : "fx:id=\"ButtonIDSortList\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDSort != null : "fx:id=\"ButtonIDSort\" was not injected: check your FXML file 'sample.fxml'.";
//        assert TextArrayIDSorted != null : "fx:id=\"TextArrayIDSorted\" was not injected: check your FXML file 'sample.fxml'.";
//        assert ButtonIDGenerate != null : "fx:id=\"ButtonIDGenerate\" was not injected: check your FXML file 'sample.fxml'.";

    }

}
