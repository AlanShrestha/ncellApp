package com.example.webbrowser;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	

	private EditText username, password;
	private Button  register, logIn;
    private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    private static final String LOGIN_URL = "http://ezercipher.herokuapp.com/login";
    private static final String REGISTER_URL = "http://192.168.43.184/register.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		
		register = (Button)findViewById(R.id.register);
		register.setOnClickListener(this);
		
		logIn = (Button)findViewById(R.id.logIn);
		logIn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.logIn:
				new LoginUser().execute();
			    break;
		case R.id.register:
			    new LoginUser().execute();
			    break;
		default:
			    break;
		}
	}
	
	class LoginUser extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Login in...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }

		@Override
		protected String doInBackground(String... para) {
			// TODO Auto-generated method stub
			  //int success=0;
	            String user = username.getText().toString();
	            String pass = password.getText().toString();
	            try {
	            	    JSONObject json = new JSONObject();
	                    List<NameValuePair> params = new ArrayList<NameValuePair>();
	                    params.add(new BasicNameValuePair("username", user));
	                   // params.add(new BasicNameValuePair("password", pass));
                       // params.add(new BasicNameValuePair("request", "login")); 
	                    
	                    json = jsonParser.makeHttpRequest(
	                       LOGIN_URL, "GET", params);
	                   // success = 1;//json.getInt(TAG_SUCCESS);
	               
	               // if (success == 1){	
	                	/*SharedPreferences sp = PreferenceManager
								.getDefaultSharedPreferences(MainActivity.this);
						Editor edit = sp.edit();
						edit.putString("username", user);
						edit.commit();
						
						//Intent i = new Intent(MainActivity.this, items.class);
						//startActivity(i);*/
						return json.getString(TAG_MESSAGE);
	                //}else{
	                //	return json.getString(TAG_MESSAGE);
	                //}
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
			return null;
		}
		
	}
	
   class CreateUser extends AsyncTask<String, String, String> {	
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }

		@Override
		protected String doInBackground(String... para) {
			// TODO Auto-generated method stub
			  int success=0;
	            String user = username.getText().toString();
	            String pass = password.getText().toString();
	            try {
	            	    JSONObject json = new JSONObject();
	                	List<NameValuePair> params = new ArrayList<NameValuePair>();
		                params.add(new BasicNameValuePair("username", user));
		                params.add(new BasicNameValuePair("password", pass));
                        params.add(new BasicNameValuePair("request", "register"));
                        //params.add(new(BasicNameValuePair("email","email")));
		                json = jsonParser.makeHttpRequest(
		                       REGISTER_URL, "POST", params);
		                success = json.getInt(TAG_SUCCESS);
	                
	                if (success == 1){	
	                	SharedPreferences sp = PreferenceManager
								.getDefaultSharedPreferences(MainActivity.this);
						Editor edit = sp.edit();
						edit.putString("username", user);
						edit.commit();
						Intent i = new Intent(MainActivity.this, items.class);
						startActivity(i);
						return json.getString(TAG_MESSAGE);
	                }else{
	                	return json.getString(TAG_MESSAGE);
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
			return null;
		}
		
	}

}