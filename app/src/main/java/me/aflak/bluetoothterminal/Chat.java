package me.aflak.bluetoothterminal;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import me.aflak.bluetooth.Bluetooth;

public class Chat extends AppCompatActivity implements Bluetooth.CommunicationCallback {

    private String name;
    private Bluetooth b;
    private EditText message;
    private Button send;
    private TextView text;
    private ScrollView scrollView;
    private boolean registered=false;
    public int volume = 5;
    private ImageButton hornup, horndown, cover, power, bridge, doublebim, bimini;
    public boolean isDoubleBimActive;
    public boolean getOpLogVal;
    private ToggleButton OpLogTog;
    Vibrator vibr;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        text = (TextView)findViewById(R.id.text);
        message = (EditText)findViewById(R.id.message);
        send = (Button)findViewById(R.id.send);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        power = (ImageButton) findViewById(R.id.btnPower);
        cover = (ImageButton) findViewById(R.id.btnCover);
        bridge = (ImageButton) findViewById(R.id.btnBridge);
        doublebim = (ImageButton) findViewById(R.id.btnDouble);
        bimini = (ImageButton) findViewById(R.id.btnBimini);
        hornup = (ImageButton) findViewById(R.id.btnHornVolUP);
        horndown = (ImageButton) findViewById(R.id.btnHornVolDOWN);
        OpLogTog = (ToggleButton) findViewById(R.id.OpLogTog);






        text.setMovementMethod(new ScrollingMovementMethod());
        send.setEnabled(false);
        power.setEnabled(false);
        cover.setEnabled(false);
        bridge.setEnabled(false);
        doublebim.setEnabled(false);
        bimini.setEnabled(false);
        hornup.setEnabled(false);
        horndown.setEnabled(false);




        b = new Bluetooth(this);
        b.enableBluetooth();

        b.setCommunicationCallback(this);

        int pos = getIntent().getExtras().getInt("pos");
        name = b.getPairedDevices().get(pos).getName();

        Display("Connecting...");
        b.connectToDevice(b.getPairedDevices().get(pos));




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = message.getText().toString();
                message.setText("");
                b.send(msg);
                vibr.vibrate(28);
                Display("You: "+msg);
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "power9";
                message.setText("");
                b.send(msg);
                vibr.vibrate(28);
                Display("You: "+msg);
            }
        });
        cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "cover9";
                message.setText("");
                b.send(msg);
                vibr.vibrate(28);
                Display("You: "+msg);

            }
        });
        bridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "bridge9";
                message.setText("");
                b.send(msg);
                vibr.vibrate(28);
                Display("You: "+msg);

            }
        });
        doublebim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDoubleBimActive;
                String msg = "doublebim9";
                message.setText("");
                b.send(msg);
                vibr.vibrate(28);
                Display("You: "+msg);

            }
        });
        bimini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "bimini9";
                message.setText("");
                b.send(msg);
                Toast.makeText(Chat.this, "hjiugui", Toast.LENGTH_SHORT).show();
                vibr.vibrate(28);
                Display("You: "+msg);

            }
        });
        hornup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(volume <= 9) {
                    volume++;

                    Toast.makeText(Chat.this, "Current Volume: " + String.valueOf(volume), Toast.LENGTH_SHORT).show();
                    vibr.vibrate(28);
                } else {
                    Toast.makeText(Chat.this, "Maximum Volume Met: " + String.valueOf(volume),  Toast.LENGTH_SHORT).show();
                }

            }
        });
        horndown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(volume > 1) {
                    volume--;

                    Toast.makeText(Chat.this, "Current Volume: " + String.valueOf(volume), Toast.LENGTH_SHORT).show();
                    vibr.vibrate(28);
                } else {
                    Toast.makeText(Chat.this, "Minimum Volume Met: " + String.valueOf(volume), Toast.LENGTH_SHORT).show();
                }

            }
        });

        OpLogTog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    TextView msg = (TextView)findViewById(R.id.text);
                    msg.setVisibility(View.VISIBLE);
                    vibr.vibrate(28);
                } else {
                    TextView msg = (TextView)findViewById(R.id.text);
                    msg.setVisibility(View.INVISIBLE);
                    vibr.vibrate(28);
                }
            }
        });
        AlertDialog alertDialog = new AlertDialog.Builder(Chat.this).create();
        alertDialog.setTitle("Welcome to the Easy Cover Experience!");
        alertDialog.setMessage("    When using the Double Bimini position, make certain that the Double Bimini Support Arms are correctly fashioned. \n " +
                "\n" +
                "   Refrain from operating the Easy Cover when in motion. " +
                "\n" +
                "   Please stay within 30 feet of the Easy Cover for optimal performance." +
                "\n" +
                "\n" +
                "Merry Pontooning!" +
                "\n" +
                "\n" +
                "Vision Marine Products");
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();


        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);
        registered=true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(registered) {
            unregisterReceiver(mReceiver);
            registered=false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.AboutDeveloper:
                Intent i = new Intent(this, Developer.class);
                startActivity(i);
                finish();
                return true;
            case R.id.close:
                b.removeCommunicationCallback();
                b.disconnect();
                Intent intent = new Intent(this, Select.class);
                startActivity(intent);
                finish();
                return true;

            /*case R.id.rate:
                Uri uri = Uri.parse("market://details?id=" + this.getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                }
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void Display(final String s){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Helper h;

                if(getOpLogVal = true) {

                    text.append(s + "\n");
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }else{
                    Toast.makeText(Chat.this, s, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onConnect(BluetoothDevice device) {
        Display("Connected to "+device.getName()+" - "+device.getAddress());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                send.setEnabled(true);
                power.setEnabled(true);
                cover.setEnabled(true);
                bridge.setEnabled(true);
                doublebim.setEnabled(true);
                bimini.setEnabled(true);
                hornup.setEnabled(true);
                horndown.setEnabled(true);


            }
        });


    }

    @Override
    public void onDisconnect(BluetoothDevice device, String message) {
        Display("Disconnected!");
        Display("Connecting again...");
        b.connectToDevice(device);
    }

    @Override
    public void onMessage(String message) {

            Display(name + ": " + message);
        }


    @Override
    public void onError(String message) {
        Display("Error: "+message);
    }

    @Override
    public void onConnectError(final BluetoothDevice device, String message) {
        Display("Error: "+message);
        Display("Trying again in 3 sec.");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.connectToDevice(device);
                    }
                }, 2000);
            }
        });
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                Intent intent1 = new Intent(Chat.this, Select.class);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        if(registered) {
                            unregisterReceiver(mReceiver);
                            registered=false;
                        }
                        startActivity(intent1);
                        finish();
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        if(registered) {
                            unregisterReceiver(mReceiver);
                            registered=false;
                        }
                        startActivity(intent1);
                        finish();
                        break;
                }
            }
        }
    };
}
