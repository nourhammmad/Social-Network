module com.example.testingg {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
//    requires testng;
    opens com.example.testingg to javafx.fxml;
    exports com.example.testingg;
}