package com.example.zahut.easyrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseLoginOrRegistration extends AppCompatActivity {

    private Button goLoginBtn, goRegisterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_or_registration);

        goLoginBtn = (Button) findViewById(R.id.goLogin);
        goRegisterBtn = (Button) findViewById(R.id.goRegister);


        goLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chooseLoginOrRegistration.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        goRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chooseLoginOrRegistration.this, registerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
