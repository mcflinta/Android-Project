package com.example.week5_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView inputTextView;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputData = inputTextView.getText().toString();

                // Tạo một implicit intent với hành động tùy chỉnh
                Intent intent = new Intent("com.example.week5_intent.ACTION_SEND_DATA");
                intent.putExtra("data", inputData);

                // Khởi động hoạt động mục tiêu bằng implicit intent
                startActivity(intent);
            }
        });
    }
}
