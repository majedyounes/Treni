package treni;

import java.util.*;

public class Linea {

	private Map<String,Stazione> mappaStazioni = new TreeMap<String,Stazione>();
	private List<Stazione> listaStazioni = new LinkedList<Stazione>();
	
	private String nome;
	
	public Linea(String nomeLinea){
		this.nome=nomeLinea;
	}
	
	public void aggiungiStazione(Stazione s){
		mappaStazioni.put(s.getNome(), s);
		listaStazioni.add(s);
	}
	
	public String getNome(){
		return nome;
	}
	
	public Collection<Stazione> getStazioni(){
		return mappaStazioni.values();
	}
	
}
