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
    final String[] datos = new String[]{"DOLAR", "EURO", "PESO MEXICANO"};

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final  private double factorDolarEuro = 0.87;
    final private  double factorPesoDolar = 0.54;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_w);

        ArrayAdapter<String> adapadador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);

        monedaActualSP = (Spinner) findViewById(R.id.MonedaActualSP);

        monedaActualSP.setAdapter(adapadador);

        monedaCambioSP = (Spinner) findViewById(R.id.MonedaCambioSP);

        SharedPreferences preferencias = getSharedPreferences("MiPreferencia", Context.MODE_PRIVATE);

        String tmpMonedaActual = preferencias.getString("monedaActual", "");
        String tmpMonedaCambio = preferencias.getString("monedaCambio","");

        if(tmpMonedaActual.equals("")){
            int indice = adapadador.getPosition(tmpMonedaActual);
            monedaActualSP.setSelection(indice);
        }

        if(tmpMonedaCambio.equals("")){
            int indice = adapadador.getPosition(tmpMonedaCambio);
            monedaCambioSP.setSelection(indice);
        }
    }

    public void clickConvert(View v){
        monedaActualSP = (Spinner) findViewById(R.id.MonedaActualSP);
        monedaCambioSP = (Spinner) findViewById(R.id.MonedaCambioSP);
        valorCambioET = (EditText)findViewById(R.id.ValorCambioET);
        resultadoTV = (TextView) findViewById(R.id.ResultadoTV);

        String monedaActual = monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());

        double resultado = procesarConversion(monedaActual, monedaCambio, valorCambio);

        if (resultado>0){
            resultadoTV.setText(String.format("Por %5.2 %s, usted recibira %5.2 %s", valorCambio, monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");

            SharedPreferences preferencias = getSharedPreferences("MiPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();

            editor.putString("monedaActual", monedaActual);
            editor.putString("monedaCambio", monedaCambio);

            editor.commit();
        }else
        {
            resultadoTV.setText(String.format("Usted Recibira"));
            Toast.makeText(MainActivityWA.this,"Las opciones elegidas no tiene un factor de conversor", Toast.LENGTH_SHORT).show();
        }
    }

    private double procesarConversion(String monedaActual, String monedaCambio, double valorCambio){

        double resultadoConversion = 0;

        switch (monedaActual){

            case "DOLAR":
                if(monedaCambio.equals("EURO"))
                    resultadoConversion = valorCambio * factorDolarEuro;

                if(monedaCambio.equals("PESO MEXICANO"))
                    resultadoConversion = valorCambio / factorPesoDolar;
                break;

            case "EURO":
                if(monedaCambio.equals("DOLAR"))
                    resultadoConversion = valorCambio / factorDolarEuro;
                break;

            case "PESO MEXICANO":
                if(monedaCambio.equals("DOLAR"))
                    resultadoConversion = valorCambio * factorDolarEuro;
                break;
        }

        return 0;
    }
}
