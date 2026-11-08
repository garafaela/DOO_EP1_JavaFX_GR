module ga.rafaela.doo_ep1_javafx_gr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;
    requires com.google.gson;

    opens ga.rafaela.doo_ep1_javafx_gr to javafx.fxml;
    opens ga.rafaela.doo_ep1_javafx_gr.controller to javafx.fxml;
    opens ga.rafaela.doo_ep1_javafx_gr.model to com.google.gson;
    opens ga.rafaela.doo_ep1_javafx_gr.dao to com.google.gson;

    exports ga.rafaela.doo_ep1_javafx_gr;
}