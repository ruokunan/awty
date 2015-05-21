package edu.awty.ruokua.arewethere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ruokua on 5/13/15.
 */
public class AlarmReceiver extends BroadcastReceiver {
    public static final String MESSAGE = "message";
    public static final String PHONE_NUMBER = "phoneNumber";
    @Override
    public void onReceive(Context context, Intent intent) {
        assert (intent != null);
        
        String message = intent.getStringExtra(MESSAGE);
        
        String phone = intent.getStringExtra(PHONE_NUMBER);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, msg, null, null);

       
       
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}
