package modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sergio on 07/04/2017.
 */

public class GestionSMS {
    private Helper helper;
    private SQLiteDatabase bd;
    public GestionSMS(Context ctx){
        helper=new Helper(ctx,"comunicacion");
        bd=helper.getWritableDatabase();
    }
    public void altaSMS(String origen,String mensaje){
        ContentValues valores=new ContentValues();
        valores.put("origen",origen);
        valores.put("mensaje",mensaje);
        bd.insert("comunicacion",null,valores);
    }

    public Cursor obtenerMensajes(){
        return bd.query("comunicacion",null,null,null,null,null,null);
    }

    public void eliminarMensajes(){
        bd.delete("comunicacion",null,null);
    }
    public void cerrar(){
        helper.close();
    }
}


