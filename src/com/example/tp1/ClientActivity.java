package com.example.tp1;

//import java.util.ArrayList;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.support.v7.app.ActionBarActivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import java.util.List;
import android.widget.ArrayAdapter;

//public class ClientActivity extends ActionBarActivity {
public class ClientActivity extends ListActivity {

	//	private ListView maListViewPerso;
	//	private SimpleAdapter mListAdapter;
	//	private ArrayList<HashMap<String, String>> listItem;
	//	private HashMap<String, String> map;

	private DAO datasource;
	private ArrayAdapter<Repertoire> adapter;

	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.client_activity);

		datasource = new DAO(this);
		datasource.open();

		List<Repertoire> values = datasource.getAllRepertoires();

		adapter = new ArrayAdapter<Repertoire>(this,
				android.R.layout.simple_list_item_1, values);

		setListAdapter(adapter);

		getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Repertoire r = (Repertoire) getListView().getItemAtPosition(position);
				//on créer une boite de dialogue
				AlertDialog.Builder adb = new AlertDialog.Builder(ClientActivity.this);
				//on attribut un titre à notre boite de dialogue
				adb.setTitle(R.string.detail);
				//on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
				String b = getString(R.string.born)+ " ";
				String ba = getString(R.string.a)+ " ";
				adb.setMessage(r.getNom()+ r.getPrenom() +  b + r.getDate()+ ba +r.getVille());
				//on indique que l'on veut le bouton ok à notre boite de dialogue
				adb.setPositiveButton(R.string.OK, null);
				//on affiche la boite de dialogue
				adb.show();
			}
		});

		//Récupération de la listview créée dans le fichier client_activity.xml
		//maListViewPerso = (ListView) findViewById(R.id.listviewperso);

		//Création de la ArrayList qui nous permettra de remplire la listView
		//listItem = new ArrayList<HashMap<String, String>>();

		//On déclare la HashMap qui contiendra les informations pour un item
		//HashMap<String, String> map;

		//Création d'une HashMap pour insérer les informations du premier item de notre listView
		//map = new HashMap<String, String>();

		//On refait la manip plusieurs fois avec des données différentes pour former
		//les items de notre ListView

		//map = new HashMap<String, String>();
		//map.put("Nom", "Withoutsoucy");
		//map.put("Prenom", "Martine");
		//listItem.add(map);

		//Création d'un SimpleAdapter qui se chargera de mettre
		//les items présent dans notre list (listItem) dans la vue affichageitem

		//mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.listviewitem,
		//new String[] {"Nom", "Prenom"}, new int[] {R.id.Nom, R.id.Prenom});

		//On attribue à notre listView l'adapter que l'on vient de créer
		//maListViewPerso.setAdapter(mListAdapter);

		/*maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//on récupère la HashMap contenant les infos de notre item (Nom, Prenom, img)
				@SuppressWarnings("unchecked")
				HashMap<String, String> map = 
				(HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
				//on créer une boite de dialogue
				AlertDialog.Builder adb = new AlertDialog.Builder(ClientActivity.this);
				//on attribut un titre à notre boite de dialogue
				adb.setTitle("Sélection Item");
				//on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
				adb.setMessage("Votre choix : "+map.get("Nom"));
				//on indique que l'on veut le bouton ok à notre boite de dialogue
				adb.setPositiveButton("Ok", null);
				//on affiche la boite de dialogue
				adb.show();
			}

		});*/
	}

	/*private void displayIntentData(){
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();

		if(extras!=null){
			Repertoire r = extras.getParcelable("key");
			String nom;
			String prenom;
			nom = r.getNom().trim();
			prenom = r.getPrenom().trim();

			//Création d'une HashMap pour insérer les informations du premier item de notre listView
			map = new HashMap<String, String>();

			//on insère un élément titre que l'on récupérera dans le textView titre 
			//créé dans le fichier affichageitem.xml
			map.put("Nom", nom);
			//on insère un élément description que l'on récupérera dans le textView description créé 
			//dans le fichier affichageitem.xml
			map.put("Prenom", prenom);			

			//enfin on ajoute cette hashMap dans la arrayList
			listItem.add(map);

			//Création d'un SimpleAdapter qui se chargera de mettre
			//les items présent dans notre list (listItem) dans la vue affichageitem

			mListAdapter = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.listviewitem,
					new String[] {"Nom", "Prenom"}, new int[] {R.id.Nom, R.id.Prenom});

			mListAdapter.notifyDataSetChanged();

			//On attribue à notre listView l'adapter que l'on vient de créer
			maListViewPerso.setAdapter(mListAdapter);
		}
		else{// nothing
		}
	}*/

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		//doit garder le nouvel intent jusqu'a ce que getIntent() renvoie l'ancien
		setIntent(intent);
		//displayIntentData();
		v2displayIntentData();
	}



	private void v2displayIntentData(){
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		Repertoire rep = null;
		if(extras!=null){
			Repertoire r = extras.getParcelable("key");
			rep = datasource.createRepertoire(r);
			adapter.add(rep);
		}
	}

	public void addItem(View v) {
		Intent intent = new Intent(ClientActivity.this, MainActivity.class);
		startActivity(intent);
		//dont call finish() here
	}

	@SuppressWarnings("unchecked")
	public void onClick(View view) {
		adapter = (ArrayAdapter<Repertoire>) getListAdapter();
		switch (view.getId()) {
		case R.id.add:
			Intent intent = new Intent(ClientActivity.this, MainActivity.class);
			startActivity(intent);				
			break;
		case R.id.delete:
			if (getListAdapter().getCount() > 0) {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setTitle(R.string.delete);
				alert.setMessage(R.string.msgdel);
				// Set an EditText view to get user input 
				final EditText input = new EditText(this);
				alert.setView(input);
				alert.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						int num = Integer.parseInt(input.getText().toString());
						if (num>=0){
							Repertoire repertoire = (Repertoire) getListAdapter().getItem(num-1);
							datasource.deleteComment(repertoire);
							adapter.remove(repertoire);
							adapter.notifyDataSetChanged();
						}
					}
				});

				alert.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});
				alert.show();
			}
			break;
		}
		adapter.notifyDataSetChanged();
	}

	protected void onResume() {
		super.onResume();
		datasource.open();
	}

	/*protected void onPause() {
		super.onPause();
		datasource.close();
	}*/
}