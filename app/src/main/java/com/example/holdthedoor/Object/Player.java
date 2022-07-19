package com.example.holdthedoor.Object;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.holdthedoor.GameLoop;
import com.example.holdthedoor.Joystick;
import com.example.holdthedoor.R;

public class Player extends Circle{
    // player can be controlled with the joystick
    //player class is an extension of a circle, which is an extension of a GameObject
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;

    private HealthBar healthBar;
    private int healthPoints;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(ContextCompat.getColor(context, R.color.player),positionX,positionY,radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context,this);
        this.healthPoints = MAX_HEALTH_POINTS;
    }

    public void update() {
        //update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        //update position
        positionX = positionX + velocityX;
        positionY = positionY + velocityY;
        //calculate direction

        if(velocityX != 0 || velocityY != 0){
            //normalize velocity to get direction
            double distance = Utils.getDistanceBetweenPoints(0,0,velocityX,velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        healthBar.draw(canvas);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >= 0)
            this.healthPoints = healthPoints;
    }
}
