package com.example.webbrowser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class article extends Activity {
	ImageView picture;
	TextView imgCaption;
	TextView article;
	Bitmap parsedImage;
	String _imgCaption, _article, _imageString;

    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    private static final String ARTICLE_URL = "http://192.168.43.184/article.php";

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_FOOD_ID = "food_id";
    private static final String TAG_NAME = "name";
    private static final String TAG_IMAGE = "image";
    private static final String TAG_IMAGE_CAPTION = "imageCaption";
    private static final String TAG_ARTRICLE = "article";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout._article);

		picture = (ImageView)findViewById(R.id.picture);
		imgCaption = (TextView)findViewById(R.id.imgCaption);
		article = (TextView)findViewById(R.id.article);
		
		new CreateUser().execute();
	
		
	}

	
	private Bitmap getBitmapFromString(String jsonString){
		byte[] decodedString = Base64.decode(jsonString, Base64.DEFAULT);
		Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
		return decodedByte;
	}
	
	class CreateUser extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(article.this);
            pDialog.setMessage("Downloading article...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;      
            try {
           
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("foodName", "newar"));
              
                JSONObject json = jsonParser.makeHttpRequest(
                       ARTICLE_URL, "POST", params);
 
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	_imgCaption = json.getString("imageCaption");
                	_article = json.getString("article");
                	_imageString = json.getString("image");
                    return "success"; 
                }else{
                	//Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
        	imgCaption.setText(_imgCaption);
    		article.setText(_article);
    		parsedImage = getBitmapFromString(_imageString);
        	picture.setImageBitmap(parsedImage);  	
    		
            if (file_url != null){
            	Toast.makeText(article.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
}
