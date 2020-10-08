package org.techtown.stemptour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PhoneActivity extends AppCompatActivity {

    Button btnSendSMS;
    EditText txtPhoneNo;
    //EditText txtMessage;

    TextView label2;
    EditText phone_edit2;
    Button phone_end;

    static String txtMessage;

    String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        checkSelfPermission();

        btnSendSMS = (Button) findViewById(R.id.phone_no);
        txtPhoneNo = (EditText) findViewById(R.id.phone_edit);

        label2 = findViewById(R.id.label2);
        phone_edit2 = findViewById(R.id.phone_edit2);
        phone_end = findViewById(R.id.phone_end);

        label2.setVisibility(View.GONE);
        phone_edit2.setVisibility(View.GONE);
        phone_end.setVisibility(View.GONE);

        final Context context = this;

        btnSendSMS.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                for(int i=0; i<6; i++){
                    Random random = new Random();
                    int randomValue = random.nextInt(10);

                    if (i==0){
                        txtMessage =String.valueOf(randomValue);
                    }else {
                        txtMessage = txtMessage.concat(String.valueOf(randomValue));
                    }

                }

                System.out.println("인증번호 : "+txtMessage);

                phoneNo = txtPhoneNo.getText().toString();
                String message = txtMessage;
                if (phoneNo.length()>0) {
                    sendSMS(phoneNo, message);

                    label2.setVisibility(View.VISIBLE);
                    phone_edit2.setVisibility(View.VISIBLE);
                    phone_end.setVisibility(View.VISIBLE);

                }else
                    Toast.makeText(getBaseContext(),
                            "Please enter both phone number and message.",
                            Toast.LENGTH_SHORT).show();
            }
        });

        phone_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = phone_edit2.getText().toString();
                if (number1.equals(txtMessage)){
                    Intent join_intent=new Intent(context, JoinActivity.class);

                    join_intent.putExtra("phone_number", phoneNo);

                    startActivity(join_intent);

                    finish();
                }else {
                    Toast.makeText(getBaseContext(), "인증번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendSMS(String phoneNumber, String message)
    {
        /*PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);*/
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    //권한
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d(" ", "권한 허용 : " + permissions[i]);
                }
            }
        }

    }

    public void checkSelfPermission() {
        String temp = "";
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.SEND_SMS + " ";
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_SMS + " ";
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.RECEIVE_SMS + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(this, temp.trim().split(" "),1);
        }else {
            // 모두 허용 상태
            Toast.makeText(this, "권한 허용", Toast.LENGTH_SHORT).show();
        }
    }
}