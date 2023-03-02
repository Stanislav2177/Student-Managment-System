module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens StudentInfo to javafx.base;
    opens com.example.demo to javafx.fxml;
    opens images to javafx.base;

    exports com.example.demo;
}