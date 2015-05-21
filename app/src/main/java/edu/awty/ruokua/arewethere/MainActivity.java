package edu.awty.ruokua.arewethere;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private boolean messageStart = false;
    private EditText message;
    private EditText phoneNumber;
    private EditText interval;
    private AlarmManager manager;
    private PendingIntent pendingIntent;
    private static final String START_TEXT = "Start";
    private static final String STOP_TEXT = "Stop";
    private static int requestCode = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        interval = (EditText) findViewById(R.id.interval);
        message = (EditText) findViewById(R.id.message);


        assert (phoneNumber != null);
        assert (interval != null);
        assert (message != null);

        final Button start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageStart) {
                    start.setText(START_TEXT);
                    messageStart = false;
                    messageStop();
                } else if (isFilledOut()) {
                    start.setText(STOP_TEXT);
                    messageStart = true;
                    messageStart();
                }
            }
        });

    }



    /**
     * @return true if user filled out all the need information with
     * legitimate values
     */
    private boolean isFilledOut() {
        return (message.getText().toString().trim().length() != 0) &&
                (phoneNumber.getText().toString().trim().length() != 0) &&
                (interval.getText().toString().trim().length() != 0) &&
                (Integer.parseInt(interval.getText().toString()) > 0) &&
                PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber.
                        getText().toString());
    }


    public void messageStart() {

        long repeatInterval = TimeUnit.MINUTES.
                toMillis(Integer.parseInt(interval.getText().toString()));


        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra(AlarmReceiver.MESSAGE, message.getText().toString());
        alarmIntent.putExtra(AlarmReceiver.PHONE_NUMBER, phoneNumber.getText().toString());

        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, requestCode, alarmIntent,
              0);

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                repeatInterval, pendingIntent);
        Toast.makeText(this, "Alarm Start", Toast.LENGTH_LONG).show();

    }

    // Stop the alarm.
    public void messageStop() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert (pendingIntent != null);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_LONG).show();
    }

   @Override
   protected void onDestroy(){
       super.onDestroy();
       AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
       assert (pendingIntent != null);
       manager.cancel(pendingIntent);
       Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_LONG).show();
   }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
