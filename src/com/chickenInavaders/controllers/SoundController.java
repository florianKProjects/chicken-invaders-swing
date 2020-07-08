package src.com.chickenInavaders.controllers;

import src.com.chickenInavaders.Commons;
import src.com.chickenInavaders.controllers.settings.SettingsController;
import javax.sound.sampled.*;
import java.io.File;

public class SoundController {
    public static void play(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - (-12.0f);
            float gain = Math.abs(range * SettingsController.getInstance().getSoundVolume()/100) + (-12.0f);
            gainControl.setValue(gain);
            clip.start();
        } catch (Exception e) {
            if (Commons.IS_DEBUG)
                System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
