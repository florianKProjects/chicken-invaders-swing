package src.com.chickenInavaders.controllers;

import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.controllers.settings.SettingsController;
import javax.sound.sampled.*;
import java.io.File;

public class SoundController {

    private static float MIN_VOLUME = -12.0f;
    private static float INF_VOLUME = -80.0f;


    public static void play(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - (MIN_VOLUME);
            float gain = Math.abs(range * SettingsController.getInstance().getSoundVolume()/100) + (MIN_VOLUME);
            gainControl.setValue(SettingsController.getInstance().getSoundVolume() == 0 ? INF_VOLUME : gain);
            clip.start();
        } catch (Exception e) {
            if (Commons.IS_DEBUG)
                System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
