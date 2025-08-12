package service;

import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {
    public void playMusic(String filepath) {
        try {
            File file = new File("resources/" + filepath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Continuous play
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
