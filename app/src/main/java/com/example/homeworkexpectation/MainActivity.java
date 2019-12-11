package com.example.homeworkexpectation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button sendMessageButton;
    private TextView displayView;
    private EditText messageInput;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("this_is_my_preference", Context.MODE_PRIVATE);
        sendMessageButton = findViewById(R.id.send_message_button);
        displayView = findViewById(R.id.display_texview);
        messageInput = findViewById(R.id.message_edittext);

        readFromSharedPreferences();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = messageInput.getText().toString().trim();

                if(message.length() > 0){

                    Intent secondIntent = new Intent(MainActivity.this, SecondActivity.class);

                    String current = displayView.getText().toString() + "\nActivity1: "+message;
                   displayView.setText(current);

                    saveToSharedPreferences(current);

                    secondIntent.putExtra("my_string", message);
                    startActivityForResult(secondIntent, 707);
                }
                else {
                    Toast.makeText(MainActivity.this, "Message should not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 707 && resultCode == AppCompatActivity.RESULT_OK){
            String messageIn = data.getStringExtra("my_string");
            String current = displayView.getText().toString() + "\nActivity2: "+messageIn;
            displayView.setText(current);
            saveToSharedPreferences(current);
        }
    }

    private void saveToSharedPreferences(String message){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("my_string_key",  message);
        editor.apply();
    }

    private void readFromSharedPreferences(){
        String itJustIn = preferences.getString("my_string_key", "Welcome");
        displayView.setText(itJustIn);
    }

}
