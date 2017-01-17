package flight.ctl;

import java.util.ArrayList;
import java.util.HashMap;

import flight.dao.ReportDAO;

/**
 * Created by math on 17/01/17.
 */

public class ReportCtl {
	private ReportDAO reportDAO = new ReportDAO();
	private String topTen[][] = new String[10][2];
	private String lowTen[][] = new String[10][2];
	private String mostIdentTen[][] = new String[10][2];
	private HashMap< String, ArrayList<String>> runwayTypePerCountrys ;

	public ReportCtl(){
		topTen = reportDAO.getTopTenCountriesAirportCount();
		lowTen = reportDAO.getLowTenCountriesAirportCount();
		runwayTypePerCountrys = reportDAO.getRunwayTypePerCountry();
		setMostIdentTen(reportDAO.getMostCommonRunwaysIdent());
				//setRunwayTypePerCountrys();
	}
	
	public String[][] getTopTen() {
		return topTen;
	}

	public void setTopTen(String topTen[][]) {
		this.topTen = topTen;
	}

	public String[][] getLowTen() {
		return lowTen;
	}

	public void setLowTen(String lowTen[][]) {
		this.lowTen = lowTen;
	}

	public HashMap< String, ArrayList<String>> getRunwayTypePerCountrys() {
		return runwayTypePerCountrys;
	}

	public void setRunwayTypePerCountrys(HashMap< String, ArrayList<String>> runwayTypePerCountrys) {
		this.runwayTypePerCountrys = runwayTypePerCountrys;
	}

	public String[][] getMostIdentTen() {
		return mostIdentTen;
	}

	public void setMostIdentTen(String mostIdentTen[][]) {
		this.mostIdentTen = mostIdentTen;
	}

 
}
