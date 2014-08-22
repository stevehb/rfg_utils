package com.robotfriendgames.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
    public AnimData animData;
    public int regionIdx;
    public float elapsed;

    public Animation(AnimData animData) {
        this.animData = animData;
    }

    public void load() {
        animData.texture = new Texture(animData.image);
        int height = animData.texture.getHeight();
        int width = height;
        int frameCount = animData.texture.getWidth() / width;
        animData.regions = new TextureRegion[frameCount];
        for(int i = 0; i < frameCount; i++) {
            int leftX = i * width;
            animData.regions[i] = new TextureRegion(animData.texture, leftX, 0, width, height);
        }
    }

    public void update(float delta) {
        elapsed += delta;
        float frameDelay = (1f / (float) animData.fps);
        if(elapsed > frameDelay) {
            elapsed -= frameDelay;
            regionIdx++;
        }
        if(regionIdx >= animData.regions.length) {
            regionIdx = 0;
        }
    }

    public TextureRegion getCurrentRegion() {
        return animData.regions[regionIdx];
    }
}
