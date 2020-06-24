package com.fracpracgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fracpracgdx.FracPracGDX;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Seaghán's Fractions";
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new FracPracGDX(), config);
	}
}
