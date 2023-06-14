package com.example.tp_base.IHM;

import com.example.tp_base.DAO.DataBaseQueries;
import com.example.tp_base.DAO.UserQueries;
import com.example.tp_base.OO.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.util.Arrays;

public class ProfilPane extends BorderPane {
    public ProfilPane(int id ,String nom, String role, String email,String mdp){
        Label lb_profil = new Label("Profil");
        lb_profil.setFont(Font.font("Georgia", FontWeight.BOLD, 20));
        lb_profil.setStyle("-fx-text-fill: #0175A2;");

        Label lb_nom=new Label("Nom et Prénom");
        TextField tf_nom=new TextField(nom);
        tf_nom.setStyle("-fx-text-fill: #0175A2;-fx-font-family: Georgia");

        Label lb_role=new Label("Role");
        Label _role = new Label(role);
        _role.setStyle("-fx-text-fill: #0175A2;-fx-font-family: Georgia");


        Label lb_mail = new Label("Adresse Email");
        TextField tf_mail = new TextField(email);
        tf_mail.setStyle("-fx-text-fill: #0175A2;-fx-font-family: Georgia");

        Label lb_mdp= new Label("Mot de passe");
        PasswordField tf_mdp = new PasswordField();
        tf_mdp.setText(mdp);


        for (Label lb : Arrays.asList(lb_nom,lb_mail,lb_role,lb_mdp)) {
            lb.setStyle("-fx-text-fill: #656f8e;");
            lb.setFont(Font.font("Georgia", FontWeight.MEDIUM, 15));
        }



        Button btn_modifier=new Button("Modifier");
        String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\edit.png";
        File file = new File(imagePath);
        Image image = new Image(file.toURI().toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(16);
        imageV.setFitHeight(16);

        btn_modifier.setGraphic(imageV);
        btn_modifier.setPadding(new Insets(5));
        btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   ");
        HBox hb_btn = new HBox(btn_modifier);
        hb_btn.setAlignment(Pos.CENTER);
        hb_btn.setPadding(new Insets(30));

        VBox vb = new VBox(lb_nom,tf_nom,lb_mail,tf_mail,lb_mdp,tf_mdp,lb_role,_role,hb_btn);
        vb.setSpacing(10);
        vb.setPadding(new Insets(20));
        vb.setMaxWidth(300);

        this.setCenter(vb);
        this.setTop(lb_profil);
        this.setPadding(new Insets(20));

        btn_modifier.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\edit (1).png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_modifier.setGraphic(imageV);
                btn_modifier.setStyle("-fx-background-color: white; -fx-text-fill: #0175A2; -fx-font-size: 13px;  "); // Change la couleur du fond en blanc
            }
        });

        btn_modifier.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String imagePath = "C:\\Users\\asus\\Documents\\Sem2\\POO 2\\ProjetV2\\src\\main\\resources\\edit.png";
                File file = new File(imagePath);
                Image image = new Image(file.toURI().toString());
                ImageView imageV = new ImageView(image);
                imageV.setFitHeight(16);
                imageV.setFitHeight(16);
                btn_modifier.setGraphic(imageV);
                btn_modifier.setStyle("-fx-background-color: #0175A2; -fx-text-fill: white; -fx-font-size: 13px;   "); // Change la couleur du fond en bleu
            }
        });
        DataBaseQueries dataBaseQueries=new DataBaseQueries();
        UserQueries userQueries=new UserQueries(dataBaseQueries.getConnection());
        btn_modifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                User user =new User(id,tf_nom.getText(),tf_mdp.getText(),tf_mail.getText(),role);
                Boolean test=userQueries.updateUser(user);
                if(test){
                    System.out.println("User updated  "+user.toString());
                }

            }
        });

    }
}
