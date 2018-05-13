package com.example.zakiryousuf.tictactoe;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activePlayer=0; //0 for cross
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int winningLocation[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOver=false;

    public void logic(View view)
    {
        int tappedLocation= Integer.parseInt( view.getTag().toString());
        if(gameState[tappedLocation]==2 && !gameOver) {
            gameState[tappedLocation]=activePlayer;

            ImageView tappedView = (ImageView) view;
            tappedView.setTranslationY(-3000f);
            if (activePlayer == 0) {
                tappedView.setImageResource(R.drawable.c);
                activePlayer = 1;
            } else {
                tappedView.setImageResource(R.drawable.o);
                activePlayer = 0;
            }

            tappedView.animate().translationYBy(3000f).setDuration(500);

        }
        String msg="";
        for(int[] winningPosition : winningLocation){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]]!=2){
                if(activePlayer==0){
                   msg="0 is winner!";

                }
                if(activePlayer==1){
                    msg="X is winner!";
                }
                LinearLayout winnerLayout=(LinearLayout)findViewById(R.id.winnerLayout);
                winnerLayout.setVisibility(View.VISIBLE);
                TextView winnermsg=(TextView)findViewById(R.id.winnermsg);
                winnermsg.setText(msg);
                gameOver=true;

            }
        }
    }

    public void playAgain(View view){
        LinearLayout winnerLayout=(LinearLayout)findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(view.INVISIBLE);
        gameOver=false;
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
            gameState[i] = 2;

            GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
            for(int j=0;j<gridLayout.getChildCount();j++)
                ((ImageView)gridLayout.getChildAt(j)).setImageResource(0);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout winnerLayout=(LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
    }
}
