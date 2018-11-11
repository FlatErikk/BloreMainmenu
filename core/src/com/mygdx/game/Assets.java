package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    public static TextureAtlas menuButtons;
    public static BitmapFont asgard;
    public static Preferences settings;
    public static float sound;
    public static int screenWidth, screenHeight;

    public static void load(){
        menuButtons = new TextureAtlas(Gdx.files.internal("menuButtons.atlas"));
        asgard = new BitmapFont(Gdx.files.internal("asgard.fnt"));

        settings = Gdx.app.getPreferences("settings");
        sound = settings.getFloat("sound");
        screenWidth = settings.getInteger("screenWidth");
        screenHeight = settings.getInteger("screenHeight");
    }
}
