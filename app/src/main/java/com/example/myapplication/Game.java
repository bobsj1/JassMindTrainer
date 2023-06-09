package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import androidx.core.content.ContextCompat;

import com.example.myapplication.object.Enemy;
import com.example.myapplication.object.Player;
import com.example.myapplication.object.Table;

/**
 * Game manages, renders and updates all objects
 *
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Joystick joystick;
    private final Enemy enemy;
    private final Table table;

    private GameLoop gameLoop;

    //private Sprite sprite;

    public Game(Context context, DisplayMetrics displayMetrics) {
        super(context);

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //create gameloop
        this.gameLoop = new GameLoop(this, surfaceHolder);

        float displayHeight = displayMetrics.heightPixels;
        float diplayWidth = displayMetrics.widthPixels;

        //init game objects
        joystick = new Joystick(400, 800, 100, 60);
        player = new Player(getContext(), joystick,500, 500, 50);
        enemy = new Enemy(getContext(), player, 100, 100,  50);
        table  = new Table(diplayWidth/2,displayHeight/2, ContextCompat.getColor(context, R.color.table));
        setFocusable(true);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event actions
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double) event.getX(), (double) event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return  true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        joystick.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);
        table.draw(canvas);
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 50, paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 100, paint);
    }

    //updates game state
    public void update() {
        joystick.update();
        player.update();
        enemy.update();
    }
}
