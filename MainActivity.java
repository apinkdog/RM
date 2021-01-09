package com.example.sqlworm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EditText editTextName;
    Button btnClickHere;
    TextView textName;
    boolean jib = false;

    public void chez2() {
        data d = new data();
        d.chez();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = (EditText) findViewById(R.id.input);
        btnClickHere = (Button) findViewById(R.id.submit);
        textName = (TextView) findViewById(R.id.textname);


        editTextName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_UP) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key pres
                    ;

                    SQLiteDatabase mydatabase = getBaseContext().openOrCreateDatabase("mydatabase5.db", MODE_PRIVATE, null);
                    Cursor mycursor = mydatabase.rawQuery("SELECT * FROM rmtable;", null);
                    String mynewname = editTextName.getText().toString();
                    String postcoderead;
                    String streetnameread = "";
                    String roundread = "";
                    if (mycursor.moveToFirst()) {
                        do {
                            String readpostcode = mycursor.getString(0);
                            String readstreet = mycursor.getString(1);
                            String readround = mycursor.getString(2);
                            if (readpostcode.equalsIgnoreCase(mynewname)) {

                                postcoderead = readpostcode;
                                streetnameread = readstreet;
                                roundread = readround;
                                jib = true;
                            }

                        } while (mycursor.moveToNext());

                        if (jib) {
                            //textName.setText(streetnameread + "\n" + roundread);
                            Toast.makeText(MainActivity.this, streetnameread + "\n"  + roundread, Toast.LENGTH_LONG).show();
                            editTextName.setText("");
                        } else {
                            Toast.makeText(MainActivity.this, "Nothing Found.....Work Smart Not Hard", Toast.LENGTH_LONG).show();
                            textName.setText("");
                            editTextName.setText("");
                        }
                    }
                    mycursor.close();
                    mydatabase.close();
                    jib=false;
                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    );
                }
                return false;
            }
        });


        btnClickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase mydatabase = getBaseContext().openOrCreateDatabase("mydatabase5.db", MODE_PRIVATE, null);
                Cursor mycursor = mydatabase.rawQuery("SELECT * FROM rmtable;", null);
                String mynewname = editTextName.getText().toString();
                String postcoderead;
                String streetnameread = "";
                String roundread = "";
                mycursor = mydatabase.rawQuery("SELECT * FROM rmtable;", null);
                if (mycursor.moveToFirst()) {
                    do {
                        String readpostcode = mycursor.getString(0);
                        String readstreet = mycursor.getString(1);
                        String readround = mycursor.getString(2);
                        if (readpostcode.equalsIgnoreCase(mynewname)) {

                            postcoderead = readpostcode;
                            streetnameread = readstreet;
                            roundread = readround;
                            jib = true;
                        }

                    } while (mycursor.moveToNext());

                    if (jib) {
                        //textName.setText(streetnameread + "\n" + roundread);
                        Toast.makeText(MainActivity.this, streetnameread + "\n"  + roundread, Toast.LENGTH_LONG).show();
                        editTextName.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "Nothing Found.....Work Smart Not Hard", Toast.LENGTH_LONG).show();
                        textName.setText("");
                        editTextName.setText("");
                    }
                }
                mycursor.close();
                mydatabase.close();
                jib=false;
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );
            }
        });



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
