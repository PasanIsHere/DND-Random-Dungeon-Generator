package gui;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class GuiDemo<toReturn> extends Application {


    private Controller theController;
    private BorderPane root;
    private Popup editPane;
    private Label textLabel;
    private Stage primaryStage;
    private BorderPane x = new BorderPane();

    private int len = 0;
    private int wid = 0;

    @Override
    public void start(Stage assignedStage) {
        theController = new Controller(this);
        primaryStage = assignedStage;
        root = setUpRoot();
        editPane = createPopUp(50, 100, "Edit");
        Scene scene = new Scene(root, 880, 925);
        primaryStage.setTitle("Dungeon Generation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane setUpRoot() {
        BorderPane temp = new BorderPane();
        BorderPane temp2 = new BorderPane();
        temp.setTop(new Label("Space\t\t\t\t\t\t\t\t\t\t\t\t\t\tSpace View"));
        Node buttons = setButtonPanel();  //separate method for the left section
        temp.setRight(buttons);
        ObservableList<String> chamberList = FXCollections.observableArrayList(theController.getNameList());
        temp2.setTop(createListView(chamberList));
        temp2.setBottom(setEditPannel());
        temp.setLeft(temp2);

        return temp;
    }

    private Node createListView(ObservableList<String> spaces){
        ListView temp = new ListView<String>(spaces);
        temp.setPrefWidth(150);
        temp.setPrefHeight(500);
        temp.setOnMouseClicked((MouseEvent event)->{
            System.out.println("Description of " + temp.getSelectionModel().getSelectedItem());

            TextArea textA = new TextArea("Description of " + temp.getSelectionModel().getSelectedItem() + ":\n" + theController.getCPDesc((String)(temp.getSelectionModel().getSelectedItem())));
            textA.setStyle(" -fx-background-color: white;");
            textA.setMinWidth(80);
            textA.setMinHeight(50);
            textA.setEditable(false);

            GridPane room = new ChamberView( theController.getLength((String)(temp.getSelectionModel().getSelectedItem())) ,theController.getWidth((String)(temp.getSelectionModel().getSelectedItem())), theController.getDoorNums((String)(temp.getSelectionModel().getSelectedItem())));
            ComboBox doorComboBox = doorComboBox = createComboBox( theController.getDoorNums((String)(temp.getSelectionModel().getSelectedItem())));


            x.setRight(doorComboBox);
            x.setCenter(room);
            x.setTop(textA);
            root.setCenter(x);
        });

        return temp;
    }


    private Node setEditPannel(){
        BorderPane temp = new BorderPane();
        Button editButton = createButton("Edit","-fx-background-color: #FFFFFF" );
        editButton.setOnAction((ActionEvent event) -> {
            editPane.show(primaryStage);
        });
        temp.setBottom(editButton);
        return editButton;

    }
    private Node setButtonPanel() {

        VBox temp = new VBox();

        temp.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");


        Button firstButton = createButton("Full Description", "-fx-background-color: #ff0000; -fx-background-radius: 10, 10, 10, 10;");
        firstButton.setOnAction((ActionEvent event) -> {
            System.out.println(theController.getFullDescription());

        });
        temp.getChildren().add(firstButton);




        return temp;

    }

    /* an example of a popup area that can be set to nearly any
    type of node
     */
    private Popup createPopUp(int x, int y, String text) {
       Button btnAddMonster = createButton("Add Monster","F");
       Button btnAddTreasure = createButton("Add Treasure","F");
       BorderPane temp = new BorderPane();
       temp.setLeft(btnAddMonster);
       temp.setRight(btnAddTreasure);
        Popup popup = new Popup();
        popup.setX(x);
        popup.setY(y);

        TextArea textA = new TextArea(text);
        textA.setStyle(" -fx-background-color: white;");
        textA.setMinWidth(20);
        textA.setMinHeight(20);
        textA.setEditable(false);



        Button hideButton = createButton("Hide Description", "-fx-background-color: #FFFFFF; ");
        hideButton.setOnAction((ActionEvent event) -> {
            editPane.hide();
            changeDescriptionText(theController.getNewDescription());
        });
        temp.setTop(hideButton);
        popup.getContent().addAll(textA);
        popup.getContent().addAll(temp);
        return popup;
    }


    private Button createButton(String text, String format) {
        Button btn = new Button();
        btn.setText(text);
        btn.setStyle("");
        return btn;
    }

    private void changeDescriptionText(String text) {
        ObservableList<Node> list = editPane.getContent();
        for (Node t : list) {
            if (t instanceof TextArea) {
                TextArea temp = (TextArea) t;
                temp.setText(text);
            }

        }

    }

    private ComboBox createComboBox(int num){
        int i;
        ComboBox x = new ComboBox();
        for ( i = 1; i < num+1; i++){
            x.getItems().add("Door " + i);
        }
        return x;
    }
  /*  private ComboBox createDoorBox(int numDoors){
        ComboBox doorComboBox = new ComboBox();
        int i = 0;

        for (i = 0; i < numDoors; i++){
            doorComboBox.getItems().add("Door " +  (i + 1));
        }
    }*/

    public static void main(String[] args) {
        launch(args);
    }

}
