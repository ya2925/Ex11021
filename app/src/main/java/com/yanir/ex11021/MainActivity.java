package com.yanir.ex11021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Yanir Aton
 * @version 1.0
 * @since 2023-11-02
 * this is the main activity
 * it handles the the buttons, the editText and the TextView
 * it has 2 buttons one for displaying the contents of the raw file in the textView
 * and one for displaying the contents of the editText in the textView
 */
public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "rawtext.txt";
    String fileName;
    int resourceId;
    TextView textTV;
    EditText textET;
    Intent si;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTV = (TextView) findViewById(R.id.textTV);
        textET = (EditText) findViewById(R.id.textET);

        fileName = FILENAME.substring(0, FILENAME.length() - 4);
        resourceId = this.getResources().getIdentifier(fileName, "raw", this.getPackageName());
    }

    /**
     * Reads the text from a raw resource file and sets it on the TextView.
     *
     * @param v The button that is clicked to trigger this function
     * @throws IOException If there is an error reading the file.
     */
    public void readFromFile(View v) {
        try {
            // Open the raw resource file.
            InputStream iS = this.getResources().openRawResource(resourceId);
            InputStreamReader iSR = new InputStreamReader(iS);
            BufferedReader bR = new BufferedReader(iSR);

            // Create a StringBuilder object to store the contents of the file.
            StringBuilder sB = new StringBuilder();

            // Read each line of the file and append it to the StringBuilder object.
            String line;
            while ((line = bR.readLine()) != null) {
                sB.append(line + '\n');
            }

            // Close the BufferedReader, InputStreamReader, and InputStream objects.
            bR.close();
            iSR.close();
            iS.close();

            // Set the text of the textTV TextView object to the contents of the StringBuilder object.
            textTV.setText(sB.toString());
        } catch (IOException e) {
            Log.e("MainActivity","ERROR reading");
        }
    }

    /**
     * Reads the text from the EditText and sets it on the TextView.
     *
     * @param v The button that is clicked to trigger this function
     */
    public void readFromEditText(View v) {
        textTV.setText(textET.getText());
    }

    /**
     * This function presents the options menu for moving between activities.
     * @param menu The options menu in which you place your items.
     * @return true in order to show the menu, otherwise false.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        System.out.println(item.getTitle().toString());
        if (item.getTitle().toString().equals("credit")){
            si = new Intent(this, credit.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(item);
    }
}