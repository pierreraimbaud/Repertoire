package com.example.tp1;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
//import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	final static int idPhoneText = 98;
	final static int idPhone = 99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button okay = (Button) findViewById(R.id.okay);
		okay.setBackgroundColor(Color.argb(255, 136, 66, 29));
		okay.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String nom="";
				TextView t1 = (TextView)findViewById(R.id.nom);
				nom += t1.getText()+"\n";

				String prenom="";
				TextView t2 = (TextView)findViewById(R.id.prenom);
				prenom += t2.getText()+"\n";

				String date="";
				TextView t3 = (TextView)findViewById(R.id.date);
				date += t3.getText()+"\n";

				String ville="";
				TextView t4 = (TextView)findViewById(R.id.ville);
				ville += t4.getText();

				Intent intent = new Intent(MainActivity.this, ClientActivity.class);
				Repertoire r = new Repertoire (nom, prenom, date, ville);
				intent.putExtra("key", r);
				startActivity(intent);
				//finish();
				//Toast.makeText(getApplicationContext(), reponse, Toast.LENGTH_LONG).show();
			}
		});

		Button wikipedia = (Button) findViewById(R.id.wikipedia);
		wikipedia.setBackgroundColor(Color.argb(255, 136, 66, 29));
		wikipedia.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				String ville ="";
				TextView t = (TextView)findViewById(R.id.ville);
				ville += t.getText()+"\n";
				String url = "http://en.wikipedia.org/wiki/"+ville;
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);//must store the new intent unless getIntent() will return the old one
	}

	public boolean remise_zero() {
		TextView t = (TextView)findViewById(R.id.nom);
		t.setText("");
		t = (TextView)findViewById(R.id.prenom);
		t.setText("");
		t = (TextView)findViewById(R.id.date);
		t.setText("");
		t = (TextView)findViewById(R.id.ville);
		t.setText("");
		t = (TextView) findViewById(idPhone);
		if (t != null){
			t.setText("");
		}
		return true;
	}

	public boolean ajouter_numero() {
		RelativeLayout l = (RelativeLayout) findViewById(R.id.activity_main);

		TextView num_phone = new TextView(this.getApplicationContext());
		num_phone.setText(R.string.ajoutPhone);
		num_phone.setTextColor(Color.BLACK);
		num_phone.setId(idPhoneText);

		EditText phone = new EditText(this.getApplicationContext());
		phone.setInputType(InputType.TYPE_CLASS_PHONE);
		phone.setCursorVisible(true);
		phone.setDrawingCacheBackgroundColor(Color.BLACK);
		phone.setTextColor(Color.BLACK);
		phone.setId(idPhone);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT
				);

		params.addRule(RelativeLayout.BELOW, R.id.ville);	    	   
		l.addView(num_phone, params);

		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT
				);

		params2.addRule(RelativeLayout.BELOW, idPhoneText);	    	   
		l.addView(phone, params2);

		l.invalidate();

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (R.id.buttonraz == id){
			remise_zero();
		}
		if (R.id.ajout_num == id){
			ajouter_numero();
		}
		return true;
		//default : return super.onOptionsItemSelected(item);
	}
}