package ejercicio36.a36_lista_sms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MandarActivity extends AppCompatActivity {
    TextView tvOrigen;
    EditText edtMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandar);
        tvOrigen=(TextView)this.findViewById(R.id.tvOrigen2);
        edtMensaje=(EditText)this.findViewById(R.id.edtMensaje);
        Intent intent=this.getIntent();
        String origen=intent.getStringExtra("origen");
        tvOrigen.setText(origen);
    }

    public void enviar (View v){
        SmsManager manager=SmsManager.getDefault();
        manager.sendTextMessage(tvOrigen.getText().toString(),
                                "mi numero",
                                edtMensaje.getText().toString(),
                                null,
                                null);
    }
}
