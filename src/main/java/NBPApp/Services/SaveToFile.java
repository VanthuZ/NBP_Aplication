package NBPApp.Services;

import NBPApp.Controller.InfoAboutCurrencyController;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {



    public String getTextFromTextFlow(TextFlow textFlow){
        StringBuilder stringBuilder = new StringBuilder();
        textFlow.getChildren().stream()
                .filter(t -> Text.class.equals(t.getClass()))
                .forEach(t -> stringBuilder.append(((Text) t).getText() + String.format("%n")));
        return  stringBuilder.toString();
    }

    public void SaveFile(String content, File file){
        FileWriter fileWriter;

        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
