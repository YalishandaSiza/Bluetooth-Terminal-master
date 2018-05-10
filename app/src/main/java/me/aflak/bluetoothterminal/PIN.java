/*package me.aflak.bluetoothterminal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PIN extends AppCompatActivity {

    EditText PinEntry;
    Button EnterPasscode;
    Helper h;
    public String x = "123456";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        EnterPasscode = (Button) findViewById(R.id.Enter);
        EnterPasscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText h = (EditText) findViewById(R.id.PIN);
                if(h.getText().toString().equals(x)){
                    try {
                        Intent i = new Intent(PIN.this, Select.class);
                        startActivity(i);
                    } catch (Exception e) {
                        Toast.makeText(PIN.this, "LINE 32 Failure to initiate intent into Scan.class", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(PIN.this, "Incorrect Entry", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
*/