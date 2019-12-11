package com.example.homeworkexpectation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView messageDisplay;

    private Button replyButton;

    private EditText inputTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String messageReceived = getIntent().getStringExtra("my_string");

        messageDisplay = findViewById(R.id.textView);
        replyButton = findViewById(R.id.send_message_button);
        inputTextField = findViewById(R.id.editText);

        messageDisplay.setText("From Activity1: " + messageReceived);

        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputTextField.getText().toString().length() > 0){

                Intent replyIntent = new Intent(SecondActivity.this, MainActivity.class);
                replyIntent.putExtra("my_string", inputTextField.getText().toString());
                setResult(AppCompatActivity.RESULT_OK, replyIntent);

                finish();
                }
                else{
                    Toast.makeText(SecondActivity.this, "Message must not be empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
