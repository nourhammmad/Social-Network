module com.example.testingg {
    requires javafx.controls;
    requires javafx.fxml;
//    requires org.junit.jupiter.api;
    requires junit;
//    requires testng;
    opens com.example.testingg to javafx.fxml;
    exports com.example.testingg;
}