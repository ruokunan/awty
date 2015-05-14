package edu.awty.ruokua.arewethere;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private boolean messageStart = false;
    private EditText message = (EditText) findViewById(R.id.message);
    private EditText phoneNumber = (EditText)findViewById(R.id.phone_number);
    private EditText interval = (EditText)findViewById(R.id.interval);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button start = (Button)findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messageStart) {
                    start.setText("Start");
                    messageStart = false;
                } else if(isFilledOut()) {
                    start.setText("Stop");
                    messageStart = true;

                }
            }
        });

    }

    /**
     * 
     */
    private void sendMessage() {

    }

    /**
     *
     * @return true if user filled out all the need information with
     * legitimate values
     */
    private boolean isFilledOut() {
        return  message.getText().toString().trim().length() != 0 &&
                phoneNumber.getText().toString().trim().length() != 0 &&
                interval.getText().toString().toString().length() !=0 &&
                Integer.parseInt(interval.getText().toString()) > 0;
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
