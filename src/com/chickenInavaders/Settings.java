package src.com.chickenInavaders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Settings {
    private  int soundVolume;
    private HashMap<String, String> keyboardLayoutPlayer1 ;
    private  HashMap<String, String> keyboardLayoutPlayer2 ;
    private JSONParser parser;
    private Object obj;
    private JSONObject jsonObject;
    public Settings(){
        init();
    }

    private void init(){
        keyboardLayoutPlayer1 = new HashMap<String, String>();
        keyboardLayoutPlayer2 = new HashMap<String, String>();
        parser = new JSONParser();
        try {
             obj = parser.parse(new FileReader(Commons.SETTINGS_FILE));
            jsonObject = (JSONObject) obj;
             loadSettings();
            }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSettings(){
        try {
            // read the sound_volume from json
            long obj1 = (long) jsonObject.get("sound_volume");
            soundVolume =(int)obj1;

            // read the Control for each Player
            JSONObject obj = (JSONObject)jsonObject.get("controls");

            JSONObject KetList = (JSONObject) obj.get("player_1");
            JSONObject KeyList2 = (JSONObject) obj.get("player_2");

            KetList.forEach((k,v)  ->{
                keyboardLayoutPlayer1.put((String)k,(String)v);
            });
            KeyList2.forEach((k,v)  ->{
                keyboardLayoutPlayer2.put((String)k,(String)v);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void saveSettings(){
        //save the need json to the settings file
        jsonObject.put("sound_volume",soundVolume);
        try (FileWriter file = new FileWriter(Commons.SETTINGS_FILE))
        {
            file.write(jsonObject.toString());
            if (Commons.IS_DEBUG)
                System.out.println("Successfully updated json Settings file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getSoundVolume()
    {
        return soundVolume;
    }
    public void setSoundVolume(int num)
    {
        soundVolume=num;
    }
    public String getKeyboardLayoutPlayer1(String Key){
        return keyboardLayoutPlayer1.get(Key);
    }

    public String getKeyboardLayoutPlayer2(String Key){
        return keyboardLayoutPlayer2.get(Key);
    }
    public void setKeyPlayer1(String Key,String value)
    {
        keyboardLayoutPlayer1.replace(Key,value);
        ((JSONObject)((JSONObject)jsonObject.get("controls")).get("player_1")).put(Key,value);
    }

    public void setKeyPlayer2(String Key,String value)
    {
        keyboardLayoutPlayer2.replace(Key,value);
        ((JSONObject)((JSONObject)jsonObject.get("controls")).get("player_2")).put(Key,value);
    }
}
/*
 public static void main(String[] args) {
        Settings mainFrame = new Settings();
    }

 */
