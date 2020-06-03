package it.vareare.tetris.content.general;

import java.io.*;

public class Settings implements Serializable {


    /* -- SETTINGS -- */

    transient static public boolean fullscreen = false;

    transient static public int [][] resolutions = new int[][]{
            {
                    854, 480
            },
            {
                    1280, 720
            },
            {
                    1920, 1080-40
            },
            {
                    1920, 1080
            },
            {
                    3840, 2160
            }
    };

    transient static public       int difficulty = 15;
    transient static public final int DIFFICULTY_MAX = 30;
    transient static public final int DIFFICULTY_MIN = 0;
    transient static public       int style = 0;
    transient static public final int STYLE_MIN = 0;
    transient static public final int STYLE_MAX = 2;
    transient static public       int mode = 0;
    transient static public final int MODE_MIN = 0;
    transient static public final int MODE_MAX = 0;
    transient static public       int volume = 0;
    transient static public final int VOLUME_MIN = 0;
    transient static public final int VOLUME_MAX = 100;
    transient static public       int quality = 0;
    transient static public final int QUALITY_MIN = 0;
    transient static public final int QUALITY_MAX = 3;
    transient static public       int resolution = 1;
    transient static public final int RESOLUTION_MIN = 0;
    transient static public final int RESOLUTION_MAX = resolutions.length-1;

    /* STANDARD ONES */
    transient static public int       s_difficulty = 15;
    transient static public int       s_style = 0;
    transient static public int       s_mode = 0;
    transient static public int       s_volume = 0;
    transient static public int       s_quality = 0;
    transient static public int       s_resolution = 1;
    transient static public boolean   s_fullscreen = false;

    /* EFFECTIVE ONES */
    static public int e_difficulty = 15;
    static public int e_style = 0;
    static public int e_mode = 0;
    static public int e_volume = 0;
    static public int e_quality = 0;
    static public int e_resolution = 1;
    static public boolean e_fullscreen = false;

    static private int changeGeneral(int subject, int amount, int MIN, int MAX) {
        if(subject + amount >= MIN && subject + amount <= MAX) {
            subject += amount;
        } else {
            if(subject + amount < MIN) {
                subject = MIN;
            } else {
                subject = MAX;
            }
        }
        Content.log += " " + subject;
        return subject;
    }

    static public void changeDifficulty(int amount) { Settings.difficulty   = Settings.changeGeneral(Settings.difficulty, amount, Settings.DIFFICULTY_MIN, Settings.DIFFICULTY_MAX); }
    static public void changeStyle(int amount)      { Settings.style        = Settings.changeGeneral(Settings.style, amount, Settings.STYLE_MIN, Settings.STYLE_MAX); }
    static public void changeMode(int amount)       { Settings.mode         = Settings.changeGeneral(Settings.mode, amount, Settings.MODE_MIN, Settings.MODE_MAX); }
    static public void changeVolume(int amount)     { Settings.volume       = Settings.changeGeneral(Settings.volume, amount, Settings.VOLUME_MIN, Settings.VOLUME_MAX); }
    static public void changeQuality(int amount)    { Settings.quality      = Settings.changeGeneral(Settings.quality, amount, Settings.QUALITY_MIN, Settings.QUALITY_MAX); }
    static public void changeResolution(int amount) { Settings.resolution   = Settings.changeGeneral(Settings.resolution, amount, Settings.RESOLUTION_MIN, Settings.RESOLUTION_MAX); }
    static public void changeFullscreen()           { Settings.fullscreen   =!Settings.fullscreen;}

    static public void saveSettings() {
        Settings.e_difficulty   = Settings.difficulty;
        Settings.e_style        = Settings.style;
        Settings.e_mode         = Settings.mode;
        Settings.e_volume       = Settings.volume;
        Settings.e_quality      = Settings.quality;
        Settings.e_resolution   = Settings.resolution;
        Settings.e_fullscreen   = Settings.fullscreen;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("settings.bin"));
            out.writeObject(new int[]{
                    Settings.e_difficulty,
                    Settings.e_style,
                    Settings.e_mode,
                    Settings.e_volume,
                    Settings.e_quality,
                    Settings.e_resolution,
                    (Settings.e_fullscreen)?(1):(0)
            });
        } catch (IOException ignored) {}
    }

    static public void resetSettings() {
        Settings.difficulty  = Settings.e_difficulty;
        Settings.style       = Settings.e_style;
        Settings.mode        = Settings.e_mode;
        Settings.volume      = Settings.e_volume;
        Settings.quality     = Settings.e_quality;
        Settings.resolution  = Settings.e_resolution;
        Settings.fullscreen  = Settings.e_fullscreen;
    }

    static public void defaultSettings() {
        Settings.volume     = Settings.s_volume;
        Settings.quality    = Settings.s_quality;
        Settings.resolution = Settings.s_resolution;
        Settings.fullscreen = Settings.s_fullscreen;
    }

    public static void defaultGameSettings() {
        Settings.difficulty = Settings.s_difficulty;
        Settings.style      = Settings.s_style;
        Settings.mode       = Settings.s_mode;
    }

    public static void loadSettings() {
        try {
            ObjectInputStream inp = new ObjectInputStream(new FileInputStream("settings.bin"));
            int [] settings = (int[]) inp.readObject();
            Settings.e_difficulty   = settings[0];
            Settings.e_style        = settings[1];
            Settings.e_mode         = settings[2];
            Settings.e_volume       = settings[3];
            Settings.e_quality      = settings[4];
            Settings.e_resolution   = settings[5];
            Settings.e_fullscreen   = settings[6] != 0;
            Settings.difficulty     = settings[0];
            Settings.style          = settings[1];
            Settings.mode           = settings[2];
            Settings.volume         = settings[3];
            Settings.quality        = settings[4];
            Settings.resolution     = settings[5];
            Settings.fullscreen     = settings[6] != 0;
            Content.updateSettings();
        } catch(IOException|ClassNotFoundException ignored) {
            Settings.saveSettings();
        }
    }
}
