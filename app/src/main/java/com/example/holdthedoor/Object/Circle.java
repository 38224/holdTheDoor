package com.example.holdthedoor.Object;

import android.graphics.Canvas;
import android.graphics.Paint;
//Circle is an abstract class which implements a draw method
//from GameObject for drawing the object as a Circle
public abstract class Circle extends GameObject{

    protected double radius;
    protected Paint paint;

    public Circle(int color, double positionX, double positionY,double radius) {
        super(positionX, positionY);
        this.radius = radius;

        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas){
        canvas.drawCircle((float) positionX,(float)positionY,(float) radius, paint);
    }
}
