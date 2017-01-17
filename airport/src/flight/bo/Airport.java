package flight.bo;

import java.util.ArrayList;

/**
 * Created by math on 17/01/17.
 */

public class Airport {
	private String ident= "";
	
	private String name="";
	private ArrayList<Runway> runways = new ArrayList<Runway>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Runway> getRunways() {
		return runways;
	}
	public void setRunways(ArrayList<Runway> runways) {
		this.runways = runways;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
}
