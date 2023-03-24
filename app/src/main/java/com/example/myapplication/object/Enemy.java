package com.example.myapplication.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.myapplication.GameLoop;
import com.example.myapplication.R;

public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND  = Player.SPEED_PIXELS_PER_SECOND * 0.8;
    private static final double MAX_SPEED =  SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Player player;

    public Enemy(Context context, Player player, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), posX, posY, radius);
        this.player = player;
    }

    @Override
    public void update() {
        //=======================================================================================================
        //update enemy velocity to face player
        //=======================================================================================================
        //calc vector from enemy to player
        double distanceToPlayerX = player.getPositionX() - posX;
        double distanceToPlayerY = player.getPositionY() - posY;

        //calc (absolute) distance between enemy (this) and player
        double distanceToPlayerAbs = GameObject.getDistnaceBetweenObjects(this, player);

        //calc direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayerAbs;
        double directionY = distanceToPlayerY/distanceToPlayerAbs;

        //set velocity in direction of player
        if(distanceToPlayerAbs > 0) {//avoid dif by 0
            velocityX = directionX * MAX_SPEED;
            velocityY = directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        //update the position of the enemy
        posX += velocityX;
        posY += velocityY;
    }
}
