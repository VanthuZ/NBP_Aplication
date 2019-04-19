package NBPApp.Services;

import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.PageRange;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SendToPrinter {

    InfoAboutCurrencyServices services = new InfoAboutCurrencyServices();

    public void print(TextFlow textFlow) {

        Text printArea = new Text(services.getTextFromTextFlow(textFlow));
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        Font font = new Font("Monospaced Regular", 12.0);
        printArea.setFont(font);

        printerJob.getJobSettings().setPageRanges(new PageRange(1, 1));
        printerJob.showPrintDialog(null);
        printerJob.printPage(printArea);
        printerJob.endJob();

    }
}

