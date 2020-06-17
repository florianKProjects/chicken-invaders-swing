package src.com.chickenInavaders;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class SoundPlayer {
    public static void play(String fileName) {
        try {
            InputStream in = new FileInputStream(fileName);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
