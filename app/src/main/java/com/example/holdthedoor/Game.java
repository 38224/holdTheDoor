package com.example.holdthedoor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.holdthedoor.Object.Enemy;
import com.example.holdthedoor.Object.Player;

import java.text.DecimalFormat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Player player;
    private final Joystick joystick;
    private final Enemy enemy;
    private GameLoop gameLoop;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    public Game(Context context) {
        super(context);

        SurfaceHolder  surfaceHolder = getHolder();
        surfaceHolder.addCallback((this));

        gameLoop = new GameLoop( this, surfaceHolder);

        //initialize game objects
        joystick = new Joystick(275,700,70,40);
        player = new Player(getContext(),joystick,500,500,30);

        enemy = new Enemy(getContext(),player,500,900,30);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event action
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double) event.getX(),(double) event.getY())){
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
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
        joystick.draw(canvas);
        player.draw(canvas);
        enemy.draw(canvas);
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
        joystick.update();
        player.update();
        enemy.update();
    }
}
