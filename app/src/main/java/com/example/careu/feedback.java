package com.example.careu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class feedback extends AppCompatActivity {
    EditText _feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        _feedback = findViewById(R.id.feedback);
        String feedback =_feedback.getText().toString();
        final Intent l = new Intent(this, requestList.class);
        if (feedback.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(feedback.this);
            alert.setTitle("Feedback");
            alert.setMessage("Cannot Enter the Empty FeedBack Field");
            alert.setPositiveButton("Request-List", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(l);
                }
            });
            alert.setCancelable(true);
        }
    }
}
