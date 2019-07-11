package com.example.tareas;

//Clase para representar un comentario
public class Comentario {
    //Campos correspondientes a la base de datos
    int id;
    String nombre;
    String comentario;
    String hora;

    //Constructor
    public Comentario(int _id, String _nombre, String _comentario, String _hora){
        id=_id;
        nombre=_nombre;
        comentario=_comentario;
        hora=_hora;
    }

    //Represetacion del objeto como cadena de texto
    @Override
    public String toString() {
        return nombre;
    }

    //Metodos de acceso a cada atribito de la clase
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public String getComentario(){
        return comentario;
    }


}
