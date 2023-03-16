package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {

    private double posX;
    private double posY;
    private double radius;
    private Paint paint;

    public Player(Context context, double posX, double posY, double radius){
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {    
        canvas.drawCircle((float)posX, (float)posY, (float)radius, paint);
    }

    public void update() {
    }
}
