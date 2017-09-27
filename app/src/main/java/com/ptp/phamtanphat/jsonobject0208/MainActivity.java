package com.ptp.phamtanphat.jsonobject0208;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView txtjson;
    Button btnreadjson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtjson = (TextView) findViewById(R.id.textViewjson);
        btnreadjson = (Button) findViewById(R.id.buttonjsonobject);
        btnreadjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetJsonObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
            }
        });
    }
    public class GetJsonObject extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return Doc_du_lieu_Url(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObjectkhoapham = new JSONObject(s);

                String monhoc = jsonObjectkhoapham.getString("monhoc");
                String noihoc = jsonObjectkhoapham.getString("noihoc");
                String website = jsonObjectkhoapham.getString("website");
                String fanpage = jsonObjectkhoapham.getString("fanpage");
                String logo = jsonObjectkhoapham.getString("logo");

                txtjson.setText(monhoc + "\n" + noihoc +"\n" + website + "\n" + fanpage+ "\n"+logo+ "\n");
//                Toast.makeText(MainActivity.this, monhoc, Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }
    public static String Doc_du_lieu_Url(String duongdan){
        StringBuilder content = new StringBuilder();
        try {

            URL url = new URL(duongdan);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                content.append(line+"\n");
            }

            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
