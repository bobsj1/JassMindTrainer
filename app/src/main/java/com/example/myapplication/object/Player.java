package com.example.myapplication.object;

import android.content.Context;
import androidx.core.content.ContextCompat;

import com.example.myapplication.GameLoop;
import com.example.myapplication.Joystick;
import com.example.myapplication.R;

public class Player extends Circle {

    public static final double SPEED_PIXELS_PER_SECOND  = 400;
    private static final double MAX_SPEED =  SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double posX, double posY, double radius){
        super(context, ContextCompat.getColor(context, R.color.player), posX, posY, radius);
        this.joystick = joystick;
    }

    public void update() {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        posX += velocityX;
        posY += velocityY;
    }
}
