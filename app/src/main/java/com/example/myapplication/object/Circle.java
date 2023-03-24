package com.example.myapplication.object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Circle extends GameObject{

    private double radius;
    private Paint paint;

    public Circle(Context context, int color, double posX, double posY, double radius) {
        super(posX, posY);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float)posX, (float)posY, (float)radius, paint);
    }
}
