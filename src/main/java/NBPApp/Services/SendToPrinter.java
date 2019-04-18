package NBPApp.Services;

import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SendToPrinter {

    InfoAboutCurrencyServices services = new InfoAboutCurrencyServices();

    public void print(TextFlow textFlow) {

        TextFlow printArea = new TextFlow(new Text(services.getTextFromTextFlow(textFlow)));
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        Alert alert = new Alert(Alert.AlertType.NONE, "Polecenie druku zostało wysłane do drukarki");
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.setTitle("Informacja");

        if (printerJob != null) {
            PageLayout pageLayout = printerJob.getJobSettings().getPageLayout();
            printArea.setMaxHeight(pageLayout.getPrintableWidth());
            alert.show();
        }

        if (printerJob.printPage(printArea)) {
            printerJob.endJob();
        }
    }
}
