package com.example.ss4_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost extends AppCompatActivity {

    private Button bt_bHost;
    private TextView text_IPs;
    private int f_IP;
    private boolean probar_ips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_host);


        bt_bHost = findViewById(R.id.bt_bHost);
        text_IPs = findViewById(R.id.text_IPs);
        probar_ips = true;

        if(probar_ips){
            buscarIPs();
            probar_ips = false;
        }



        bt_bHost.setOnClickListener(
                (v)->{
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
        );


    }

    private void buscarIPs(){
        new Thread(

                ()->{
                    f_IP = 0;
                    while(f_IP < 255){
                        try {
                            InetAddress inetAddress = InetAddress.getByName("192.168.0."+f_IP);
                            boolean conectado = inetAddress.isReachable(400);
                            if(conectado){
                                runOnUiThread(
                                        ()->{
                                            Log.e( "buscarIPs: ","192.168.0."+f_IP+"\n" );
                                            text_IPs.append("192.168.0."+f_IP+"\n");
                                        }
                                );
                            }
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        f_IP++;
                        try {
                            Thread.sleep(450);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
        ).start();
    }
}