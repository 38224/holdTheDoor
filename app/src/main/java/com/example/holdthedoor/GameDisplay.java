package com.example.holdthedoor;

import com.example.holdthedoor.gameobject.GameObject;

public class GameDisplay {

    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels,int heightPixels, GameObject centerObject) {
        this.centerObject = centerObject;
        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }
    public void update(){
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();
        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }
    public double gameToDisplayCoordinateX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinateY(double y) {
       return y + gameToDisplayCoordinateOffsetY;
    }
}
