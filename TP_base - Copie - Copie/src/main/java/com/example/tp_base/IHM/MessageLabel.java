package com.example.tp_base.IHM;

import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class MessageLabel extends Label {

    public MessageLabel(String text, boolean sentByMe) {
        super(text);
        this.setWrapText(true);
        this.setPadding(new Insets(5));
        this.setMaxWidth(400);

        if (sentByMe) {
            this.setStyle("-fx-background-color: #DCF8C6; -fx-background-radius: 10; -fx-padding: 5px;");
        } else {
            this.setStyle("-fx-background-color: #F6F6F6; -fx-background-radius: 10; -fx-padding: 5px;");
        }
    }
}
