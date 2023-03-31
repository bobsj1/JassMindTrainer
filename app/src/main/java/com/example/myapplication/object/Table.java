package com.example.myapplication.object;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Table extends GameObject{

    private Paint paint;
    private float rectLength = 300;
    private float rectWidth = 150;
    private float tableLeft;
    private float tableTop;
    private float tableRight;
    private float tableBottom;

    public Table(double posX, double posY, int color) {
        super(posX, posY);
        paint = new Paint();
        paint.setColor(color);

        tableLeft = (float) posY - rectLength;
        tableTop = (float) posX - rectWidth;
        tableRight = (float) posY + rectLength;
        tableBottom = (float) posX + rectWidth;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect( tableLeft, tableTop, rectWidth, tableBottom, paint);
    }

    @Override
    public void update() {

    }
}
