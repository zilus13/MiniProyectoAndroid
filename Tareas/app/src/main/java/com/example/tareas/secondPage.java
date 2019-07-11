package com.example.tareas;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class secondPage extends AppCompatActivity {

    //Controlador de bases de datos
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);


        //Iniciamos el controlador de la base de datos
        db = new MyOpenHelper(this);
        // Get listview checkbox.
        final ListView listViewWithCheckbox = (ListView) findViewById(R.id.list_view_with_checkbox2);


        final List<ListViewItemDTO> initItemList = this.getInitViewItemDtoList();
        final List<ListViewItemDTO> initItemList2 = this.getInitViewItemDtoList2();
        final List<ListViewItemDTO> initItemList3 = this.getInitViewItemDtoList3();

        // Create a custom list view adapter with checkbox control.
        final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList, initItemList2, initItemList3);
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
        Button selectAllButton = (Button) findViewById(R.id.list_select_all2);
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
        Button selectNoneButton = (Button) findViewById(R.id.list_select_none2);
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
        Button selectReverseButton = (Button) findViewById(R.id.list_select_reverse2);
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
        Button selectRemoveButton = (Button) findViewById(R.id.list_remove_selected_rows2);
        selectRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = new AlertDialog.Builder(secondPage.this).create();
                alertDialog.setMessage("Are you sure to remove selected listview items?");

                alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        int size = initItemList.size();
                        for (int i = 0; i < size; i++) {
                            ListViewItemDTO dto = initItemList.get(i);

                            if (dto.isChecked()) {
                                System.out.println("Esto sale en pantalladdSASSSSSSSSSS:::" + initItemList.get(i).getItemText());
                                db.borrar(initItemList.get(i).getItemText());

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
        String[] lista = db.getTitulo2();
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
        String[] lista = db.getFecha2();
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
        String[] lista = db.getHour2();
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
}
