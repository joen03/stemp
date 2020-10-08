package org.techtown.stemptour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {

    Spinner join_gender;
    TextView phone_tv;
    Button join_button;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        join_gender = findViewById(R.id.join_gender);
        phone_tv = findViewById(R.id.phone1);
        join_button = findViewById(R.id.join_button);

        ArrayAdapter ad_Adapter1 = ArrayAdapter.createFromResource(this,
                R.array.date_gender, android.R.layout.simple_spinner_item);
        ad_Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        join_gender.setAdapter(ad_Adapter1);

        Intent intent=getIntent();

        String phone_num=intent.getExtras().getString("phone_number");

        phone_tv.setText(phone_num);

        final Context context = this;

        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent= new Intent(context, MainActivity.class);
                startActivity(login_intent);

                finish();
            }
        });
    }
}