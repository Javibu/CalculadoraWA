package com.facci.conversorwa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.R;

public class MainActivityWA extends AppCompatActivity {

    final String[] datos = new String[] {"DOLAR","EURO","PESO MEXICANO"};

    private Spinner MonedaActualSP ;
    private Spinner MonedaCambioSP;
    private EditText ValorCambioET;
    private TextView ResultadoTV;

    final private double FactorDolarEuro= 0.87;
    final private double FactorPesoDolar= 0.54;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_w);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);
         MonedaActualSP = (Spinner) findViewById(R.id.MonedaActualSP);
        MonedaActualSP.setAdapter(adapter);
        MonedaCambioSP= (Spinner) findViewById(R.id.MonedaCambioSP);

        SharedPreferences Preferencias = getSharedPreferences("Mis Preferencias", Context.MODE_PRIVATE);
        String  tmpMonedaActual = Preferencias.getString("MonedaActual", "");
        String  tmpmonedaCambio = Preferencias.getString("MonedaCambio", "");
    }

    public void ClickConvertir(View v){
        MonedaActualSP= (Spinner) findViewById(R.id.MonedaActualSP);
        MonedaCambioSP= (Spinner) findViewById(R.id.MonedaCambioSP);

        ValorCambioET= (EditText) findViewById(R.id.ValorCambioET);
        ResultadoTV = (TextView) findViewById(R.id.ResulatadoTV);

        String monedaActual = MonedaActualSP.getSelectedItem().toString();
        String monedaCambio = MonedaCambioSP.getSelectedItem().toString();

        double valorCambio= Double.parseDouble(ValorCambioET.getText().toString());

        double Resultado= ProcesarConversion(monedaActual, monedaCambio, valorCambio);
            if (Resultado>0){
                ResultadoTV.setText(String.format("Por 5%.2f %s usted recibirá 5%.2f %s",valorCambio, monedaActual,Resultado,monedaCambio) );
                ValorCambioET.setText("");
                SharedPreferences Preferencias = getSharedPreferences("Mis Preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor Editor = Preferencias.edit();

                Editor.putString("monedaActual",monedaActual);
                Editor.putString("monedaCambio",monedaCambio);

                Editor.commit();

            }else{
                ResultadoTV.setText(String.format("Usted Recibirá"));
                Toast.makeText(MainActivityWA.this, "Las opciones elejidas no tienen un formato de conversion", Toast.LENGTH_SHORT).show();


            };
    }

    private double ProcesarConversion(String monedaActual,String monedaCambio,double valorCambio){
        double ResultadoConversion= 0;

        switch (monedaActual){
            case "DOLAR":
                if(monedaCambio.equals("EURO")){
                    ResultadoConversion= valorCambio = FactorDolarEuro;
                }
                if(monedaCambio.equals("PESO MEXICANO")){
                    ResultadoConversion= valorCambio * FactorPesoDolar;
                }
                break;
            case "EURO":
                if(monedaCambio.equals("DOLAR")){
                    ResultadoConversion= valorCambio / FactorDolarEuro;
                }
                break;
            case "PESO MEXICANO":
                if(monedaCambio.equals("DOLAR")){
                    ResultadoConversion= valorCambio * FactorPesoDolar;
                }
                break;
        }
        return ResultadoConversion ;

    }
}
