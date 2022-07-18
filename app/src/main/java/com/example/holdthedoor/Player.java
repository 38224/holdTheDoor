package com.example.holdthedoor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;

public class Player extends GameObject{

    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    public double positionX;
    public double positionY;
    private double radius;
    private Paint paint;
    private double velocityX;
    private double velocityY;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(positionX,positionY);
        this.joystick = joystick;
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX,(float) positionY, (float) radius, paint);
    }

    public void update() {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;
    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
