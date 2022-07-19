package com.example.holdthedoor.gameobject;

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

    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1,obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        return distance <= distanceToCollision;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas){
        canvas.drawCircle((float) positionX,(float)positionY,(float) radius, paint);
    }
}
