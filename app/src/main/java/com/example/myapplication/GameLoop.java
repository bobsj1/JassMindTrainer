package com.example.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Observer;

public class GameLoop extends Thread{

    private static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private Game game;
    private SurfaceHolder surfaceHolder;
    private boolean isRunning = false;
    private double averageUPS;
    private double averageFps;


    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFps;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        super.run();


        //declare tracking variables
        int updateCount = 0;
        int frameCount = 0;

        long starTime;
        long elapsedTime;
        long sleepTime;


        //Game Loop
        Canvas canvas = null;
        starTime = System.currentTimeMillis();
        while(isRunning){

            //update and render the game
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }


            }catch (IllegalArgumentException e){
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            //pause loop to not exceed target ups
            elapsedTime = System.currentTimeMillis() - starTime;
            sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //skip frames to keep up with target ups
            while(sleepTime<0&&updateCount<MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - starTime;
                sleepTime = (long)(updateCount*UPS_PERIOD - elapsedTime);
            }

            //calc average ups and fps
            elapsedTime = System.currentTimeMillis() - starTime;
            if(elapsedTime >= 1000){
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFps = updateCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                starTime = System.currentTimeMillis();
            }

        }
    }
}
