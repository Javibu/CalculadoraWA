package com.facci.restaurantwa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivityWA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_w);
    }

    public void Click1(View v){
        Toast.makeText(MainActivityWA.this, "Bamdeja de mariscos", Toast.LENGTH_SHORT).show();
    }
    public void Click2(View v){
        Toast.makeText(MainActivityWA.this, "Caldo de gallina", Toast.LENGTH_SHORT).show();
    }
    public void Click3(View v){
        Toast.makeText(MainActivityWA.this, "Ceviche de camaron", Toast.LENGTH_SHORT).show();
    }
    public void Click4(View v){
        Toast.makeText(MainActivityWA.this, "Corviche", Toast.LENGTH_SHORT).show();
    }
    public void Click5(View v){
        Toast.makeText(MainActivityWA.this, "Pescado Frito", Toast.LENGTH_SHORT).show();
    }
    public void Click6(View v){
        Toast.makeText(MainActivityWA.this, "Salprieta", Toast.LENGTH_SHORT).show();
    }
    public void Click7(View v){
        Toast.makeText(MainActivityWA.this, "Arroz de mariscos", Toast.LENGTH_SHORT).show();
    }

}
