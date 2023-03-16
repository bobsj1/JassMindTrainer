package com.example.myapplication.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private final Rect rect;
    private final SpriteSheet spriteSheet;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(50, 50,200,200),
                null
        );
    }
}
