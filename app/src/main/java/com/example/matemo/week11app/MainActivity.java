package com.example.matemo.week11app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextUsername, editTextPassword, editTextConfPassword;
    Button btnRegister;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfPassword = (EditText) findViewById(R.id.editTextConfPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtResult = (TextView) findViewById(R.id.txtResult);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().equals("") || editTextUsername.getText().toString().equals("") || editTextPassword.getText().toString().equals("") || editTextConfPassword.getText().toString().equals(""))
                {
                    txtResult.setText("Inputan tidak boleh kosong");
                }
                else if(!editTextPassword.getText().toString().equals(editTextConfPassword.getText().toString()))
                {
                    txtResult.setText("Password dan Confirm Password harus sama");
                }
                else
                {
                    registerProcess();
                }
            }
        });
    }

    public void registerProcess()
    {
        String url = "http://192.168.43.148/myappdb/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtResult.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtResult.setText("ERROR!");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", editTextUsername.getText().toString());
                params.put("password", editTextPassword.getText().toString());
                params.put("name", editTextName.getText().toString());
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
