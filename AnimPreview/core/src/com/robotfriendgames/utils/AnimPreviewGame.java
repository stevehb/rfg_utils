package com.robotfriendgames.utils;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class AnimPreviewGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Animation anim;

    @Override
    public void create () {
        Gdx.app.setLogLevel(Application.LOG_INFO);

        batch = new SpriteBatch();
        Json json = new Json();

        FileHandle fh = FileLoader.load(CmdArgs.jsonFilename);
        JsonReader jsonReader = new JsonReader();
        JsonValue root = jsonReader.parse(fh);
        String imgName = root.getString("image");
        int fps = root.getInt("fps");
        AnimData animData = new AnimData();
        animData.image = imgName;
        animData.fps = fps;
        anim = new Animation(animData);
        anim.load();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.78f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        anim.update(Gdx.graphics.getDeltaTime());
        TextureRegion frame = anim.getCurrentRegion();
        float x = (Gdx.graphics.getWidth() - frame.getRegionWidth()) / 2f;
        float y = (Gdx.graphics.getHeight() - frame.getRegionHeight()) / 2f;
        batch.draw(frame, x, y);
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
