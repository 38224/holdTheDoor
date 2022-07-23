package com.example.holdthedoor;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java","onCreate()");
        super.onCreate(savedInstanceState);
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity.java","onStart()");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.d("MainActivity.java","onResume()");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("MainActivity.java","onPause()");
        game.pause();
        super.onPause();
    }
    @Override
    protected void onStop() {
        Log.d("MainActivity.java","onStop()");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java","onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        // forces the app to stay up when pressing the back button
        Log.d("MainActivity.java","onBackPressed()");
        //super.onBackPressed();
    }
}