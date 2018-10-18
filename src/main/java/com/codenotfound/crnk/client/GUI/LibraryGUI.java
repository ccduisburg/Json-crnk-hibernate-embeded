package com.codenotfound.crnk.client.GUI;

import com.codenotfound.crnk.client.BlogClient;
import com.codenotfound.crnk.domain.model.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class LibraryGUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Scene primaryScene, scene2;
    private VBox vBoxFieldBook, vBoxPerson,vBoxAdress,vBoxLabel;
    private HBox hBoxB,hBoxTextField,root2;
    private Button  btnListAdress,btncreate,btnSave,btnDelete,btnUpdate;
    private TextField fieldUniId,txtfPName,txtPVorname,txtPBeruf,txtAStrasse,txtAHnummer,txtAPLZ,txtACity,txtBTitle,txtBDescription,txtLName;
    private TextArea areaContent;
    private BlogClient client;
    private TableView table;
    private List<Address> alladress;
    private List<Book> allbook;
    private GridPane gridPane;
    private Group root;
    private Label lblPName,lblbPVorname,lblPBeruf,lblAStrasse,lblAHnummer,lblAPLZ,lblACity,lblBTitle,lblBDescription,lblLName;


    @Override
    public void start(Stage primaryStage) throws Exception {
        client = new BlogClient();
        client.init();
//        addComponents();
      //  registerEvents();
        Scene scene = new Scene(getRoot(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("List Data");
//        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    private void setButtonWidthHeight(Button btn) {
        btn.setPrefWidth(200);
        btn.setPrefHeight(50);
    }


    private HBox getRoot() {
        HBox root = new HBox();
        root.getChildren().add(getGridPane());
        root.getChildren().add(getVBox());
        return root;
    }

    private void refreshTable() {
        table.getItems().clear();
        //table.getItems().addAll(client.findBookPersonAddressLibrary());
    }
    private VBox getVBox() {
        /*
           private String personName;
    private String personVorname;
    private String personBeruf;
    private String personStrasse;
    private int personHausnummer;
    private String personPlz;
    private String personStadt;
    private String bookTitle;
    private String bookDescription;
    private String libraryName;

         */
        TableColumn name = new TableColumn("Personal Name");
        name.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("personName"));
        TableColumn vorname = new TableColumn("Personal Vorname");
        vorname.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("personVorname"));
        TableColumn beruf = new TableColumn("Personal Beruf");
        beruf.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary,String>("personBeruf"));
        TableColumn strasse = new TableColumn("Strasse");
        strasse.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("personStrasse"));
        TableColumn hnummer = new TableColumn("Hausnummer");
        hnummer.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, Integer>("personHausnummer"));
        TableColumn PLZ = new TableColumn("PLZ");
        PLZ.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("personPlz"));
        TableColumn city = new TableColumn("city");
        city.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("personStadt"));
        TableColumn title = new TableColumn("Book Title");
        title.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary, String>("bookTitle"));
        TableColumn description = new TableColumn("Book Description");
        description.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary,String>("bookDescription"));
        TableColumn libraryName = new TableColumn("Bibliothek Name");
        libraryName.setCellValueFactory(new PropertyValueFactory<BookPersonAddressLibrary,String>("libraryName"));

        table = new TableView();
        table.getColumns().addAll(name, vorname, beruf,
                      strasse, hnummer, PLZ, city,
                       title, description, libraryName);

//        alladress.addAll(client.findAllAddresses());
//        table.getItems().clear();
//        table.getItems().addAll(alladress);
//        System.out.println(alladress);

        refreshTable();
        VBox root = new VBox();
        root.getChildren().addAll(new Label("Content of table"), table);
        return root;
    }
    private GridPane getGridPane() {
        //  ---------primary scene--------------------------------
        btncreate=new Button("Create");
        btnSave=new Button("Save");
        btnUpdate=new Button("Update");
        btnDelete=new Button("Delete");
        btnListAdress = new Button("List Addresses");
        setButtonWidthHeight(btncreate);
        setButtonWidthHeight(btnSave);
        setButtonWidthHeight(btnUpdate);
        setButtonWidthHeight(btnDelete);
        setButtonWidthHeight(btnListAdress);

        //-------------------------------------------------
        txtfPName=new TextField();
        txtPVorname=new TextField();
        txtPBeruf=new TextField();
        txtAStrasse=new TextField();
        txtAHnummer=new TextField();
        txtAPLZ=new TextField();
        txtACity=new TextField();
        txtBTitle=new TextField();
        txtBDescription=new TextField();
        txtLName=new TextField();
//----------------------------------------------------------
        lblPName=new Label("Personal Name");
        lblbPVorname=new Label("Personal Vorname");
        lblPBeruf=new Label("Personal Beruf");
        lblAStrasse=new Label("Strasse");
        lblAHnummer=new Label("Haus nummer");
        lblAPLZ=new Label("PLZ");
        lblACity=new Label("Stadt");
        lblBTitle=new Label("Book Title");
        lblBDescription=new Label("Book Description");
        lblLName=new Label("Biblothek Name");

        Node nodes[][] = {
                {lblPName, txtfPName},
                {lblbPVorname, txtPVorname},
                {lblPBeruf, txtPBeruf},
                {lblAStrasse, txtAStrasse},
                {lblAHnummer, txtAHnummer},
                {lblAPLZ, txtAPLZ},
                {lblACity, txtACity},
                {lblBTitle, txtBTitle},
                {lblBDescription, txtBDescription},
                {lblLName, txtLName},
                {btncreate, btnDelete},
                {btnSave, btnUpdate},
                {btnListAdress, new Label()}
        };

        GridPane root = new GridPane();
//        root.addRow(0, lblPName, txtfPName);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(20));
        for(int i = 0; i < nodes.length; ++i) {
            root.addRow(i, nodes[i][0], nodes[i][1]);
        }
        return root;
    }

    private void addComponents() {
      //  ---------primary scene--------------------------------
        btncreate=new Button("Create");
        btnSave=new Button("Save");
        btnUpdate=new Button("Update");
        btnDelete=new Button("Delete");
        btnListAdress = new Button("List Addresses");
       //-------------------------------------------------
        txtfPName=new TextField();
        txtPVorname=new TextField();
        txtPBeruf=new TextField();
        txtAStrasse=new TextField();
        txtAHnummer=new TextField();
        txtAPLZ=new TextField();
        txtACity=new TextField();
        txtBTitle=new TextField();
        txtBDescription=new TextField();
        txtLName=new TextField();
//----------------------------------------------------------
        lblPName=new Label("Personal Name");
        lblbPVorname=new Label("Personal Vorname");
        lblPBeruf=new Label("Personal Beruf");
        lblAStrasse=new Label("Strasse");
        lblAHnummer=new Label("Haus nummer");
        lblAPLZ=new Label("PLZ");
        lblACity=new Label("Stadt");
        lblBTitle=new Label("Book Title");
        lblBDescription=new Label("Book Description");
        lblLName=new Label("Biblothek Name");

//----------------------------
        //StackPane root = new StackPane();


        vBoxFieldBook=new VBox();
//        //vBoxFieldBook.setAlignment(Pos.TOP_CENTER);
        vBoxFieldBook.getChildren().addAll(txtBTitle,txtBDescription,txtLName);

        vBoxAdress=new VBox(txtAStrasse,txtAHnummer,txtAPLZ,txtACity);
//       // vBoxAdress.setAlignment(Pos.BASELINE_CENTER);
//        vBoxAdress.getChildren().addAll(txtAStrasse,txtAHnummer,txtAPLZ,txtACity);

        vBoxPerson=new VBox(txtfPName,txtPVorname,txtPBeruf);
//       // vBoxPerson.setAlignment(Pos.TOP_RIGHT);
//        vBoxPerson.getChildren().addAll(txtfPName,txtPVorname,txtPBeruf);


        hBoxB=new HBox(btncreate,btnSave,btnUpdate,btnDelete);
//        hBoxB.setAlignment(Pos.BOTTOM_CENTER);
//        hBoxB.getChildren().addAll(btncreate,btnSave,btnUpdate,btnDelete);

        vBoxLabel=new VBox(lblPName,lblbPVorname,lblPBeruf,lblAStrasse,lblAHnummer,lblAPLZ,lblACity,lblBTitle,lblBDescription,lblLName);
        hBoxTextField=new HBox(vBoxFieldBook,vBoxAdress,vBoxPerson);
        root2 = new HBox();
        root2.setPadding(new Insets(20));
        //root=new Group(hBoxLabel,hBoxLabel,hBoxB);
        root2.getChildren().addAll(vBoxLabel,vBoxPerson,vBoxAdress,vBoxFieldBook);
        //root2.getChildren().addAll();
        primaryScene = new Scene(root2, 500, 500);








   //--------------scene2------------

//        fieldUniId = new TextField();
//        areaContent = new TextArea();
//        table = new TableView<>();
//        gridPane = new GridPane();
//        alladress = new ArrayList<>();
//        btnListAdress = new Button("Suchen");
//        root2.getChildren().addAll(btnListAdress,fieldUniId,gridPane,areaContent,table);
//        scene2 = new Scene(root2, 500, 500);


        allbook=new ArrayList<>();
        alladress =new ArrayList<>();
    }




    private void registerEvents() {
       // ShowAllAdress();
        //showAllBook();
        //showDataAufGrid();

        btnListAdress.setOnAction(evt -> {
//            System.out.println("debug");


            try {
                long id = Long.parseLong(fieldUniId.getText());
                Person adress = client.findOnePerson(id);
                areaContent.setText(adress.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                areaContent.setText(ex.getMessage());
            }
        });


    }

    private void ShowAllAdress() {
        table.setEditable(true);
//            TableColumn firstNameCol = new TableColumn("id");
//            firstNameCol.setCellValueFactory( new PropertyValueFactory<Adress,Long>("id"));
        TableColumn strasse = new TableColumn("Strasse");
        strasse.setCellValueFactory(new PropertyValueFactory<Address, String>("location"));
        TableColumn hnummer = new TableColumn("Hausnummer");
        hnummer.setCellValueFactory(new PropertyValueFactory<Address, Integer>("hnummer"));
        TableColumn PLZ = new TableColumn("PLZ");
        PLZ.setCellValueFactory(new PropertyValueFactory<Address, String>("PLZ"));
        TableColumn city = new TableColumn("city");
        city.setCellValueFactory(new PropertyValueFactory<Address, String>("stadt"));

        table.getColumns().addAll(strasse, hnummer, PLZ, city);

       // alladress.addAll(client.findAllAddresses());
        table.getItems().clear();
        table.getItems().addAll(alladress);
        System.out.println(alladress);


    }

    private void showAllBook(){
        table.setEditable(true);
        TableColumn name = new TableColumn("title");
        name.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
       // TableColumn description = new TableColumn("Description");
       // description.setCellValueFactory(new PropertyValueFactory<Address, Long>("location"));

        allbook.addAll(client.findAllBooks());

        table.getItems().clear();
        System.out.println(allbook);
        System.out.println(alladress);
       // table.getColumns().addAll(name,description);

    }

    private void showDataAufGrid() {
        gridPane.getColumnConstraints().addAll(table.getItems());

    }
}

