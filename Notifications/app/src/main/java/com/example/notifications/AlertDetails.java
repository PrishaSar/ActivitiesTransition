package com.example.notifications;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AlertDetails extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_details);

        textView = findViewById(R.id.textView);
        String data = getIntent().getStringExtra("data (if needed?)");
        textView.setText(data);
    }
}