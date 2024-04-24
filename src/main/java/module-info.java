module dclvs.gui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens dclvs.gui to javafx.fxml;
    exports dclvs.gui;
    exports testApps.helloWorld;
    opens testApps.helloWorld to javafx.fxml;
    exports testApps.oldCalculatorGUI;
    opens testApps.oldCalculatorGUI to javafx.fxml;
}