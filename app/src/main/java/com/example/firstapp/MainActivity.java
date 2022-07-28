package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int[][] grid = new int[3][3];
    private boolean curr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeGame();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void initializeGame(){
        // Initialize grid and curr value
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                grid[i][j] = -1;
            }
        }
        curr = false;
    }

    public void resetGame(View v){
        View infoTextView = findViewById(R.id.infoText);
        TextView infoText = (TextView) infoTextView;
        infoText.setText("Player 1's turn");

        // Re-initialize grid and curr value
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                grid[i][j] = -1;
            }
        }
        curr = false;

        View temp;
        Button tempBtn;

        // Reset all the button

        temp = findViewById(R.id.button00);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button01);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button02);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button10);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button11);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button12);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button20);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button21);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");

        temp = findViewById(R.id.button22);
        temp.setEnabled(true);
        tempBtn = (Button) temp;
        tempBtn.setText("BUTTON");
    }

    public void disableBtn(View v){

        // disable button
        v.setEnabled(false);

        // find button position
        String viewId = getId(v);

        int x = viewId.charAt(viewId.length()-2)-'0';
        int y = viewId.charAt(viewId.length()-1)-'0';

        View infoTextView = findViewById(R.id.infoText);
        TextView infoText = (TextView) infoTextView;

        // change button text
        Button b = (Button) v;
        if(curr){ // set to X
            b.setText("X");
            curr = false;
            infoText.setText("Player 1's turn");
        } else { // set to O
            b.setText("O");
            curr = true;
            infoText.setText("Player 2's turn");
        }

        // Run the game logics
        // We pass the negation of curr because we have change it previously
        logic(x, y, !curr);

        // Log.d("success", "button has been processed");
    }

    public void logic(int x, int y, boolean curr){
        // Change grid value
        if(curr){
            grid[x][y] = 0;
        } else {
            grid[x][y] = 1;
        }

        // Check win/lose
        boolean flag = false;

        // Check horizontal
        for(int i = 0; i < 3; i++){
            if(grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2] &&
            grid[i][0] != -1 && grid[i][1] != -1 && grid[i][2] != -1){
                flag = true;
                break;
            }
        }

        // Check vertical
        for(int i = 0; i < 3; i++){
            if(grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i] &&
            grid[0][i] != -1 && grid[1][i] != -1 && grid[2][i] != -1){
                flag = true;
                break;
            }
        }

        // Check diagonal
        if((grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] &&
        grid[0][0] != -1 && grid[1][1] != -1 && grid[2][2] != -1) ||
        (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] &&
        grid[0][2] != -1 && grid[1][1] != -1 && grid[2][0] != -1)){
            flag = true;
        }

        if(flag){
            View temp;

            temp = findViewById(R.id.button00);
            temp.setEnabled(false);

            temp = findViewById(R.id.button01);
            temp.setEnabled(false);

            temp = findViewById(R.id.button02);
            temp.setEnabled(false);

            temp = findViewById(R.id.button10);
            temp.setEnabled(false);

            temp = findViewById(R.id.button11);
            temp.setEnabled(false);

            temp = findViewById(R.id.button12);
            temp.setEnabled(false);

            temp = findViewById(R.id.button20);
            temp.setEnabled(false);

            temp = findViewById(R.id.button21);
            temp.setEnabled(false);

            temp = findViewById(R.id.button22);
            temp.setEnabled(false);

            View infoTextView = findViewById(R.id.infoText);
            TextView infoText = (TextView) infoTextView;

            if(curr){
                infoText.setText("Player 2 Win!!");
            } else {
                infoText.setText("Player 1 Win!!");
            }
            return;
        }

        // Check draw
        boolean flag2 = false;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == -1) flag2 = true;
            }
        }
        if(!flag2){
            View infoTextView = findViewById(R.id.infoText);
            TextView infoText = (TextView) infoTextView;

            infoText.setText("Game Draw!!");
        }
    }

    public static String getId(View view) {
        if (view.getId() == View.NO_ID) return "no-id";
        else return view.getResources().getResourceName(view.getId());
    }
}