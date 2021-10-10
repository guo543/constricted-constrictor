package edu.purdue;

import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;

public class MusicClip implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                FileInputStream fis = new FileInputStream(new File("music/CGT_BGM.mp3").getAbsolutePath());
                Player player = new Player(fis);
                player.play();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
