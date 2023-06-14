package com.example.projet.IHM;

import com.example.projet.DAO.UserQueries;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGrid extends GridPane {
    public TextField tfusername;
    public PasswordField passwordField;
    public Label statusLabel;
    public Button loginButton;

    public LoginGrid(UserQueries uq) {
        // Création des éléments de l'interface
        Label titleLabel = new Label("Connexion");
        titleLabel.setFont(new Font("Georgia", 24));
        titleLabel.setTextFill(Color.web("#656f8e"));

        ImageView userIcon = new ImageView(new Image("C:\\Users\\WSI\\Downloads\\user.png"));
        userIcon.setFitHeight(30);
        userIcon.setFitWidth(30);

        tfusername = new TextField();
        tfusername.setPromptText("Nom d'utilisateur");
        tfusername.setPrefWidth(200);

        HBox usernameBox = new HBox(10, userIcon, tfusername);

        ImageView lockIcon = new ImageView(new Image("C:\\Users\\WSI\\Downloads\\mdp.png"));
        lockIcon.setFitHeight(28);
        lockIcon.setFitWidth(28);

        passwordField = new PasswordField();
        passwordField.setPromptText("Mot de passe");
        passwordField.setPrefWidth(200);

        HBox passwordBox = new HBox(10, lockIcon, passwordField);

        loginButton = new Button("Se connecter");
        loginButton.setTextFill(Color.WHITE);
        loginButton.setBackground(new Background(new BackgroundFill(Color.web("#656f8e"), null, null)));
        loginButton.setOnAction(event -> {
            // Action du bouton de connexion
                handleConnectButtonClick(event, uq);
        });

        statusLabel = new Label();
        statusLabel.setTextFill(Color.RED);

        // Création de la grille pour disposer les éléments

        this.setPadding(new Insets(30));
        this.setVgap(10);
        this.setHgap(10);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.web("#EBE8DD"), null, null)));
        this.add(titleLabel, 1, 0, 3, 1);
        this.add(usernameBox, 0, 1, 2, 1);
        this.add(passwordBox, 0, 2, 2, 1);
        this.add(loginButton, 1, 3);
        this.add(statusLabel, 1, 4);
    }

    private void handleConnectButtonClick(ActionEvent event, UserQueries userQueries) {
        String nom = tfusername.getText().toString().trim();
        String mdp = passwordField.getText().toString().trim();

        try {
            ResultSet rs = userQueries.getUserByUsernameAndPassword(nom, mdp);

            if (rs.next()) {
                // utilisateur trouvé
                int id = rs.getInt("id");
                String role = rs.getString("role");
                String email=rs.getString("mail");
                String mpd=rs.getString("mdp");


                WelcomeGrid welcomeGrid = new WelcomeGrid(id, nom ,  role,email,mpd);
                Scene newScene = new Scene(welcomeGrid, 1000, 600);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Gestion du Stock");
                stage.setScene(newScene);

                // fermer la fenetre de connexion
                Stage currentStage = (Stage) this.getScene().getWindow();
                currentStage.close();
            } else {
                // utilisateur non trouvé
                statusLabel.setText("Nom d'utilisateur ou mot de passe incorrect");
            }
        } catch (SQLException e) {
            // handle the exception (e.g. log it or show an error message to the user)
            e.printStackTrace();
        }

    }
}