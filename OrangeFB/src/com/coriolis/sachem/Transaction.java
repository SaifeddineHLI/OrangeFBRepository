package com.coriolis.sachem;

import jdk.internal.org.xml.sax.InputSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.XMLReader;

import com.sun.xml.internal.txw2.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;


public class Transaction {
	public int ChargerCompteur(long NDossier,short TypeCompte,long Montant, String Signe)
	{
		Tracer("Requete Charger Compteur: Dossier=" + String.valueOf(NDossier) + " TypeCompte=" + String.valueOf(TypeCompte) + " Montant=" + String.valueOf(Montant) + "Signe="+ Signe);
		int statut;
		statut = (int)NDossier + (int)TypeCompte + (int)Montant;
		Tracer("Reponse charger Compteur: Statut= " + String.valueOf(statut));
		return statut;		
	}
	
	public Compte ConsulterCompte(long NDossier)
	{
		Tracer("Requete Consulter Compte: Dossier=" + String.valueOf(NDossier));
		
		Compte cpt = new Compte("01-01-2050",458,(short)2);
		
		Tracer("Reponse Consulter Compte");
		
		return cpt;
	}
	
	
	
	public Dossier ConsulterDossier(long NDossier)
	{
		Tracer("Requete Consulter Dossier: Dossier=" + String.valueOf(NDossier));
		
		Dossier dos = new Dossier();
		dos.setNumero(145);
		
		
		List<String> stockList = new ArrayList<String>();
		stockList.add("stock1");
		stockList.add("stock2");
		
		 //Convertir List en tableau  
		String[] stockArr = new String[stockList.size()];
		stockArr = stockList.toArray(stockArr);
		
		Compte sec1 = new Compte("16-12-2015",111,(short)1);
	
		Compte sec2 = new Compte("27-09-2015",222,(short)2);
		
		Compte sec3 = new Compte("01-01-2015",333,(short)3);
		
		List<Compte> compte = new ArrayList<Compte>();	
		compte.add(sec1);
		compte.add(sec2);
		compte.add(sec3);
		
		Compte[] compteArray = new Compte[compte.size()];
		compteArray = compte.toArray(compteArray);
		
		ListeCompte comptes = new ListeCompte();
		
		comptes.setListeCompte(compteArray);
		
		dos.setComptes(comptes);	
		
		Tracer("Reponse Consulter Dossier");
		
		return dos;
	}

	
	private Connection connect () 
	{	
		try 
		{
		
		// Solution JNDI
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/OrangeFB");
		Connection con = ds.getConnection();
		
//		
//		String URLProd = envContext.lookup("UrlProd").toString();
//		String URLlocal = (String) envContext.lookup("Urllocal").toString();
//		String URlInteg = (String) envContext.lookup("UrlInteg").toString();
//		
//		System.out.println(" URLProd = " +URLProd + "  URLlocal = " +URLlocal+ " URlInteg = "+ URlInteg);
//		
		return con;
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
		  return null;
		}
		
	}
	
		
	private void Tracer(String message)
	{
		try {
			//Log dans la base de données
			 Connection cnx = connect ();
	         Statement statement = cnx.createStatement();
	         String commandString = "Exec Log_Create '" + message  +"'";	         
	         statement.executeUpdate(commandString);
	         cnx.close();
	         
	         // log dans un fichier txt 
	         Context initContext = new InitialContext();
	 		 Context envContext = (Context) initContext.lookup("java:comp/env");
	         String Chemin_Log = (String) envContext.lookup("Dossier_log").toString();//récuperer chemin du dossier log
	         File dossier = new File(Chemin_Log+"\\"+"Log");
	         // if the directory and file does not exist, create it
		      if (!dossier.exists()) 
		    	  dossier.mkdir();
		      
		      // Crée log
		      Logger logger = Logger.getLogger("MyLog");  
		      FileHandler fh;  

		      try {  

		          // This block configure the logger with handler and formatter  
		          fh = new FileHandler(Chemin_Log+"\\"+"Log"+"\\Log_SACHEM");  
		          logger.addHandler(fh);
		          SimpleFormatter formatter = new SimpleFormatter();  
		          fh.setFormatter(formatter);  

		          // the following statement is used to log any messages  
		          logger.info(message);  

		      } catch (SecurityException e) {  
		          e.printStackTrace();  
		      } catch (IOException e) {  
		          e.printStackTrace();  
		      }  
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
		
	}
	
}
