package NBPApp.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {

    public void saveFile(String content, File file){
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
