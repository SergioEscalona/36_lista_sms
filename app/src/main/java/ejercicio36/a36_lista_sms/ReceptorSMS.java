package ejercicio36.a36_lista_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.RequiresApi;
import android.telephony.SmsMessage;
import android.widget.Toast;

import modelo.GestionSMS;

public class ReceptorSMS extends BroadcastReceiver {

        @RequiresApi(api = 25)
        @Override
        public void onReceive(Context context, Intent intent) {
            String origen="";
            String mensaje="";
            Object[] trozos=(Object[])intent.getExtras().get("pdus");
            for(int i=0;i<trozos.length;i++){
                SmsMessage sms=SmsMessage.createFromPdu((byte[])trozos[i],"3gpp");
                mensaje+=sms.getMessageBody().toString();
                origen=sms.getOriginatingAddress();
            }
            GestionSMS gsms=new GestionSMS(context.getApplicationContext());
            gsms.altaSMS(origen,mensaje);
            gsms.cerrar();
        }
}
