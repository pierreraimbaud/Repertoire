package com.example.tp1;

import android.os.Parcel;
import android.os.Parcelable;

public class Repertoire implements Parcelable{

	private long id;
	private String nom;
	private String prenom;
	private String date;
	private String ville;
	
	public static final Parcelable.Creator<Repertoire> CREATOR = new Parcelable.Creator<Repertoire>() {
		public Repertoire createFromParcel(Parcel in) {
			return new Repertoire(in);
		}

		public Repertoire[] newArray(int size) {
			return new Repertoire[size];
		}
	};
	
	public Repertoire(){
		nom = "Rene";
		prenom = "Charles";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Repertoire (String nom, String prenom, String date, String ville){
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.ville = ville;
	}
	
	public String toString() {
		return nom;
	}
	
	public Repertoire (Parcel p){
		nom = p.readString();
		prenom = p.readString();
		date = p.readString();
		ville = p.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nom);
		dest.writeString(prenom);
		dest.writeString(date);
		dest.writeString(ville);

	}
}