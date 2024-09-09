package com.example.javafinalproject;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();
    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private  int playerTurn=0;
    private  int toatlselectedBoxes=1;
    private  LinearLayout playerOneLayout,playerTwoLayout;
    private TextView playerOneName,playerTwoName;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
          playerOneName = findViewById(R.id.playerOneName);
          playerTwoName = findViewById(R.id.playerTwoName);
        final LinearLayout playerOneLayout=findViewById(R.id.playerOneLayout);
        final LinearLayout playerTwoLayout=findViewById(R.id.playerTwoLayout);
         image1 = findViewById(R.id.image1);
         image2 = findViewById(R.id.image2);
         image3 = findViewById(R.id.image3);
         image4 = findViewById(R.id.image4);
         image5 = findViewById(R.id.image5);
         image6 = findViewById(R.id.image6);
         image7 = findViewById(R.id.image7);
         image8 = findViewById(R.id.image8);
         image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 4, 8});
        combinationsList.add(new int[]{2, 4, 6});

        final  String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final  String getPlayerTwoName = getIntent().getStringExtra("playerTwo");
        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(0)){
                    performAction((ImageView)v,0 );
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(1)){
                    performAction((ImageView)v,1 );
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(2)){
                    performAction((ImageView)v,2 );
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(3)){
                    performAction((ImageView)v,3 );
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(4)){
                    performAction((ImageView)v,4 );
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(5)){
                    performAction((ImageView)v,5 );
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(6)){
                    performAction((ImageView)v,6 );
                }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(7)){
                    performAction((ImageView)v,7 );
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelecteable(8)){
                    performAction((ImageView)v,8 );
                }
            }
        });


    }

    private void performAction(ImageView imageView,int selectedBoxPosaistion){

        boxPositions[selectedBoxPosaistion]=playerTurn;
        if(playerTurn==1){
            imageView.setImageResource(R.drawable.letter_x);
            if (checkPlayerwin()){
                WinDialog winDialog = new WinDialog(MainActivity.this, playerOneName.getText().toString()+"has won the game",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(toatlselectedBoxes==9){
                WinDialog winDialog = new WinDialog(MainActivity.this, "Draw",MainActivity.this);
                winDialog.show();
            }
            else {
                changePlayerTurn(2);

                toatlselectedBoxes++;
            }

        }
        else {
            imageView.setImageResource(R.drawable.letter_o);
            if (checkPlayerwin()){
                WinDialog winDialog = new WinDialog(MainActivity.this, playerOneName.getText().toString()+"has won the game",MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if (selectedBoxPosaistion==9){
                WinDialog winDialog = new WinDialog(MainActivity.this, "Draw",MainActivity.this);
                winDialog.show();
            }
            else {
                changePlayerTurn(1);

                toatlselectedBoxes++;
            }
        }
    }

    private void  changePlayerTurn(int currentPlayerTurn){
        playerTurn=currentPlayerTurn;
        if (playerTurn==1){
            playerOneLayout.setBackgroundResource(R.drawable.ic_launcher_background);
            playerTwoLayout.setBackgroundResource(R.drawable.for_x_y);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.ic_launcher_background);
            playerOneLayout.setBackgroundResource(R.drawable.for_x_y);
        }
    }

    private boolean checkPlayerwin(){
        boolean response = false;
        for (int i=0;i<combinationsList.size();i++){
            final  int[] combinatio = combinationsList.get(i);
            if(boxPositions[combinatio[0]]==playerTurn&&boxPositions[combinatio[1]]==playerTurn&&boxPositions[combinatio[2]]==playerTurn){
                response = true;
            }
        }
        return  response;
    }

    private boolean isBoxSelecteable(int boxPosition) {
    boolean response = false;
    if (boxPositions[boxPosition] == 0) {
        response = true;
    }
    return response;}

    private void startMatch(){
        boxPositions=new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        toatlselectedBoxes=1;
        image1.setImageResource(R.drawable.for_buttons);
        image2.setImageResource(R.drawable.for_buttons);
        image3.setImageResource(R.drawable.for_buttons);
        image4.setImageResource(R.drawable.for_buttons);
        image5.setImageResource(R.drawable.for_buttons);
        image6.setImageResource(R.drawable.for_buttons);
        image7.setImageResource(R.drawable.for_buttons);
        image8.setImageResource(R.drawable.for_buttons);
        image9.setImageResource(R.drawable.for_buttons);

    }
}