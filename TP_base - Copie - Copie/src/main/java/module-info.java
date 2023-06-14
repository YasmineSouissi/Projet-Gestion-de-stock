module com.example.tp_base {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;

    opens com.example.tp_base to javafx.fxml;
    exports com.example.tp_base;
    exports com.example.tp_base.DAO;
    opens com.example.tp_base.DAO to javafx.fxml;
    exports com.example.tp_base.IHM;
    opens com.example.tp_base.IHM to javafx.fxml;
    opens com.example.tp_base.OO to javafx.base;

}