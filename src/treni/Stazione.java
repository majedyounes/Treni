package treni;

import java.util.*;

public class Stazione {

	String nome;
	Linea linea;
	String chilometro;
	double chilometroComeDouble;
	
	public Stazione(String nomeStazione, Linea linea, String chilometro) {
		this.nome = nomeStazione;
		this.linea = linea;
		this.chilometro = chilometro;
		
		String km = chilometro.substring(0, chilometro.indexOf("+"));
		String me = chilometro.substring(chilometro.indexOf("+")+1);
		chilometroComeDouble = Double.parseDouble(km)+Double.parseDouble(me)/1000.0;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeLinea() {
		return linea.getNome();
	}

	public String getChilometro() {
		return chilometro;
	}
	
	public double getChilometroComeDouble() {
		return chilometroComeDouble;
	}
	
	
	public static class ComparatoreDiStazioniPerChilometriCrescenti implements Comparator<Stazione>{

		public int compare(Stazione s1, Stazione s2) {

			return (int)(s1.getChilometroComeDouble() - s2.getChilometroComeDouble());
		}
		
	}
	
	public static class ComparatoreDiStazioniPerChilometriDecrescenti implements Comparator<Stazione>{

		public int compare(Stazione s1, Stazione s2) {

			return -(int)(s1.getChilometroComeDouble() - s2.getChilometroComeDouble());
		}
		
	}
	
}
