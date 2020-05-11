package com.example.mortytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 — evilmorty, 1 — normalmorty, 2 — empty

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0, 1, 2}, {0, 3, 6}, {1, 4 , 7}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn (View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);

        int counterIndex = Integer.parseInt(counter.getTag().toString());

        if (gameState[counterIndex] == 2 && gameActive) {
            gameState[counterIndex] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.morty2);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.morty1);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    gameActive = false;

                    if (activePlayer == 1) {
                        winner = "Evil Morty";
                    } else {
                        winner = "Morty C-137";
                    }

                    Toast.makeText(this, winner + " has won!!", Toast.LENGTH_LONG).show();

                    Button playAgainButton = (Button) findViewById(R.id.button);
                    playAgainButton.setVisibility(View.VISIBLE);

                    TextView winnerView = (TextView) findViewById(R.id.textView);

                    winnerView.setText(winner + " has won!!");
                    winnerView.setVisibility(View.VISIBLE);


                }
            }
        }
    }

    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.button);
        TextView winnerView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerView.setVisibility(View.INVISIBLE);

        GridLayout gridLayoutName = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayoutName.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayoutName.getChildAt(i);
            counter.setImageDrawable(null);

        }

        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }


        activePlayer = 0;
        gameActive = true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
