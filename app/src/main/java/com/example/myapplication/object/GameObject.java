package com.example.myapplication.object;

import android.graphics.Canvas;

public abstract class GameObject {

    protected double posX;
    protected double posY;
    protected double velocityX;
    protected double velocityY;

    public GameObject(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }



    public abstract void draw(Canvas canvas);
    public abstract void update();

    protected double getPositionX() {
        return posX;
    }

    protected double getPositionY() {
        return posY;
    }

    protected static double getDistnaceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
                        Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }
}
