package com.example.holdthedoor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Player player;
    private GameLoop gameLoop;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public Game(Context context) {
        super(context);

        SurfaceHolder  surfaceHolder = getHolder();
        surfaceHolder.addCallback((this));

        gameLoop = new GameLoop( this, surfaceHolder);

        //initialize player

        player = new Player(getContext(),500,500,30);


        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event action
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                player.setPosition((double) event.getX(),(double) event.getY());
                return true;
            case MotionEvent.ACTION_MOVE:
                player.setPosition((double) event.getX(),(double) event.getY());
                return true;
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        player.draw(canvas);
    }

    public void drawUPS( Canvas canvas) {
        String averageUPS = df.format(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("UPS: " + averageUPS, 120, 100, paint);
    }
    public void drawFPS( Canvas canvas) {


        String averageFPS = df.format(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("FPS: " + averageFPS, 120, 160, paint);
    }

    public void update() {
        player.update();
    }
}
