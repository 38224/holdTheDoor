package com.example.holdthedoor.gameobject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.holdthedoor.GameLoop;
import com.example.holdthedoor.R;

public class Spell extends Circle{

    public static final double SPEED_PIXELS_PER_SECOND = 700.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;


    public Spell(Context context, Player spellCaster){
        super(
                ContextCompat.getColor(context, R.color.spell),
                spellCaster.positionX,
                spellCaster.positionY,
                20);
        velocityX = spellCaster.getDirectionX()*MAX_SPEED;
        velocityY = spellCaster.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
