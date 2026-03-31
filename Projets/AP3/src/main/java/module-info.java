module fr.riot.ap {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires se.alipsa.ymp;
    requires jbcrypt;
    requires org.apache.commons.lang3;

    opens fr.riot.ap to javafx.fxml;
    opens fr.riot.ap.controller to javafx.fxml;
    opens fr.riot.ap.controls to javafx.fxml;
    opens fr.riot.ap.model to javafx.fxml;

    exports fr.riot.ap;
    exports fr.riot.ap.controls;
    exports fr.riot.ap.model;
}
