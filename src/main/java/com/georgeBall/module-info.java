module com.georgeBall {
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;
    requires javafx.controls;

    opens com.georgeBall.controller to javafx.fxml;

    exports com.georgeBall;
    exports com.georgeBall.controller;
    exports com.georgeBall.model;
    exports com.georgeBall.service;
    exports com.georgeBall.dao;
}
