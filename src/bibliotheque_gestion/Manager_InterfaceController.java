package bibliotheque_gestion;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Manager_InterfaceController implements Initializable{

    @FXML
    private AnchorPane book_panel;

   

   

    @FXML
    private AnchorPane subscriber_panel;

    @FXML
    private Button add_subscriber_btn;

    @FXML
    private Button add_author_btn;

    @FXML
    private Button add_btn;

    @FXML
    private Button add_lend_btn;

    @FXML
    private AnchorPane author_panel;

    @FXML
    private Button auteurs_btn;

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private Button clear_subscriber_btn;

    @FXML
    private Button clear_btn;

    @FXML
    private Button clear_res_btn;

    @FXML
    private Label dash_nb_subscribers;

    @FXML
    private Label dash_nb_authors;

    @FXML
    private Label dash_nb_styles;

    @FXML
    private Label dash_nb_books;

    @FXML
    private Label dash_nb_lend_finished;

    @FXML
    private Label dash_nb_lends;

   

    @FXML
    private AnchorPane home_panel;

    @FXML
    private Button delete_subscriber_btn;

    @FXML
    private Button delete_author_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private Button delete_lend_btn;

    
    @FXML
    private Button log_out_btn;

    

    @FXML
    private AnchorPane panel1;

    @FXML
    private AnchorPane panel2;

   
    

    @FXML
    private Button quit_btn;

   

    @FXML
    private AnchorPane lend_panel;

    @FXML
    private TableView<Abonne> subscribers_table;

    @FXML
    private TableView<Auteur> authors_table;

    @FXML
    private TableView<Livre> book_table;

    @FXML
    private TableView<Reservation> lends_table;

    @FXML
    private TextArea text_description;

    @FXML
    private TextField txt_author_id;

    @FXML
    private TextField txt_author_name;

    @FXML
    private DatePicker txt_date_lend;

    @FXML
    private DatePicker txt_date_finished_lend;

    @FXML
    private TextField txt_subscriber_email;

    @FXML
    private TextField txt_style;

    @FXML
    private TextField txt_id_subscriber;

    @FXML
    private TextField txt_id_subsc_lend;

    @FXML
    private TextField txt_id_author_edit;

    @FXML
    private TextField txt_id_lend;

    @FXML
    private TextField txt_id_book;

    @FXML
    private TextField txt_id_book_lend;


    @FXML
    private TextField txt_title;

    @FXML
    private TextField txt_subscriber_name;

    @FXML
    private Button update_subscriber_btn;

    @FXML
    private Button update_author_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Button update_lend_btn;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;
    
    
    


    
    LivreService livreDAO = new LivreService();
    AuteurService auteurDAO = new AuteurService();
    AbonneService abonneDAO = new AbonneService();
    ReservationService reservationDAO = new ReservationService();
    
    
    
    private void remplirTableViewAvecLivres() {
        
        
        ArrayList<Livre> livres = livreDAO.getLivres();
        TableColumn<Livre, Integer> id_col = new TableColumn<>("ID Book");
        TableColumn<Livre, String> titre_col = new TableColumn<>("Title");
            TableColumn<Livre, Integer> id_auteur_col = new TableColumn<>("ID Author");
            TableColumn<Livre, String> description_col = new TableColumn<>("Description");
            TableColumn<Livre, String> genre_col = new TableColumn<>("Style");

            id_col.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));
            id_auteur_col.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
            description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            genre_col.setCellValueFactory(new PropertyValueFactory<>("genre"));
            book_table.getColumns().clear();
            book_table.getColumns().addAll(id_col, titre_col, id_auteur_col, description_col, genre_col);
            book_table.getItems().clear();
            book_table.getItems().addAll(livres);
    }
    
    private void remplirTableViewAvecAuteurs() {
        ArrayList<Auteur> auteurs = auteurDAO.getAuteurs();
        
        TableColumn<Auteur, Integer> id_col = new TableColumn<>("ID Author");
        TableColumn<Auteur, String> nom_col = new TableColumn<>("Author Name");
        
        id_col.setCellValueFactory(new PropertyValueFactory<>("id_auteur"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("auteur_name"));
        authors_table.getColumns().clear();
        authors_table.getColumns().addAll(id_col, nom_col);
        authors_table.getItems().clear();
        authors_table.getItems().addAll(auteurs);
    }
    
    private void remplirTableViewAvecAbonnes() {
        ArrayList<Abonne> abonnes = abonneDAO.getAbonnees();
    
        TableColumn<Abonne, Integer> id_col = new TableColumn<>("ID Subscriber");
        TableColumn<Abonne, String> email_col = new TableColumn<>("Email");
        TableColumn<Abonne, String> username_col = new TableColumn<>("Subscriber Name");

        id_col.setCellValueFactory(new PropertyValueFactory<>("id_abonne"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        username_col.setCellValueFactory(new PropertyValueFactory<>("username"));

        subscribers_table.getColumns().clear();
        subscribers_table.getColumns().addAll(id_col, email_col, username_col);
        subscribers_table.getItems().clear();
        subscribers_table.getItems().addAll(abonnes);
    }
    
    private void remplirTableViewAvecReservations() {
        ArrayList<Reservation> reservations = reservationDAO.getReservations();
    
        TableColumn<Reservation, Integer> idReservationCol = new TableColumn<>("ID Lend");
        TableColumn<Reservation, Integer> idAbonneCol = new TableColumn<>("ID Subscriber");
        TableColumn<Reservation, Integer> isbnCol = new TableColumn<>("ID Book");
        TableColumn<Reservation, LocalDate> dateReservationCol = new TableColumn<>("Date réservation");
        TableColumn<Reservation, LocalDate> dateRetourCol = new TableColumn<>("Date retour");
    
        idReservationCol.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        idAbonneCol.setCellValueFactory(new PropertyValueFactory<>("id_abonne"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        dateReservationCol.setCellValueFactory(new PropertyValueFactory<>("date_reservation"));
        dateRetourCol.setCellValueFactory(new PropertyValueFactory<>("date_retour"));
    
        lends_table.getColumns().clear();
        lends_table.getColumns().addAll(idReservationCol, idAbonneCol, isbnCol, dateReservationCol, dateRetourCol);
        lends_table.getItems().clear();
        lends_table.getItems().addAll(reservations);
    }


    @FXML
    void add_book(MouseEvent event) {
        ajouterLivre();

    }
    @FXML
    void show_book(MouseEvent event) {
        Livre selectedLivre = book_table.getSelectionModel().getSelectedItem();
        if (selectedLivre != null) {
            int isbn = selectedLivre.getIsbn();
            String titre = selectedLivre.getTitre();
            int idAuteur = selectedLivre.getId_auteur();
            String description = selectedLivre.getDescription();
            String genre = selectedLivre.getGenre();
    
            txt_id_book.setText(String.valueOf(isbn));
            txt_title.setText(titre);
            txt_author_id.setText(String.valueOf(idAuteur));
            txt_style.setText(genre);
            text_description.setText(description);
            
        }


    }
    @FXML
    void delete_book(MouseEvent event) {
        String titre = txt_title.getText();
        if (!titre.isEmpty()) {
            livreDAO.deleteLivreByTitre(titre);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Livre supprimé avec succès !");
            alert.showAndWait();
            
            
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir le titre du livre");
            alert.showAndWait();
        }
        
        remplirTableViewAvecLivres();

    }

    @FXML
    void quit_app(MouseEvent event) {
        System.exit(0);

    }
    @FXML
    void clear_book(MouseEvent event) {
         txt_id_book.clear();
        txt_title.clear();
        txt_author_id.clear();
        txt_style.clear();
        text_description.clear();

    }
   
    
    @FXML
    void update_book(MouseEvent event) {
        String isbnText = txt_id_book.getText();
        String titre = txt_title.getText();
        String auteurIdText = txt_author_id.getText();
        String genre = txt_style.getText();
        String description = text_description.getText();

        if (isbnText.isEmpty() || titre.isEmpty() || auteurIdText.isEmpty() || genre.isEmpty() || description.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; 
        }

        int isbn;
        int auteurId;
        try {
            isbn = Integer.parseInt(isbnText);
            auteurId = Integer.parseInt(auteurIdText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ISBN et ID Auteur doivent être des nombres entiers.");
            alert.showAndWait();
            return; 
        }

        Livre livre = new Livre(isbn, titre, auteurId, description, genre);

        livreDAO.updateLivre(livre);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Livre mis à jour avec succès.");
        alert.showAndWait();

        txt_id_book.clear();
        txt_title.clear();
        txt_author_id.clear();
        txt_style.clear();
        text_description.clear();
        remplirTableViewAvecLivres();

    }
    
    
    
   
    
    
    

    @FXML
    void show_authors_panel(MouseEvent event) {
        book_panel.setVisible(false);
        author_panel.setVisible(true);
        subscriber_panel.setVisible(false);
        lend_panel.setVisible(false);
        home_panel.setVisible(false);
    }
    
     @FXML
     void show_book_panel(MouseEvent event) {
        book_panel.setVisible(true);
        author_panel.setVisible(false);
        subscriber_panel.setVisible(false);
        lend_panel.setVisible(false);
        home_panel.setVisible(false);
        
    }
    @FXML
      void show_subscriber_panel(MouseEvent event) {
        subscriber_panel.setVisible(true);
        author_panel.setVisible(false);
        book_panel.setVisible(false);
        lend_panel.setVisible(false);
        home_panel.setVisible(false);

    }
    @FXML
     void show_lend_panel(MouseEvent event) {
        subscriber_panel.setVisible(false);
        author_panel.setVisible(false);
        book_panel.setVisible(false);
        lend_panel.setVisible(true);
        home_panel.setVisible(false);
        

    }
    @FXML
    void show_home(MouseEvent event) {
        home_panel.setVisible(true);
        subscriber_panel.setVisible(false);
        author_panel.setVisible(false);
        book_panel.setVisible(false);
        lend_panel.setVisible(false);
        dashboard_view();

    }

    
    

    
    
    private void ajouterLivre() {
    if (!txt_title.getText().isEmpty() || !txt_author_id.getText().isEmpty() || !text_description.getText().isEmpty() || !txt_style.getText().isEmpty()) {
         int isbn=0;   
        if(!txt_id_book.getText().isEmpty()){
                 isbn = Integer.parseInt(txt_id_book.getText());
            }
            
            String titre = txt_title.getText();
            int idAuteur = Integer.parseInt(txt_author_id.getText());
            String description = text_description.getText();
            String genre = txt_style.getText();
            Livre nouveauLivre = new Livre();
            if(isbn==0){
                 nouveauLivre = new Livre( titre, idAuteur, description, genre);
                
            }
            else{
                 nouveauLivre = new Livre(isbn, titre, idAuteur, description, genre);
            }
            
            livreDAO.addLivre(nouveauLivre);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Livre ajouté avec succès !");
            alert.showAndWait();
            txt_id_book.clear();
            txt_style.clear();
            txt_title.clear();
            txt_author_id.clear();
            text_description.clear();
            remplirTableViewAvecLivres();
            
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Livre non ajouté, verifier vos inputs");
            alert.showAndWait();
        }
    }
    private void ajouterAuteur() {
        if (!txt_author_name.getText().isEmpty()) {
            int idAuteur = Integer.parseInt(txt_id_author_edit.getText());
            String nomAuteur = txt_author_name.getText();

            Auteur nouvelAuteur = new Auteur(idAuteur, nomAuteur);
            auteurDAO.addAuteur(nouvelAuteur);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Auteur ajouté avec succès !");
            alert.showAndWait();

            txt_id_author_edit.clear();
            txt_author_name.clear();
            remplirTableViewAvecAuteurs();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Auteur non ajouté, veuillez vérifier vos entrées !");
            alert.showAndWait();
        }
    }
    @FXML
     void add_author(MouseEvent event) {
        ajouterAuteur();

    }
     @FXML
    void show_author(MouseEvent event) {
         Auteur selectedAuteur = authors_table.getSelectionModel().getSelectedItem();
        if (selectedAuteur != null) {
            int idAuteur = selectedAuteur.getId_auteur();
            String nomAuteur = selectedAuteur.getAuteur_name();

            txt_id_author_edit.setText(String.valueOf(idAuteur));
            txt_author_name.setText(nomAuteur);
        }

    }
    @FXML
    void delete_author(MouseEvent event) {
        String idAuteurText = txt_id_author_edit.getText();
        if (!idAuteurText.isEmpty()) {
            int idAuteur = Integer.parseInt(idAuteurText);
            auteurDAO.deleteAuteur(idAuteur);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Auteur supprimé avec succès !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir l'ID de l'auteur à supprimer.");
            alert.showAndWait();
        }
    
        remplirTableViewAvecAuteurs();
    

    }
    @FXML
    void update_author(MouseEvent event) {
         String idAuteurText = txt_id_author_edit.getText();
         String nomAuteur = txt_author_name.getText();

        if (idAuteurText.isEmpty() || nomAuteur.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; 
        }

        int idAuteur;
        try {
            idAuteur = Integer.parseInt(idAuteurText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le champ ID Auteur doit être un nombre entier.");
            alert.showAndWait();
            return; 
        }

        Auteur auteur = new Auteur(idAuteur, nomAuteur);

        auteurDAO.updateAuteur(auteur);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Auteur mis à jour avec succès.");
        alert.showAndWait();

        txt_id_author_edit.clear();
        txt_author_name.clear();
        remplirTableViewAvecAuteurs();
        

    }
   
    
    private void ajouterAbonne() {
        if (!txt_subscriber_email.getText().isEmpty() && !txt_subscriber_name.getText().isEmpty()) {
            String email = txt_subscriber_email.getText();
            String username = txt_subscriber_name.getText();

            Abonne nouvelAbonne = new Abonne(email, username);
            abonneDAO.addAbonne(nouvelAbonne);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Abonné ajouté avec succès !");
            alert.showAndWait();

            txt_subscriber_email.clear();
            txt_subscriber_name.clear();
            remplirTableViewAvecAbonnes();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Abonné non ajouté, veuillez vérifier vos entrées !");
            alert.showAndWait();
        }
    }

    
    @FXML
    void add_subscriber(MouseEvent event) {
        ajouterAbonne();
    }
    @FXML
    void show_subscriber(MouseEvent event) {
        Abonne selectedAbonne = subscribers_table.getSelectionModel().getSelectedItem();
        if (selectedAbonne != null) {
            int idAbonne = selectedAbonne.getId_abonne();
            String email = selectedAbonne.getEmail();
            String username = selectedAbonne.getUsername();

            txt_id_subscriber.setText(String.valueOf(idAbonne));
            txt_subscriber_email.setText(email);
            txt_subscriber_name.setText(username);
        }

    }
    @FXML
    void clear_subscriber(MouseEvent event){
        txt_id_subscriber.clear();
        txt_subscriber_email.clear();
        txt_subscriber_name.clear();
        
        
    }
    
    @FXML
    void delete_subscriber(MouseEvent event) {
         String idAbonneText = txt_id_subscriber.getText();
        if (!idAbonneText.isEmpty()) {
            int idAbonne = Integer.parseInt(idAbonneText);
            abonneDAO.deleteAbonne(idAbonne);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Abonné supprimé avec succès !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir l'ID de l'abonné à supprimer.");
            alert.showAndWait();
        }

        remplirTableViewAvecAbonnes();
         

    }
   
    @FXML
    void update_subscriber(MouseEvent event) {
        String idAbonneText = txt_id_subscriber.getText();
        String email = txt_subscriber_email.getText();
        String username = txt_subscriber_name.getText();

        if (idAbonneText.isEmpty() || email.isEmpty() || username.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; 
        }

        int idAbonne;
        try {
            idAbonne = Integer.parseInt(idAbonneText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Le champ ID Abonné doit être un nombre entier.");
            alert.showAndWait();
            return; 
        }

        Abonne abonne = new Abonne(idAbonne, email, username);

        abonneDAO.updateAbonne(abonne);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Abonné mis à jour avec succès.");
        alert.showAndWait();

        txt_id_subscriber.clear();
        txt_subscriber_email.clear();
        txt_subscriber_name.clear();
        remplirTableViewAvecAbonnes();

    }
    
    
    
    @FXML
    void show_lend(MouseEvent event) {
         Reservation selectedReservation = lends_table.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            int idReservation = selectedReservation.getId_reservation();
            int idAbonne = selectedReservation.getId_abonne();
            int isbn = selectedReservation.getIsbn();
            LocalDate dateReservation = selectedReservation.getDate_reservation();
            LocalDate dateRetour = selectedReservation.getDate_retour();

            txt_id_lend.setText(String.valueOf(idReservation));
            txt_id_subsc_lend.setText(String.valueOf(idAbonne));
            txt_id_book_lend.setText(String.valueOf(isbn));
            txt_date_lend.setValue(dateReservation);
            txt_date_finished_lend.setValue(dateRetour);
        }

    }
    @FXML
     void clear_lend(MouseEvent event) {
        txt_id_lend.clear();
        txt_id_subsc_lend.clear();
        txt_id_book_lend.clear();
        txt_date_lend.setValue(null);
        txt_date_finished_lend.setValue(null);

    }
    
    
    private void ajouterReservation() {
    if (!txt_id_subsc_lend.getText().isEmpty() && !txt_id_book_lend.getText().isEmpty() && !txt_date_lend.getValue().toString().isEmpty() && !txt_date_finished_lend.getValue().toString().isEmpty()) {
        int idAbonne = Integer.parseInt(txt_id_subsc_lend.getText());
        int isbn = Integer.parseInt(txt_id_book_lend.getText());
        LocalDate dateReservation = txt_date_lend.getValue();
        LocalDate dateRetour = txt_date_finished_lend.getValue();

        Reservation nouvelleReservation = new Reservation(idAbonne, isbn, dateReservation, dateRetour);
        reservationDAO.addReservation(nouvelleReservation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Réservation ajoutée avec succès !");
        alert.showAndWait();

        txt_id_subsc_lend.clear();
        txt_id_book_lend.clear();
        txt_date_lend.setValue(null);
        txt_date_finished_lend.setValue(null);
        remplirTableViewAvecReservations();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Réservation non ajoutée, veuillez vérifier vos entrées !");
            alert.showAndWait();
        }
    }

    
    
    
    
   
    @FXML
    void add_lend(MouseEvent event) {
        ajouterReservation();

    }
    
    @FXML
     void delete_lend(MouseEvent event) {
        String idReservationText = txt_id_lend.getText();
        if (!idReservationText.isEmpty()) {
            int idReservation = Integer.parseInt(idReservationText);
            reservationDAO.deleteReservation(idReservation);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Réservation supprimée avec succès !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Échec");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir l'ID de la réservation à supprimer.");
            alert.showAndWait();
        }

        remplirTableViewAvecReservations();

    }
    
    
    
    
    
   
    
    
    @FXML
     void update_lend(MouseEvent event) {
        String idReservationText = txt_id_lend.getText();
        String idAbonneText = txt_id_subsc_lend.getText();
        String isbnText = txt_id_book_lend.getText();
        LocalDate dateReservation = txt_date_lend.getValue();
        LocalDate dateRetour = txt_date_finished_lend.getValue();

        if (idReservationText.isEmpty() || idAbonneText.isEmpty() || isbnText.isEmpty() || dateReservation == null || dateRetour == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.showAndWait();
            return; 
        }

        int idReservation;
        int idAbonne;
        int isbn;
        try {
            idReservation = Integer.parseInt(idReservationText);
            idAbonne = Integer.parseInt(idAbonneText);
            isbn = Integer.parseInt(isbnText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("Les champs ID Réservation, ID Abonné et ISBN doivent être des nombres entiers.");
            alert.showAndWait();
            return; 
        }

        Reservation reservation = new Reservation(idReservation, idAbonne, isbn, dateReservation, dateRetour);

        reservationDAO.updateReservation(reservation);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Réservation mise à jour avec succès.");
        alert.showAndWait();

        txt_id_lend.clear();
        txt_id_subsc_lend.clear();
        txt_id_book_lend.clear();
        txt_date_lend.setValue(null);
        txt_date_finished_lend.setValue(null);
        remplirTableViewAvecReservations();

    }
    
   
    
    @FXML
    void log_out(MouseEvent event) {
        
        System.exit(0);
      
    }
    
    public void dashboard_view(){
        dash_nb_books.setText(String.valueOf(livreDAO.getNombreLivres()));
        dash_nb_styles.setText(String.valueOf(livreDAO.getNombreGenres()));
        dash_nb_authors.setText(String.valueOf(auteurDAO.getNombreAuteurs()));
        dash_nb_subscribers.setText(String.valueOf(abonneDAO.getNombreAbonnes()));
        dash_nb_lends.setText(String.valueOf(reservationDAO.getNombreReservations()));
        dash_nb_lend_finished.setText(String.valueOf(reservationDAO.getNombreReservationsDateRetourInferieureAujourdhui()));
        afficherNombreLivresParAuteur();
        
        
                
        
        
        
    }
    
    
    
    
    
    private void afficherNombreLivresParAuteur() {
        Map<String, Integer> nombreLivresParAuteur = livreDAO.getNombreLivresParAuteur();

        XYChart.Series series = new XYChart.Series();
        series.setName("Nombre de livres par auteur");

        for (Map.Entry<String, Integer> entry : nombreLivresParAuteur.entrySet()) {
            series.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
        }

        barChart.getData().addAll(series);
    }
    



     
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remplirTableViewAvecLivres();
        remplirTableViewAvecAuteurs();
        remplirTableViewAvecAbonnes();
        remplirTableViewAvecReservations();
        dashboard_view();
        
        
        
        
        
        
    }
    
    
    
    

}
