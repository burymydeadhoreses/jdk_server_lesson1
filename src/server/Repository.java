package server;

import server.interfaces.IRepository;

import java.io.FileReader;
import java.io.FileWriter;

public class Repository implements IRepository {
    public String LOG_PATH;

    public Repository(String filePath) {
        LOG_PATH = filePath;
    }

    public void appendLog(String text){
        try (FileWriter writer = new FileWriter(LOG_PATH, true)){
            writer.write(text);
            writer.write("\n");
        } catch (Exception e){
            e.fillInStackTrace();
        }
    }

    public String readLog(){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)){
            int c;
            while ((c = reader.read()) != -1){
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e){
            e.fillInStackTrace();
            return null;
        }
    }
}
