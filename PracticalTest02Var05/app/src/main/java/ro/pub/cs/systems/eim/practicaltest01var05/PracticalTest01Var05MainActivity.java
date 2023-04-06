package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    Button bottom_left, bottom_right, top_left, top_right, center, nav_to_second_activity;
    TextView text_field;

    int press_count = 0;

    BroadcastReceiver br = new PracticalTest01Var05BroadcastReceiver();
    IntentFilter filter = new IntentFilter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        bottom_left = findViewById(R.id.bottom_left);
        bottom_right = findViewById(R.id.bottom_right);
        top_left = findViewById(R.id.top_left);
        top_right = findViewById(R.id.top_right);
        center = findViewById(R.id.center);
        text_field = findViewById(R.id.text_field);

        filter.addAction(Constants.ACTION_MESSAGE);

        nav_to_second_activity = findViewById(R.id.nav_to_second_activity);

        nav_to_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
                i.putExtra(Constants.MESSAGE, text_field.getText());
                startActivityForResult(i, 111);
            }
        });

        bottom_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToTextField("BottomLeft");
            }
        });

        bottom_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToTextField("BottomRight");
            }
        });

        top_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToTextField("TopLeft");
            }
        });

        top_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToTextField("TopRight");
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToTextField("Center");
            }
        });
    }

    @Override
    protected void onDestroy() {
        Intent i_s = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
        stopService(i_s);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("press_count", press_count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        int press_count2 = savedInstanceState.getInt("press_count");
        Toast.makeText(getApplicationContext(), String.valueOf(press_count2), Toast.LENGTH_LONG).show();
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, filter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(br);
        super.onPause();
    }

    void addToTextField(String text) {
        press_count++;
        if (press_count > Constants.THRESHOLD) {
            Intent i_s = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
            i_s.putExtra(Constants.MESSAGE, text_field.getText());
            startService(i_s);
        }
        if (text_field.length() == 0)
            text_field.setText(text);
        else
            text_field.setText(text_field.getText() + ", " + text);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 111) {
            press_count = 0;
            text_field.setText("");
            Toast.makeText(getApplicationContext(), String.valueOf(data.getStringExtra(Constants.RETURNED_STRING)), Toast.LENGTH_LONG).show();
        }
    }
}