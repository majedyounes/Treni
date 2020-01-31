package treni;

public class TrenoAltaVelocita extends Treno{

	int postiDisponibili = 500;
	
	public TrenoAltaVelocita(int numero, String tipologia, Linea linea) {
		super(numero, tipologia, linea);
	}

	public void prenota() throws PostiEsauritiException{
		if(postiDisponibili>=1)
			postiDisponibili--;
		else
			throw new PostiEsauritiException();
	}
	
	
}
