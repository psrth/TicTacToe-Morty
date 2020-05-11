/*
    -----------------------------------------------------------------------------------------------
    Tic Tac Toe — Morty Edition
    -----------------------------------------------------------------------------------------------
    Version 1.4
    Author: Parth Sharma
    Date: 11-05-20
    -----------------------------------------------------------------------------------------------
    The purpose of this tic-tac-toe game is to incorporate a simple and classic game into a basic
    android app with cool animations and stuff.
    -----------------------------------------------------------------------------------------------
 */

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

    // NOTE: 0 denotes evil morty state, 1 denotes c-137 morty state, and 2 denotes a blank state

    // single-dimensional array containing the states (initially the grid is blank)
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0, 1, 2}, {0, 3, 6}, {1, 4 , 7}, {2, 5, 8}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    // checks whether the game is active or it has ended
    boolean gameActive = true;

    // method that drops in the different tic-tac-toe pieces
    public void dropIn (View view)
    {
        // creating a new image view
        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1500);


        int counterIndex = Integer.parseInt(counter.getTag().toString());

        // if the grid square is blank and the game is active, the image will be dropped in
        if (gameState[counterIndex] == 2 && gameActive)
        {
            // assigns value of grid square to person who clicked
            gameState[counterIndex] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.morty2);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.morty1);
                activePlayer = 0;
            }

            // animating the piece to drop down
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions)
            {
                // checks if the currrent position on board is a win
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && gameState[winningPosition[0]] != 2)
                {
                    String winner = "";
                    gameActive = false;

                    if (activePlayer == 1)
                    {
                        winner = "Evil Morty";
                    } else
                        {
                        winner = "Morty C-137";
                    }

                    // toast deployed
                    Toast.makeText(this, winner + " has won!!", Toast.LENGTH_LONG).show();

                    // play again button and winner announcement appear
                    Button playAgainButton = (Button) findViewById(R.id.button);
                    playAgainButton.setVisibility(View.VISIBLE);

                    TextView winnerView = (TextView) findViewById(R.id.textView);

                    winnerView.setText(winner + " has won!!");
                    winnerView.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    // method envoked when play again button is pressed
    public void playAgain(View view)
    {
        Button playAgainButton = (Button) findViewById(R.id.button);
        TextView winnerView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerView.setVisibility(View.INVISIBLE);

        // sets each grid piece and makes it visually blank
        GridLayout gridLayoutName = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayoutName.getChildCount(); i++)
        {
            ImageView counter = (ImageView) gridLayoutName.getChildAt(i);
            counter.setImageDrawable(null);

        }

        // reassigns each gameState back to empty
        for (int i = 0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        // the active player and game are reset
        activePlayer = 0;
        gameActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

// thank you for reading this far, i appreciate it