module com.example.phonebook1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.phonebook1 to javafx.fxml;
    exports com.example.phonebook1;
}