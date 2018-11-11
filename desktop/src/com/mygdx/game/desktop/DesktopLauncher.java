package com.mygdx.game.desktop;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Assets;
import com.mygdx.game.Blore;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		//config.width = 640;
		//config.height = 480;

		//config.width = 720;
		//config.height = 480;

		//config.width = 1280;
		//config.height = 720;


		//config.width = 1920;
		//config.height = 1080;

		// fullscreen
		//config.fullscreen = true;
		// vSync
		config.vSyncEnabled = true;

		new LwjglApplication(new Blore(), config);
	}
}
