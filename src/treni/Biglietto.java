package treni;

public class Biglietto {

	int numero;
	Stazione stazionePartenza;
	Stazione stazioneArrivo;
	String data;
	Treno treno;
	double prezzo;
	
	public Biglietto(int numero, Stazione stazionePartenza, Stazione stazioneArrivo, String data, Treno treno, double prezzo) {
		this.numero=numero;
		this.stazionePartenza=stazionePartenza;
		this.stazioneArrivo=stazioneArrivo;
		this.data=data;
		this.treno=treno;
		this.prezzo=prezzo;
	}
	

	public int getNumeroBiglietto() {
		return numero;
	}

	public String getNomeStazionePartenza() {
		return stazionePartenza.getNome();
	}

	public String getNomeStazioneArrivo() {
		return stazioneArrivo.getNome();
	}
	
	public String getData(){
		return data;
	}

	public int getNumeroTreno() {
		return treno.getNumero();
	}

	public String getTipologiaTreno() {
		return treno.getTipologia();
	}

	public double getPrezzo() {
		return prezzo;
	}
	
	

}
