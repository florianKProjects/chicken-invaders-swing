package src.com.chickenInavaders.controllers.settings;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import src.com.chickenInavaders.Commons;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class SaveReader {
    public HashMap<Integer, save> LoadsList;
    private JSONObject SaveFile;
    private int index;
    private LocalDateTime now;

    public SaveReader() {
        index = 0;
        LoadsList = new HashMap<Integer, save>();
        init();
    }

    private void init() {
        now = LocalDateTime.now();
        LoadSaveFile();
        // addRecord("test3",1,2);
    }

    private JSONArray LoadSaveFile() {
        JSONParser parser = new JSONParser();
        JSONArray companyList = new JSONArray();
        try {
            Object obj = parser.parse(new FileReader(Commons.LOAD_FILE));
            // A JSON object. Key value pairs are unordered. JSONObject supports
            // java.util.Map interface.
            SaveFile = (JSONObject) obj;
            // A JSON array. JSONObject supports java.util.List interface.
            index = (int) ((long) SaveFile.get("lastIndex"));
            companyList = (JSONArray) SaveFile.get("Saves");
            companyList.forEach(item -> {
                JSONObject obj2 = (JSONObject) item;
                LoadsList.put(Integer.parseInt(obj2.get("Id").toString()),
                        new save(Integer.parseInt(obj2.get("Id").toString()), obj2.get("Name").toString(),
                                Integer.parseInt(obj2.get("Score").toString()),
                                Integer.parseInt(obj2.get("Level").toString()), obj2.get("Date").toString()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public int addRecord(String name, int score, int level, String date) {
        LoadsList.put(++index, new save(index, name, score, level, date));
        SaveFile.put("lastIndex", index);
        JSONObject newSave = new JSONObject();
        newSave.put("Id", index);
        newSave.put("Name", name);
        newSave.put("Score", score);
        newSave.put("Level", level);
        newSave.put("Date", date);
        ((JSONArray) SaveFile.get("Saves")).add(newSave);
        try (FileWriter file = new FileWriter(Commons.SAVES_FILE)) {
            file.write(SaveFile.toString());
            if (Commons.IS_DEBUG)
                System.out.println("Successfully add new Save to json file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index;
    }

    public void addRecord(String name, int score, int level) {
        DateTimeFormatter gameDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        addRecord(name, score, level, gameDate.format(now));
    }

    public void updateRecord(int id, String updateField, Object newValue) {
        switch (updateField.toLowerCase()) {
            case "name":
                LoadsList.get(id).name = (String) newValue;
                break;
            case "score":
                LoadsList.get(id).score = (int) newValue;
                break;
            case "level":
                LoadsList.get(id).level = (int) newValue;
                break;
        }
        JSONArray companyList = (JSONArray) SaveFile.get("Saves");
        companyList.forEach(item -> {
            JSONObject obj2 = (JSONObject) item;
            if (Integer.parseInt(obj2.get("Id").toString()) == (id)) {
                obj2.put(updateField, newValue);
            }
        });
        SaveFile.put("Saves", companyList);
        try (FileWriter file = new FileWriter(Commons.SAVES_FILE)) {
            file.write(SaveFile.toString());
            if (Commons.IS_DEBUG)
                System.out.println("Successfully updated json Saves file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getLastIndex() {
        return index;
    }

    public static class save {
        public int id;
        public String name;
        public int score;
        public String date;
        public int level;

        public save(int id, String name, int score, int level, String date) {
            this.id = id;
            this.name = name;
            this.score = score;
            this.date = date;
            this.level = level;
        }

        @Override
        public String toString() {
            return String.format("{\"Id\":%d,\"Data\": %s,\"Level\" :%d,\"Id\" : %d,\"Name\" : %s},", id, date, level,
                    id, name);
        }
    }
    /*
     * result.put("Id", ++index); result.put("Name", name); result.put("Score",
     * score); result.put("Level", level); result.put("Data", date);
     *
     */
}
