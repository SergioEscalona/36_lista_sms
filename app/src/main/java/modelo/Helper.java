package modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergio on 07/04/2017.
 */


public class Helper extends SQLiteOpenHelper {

    public Helper(Context ctx, String nombre){
        super(ctx,nombre,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //montamos la instrucciÃ³n SQL de creaciÃ³n de la tabla
        String sqlCreaTabla="create table sms (";
        sqlCreaTabla+="_id integer primary key autoincrement,";
        sqlCreaTabla+="origen text,";
        sqlCreaTabla+="mensaje text)";
        //ejecutamos la instruccion
        db.execSQL(sqlCreaTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

