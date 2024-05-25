package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundUrl[] = new URL[30];

    public Sound() {
        soundUrl[0] = getClass().getResource("../assets/sound/BlueBoyAdventure.wav");
        soundUrl[1] = getClass().getResource("../assets/sound/coin.wav");
        soundUrl[2] = getClass().getResource("../assets/sound/powerup.wav");
        soundUrl[3] = getClass().getResource("../assets/sound/unlock.wav");
        soundUrl[4] = getClass().getResource("../assets/sound/fanfare.wav");
        soundUrl[5] = getClass().getResource("../assets/sound/hitmonster.wav");
        soundUrl[6] = getClass().getResource("../assets/sound/receivedamage.wav");
        soundUrl[7] = getClass().getResource("../assets/sound/swingweapon.wav");
    };

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
