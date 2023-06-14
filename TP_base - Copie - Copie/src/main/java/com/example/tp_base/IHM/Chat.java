package com.example.tp_base.IHM;

import com.example.tp_base.OO.User;
import com.example.tp_base.Socket.EnvoyerDemande;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class Chat extends BorderPane {
    private  TextArea ta_msg;
    private  Button btn_envoyer;
    private  HBox hb_msg;
    private  EnvoyerDemande envoi;

    public Chat(User user) {
        ta_msg = new TextArea();
        ta_msg.setMinWidth(250);

        btn_envoyer = new Button("Envoyer");
        btn_envoyer.setMinWidth(100);

        hb_msg= new HBox(ta_msg,btn_envoyer);
        hb_msg.setMinSize(100,400);
        hb_msg.setSpacing(20);
        hb_msg.setPadding(new Insets(20));






        envoi = new EnvoyerDemande();

        btn_envoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String msg = ta_msg.getText();
                System.out.println("ta_msg "+msg);
                envoi.envoyerMsg(msg);

            }
        });

        this.getChildren().add(hb_msg);
        this.setPadding(new Insets(20));
    }
}