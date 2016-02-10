package com.coriolis.sachem;

import java.io.Serializable;
import java.util.List;

public class Dossier implements Serializable{
	
	private static final long serialVersionUID = -2577579081118070434L;
	private long numero;	
	//private List<Compte> listeCompte;
	private ListeCompte Comptes;

    public long getNumero()
    {
    	return numero;
    }
    public void setNumero(long numero)
    {
    	this.numero = numero;
    }
	public ListeCompte getComptes() {
		return Comptes;
	}
	public void setComptes(ListeCompte comptes) {
		Comptes = comptes;
	}
	
}
