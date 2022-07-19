package com.example.holdthedoor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.holdthedoor.gameobject.Circle;
import com.example.holdthedoor.gameobject.Enemy;
import com.example.holdthedoor.gameobject.Player;
import com.example.holdthedoor.gameobject.Spell;
import com.example.holdthedoor.gamepanel.GameOver;
import com.example.holdthedoor.gamepanel.Joystick;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final Player player;
    private final Joystick joystick;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private GameLoop gameLoop;

    private int playerScore =0;
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;

    public Game(Context context) {
        super(context);

        SurfaceHolder  surfaceHolder = getHolder();
        surfaceHolder.addCallback((this));

        gameLoop = new GameLoop( this, surfaceHolder);

        //initialize game panels
        performance = new Performance(context,gameLoop);
        gameOver = new GameOver(context);
        joystick = new Joystick(275,700,70,40);

        //initialize game objects

        player = new Player(getContext(),joystick,500,500,30);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event action
        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()) {
                    numberOfSpellsToCast++; // joystick was pressed before this event
                }else if(joystick.isPressed((double) event.getX(),(double) event.getY())){
                    // joystick is pressed in this event
                    if(player.getHealthPoints() <= 0) resetGame();
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }else{
                    numberOfSpellsToCast++;
                    // joystick wat not previously and is not pressed in this event
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                // joystick was pressed previously and is now moved
                if(joystick.getIsPressed()){
                    joystick.setActuator((double) event.getX(),(double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    // joystick was let go of -> setIsPressed(false) and resetActualtor
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;
        }


        return super.onTouchEvent(event);
    }

    public void resetGame(){
        player.setHealthPoints(player.MAX_HEALTH_POINTS);
        playerScore = 0;
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


        //draw game objects
        player.draw(canvas);
        for(Enemy enemy : enemyList){
            enemy.draw(canvas);
        }
        for(Spell spell : spellList){
            spell.draw(canvas);
        }
        //draw game panels
        joystick.draw(canvas);
        performance.draw(canvas);

        //draw  SCORE
        drawScore(canvas);



        // draw Game over if the player is dead
        if( player.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }
    }


    public void drawScore( Canvas canvas){
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("Score: " + playerScore, 120, 240, paint);
    }

    public void update() {

        // stop updating the game if the player is dead
        if(player.getHealthPoints() <= 0) return;

        joystick.update();
        player.update();
        if(Enemy.readyToSpawn()){
            enemyList.add(new Enemy(getContext() ,player));
        }

        //update state of each enemy
        while(numberOfSpellsToCast >0){
            spellList.add(new Spell(getContext(),player));
            numberOfSpellsToCast--;
        }

        for(Enemy enemy : enemyList){
            enemy.update();
        }
        for(Spell spell : spellList){
            spell.update();
        }



        //iterate enemy list and check for collision between each enemy and the player
        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while(iteratorEnemy.hasNext()){
            Circle enemy = iteratorEnemy.next();
            if(Circle.isColliding(enemy,player)) {
                //remove enemy if collides
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() -1);
                continue;
            }
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while(iteratorSpell.hasNext()){
                Circle spell = iteratorSpell.next();
                //remove the spell if colide with enemy
                if(Circle.isColliding(spell,enemy)){
                    playerScore++;
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }
    }
}
