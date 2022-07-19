package com.example.holdthedoor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;

public class Performance {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private GameLoop gameLoop;
    private Context context;

    public Performance(Context context, GameLoop gameLoop){
        this.context = context;
        this.gameLoop = gameLoop;
    }
    public void draw(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
    }
    public void drawUPS( Canvas canvas) {
        String averageUPS = df.format(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("UPS: " + averageUPS, 120, 100, paint);
    }
    public void drawFPS( Canvas canvas) {
        String averageFPS = df.format(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(60);
        canvas.drawText("FPS: " + averageFPS, 120, 160, paint);
    }
}
