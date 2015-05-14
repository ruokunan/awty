package edu.awty.ruokua.arewethere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ruokua on 5/13/15.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String MESSAGE = "message";

    @Override
    public void onReceive(Context context, Intent intent) {
        assert (intent != null);
        String message = intent.getStringExtra(MESSAGE);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


}