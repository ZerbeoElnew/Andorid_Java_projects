package com.example.javafinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playes);
        final EditText playerOne= findViewById(R.id.P1_Name);
        final EditText playerTwo= findViewById(R.id.P2_Name);
        final Button startgameBtnn=findViewById(R.id.Start_Game);

        startgameBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  String getplayerOneName = playerOne.getText().toString();
                final  String getplayerTwoName = playerTwo.getText().toString();
                if(getplayerOneName.isEmpty()||getplayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayers.this,"Please enter player names",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(AddPlayers.this,MainActivity.class);
                    intent.putExtra("playerOne",getplayerOneName);
                    intent.putExtra("playerTwo",getplayerTwoName);
                    startActivity(intent);
                }
            }
        });
    }
}