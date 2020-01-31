package treni;

public class TrenoInterregionale extends Treno{

	String regioneOrigine;
	String regioneDestinazione;
	
	public TrenoInterregionale(int numero, String tipologia, Linea linea) {
		super(numero, tipologia, linea);
	}

	public String getRegioneOrigine() {
		return regioneOrigine;
	}

	public String getRegioneDestinazione() {
		return regioneDestinazione;
	}

	public void setRegioneOrigine(String regioneOrigine) {
		this.regioneOrigine = regioneOrigine;
	}

	public void setRegioneDestinazione(String regioneDestinazione) {
		this.regioneDestinazione = regioneDestinazione;
	}

	
	
	
}
