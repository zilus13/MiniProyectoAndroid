package com.example.miniproyecto1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class lista extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.extra.REPLY";

    private EditText mReply;
    public String articulos[] = {
            "Queso",
            "Mortadela",
            "Pasta",
            "Mantequilla",
            "Arroz",
            "Pollo",
            "Carne",
            "Pan",
            "Jamon",
            "Agua",
            "Coca-Cola",
            "Pepsi",
            "Tang",
            "Atun",
            "Cebolla",
            "Tomate",
            "Platano",
            "Ajo",
            "Harina",
            "Azucar",
            "Cafe"
    };
    public ListView lista;
    public ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        lista = (ListView) findViewById(R.id.listView);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adaptador.addAll(articulos);
        lista.setAdapter(adaptador);

        //Aqui de agrega los listener a cada item de la lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Extraemos el valor de la lista
                String itemval = (String) lista.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Agregado al carrito", Toast.LENGTH_LONG).show();

                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_REPLY, itemval);

                //Aqui devolvemos el resultado al main activity
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }


}
