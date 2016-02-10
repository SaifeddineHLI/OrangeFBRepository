package com.coriolis.sachem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ArrayListenerHelper;

import jdk.nashorn.internal.runtime.ListAdapter;

public class ListeCompte implements Serializable{
	
	private static final long serialVersionUID = -8577579081118070434L;

	private Compte[] comptes;

	public Compte[] getListeCompte() {
		return comptes;
	}

	public void setListeCompte(Compte[] compte) {
		this.comptes = compte;
	}
	

}
