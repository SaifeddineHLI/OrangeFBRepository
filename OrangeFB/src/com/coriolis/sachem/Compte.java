package com.coriolis.sachem;

import java.io.Serializable;

public class Compte implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5577579081118070434L;
	private short type;	
    private long solde;	
    private String dateLimite;
    
    public Compte(String dateLimite, int solde, short type) {
		this.dateLimite=dateLimite;
		this.solde=solde;
		this.type=type;
	}
    
	public short getType()
    {
    	return type;
    }
    public void setType(short type)
    {
    	this.type = type;
    }

    public long getSolde()
    {
    	return solde;
    }
    public void setSolde(long solde)
    {
    	this.solde = solde;
    }

    public String getDateLimite()
    {
    	return dateLimite;
    }
    public void setDateLimite(String dateLimite)
    {
    	this.dateLimite = dateLimite;
    }

    
}
