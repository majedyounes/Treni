package treni;

import java.util.*;

public class SistemaFerroviario {


	Map<String,Linea> linee = new TreeMap<String,Linea>();
	List<Treno> treni = new LinkedList<Treno>();
	Map<String,Stazione> stazioni = new TreeMap<String,Stazione>();
	int numeroBiglietto=10000;
	
	
	public void nuovaLinea(String nomeLinea){
		Linea ltemp = new Linea(nomeLinea);
		linee.put(nomeLinea, ltemp);
	}
	
	public Stazione nuovaStazione(String nomeStazione, String nomeLinea, String chilometro){

		Linea ltemp = linee.get(nomeLinea);
		if(ltemp==null)
			nuovaLinea(nomeLinea);

		ltemp = linee.get(nomeLinea);

		Stazione stemp = new Stazione(nomeStazione, ltemp, chilometro);

		ltemp.aggiungiStazione(stemp);
		
		stazioni.put(nomeStazione, stemp);

		return stemp;
	}

	
	
	
	public String lunghezzaLinea(String nomeLinea){
		Linea ltemp = linee.get(nomeLinea);
		if(ltemp==null)
			return "-1+0";
		double max=-1;
		Stazione s = null;
		for(Stazione stemp : ltemp.getStazioni()){
			if(stemp.getChilometroComeDouble()>max){
				max = stemp.getChilometroComeDouble();
				s = stemp;
				}
		}
				
		return s.getChilometro();
	}

	
	public Collection<Stazione> elencoStazioni(){
		
		Map<String,Stazione> stazioni = new TreeMap<String,Stazione>();
		
		for(Linea ltemp : linee.values())
		{
			for(Stazione stemp : ltemp.getStazioni())
			{
				stazioni.put(stemp.getNome(), stemp);
			}
		}
		
		return stazioni.values();
		
	}

	
	public Collection<Stazione> elencoStazioni(String direzione){

		String nomeStazione1 = direzione.substring(0,direzione.indexOf("-"));
		String nomeStazione2 = direzione.substring(direzione.indexOf("-")+1);
		String nomeLinea1 = nomeStazione1+"-"+nomeStazione2;
		String nomeLinea2 = nomeStazione2+"-"+nomeStazione1;

		List<Stazione> listaLinee = null;

		Linea linea = linee.get(nomeLinea1);
		if(linea!=null)
		{		
			// L'ordine e' quello di chilometri crescenti
			listaLinee = new LinkedList<Stazione>(linea.getStazioni());
			Collections.sort(listaLinee, new Stazione.ComparatoreDiStazioniPerChilometriCrescenti());
		}
		else{
				// L'ordine e' quello di chilometri decresenti
			linea = linee.get(nomeLinea2);
			listaLinee = new LinkedList<Stazione>(linea.getStazioni());
			Collections.sort(listaLinee, new Stazione.ComparatoreDiStazioniPerChilometriDecrescenti());
		}
		return listaLinee;
	
	}
	
	
	public void nuovoTreno(int numero, String tipologia, String nomeLinea){
		

		Treno treno=null;
		Linea linea = linee.get(nomeLinea);
		if(linea==null)
			return;


		if(tipologia.compareTo("R")==0){
			treno = new TrenoRegionale(numero, tipologia, linea);
		}
		else if(tipologia.compareTo("IR")==0){
			treno = new TrenoInterregionale(numero, tipologia, linea);
		}
		else if(tipologia.compareTo("AV")==0)
		{
			treno = new TrenoAltaVelocita(numero, tipologia, linea);
		}

		treni.add(treno);
		
	}
	
	public Treno treno(int numeroTreno, String tipologia){
		
		Treno treno = null;
		for(Treno ttemp : treni){
			
			String tipo = "";
			if(ttemp instanceof TrenoRegionale)
				tipo ="R";
			else if(ttemp instanceof TrenoInterregionale)
				tipo ="IR";
			else if(ttemp instanceof TrenoAltaVelocita)
				tipo ="AV";
			
			if (ttemp.getNumero()==numeroTreno && tipo.compareTo(tipologia)==0){
				treno = ttemp;
				break;
			}
		}
		
		return treno;
		
	}
	
	public void aggiungiFermata(int numeroTreno, String tipologia, String nomeStazione, String ora){

		Treno ttemp = treno(numeroTreno,tipologia);
		if(ttemp==null)
			return;
		Stazione stazione = stazioni.get(nomeStazione);
		if(stazione==null)
			return;
		else
			ttemp.aggiungiFermata(nomeStazione,ora);
	}

	
	


	public Collection<Treno> cerca(String nomeStazionePartenza, String nomeStazioneArrivo) {

		Collection<Treno> lista = new LinkedList<Treno>();
		
		
		for(Treno t : treni){
			boolean flagPartenza = false;
			String oraPartenza ="";
			
			List<String> f = new LinkedList<String>(t.getNomi());
			List<String> o = new LinkedList<String>(t.getOre());

			for(int i=0;i<f.size();i++){
				if(f.get(i).compareTo(nomeStazionePartenza)==0){
					flagPartenza=true;
					oraPartenza = o.get(i);
				}
			}

			boolean flagArrivo = false;
			String oraArrivo = "";
			if(flagPartenza){
				List<String> ff = new LinkedList<String>(t.getNomi());
				List<String> oo = new LinkedList<String>(t.getOre());
				for(int j=0; j<ff.size();j++){
					if(ff.get(j).compareTo(nomeStazioneArrivo)==0){
						flagArrivo=true;
						oraArrivo = oo.get(j);
					}
				}
			}
			
			if(flagPartenza && flagArrivo)
				if(oraPartenza.compareTo(oraArrivo)<0)
					lista.add(t);
		}
		
		return lista;
	}
	
	
	public Biglietto emettiBiglietto(String nomeStazionePartenza, String nomeStazioneArrivo, String data, int numeroTreno, String tipologia) throws PostiEsauritiException{
		
		Stazione stazionePartenza = stazioni.get(nomeStazionePartenza);
		Stazione stazioneArrivo = stazioni.get(nomeStazioneArrivo);
		
		double distanza = Math.abs(stazionePartenza.getChilometroComeDouble()-stazioneArrivo.getChilometroComeDouble());

		Treno treno = null;
		for(Treno ttemp : treni){
			
			String tipo = "";
			if(ttemp instanceof TrenoRegionale)
				tipo ="R";
			else if(ttemp instanceof TrenoInterregionale)
				tipo ="IR";
			else if(ttemp instanceof TrenoAltaVelocita)
				tipo ="AV";
			
			if (ttemp.getNumero()==numeroTreno && tipo.compareTo(tipologia)==0){
				treno = ttemp;
				break;
			}
		}
		
		if(treno==null)
			return null;
		
		boolean flagPartenza = false;
		boolean flagArrivo = false;

		for(String f : treno.getNomi())
			if(f.compareTo(nomeStazionePartenza)==0)
				flagPartenza=true;
		
		for(String ff : treno.getNomi())
			if(ff.compareTo(nomeStazioneArrivo)==0)
				flagArrivo=true;
		
		if(!( flagPartenza && flagArrivo  ))
			return null;
			
		double prezzo = 0.0;
		
		if(treno instanceof TrenoRegionale || treno instanceof TrenoInterregionale){
			prezzo = distanza * 0.15;
	
		}
		else if(treno instanceof TrenoAltaVelocita){
			prezzo = distanza * 0.30;
		}
		
		if(treno instanceof TrenoAltaVelocita){
			((TrenoAltaVelocita)treno).prenota();
		}
		
		
		Biglietto btemp = new Biglietto(numeroBiglietto, stazionePartenza, stazioneArrivo, data, treno, prezzo);

		this.numeroBiglietto++;
		
		return btemp;
	}
	
	

	
}








