package com.example.miniproyecto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;

    private ListView listreply;
    private  ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listreply = (ListView) findViewById(R.id.listView2);
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
    }

    //En este metodo llamamos ala segunda activity
    public void launchList(View view) {

        Intent intent = new Intent(this, lista.class);
        startActivityForResult(intent,1);

    }

    //En este metodo se devuelve el el item que se agregara al carrito
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

       if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
               adaptador.add(data.getStringExtra(lista.EXTRA_REPLY));
                listreply.setAdapter(adaptador);
            }
        }
    }


}
