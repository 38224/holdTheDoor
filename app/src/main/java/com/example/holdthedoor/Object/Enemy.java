package com.example.holdthedoor.Object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.holdthedoor.GameLoop;
import com.example.holdthedoor.R;

//Enemy is a character which always move in the direction of the player
public class Enemy extends Circle{
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final Player player;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius) {
        super(ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
    }

    @Override
    public void update() {
        //update velocity of the enemy in direction of the player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;
        //calculate vector from enemy to player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        //calculate direction of enemy to player

        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        //set velocity in the direction of the player

        if(distanceToPlayer >0){
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        }else{
            velocityX = 0;
            velocityY = 0;
        }

        positionX += velocityX;
        positionY += velocityY;
        //update the positionm of the enemy
        
    }

}
