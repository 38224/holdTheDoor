package com.example.holdthedoor.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.holdthedoor.GameLoop;
import com.example.holdthedoor.Joystick;
import com.example.holdthedoor.R;

public class Player extends Circle{
    // player can be controlled with the joystick
    //player class is an extension of a circle, which is an extension of a GameObject
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(ContextCompat.getColor(context, R.color.player),positionX,positionY,radius);
        this.joystick = joystick;
    }

    public void update() {
        //update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        //update position
        positionX = positionX + velocityX;
        positionY = positionY + velocityY;
    }
}
