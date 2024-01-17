package Contact; 

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import java.net.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane; 
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.geometry.Insets;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable; 

// TODO: change all occurences of Contact to Contact
class Contact extends HBox {

    private Label index;
    private TextField contactName;
    private TextField contactNumber;
    private TextField contactEmail;
    private Button deleteButton;
    private Button uploadButton; 
    private ImageView imageView; 
    private boolean markedDone;

    Contact(Stage primaryStage) {
        this.setPrefSize(500, 20); // sets size of Contact
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background color of Contact
        markedDone = false;

        index = new Label();
        index.setText(""); // create index label
        index.setPrefSize(40, 20); // set size of Index label
        index.setTextAlignment(TextAlignment.LEFT); // Set alignment of index label
        index.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the Contact
        // this.getChildren().add(index); // add index label to Contact

        contactName = new TextField(); // create Contact name text field
        this.contactName.setPromptText("Name");
        contactName.setPrefSize(380, 20); // set size of text field
        contactName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.CENTER); // set alignment of text field
        contactName.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(contactName); // add textlabel to Contact

        contactNumber = new TextField(); // create Contact name text field
        contactNumber.setPrefSize(380, 20); // set size of text field
        this.contactNumber.setPromptText("Number");
        contactNumber.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.RIGHT); // set alignment of text field
        contactNumber.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(contactNumber); // add textlabel to Contact

        contactEmail = new TextField(); // create Contact name text field
        this.contactEmail.setPromptText("Email");
        contactEmail.setPrefSize(380, 20); // set size of text field
        contactEmail.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // set background color of texfield
        index.setTextAlignment(TextAlignment.LEFT); // set alignment of text field
        contactEmail.setPadding(new Insets(10, 0, 10, 0)); // adds some padding to the text field
        this.getChildren().add(contactEmail); // add textlabel to Contact
        try{ 
            // Image image = new Image(new FileInputStream("/Users/josuemartinez/Documents/GitHub/CSE110_FA23_Mini_Proj/src/Contact/download.png")); // creates a button for marking the Contact as done
            Image image = new Image(new FileInputStream("C:\\Users\\ators\\Documents\\(1) UCSD Stuff\\4TH YEAR\\1 - CSE 110\\Mini-Project\\CSE110_FA23_Mini_Proj\\src\\Contact\\download.png")); // creates a button for marking the Contact as done 

            imageView = new ImageView(image); 
            imageView.setFitHeight(100);
            imageView.setFitWidth(100); 
            uploadButton = new Button("", (Node)imageView); 
            deleteButton = new Button("✔"); 
            deleteButton.setMinSize(30, 30); 
            this.getChildren().add(uploadButton); 
            this.getChildren().add(deleteButton); 
            // Update: set an event listener to always allow user to upload an image
            uploadButton.setOnAction(e -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
                File selectedFile = fileChooser.showOpenDialog(primaryStage);

                if (selectedFile != null) {
                    Image uploadedImage = new Image(selectedFile.toURI().toString());

                    imageView.setImage(uploadedImage);
                    imageView = new ImageView(uploadedImage); 
                    uploadButton.setGraphic(imageView); 
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100); 
                }
            });
        } catch(Exception e){
            System.out.println("ERROR"); 
        }
    }

    public void setContactIndex(int num) {
        this.index.setText(num + "");
    }

    public TextField getContactName() { 
        return this.contactName; 
    } 

    public TextField getContactEmail() { 
        return this.contactEmail; 
    } 

    public TextField getContactNumber() { 
        return this.contactNumber; 
    } 

    public Button getDoneButton() {
        return this.deleteButton;
    }

    public boolean isMarkedDone() {
        return this.markedDone;
    }

    public void toggleDone() {
        if(markedDone == true) {
            markedDone = false;
            contactName.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            deleteButton.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;");
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0;"); // change color of Contact to green
            }
        }else {
            markedDone = true;
            this.setStyle("-fx-border-color: #000000; -fx-border-width: 0; -fx-font-weight: bold;"); // remove border of Contact
            for (int i = 0; i < this.getChildren().size(); i++) {
                this.getChildren().get(i).setStyle("-fx-background-color: #BCE29E; -fx-border-width: 0;"); // change color of Contact to green
            }
        }
    }
}
// TODO: change all occurences of ContactList to ContactList
class ContactList extends VBox {

    ContactList() {
        this.setSpacing(5); // sets spacing between Contacts
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }

    public void updateContactIndices() {
        int index = 1;
        for (int i = 0; i < this.getChildren().size(); i++) {
            if (this.getChildren().get(i) instanceof Contact) {
                ((Contact) this.getChildren().get(i)).setContactIndex(index);
                index++;
            }
        }
    }

    public void removeCompletedContacts() {
        this.getChildren().removeIf(Contact -> Contact instanceof Contact && ((Contact) Contact).isMarkedDone());
        this.updateContactIndices();
    }

    // TODO: Complete this method
    /*
     * Save contacts to a file called "contacts.txt"
     */
    public void saveContacts() {
        try {
            FileWriter f = new FileWriter("C:\\Users\\ators\\Documents\\(1) UCSD Stuff\\4TH YEAR\\1 - CSE 110\\Mini-Project\\CSE110_FA23_Mini_Proj\\src\\Contact\\contacts.csv"); 
            for(int i = 0; i < this.getChildren().size(); i++) { 
                Contact Contact = ((Contact) this.getChildren().get(i)); 
                // System.out.println(Contact.getContactName().getText()); 
                f.write(Contact.getContactName().getText() + "," + Contact.getContactNumber().getText() + "," + Contact.getContactEmail().getText()); 
                f.write("\n"); 
                this.updateContactIndices();
            }
            f.close(); 
        } catch (Exception e) { 
            System.out.println("Error"); 
        }
    } 
    public void sortContacts(Stage primaryStage) { 
        ArrayList<Contact> children = new ArrayList<Contact>(); 
        for(int i = 0; i < this.getChildren().size(); i++) { 
            Contact contact = ((Contact) this.getChildren().get(i)); 
            // String[] info = new String[3]; 
            // info[0] = contact.getContactName().getText(); 
            // info[1] = contact.getContactEmail().getText(); 
            // info[2] = contact.getContactNumber().getText(); 
            children.add(contact); 
        } 
        Collections.sort(children, (a,b) -> a.getContactName().getText().compareTo(b.getContactName().getText())); 
        this.getChildren().removeIf(contact -> contact instanceof Contact); 
        for(int i = 0; i < children.size(); i++) { 
                Contact contact = children.get(i); 
                Button doneButton = contact.getDoneButton(); 
                doneButton.setOnAction(e1 -> {
                // Call toggleDone on click
                    contact.toggleDone();
                }); 
                this.getChildren().add(contact);
                // Add task to tasklist 
            }
        
    }
}

class Footer extends HBox {

    private Button addButton;
    private Button deleteButton; 
    private Button saveButton; 
    private Button sortButton; 
    Footer() {
        this.setPrefSize(500, 60);
        this.setStyle("-fx-background-color: #F0F8FF;");
        this.setSpacing(15);

        // set a default style for buttons - background color, font size, italics
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";

        addButton = new Button("Add Contact"); // text displayed on add button
        addButton.setStyle(defaultButtonStyle); // styling the button
        deleteButton = new Button("Remove Selected"); // text displayed on clear Contacts button 
        deleteButton.setStyle(defaultButtonStyle); 
        saveButton = new Button("Save Contacts"); // text displayed on clear Contacts button 
        saveButton.setStyle(defaultButtonStyle); 
        sortButton = new Button("Sort Contacts"); // text displayed on clear Contacts button 
        sortButton.setStyle(defaultButtonStyle); 

        this.getChildren().addAll(addButton, deleteButton, saveButton, sortButton); // adding buttons to footer 
        this.setAlignment(Pos.CENTER); // aligning the buttons to center 
    } 

    // create getters for save and sort
    public Button getAddButton() {
        return addButton; 
    }

    public Button getRemoveButton() { 
        return deleteButton; 
    } 

    public Button getSaveButton() {
        return saveButton; 
    } 

    public Button getSortButton() {
        return sortButton; 
    } 
}

class Header extends HBox {

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F0F8FF;");

        Text titleText = new Text("Contacts"); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
}

class AppFrame extends BorderPane{

    private Header header;
    private Footer footer;
    private ContactList ContactList;

    // TODO: create saveButton (save contacts to list) and sortButton (sort contacts) 
    private Button addButton;
    private Button deleteButton; 
    private Button saveButton; 
    private Button sortButton; 

    AppFrame(Stage primaryStage)
    {
        // Initialise the header Object
        header = new Header();

        // Create a Contactlist Object to hold the Contacts
        ContactList = new ContactList();
        
        // Initialise the Footer Object
        footer = new Footer();

        // DONE: Add a Scroller to the Contact List
        ScrollPane scrollPane = new ScrollPane(ContactList);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);


        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(scrollPane);
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);


        // TODO: create buttons for save and sort
        // Initialise Button Variables through the getters in Footer
        addButton = footer.getAddButton(); 
        deleteButton = footer.getRemoveButton(); 
        saveButton = footer.getSaveButton(); 
        sortButton = footer.getSortButton(); 
        // Call Event Listeners for the Buttons
        addListeners(primaryStage);
    }

    public void addListeners(Stage primaryStage)
    {
        // TODO: create event listeners for save and sort
        // Add button functionality
        addButton.setOnAction(e -> {
            // Create a new Contact
            Contact Contact = new Contact(primaryStage);
            // Add Contact to Contactlist 
            ContactList.getChildren().add(Contact);
            // Add doneButtonToggle to the Done button
            Button deleteButton = Contact.getDoneButton();
            deleteButton.setOnAction(e1 -> {
                // Call toggleDone on click
                Contact.toggleDone(); 
            });
            // Update Contact indices
            ContactList.updateContactIndices();
        });
        if (deleteButton != null) {
            deleteButton.setOnAction(e -> {
                ContactList.removeCompletedContacts();
            }); 
        }
        saveButton.setOnAction(e -> {
            ContactList.saveContacts(); 
        }); 
        sortButton.setOnAction(e -> {
            ContactList.sortContacts(primaryStage); 
        }); 
    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Setting the Layout of the Window- Should contain a Header, Footer and the ContactList
        AppFrame root = new AppFrame(primaryStage);

        // Set the title of the app
        primaryStage.setTitle("Contact Manager"); 
        // Create scene of mentioned size with the border pane 
        primaryStage.setScene(new Scene(root, 500, 600)); 
        // Make window non-resizable
        primaryStage.setResizable(false);
        // Show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
