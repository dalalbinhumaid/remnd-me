package com.example.remndme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class ReminderDetailsActivity extends AppCompatActivity {

    TextInputLayout taskTitle;
    ImageView importance;
    Button date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            taskTitle =  findViewById(R.id.titleDisplay);
            importance = (ImageView) findViewById(R.id.imageImportance);
            date = (Button) findViewById(R.id.dateDisplay);
            time = (Button) findViewById(R.id.timeDisplay);

            // extract the extra-data in the Notification
            String title = extras.getString("titletask");
            int impo = extras.getInt("impo");
            String taskDate = extras.getString("date");
            String taskTime = extras.getString("time");

            switch (impo) {
                case 0:
                    importance.setImageResource(R.drawable.highimp);
                    break;
                case 1:
                    importance.setImageResource(R.drawable.mediumimp);
                    break;
                case 2:
                    importance.setImageResource(R.drawable.lowimp);
                    break;
            }

            taskTitle.getEditText().setText(title);
            date.setText(taskDate);
            time.setText(taskTime);

        }
    }
}
