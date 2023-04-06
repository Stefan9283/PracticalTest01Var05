package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PracticalTest01Var05SecondaryActivity extends Activity {
    Button cancel, verify;
    TextView template;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        cancel = findViewById(R.id.cancel);
        verify = findViewById(R.id.verify);
        template = findViewById(R.id.template);

        template.setText(getIntent().getStringExtra(Constants.MESSAGE));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endActivity("CANCEL");
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endActivity("VERIFY");
            }
        });
    }

    void endActivity(String message) {
        Intent i = new Intent();
        i.putExtra(Constants.RETURNED_STRING, message);
        setResult(111, i);
        finish();
    }
}
