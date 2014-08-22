package com.robotfriendgames.utils.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.robotfriendgames.utils.AnimPreviewGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(600, 600);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new AnimPreviewGame();
        }
}
