package com.example.tareas;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;



import java.util.List;


public class ListViewItemCheckboxBaseAdapter extends BaseAdapter {

    private List<ListViewItemDTO> listViewItemDtoList = null;
    private List<ListViewItemDTO> listViewItemDtoList2 = null;
    private List<ListViewItemDTO> listViewItemDtoList3 = null;
    private Context ctx = null;

    public ListViewItemCheckboxBaseAdapter(Context ctx, List<ListViewItemDTO> listViewItemDtoList,List<ListViewItemDTO> listViewItemDtoList2,List<ListViewItemDTO> listViewItemDtoList3) {
        this.ctx = ctx;
        this.listViewItemDtoList = listViewItemDtoList;
        this.listViewItemDtoList2 = listViewItemDtoList2;
        this.listViewItemDtoList3 = listViewItemDtoList3;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if(listViewItemDtoList!=null)
        {
            ret = listViewItemDtoList.size();
        }
        return ret;
    }

    public int getCount2() {
        int ret = 0;
        if(listViewItemDtoList2!=null)
        {
            ret = listViewItemDtoList2.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int itemIndex) {
        Object ret = null;
        if(listViewItemDtoList!=null) {
            ret = listViewItemDtoList.get(itemIndex);
        }
        return ret;
    }


    public Object getItem2(int itemIndex) {
        Object ret = null;
        if(listViewItemDtoList2!=null) {
            ret = listViewItemDtoList2.get(itemIndex);
        }
        return ret;
    }
    public Object getItem3(int itemIndex) {
        Object ret = null;
        if(listViewItemDtoList3!=null) {
            ret = listViewItemDtoList3.get(itemIndex);
        }
        return ret;
    }
    @Override
    public long getItemId(int itemIndex) {
        return itemIndex;
    }


    public long getItemId2(int itemIndex) {
        return itemIndex;
    }

    @Override
    public View getView(int itemIndex, View convertView, ViewGroup viewGroup) {

        ListViewItemViewHolder viewHolder = null;

        if(convertView!=null)
        {
            viewHolder = (ListViewItemViewHolder) convertView.getTag();
        }else
        {
            convertView = View.inflate(ctx, R.layout.activity_list_view_with_checkbox_item, null);

            CheckBox listItemCheckbox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkbox);

            TextView listItemText = (TextView) convertView.findViewById(R.id.list_view_item_text);
            TextView listItemText2 = (TextView) convertView.findViewById(R.id.list_view_item_text2);
            TextView listItemText3 = (TextView) convertView.findViewById(R.id.list_view_item_text3);

            viewHolder = new ListViewItemViewHolder(convertView);

            viewHolder.setItemCheckbox(listItemCheckbox);
            viewHolder.setItemTextView(listItemText);
            viewHolder.setItemTextView2(listItemText2);
            viewHolder.setItemTextView3(listItemText3);


            convertView.setTag(viewHolder);
        }

        ListViewItemDTO listViewItemDto = listViewItemDtoList.get(itemIndex);
        ListViewItemDTO listViewItemDto2 = listViewItemDtoList2.get(itemIndex);
        ListViewItemDTO listViewItemDto3 = listViewItemDtoList3.get(itemIndex);
        viewHolder.getItemCheckbox().setChecked(listViewItemDto.isChecked());
        viewHolder.getItemTextView().setText(listViewItemDto.getItemText());
        viewHolder.getItemTextView2().setText(listViewItemDto2.getItemText());
        viewHolder.getItemTextView3().setText(listViewItemDto3.getItemText());


        return convertView;
    }
}