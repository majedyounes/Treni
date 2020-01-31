import java.util.*;

import treni.*;

public class Esempio {

	public static void main(String[] args) throws PostiEsauritiException {

		System.out.println("/****** R1. LINEE E STAZIONI ******/");
		
		SistemaFerroviario sf = new SistemaFerroviario();
		
		Stazione s;
		List<Stazione> stazioni; 
		Treno t;
		Biglietto b;
		List <Treno> treni;
		
		System.out.println("\nNuova linea Torino-Milano");
		sf.nuovaLinea("Torino-Milano");

		System.out.println("\nNuova stazione");
		s = sf.nuovaStazione("Torino Porta Nuova", "Torino-Milano", "0+0");
		System.out.println(" Nome: "+s.getNome());
		System.out.println(" Linea: "+s.getNomeLinea());
		System.out.println(" Km: "+s.getChilometro());

		System.out.println("\nAltre nuove stazioni stessa linea");
		sf.nuovaStazione("Torino Porta Susa", "Torino-Milano", "3+943");
		sf.nuovaStazione("Chivasso", "Torino-Milano", "27+251");
		sf.nuovaStazione("Vercelli", "Torino-Milano", "77+54");
		sf.nuovaStazione("Novara", "Torino-Milano", "98+940");
		sf.nuovaStazione("Milano Centrale", "Torino-Milano", "149+612");

		System.out.println("\nLunghezza linea Torino-Milano");
		System.out.println(" "+sf.lunghezzaLinea("Torino-Milano"));

		System.out.println("\nAltre nuove stazioni linea Milano-Venezia");
		sf.nuovaLinea("Milano-Venezia");
		s = sf.nuovaStazione("Padova", "Milano-Venezia", "234+36");
		s = sf.nuovaStazione("Venezia Mestre", "Milano-Venezia", "261+182");
		
		System.out.println("\nElenco stazioni (tutte le linee, ordine alfabetico)");
		stazioni = new LinkedList<Stazione>(sf.elencoStazioni());
		for(Stazione stemp : stazioni)
			System.out.println(" "+stemp.getNome()+" "+stemp.getChilometro());

		System.out.println("\nElenco stazioni (direzione Torino-Milano, per chilometro)");
		stazioni = new LinkedList<Stazione>(sf.elencoStazioni("Torino-Milano"));
		for(Stazione stemp : stazioni)
			System.out.println(" "+stemp.getNome()+" "+stemp.getChilometro());
		
		
		System.out.println("\n/****** R2. TRENI ******/");

		System.out.println("\nNuovo treno IR");
		sf.nuovoTreno(3456, "IR", "Torino-Milano");
		
		t = sf.treno(3456,"IR");
		
		System.out.println(" Numero: "+t.getNumero());
		System.out.println(" Linea: "+t.getNomeLinea());
		((TrenoInterregionale)t).setRegioneOrigine("Piemonte");
		((TrenoInterregionale)t).setRegioneDestinazione("Lombardia");
		
		if(t instanceof TrenoRegionale)
			System.out.println(" Tiplogia: R");
		else if (t instanceof TrenoInterregionale){
			System.out.println(" Tiplogia: IR");
			System.out.println(" Regione origine: "+((TrenoInterregionale)t).getRegioneOrigine());
			System.out.println(" Regione destinazione: "+((TrenoInterregionale)t).getRegioneDestinazione());
		
		}
		else if (t instanceof TrenoAltaVelocita)
			System.out.println(" Tiplogia: AV");

		System.out.println("\nNuovo treno AV");
		sf.nuovoTreno(634, "AV", "Torino-Milano");
		
		System.out.println("\n/****** R3. FERMATE ******/");

		System.out.println("\nDefinite fermate");
		
		sf.aggiungiFermata(3456,"IR", "Torino Porta Nuova", "13:54");
		sf.aggiungiFermata(3456,"IR", "Torino Porta Susa", "14:05");
		sf.aggiungiFermata(3456,"IR", "Novara", "15:05");
		sf.aggiungiFermata(3456,"IR", "Milano Centrale", "15:46");
		sf.aggiungiFermata(3456,"IR", "Vercelli", "14:49");

		System.out.println("\nFermate 3456 IR (orari crescenti)");
		System.out.println(" "+t.fermate());
		
		sf.aggiungiFermata(634,"AV", "Milano Centrale", "16:20");
		sf.aggiungiFermata(634,"AV", "Torino Porta Susa", "17:00");
		sf.aggiungiFermata(634,"AV", "Torino Porta Nuova", "17:05");
		
		System.out.println("\nFermate 634 AV (orari crescenti)");
		t = sf.treno(634,"AV");
		System.out.println(" "+t.fermate());
		
		
		System.out.println("\nTreni da Torino Porta Nuova a Milano Centrale");
		treni = new LinkedList<Treno>(sf.cerca("Torino Porta Nuova", "Milano Centrale"));
		for (Treno ttemp : treni)
			System.out.println(" "+ttemp.getNumero()+" "+ttemp.getTipologia());

		System.out.println("\n/****** R4. BIGLIETTI  ******/");

		System.out.println("\nBiglietto per treno IR");
		b = sf.emettiBiglietto("Torino Porta Nuova", "Torino Porta Susa", "2014-16-02",3456, "IR");
		System.out.println(" Numero: "+b.getNumeroBiglietto());
		System.out.println(" Da: "+b.getNomeStazionePartenza());
		System.out.println(" A: "+b.getNomeStazioneArrivo());
		System.out.println(" Data: "+b.getData());
		System.out.println(" Treno: "+b.getNumeroTreno());
		System.out.println(" Tipologia: "+b.getTipologiaTreno());
		System.out.println(" Prezzo: "+b.getPrezzo());
		
	}

}
