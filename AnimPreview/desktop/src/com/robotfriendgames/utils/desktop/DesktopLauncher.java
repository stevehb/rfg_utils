package com.robotfriendgames.utils.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.robotfriendgames.utils.AnimPreviewGame;
import com.robotfriendgames.utils.CmdArgs;

public class DesktopLauncher {
	public static void main (String[] args) {
        if(args.length != 1) {
            System.out.println("USAGE: java -jar AnimPreview.jar animation.json");
            return;
        } else {
            CmdArgs.jsonFilename = args[0];
        }

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 600;
        config.height = 600;
		new LwjglApplication(new AnimPreviewGame(), config);
	}
}
