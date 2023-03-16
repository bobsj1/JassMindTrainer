package com.example.myapplication.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;

public class SpriteSheet {

    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jasscard_spritesheet, bitmapOptions);
    }

    public Sprite getSprite(){

        return new Sprite(this, new Rect(200,200,232,232));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
