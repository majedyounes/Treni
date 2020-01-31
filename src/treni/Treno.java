package treni;

import java.util.*;

public class Treno {

	int numero;
	Linea linea;
	String tipologia;
	
	Map<String,String> fermate = new TreeMap<String,String>();
	
	public Treno(int numero, String tipologia, Linea linea){
		this.numero=numero;
		this.tipologia = tipologia;
		this.linea=linea;
	}
	

	public int getNumero() {
		return numero;
	}

	public String getTipologia(){
		return tipologia;
	
	}

	public String getNomeLinea() {
		return linea.getNome();
	}


	void aggiungiFermata(String nomeStazione, String ora){
		fermate.put(ora, nomeStazione);
	}

	public String fermate(){

		String risultato = "";

		for(String ora : fermate.keySet()){
			if(risultato.compareTo("")!=0)
				risultato+=";";
			String nomeStazione = fermate.get(ora);
			risultato+=""+nomeStazione+","+ora+"";
		}

		return risultato;
	}
	
	
	
	public Collection<String> getOre(){
		return fermate.keySet();
	}
	public Collection<String> getNomi(){
		return fermate.values();
	}
	
}
