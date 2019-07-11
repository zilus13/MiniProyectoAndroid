package com.example.tareas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class ListViewWithCheckboxActivity extends AppCompatActivity {


    //Declaración del spinner y su Adapter
    private Spinner spinComentarios;
    private ArrayAdapter spinnerAdapter;

    //Lista de comentarios y comentario actual
    private ArrayList<Comentario> lista;
    private Comentario c;

    //Controlador de bases de datos
    private MyOpenHelper db;

    private EditText editNombre;
    private EditText editComentario;
    private EditText edithora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNombre = (EditText) findViewById(R.id.editText4);
        editComentario = (EditText) findViewById(R.id.editText5);
        edithora = (EditText) findViewById(R.id.editText6);
        //setContentView(R.layout.activity_list_view_with_checkbox);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Insertamos un nuevo elemento en base de datos
                if (editNombre.getText().toString().equals("")  || editComentario.getText().toString().equals("")  || edithora.getText().toString().equals("") ) {
                    Snackbar.make(view, "Los campos no pueden estar vacios", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {
                    db.insertar(editNombre.getText().toString(), editComentario.getText().toString(), edithora.getText().toString());
                    editNombre.setText("Titulo");
                    editComentario.setText("fecha");
                    edithora.setText("hora");
                    Snackbar.make(view, "Se agregro la tarea", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });


        //Iniciamos el controlador de la base de datos
        db = new MyOpenHelper(this);
        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView) findViewById(R.id.list_view_with_checkbox);

        // Initiate listview data.
        final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();
        final List<ListViewItemDTO> initItemList2 = this.getInitViewItemDtoList2();
        final List<ListViewItemDTO> initItemList3 = this.getInitViewItemDtoList3();

        // Create a custom list view adapter with checkbox control.
        final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList, initItemList2, initItemList3);

        listViewDataAdapter.notifyDataSetChanged();

        // Set data adapter to list view.
        listViewWithCheckbox.setAdapter(listViewDataAdapter);

        // When list view item is clicked.
        listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                // Get user selected item.
                Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                // Translate the selected item to DTO object.
                ListViewItemDTO itemDto = (ListViewItemDTO) itemObject;

                // Get the checkbox.
                CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                // Reverse the checkbox and clicked item check state.
                if (itemDto.isChecked()) {
                    itemCheckbox.setChecked(false);
                    itemDto.setChecked(false);
                } else {
                    itemCheckbox.setChecked(true);
                    itemDto.setChecked(true);
                }

                //Toast.makeText(getApplicationContext(), "select item text : " + itemDto.getItemText(), Toast.LENGTH_SHORT).show();
            }
        });

        // Click this button to select all listview items with checkbox checked.
        Button selectAllButton = (Button) findViewById(R.id.list_select_all);
        selectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for (int i = 0; i < size; i++) {
                    ListViewItemDTO dto = initItemList.get(i);
                    dto.setChecked(true);
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to disselect all listview items with checkbox unchecked.
        Button selectNoneButton = (Button) findViewById(R.id.list_select_none);
        selectNoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for (int i = 0; i < size; i++) {
                    ListViewItemDTO dto = initItemList.get(i);
                    dto.setChecked(false);
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to reverse select listview items.
        Button selectReverseButton = (Button) findViewById(R.id.list_select_reverse);
        selectReverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int size = initItemList.size();
                for (int i = 0; i < size; i++) {
                    ListViewItemDTO dto = initItemList.get(i);

                    if (dto.isChecked()) {
                        dto.setChecked(false);
                    } else {
                        dto.setChecked(true);
                    }
                }

                listViewDataAdapter.notifyDataSetChanged();
            }
        });

        // Click this button to remove selected items from listview.
        Button selectRemoveButton = (Button) findViewById(R.id.list_remove_selected_rows);
        selectRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(ListViewWithCheckboxActivity.this).create();
                alertDialog.setMessage("Are you sure to remove selected listview items?");

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        int size = initItemList.size();
                        for (int i = 0; i < size; i++) {
                            ListViewItemDTO dto = initItemList.get(i);

                            if (dto.isChecked()) {
                                System.out.println("Esto sale en pantalladdSASSSSSSSSSS:::" + initItemList.get(i).getItemText());
                                db.modificar(initItemList.get(i).getItemText());

                                initItemList.get(i).getItemText();

                                initItemList.remove(i);

                                i--;
                                size = initItemList.size();
                            }
                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                alertDialog.show();
            }
        });

    }

    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList() {
        String[] lista = db.getTitulo();
        //String itemTextArr[] = {"Tarea1", "Tarea2", "Tarea3", "Tarea4", "Tarea5", "Tarea5", "Tarea5", "Tarea5", "Tarea5", "Tarea5"};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        //int length = itemTextArr.length;
        int length = lista.length;
        for (int i = 0; i < length; i++) {
            //  String itemText = itemTextArr[i];
            String itemText = lista[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList2() {
        String[] lista = db.getFecha();
        //String itemTextArr[] = {"12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19", "12/12/19","12/12/19"};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = lista.length;

        for (int i = 0; i < length; i++) {
            String itemText = lista[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

    // Return an initialize list of ListViewItemDTO.
    private List<ListViewItemDTO> getInitViewItemDtoList3() {
        //  String itemTextArr[] = {"00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00", "00:00","12/12/19"};
        String[] lista = db.getHour();
        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = lista.length;

        for (int i = 0; i < length; i++) {
            String itemText = lista[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Toast por defecto2", Toast.LENGTH_SHORT);

            toast1.show();

            Intent intent = new Intent (getApplicationContext(), secondPage.class);
            startActivityForResult(intent, 0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
