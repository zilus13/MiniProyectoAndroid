package com.example.tareas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Jerry on 1/21/2018.
 */

public class ListViewItemViewHolder extends RecyclerView.ViewHolder {

    private CheckBox itemCheckbox;

    private TextView itemTextView;
    private TextView itemTextView2;
    private TextView itemTextView3;

    public ListViewItemViewHolder(View itemView) {
        super(itemView);
    }

    public CheckBox getItemCheckbox() {
        return itemCheckbox;
    }

    public void setItemCheckbox(CheckBox itemCheckbox) {
        this.itemCheckbox = itemCheckbox;
    }

    public TextView getItemTextView() {
        return itemTextView;
    }
    public TextView getItemTextView2() {
        return itemTextView2;
    }
    public TextView getItemTextView3() {
        return itemTextView3;
    }

    public void setItemTextView(TextView itemTextView) {
        this.itemTextView = itemTextView;
    }
    public void setItemTextView2(TextView itemTextView2) {
        this.itemTextView2 = itemTextView2;
    }
    public void setItemTextView3(TextView itemTextView3) {
        this.itemTextView3 = itemTextView3;
    }
}