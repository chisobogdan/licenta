import java.util.ArrayList;
import java.util.List;

/**
 * Clasa model pentru Localitate cu numele sau, zona de pericol si sitemele sale
 * Sisteme: ISU, jandarmerie, primarie, etc.
 * @author chiso
 *
 */
public class Localitate {
	
	private String nume;
	private int zona;
	private List<String> sisteme = new ArrayList<String>();
	
	public Localitate(String nume, int zona, ArrayList<String> sisteme){
		this.nume=nume;
		this.zona=zona;
		this.sisteme=sisteme;
	}
	
	public int getZona() {
		return zona;
	}

	public void setZona(int zona) {
		this.zona = zona;
	}

	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public List<String> getSisteme() {
		return sisteme;
	}
	public void setSisteme(List<String> sisteme) {
		this.sisteme = sisteme;
	}
}
