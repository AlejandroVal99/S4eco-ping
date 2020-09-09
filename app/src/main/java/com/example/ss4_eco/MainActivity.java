package com.example.ss4_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt_Ping, bt_Host;
    private EditText text_n1, text_n2, text_n3, text_n4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Ping = findViewById(R.id.bt_Ping);
        bt_Host = findViewById(R.id.bt_Host);
        text_n1 = findViewById(R.id.text_1n);
        text_n2 = findViewById(R.id.text_2n);
        text_n3 = findViewById(R.id.text_n3);
        text_n4 = findViewById(R.id.text_4n);

        bt_Host.setOnClickListener(
                (v)->{
                    Intent p = new Intent(this, LocalHost.class);
                    startActivity(p);

                }
        );

        bt_Ping.setOnClickListener(
                (v)->{
                    boolean vacios = text_n1.getText().toString().trim().isEmpty() || text_n2.getText().toString().trim().isEmpty()
                            || text_n3.getText().toString().trim().isEmpty() || text_n4.getText().toString().trim().isEmpty();

                    if(vacios){

                        Toast toast = Toast.makeText(this,"Llena todos los campos", Toast.LENGTH_LONG);
                        toast.show();
                    }else{
                        String ipBuscar = text_n1.getText().toString()+"."+text_n2.getText().toString()+"."+text_n3.getText().toString()+"."+text_n4.getText().toString();

                        Intent h = new Intent(this, Ping.class );
                        h.putExtra("IP",ipBuscar);
                        startActivity(h);
                    }
                }
        );
    }


}