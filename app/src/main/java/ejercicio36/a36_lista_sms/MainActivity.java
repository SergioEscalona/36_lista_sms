package ejercicio36.a36_lista_sms;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

import modelo.GestionSMS;

public class MainActivity extends AppCompatActivity {
    ListView lstSMS;
    int pos;
    TextView tvOrigen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstSMS=(ListView)this.findViewById(R.id.lstSMS);
        tvOrigen=(TextView) this.findViewById(R.id.tvOrigen);
        lstSMS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //asignamos a la variable pos la posición del item pulsado
                //para que pueda ser utilizada desde el manejador del evento
                //del botón si.
                pos=position;
                AlertDialog.Builder cuadro=new AlertDialog.Builder(MainActivity.this);
                cuadro.setTitle("Mandar SMS");
                cuadro.setMessage("¿Desea mandar un SMS a este número?");
                cuadro.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,MandarActivity.class);
                        intent.putExtra("origen",tvOrigen.getText());
                        MainActivity.this.startActivity(intent);

                    }
                });
                cuadro.setNegativeButton(android.R.string.no,null);
                cuadro.show();
            }
        });
    }

    public void mostrar (View v){
        cargarLista();
    }

    public void borrar (View v){
        GestionSMS gloc=new GestionSMS(this);
        gloc.eliminarMensajes();
        gloc.cerrar();
        //para vaciar también el ListView
        lstSMS.setAdapter(null);
    }

    public void cargarLista(){
        GestionSMS gsms=new GestionSMS(this);
        SimpleCursorAdapter adp=new SimpleCursorAdapter(this,
                R.layout.fila,
                gsms.obtenerMensajes(),
                new String[]{"origen","mensaje"},
                new int[]{R.id.tvOrigen,R.id.tvMensaje},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //asignamos el adaptador al ListView
        lstSMS.setAdapter(adp);
        gsms.cerrar();
    }

    class Comunicacion extends AsyncTask<String,Void,ArrayList<Lugar>> {
        @Override
        protected void onPostExecute(ArrayList<Lugar> lugares) {
            ArrayAdapter<Lugar> adp=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1, lugares);
            lstLugares.setAdapter(adp);

        }

        @Override
        protected ArrayList<Lugar> doInBackground(String... params) {
            //creamos un objeto que encapsula las tareas de comunicacion
            GestionComunicacion gcomunicacion=new GestionComunicacion();
            //recuperamos un arraylist de lugares devuelto por el servidor
            return gcomunicacion.buscadorLugares(params[0]);
        }
    }
}