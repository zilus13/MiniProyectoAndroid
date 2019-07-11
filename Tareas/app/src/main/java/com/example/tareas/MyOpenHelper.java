package com.example.tareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.text.format.Time;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE comments(_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, comment TEXT, hour TEXT,status TEXT)";
    private static final String DB_NAME = "comments.sqlite";
    private static final int DB_VERSION = 1;
    private SQLiteDatabase db;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMMENTS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Insertar un nuevo comentario
    public void insertar(String nombre, String comentario,String hora){
        ContentValues cv = new ContentValues();
        cv.put("user", nombre);
        cv.put("comment", comentario);
        cv.put("hour", hora);
        cv.put("status", "true");
        db.insert("comments", null, cv);
    }

    //Borrar un comentario a partir de su id
    public void borrar(String titulo){
        String[] args = new String[]{titulo};
        db.delete("comments", "user=?", args);
    }


    //modificar un comentario a partir de su titulo
    public void modificar(String nombre){

        Time time = new Time(); time.setToNow();
        Time t = new Time(Time.getCurrentTimezone()); t.setToNow(); String date = t.format("%Y/%m/%d");
        System.out.println(" sdsdsddddddd hola: " + time.hour+":"+time.minute);
        String[] args = new String[]{nombre};


        ContentValues cv = new ContentValues();
        cv.put("status","false");
        cv.put("hour",time.hour+":"+time.minute);
        cv.put("comment",date);
       db.update("comments",cv,"user=?",args);

    }

    //Obtener la lista de comentarios en la base de datos
    public ArrayList<Comentario> getComments(){
        //Creamos el cursor
        ArrayList<Comentario> lista=new ArrayList<Comentario>();
        Cursor c = db.rawQuery("select _id, user,comment from comments where status == null ", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("user"));
                String comment = c.getString(c.getColumnIndex("comment"));
                String hour = c.getString(c.getColumnIndex("hour"));
                int id=c.getInt(c.getColumnIndex("_id"));
                Comentario com =new Comentario(id,user,comment,hour);
                //Añadimos el comentario a la lista
                lista.add(com);
            } while (c.moveToNext());
        }

        //Cerramos el cursor
        c.close();
        return lista;
    }


    //Obtener la lista de comentarios en la base de datos
    public String[] getTitulo(){
        //Creamos el cursor
      //  String itemTextArr[];

        Cursor c = db.rawQuery("select _id, user,comment from comments where status == 'true'", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("user"));
                itemTextArr[i]= user;
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }

    //Obtener la lista de comentarios en la base de datos
    public String[] getFecha(){
        //Creamos el cursor
        //  String itemTextArr[];

        Cursor c = db.rawQuery("select _id, user,comment,status from comments where status =='true' ", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("comment"));
                String status = c.getString(c.getColumnIndex("status"));
                if (status !="true"){
                itemTextArr[i]= user;
                }
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }
    //Obtener la lista de comentarios en la base de datos
    public String[] getHour(){
        //Creamos el cursor
        //  String itemTextArr[];

        Cursor c = db.rawQuery("select _id, user,comment,hour from comments where status == 'true'", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("hour"));
                itemTextArr[i]= user;
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }




    //Obtener la lista de comentarios en la base de datos
    public String[] getTitulo2(){
        //Creamos el cursor
        //  String itemTextArr[];

        Cursor c = db.rawQuery("select _id, user,comment from comments where status == 'false'", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("user"));
                itemTextArr[i]= user;
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }

    //Obtener la lista de comentarios en la base de datos
    public String[] getFecha2(){
        //Creamos el cursor


        Cursor c = db.rawQuery("select _id, user,comment,status from comments where status =='false' ", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("comment"));
                String status = c.getString(c.getColumnIndex("status"));
                if (status !="true"){
                    itemTextArr[i]= user;
                }
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }
    //Obtener la lista de comentarios en la base de datos
    public String[] getHour2(){
        //Creamos el cursor
        //  String itemTextArr[];

        Cursor c = db.rawQuery("select _id, user,comment,hour from comments where status == 'false'", null);
        String itemTextArr[]= new String[c.getCount()];
        if (c != null && c.getCount()>0) {

            int i=0;
            c.moveToFirst();
            do {
                //Asignamos el valor en nuestras variables para crear un nuevo objeto Comentario
                String user = c.getString(c.getColumnIndex("hour"));
                itemTextArr[i]= user;
                i++;
            } while (c.moveToNext());
        }


        //Cerramos el cursor
        c.close();
        return itemTextArr;
    }

}