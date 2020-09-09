package com.example.ss4_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping extends AppCompatActivity {

    private Button bt_bPing;
    private TextView text_Result;
    private String ipBuscar;
    private int contadorP;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        bt_bPing = findViewById(R.id.bt_bPing);
        text_Result = findViewById(R.id.text_Results);
        contadorP = 0;

        ipBuscar = getIntent().getExtras().getString("IP");

        conectarDispositivo();

        bt_bPing.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
        );
    }

    private void conectarDispositivo() {
        try {
            InetAddress inetAddress = InetAddress.getByName(ipBuscar);

            Log.e("La ip buscada es",ipBuscar);

            String dispositivo = inetAddress.getHostAddress();

            Log.e("IP_dispo",dispositivo);
            new Thread(
                    ()->{
                        while(contadorP < 11){
                            try {
                                long timeI = System.currentTimeMillis();
                                boolean connected = inetAddress.isReachable(1000);
                                long timeF = System.currentTimeMillis()-timeI;
                                Log.e( "conectarDispositivo: ", ""+connected+" Timepo:"+timeF );
                                runOnUiThread(
                                        ()->{
                                            if(connected){
                                                text_Result.append("Recibido\n");
                                            }else{
                                                String mensaje = "Perdido\n";
                                                text_Result.append(mensaje);
                                            }
                                        }
                                );
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            contadorP++;
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        }
            ).start();



            /*
            if(connected){
                text_Result.setText("Conectado");
            }else{
                String mensaje = "Dirección IP no encontrada, inténtalo con otra";
                text_Result.setText(mensaje);
            }*/

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}