package consulta_temperatura_com_historico.example.dispositivosmoveis.consulta_temperatura_com_historico_2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private List<Button> buttonList = new ArrayList<>();
    public ArrayAdapter<Button> buttonArrayAdapter;
    private ListView buttonListView;
    //private int i;
    private EditText locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        locationEditText = findViewById(R.id.locationEditText);
        buttonListView = findViewById(R.id.buttonListView);
        buttonArrayAdapter = new ButtonArrayAdapter(this, buttonList, locationEditText);
        buttonListView.setAdapter(buttonArrayAdapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                String textButton = locationEditText.getText().toString();
                new getButtonView().execute(textButton);
                //aaa
            }
        });
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

    private class getButtonView extends
            AsyncTask<String, Void, Void> {
        protected void onPostExecute(Void aVoid) {
            buttonArrayAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(String... strings) {
            String textButton = strings[0];
            Button w = new Button(getApplicationContext());
            w.setText(textButton);
            buttonList.add(w);
            return null;
        }
    }
}

