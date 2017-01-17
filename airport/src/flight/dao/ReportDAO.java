package flight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Created by math on 17/01/17.
 */

public class ReportDAO {

	public String[][] getTopTenCountriesAirportCount(){
		String topTen[][] = new String[10][2];
		DataSource ds=null;
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
			//stmt = conn.prepareStatement("select  airports.name, runways.id from countries, airports, runways where countries.code=airports.iso_country and airports.ident= runways.airport_ident and (countries.code=? or countries.name=? )  order by airports.name ");
			stmt = conn.prepareStatement("select  countries.name, count(airports.iso_country) from countries, airports where countries.code=airports.iso_country  GROUP BY countries.name order  by count(airports.iso_country) Desc limit 10 ");
		    rs =  stmt.executeQuery();
			int i=0;
			while (rs.next()) {
				topTen[i][0] = rs.getString(1);
				topTen[i][1] = rs.getString(2);				
				i++;
			}
			stmt.close();
			stmt = null;	 
		} catch (Exception e) {
			e.printStackTrace();
	    } finally { //cleanup
	    	if (stmt != null) {
	    		try {
	    			stmt.close();
	    		} catch (SQLException ex) {
	    			System.out.println(ex);
	    		}
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	} catch (SQLException ex) {
	        		System.out.println(ex);
	        	}
	        }
	    }
		return topTen;
	}

	
	public String[][] getLowTenCountriesAirportCount(){
		String lowTen[][] = new String[10][2];
		DataSource ds=null;
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select  countries.name, count(airports.iso_country) from countries, airports where countries.code=airports.iso_country  GROUP BY countries.name order  by count(airports.iso_country) Asc limit 10 ");
		    rs =  stmt.executeQuery();
			int i=0;
			while (rs.next()) {
				lowTen[i][0] = rs.getString(1);
				lowTen[i][1] = rs.getString(2);				
				i++;
			}
			stmt.close();
			stmt = null;	 
		} catch (Exception e) {
			e.printStackTrace();
	    } finally { //cleanup
	    	if (stmt != null) {
	    		try {
	    			stmt.close();
	    		} catch (SQLException ex) {
	    			System.out.println(ex);
	    		}
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	} catch (SQLException ex) {
	        		System.out.println(ex);
	        	}
	        }
	    }
		return lowTen;
	}
	
	public HashMap< String, ArrayList<String>> getRunwayTypePerCountry(){
		HashMap< String, ArrayList<String>> runwayTypePerCountrys  = new HashMap< String, ArrayList<String>>();
		DataSource ds=null;
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //ArrayList<String[]> arrayListC = new ArrayList<String[]>(); 
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT countries.name, airports.iso_country, runways.surface FROM countries,runways, airports WHERE airports.ident=runways.airport_ident and countries.code=airports.iso_country  group by runways.surface, countries.code order by countries.code");
		    rs =  stmt.executeQuery();
			String countryNameMem ="";
			ArrayList<String> countryRunwayTypes = new ArrayList<String>();
			while (rs.next()) {
				String countryName = rs.getString(1);			
				String runwayType = rs.getString(3);								
				if(!countryName.equals(countryNameMem)){
					countryRunwayTypes = new ArrayList<String>();
					runwayTypePerCountrys.put(countryName, countryRunwayTypes);
					countryNameMem=countryName;
				}
				if(!runwayType.trim().equals("")){
					countryRunwayTypes.add(runwayType);
				}
			}
			stmt.close();
			stmt = null;	 
		} catch (Exception e) {
			e.printStackTrace();
	    } finally { //cleanup
	    	if (stmt != null) {
	    		try {
	    			stmt.close();
	    		} catch (SQLException ex) {
	    			System.out.println(ex);
	    		}
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	} catch (SQLException ex) {
	        		System.out.println(ex);
	        	}
	        }
	    }
		return runwayTypePerCountrys;
	}
	
	public String[][] getMostCommonRunwaysIdent(){
		String mostIdentTen[][] = new String[10][2];
		DataSource ds=null;
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select runways.le_ident, count(runways.le_ident) from runways  inner join airports on runways.airport_ident = airports.ident group by le_ident order by count(runways.le_ident)  Desc limit 10");
		    rs =  stmt.executeQuery();
			int i=0;
			while (rs.next()) {
				mostIdentTen[i][0] = rs.getString(1);
				mostIdentTen[i][1] = rs.getString(2);				
				i++;
			}
			stmt.close();
			stmt = null;	 
		} catch (Exception e) {
			e.printStackTrace();
	    } finally { //cleanup
	    	if (stmt != null) {
	    		try {
	    			stmt.close();
	    		} catch (SQLException ex) {
	    			System.out.println(ex);
	    		}
	        }
	        if (conn != null) {
	        	try {
	        		conn.close();
	        	} catch (SQLException ex) {
	        		System.out.println(ex);
	        	}
	        }
	    }
		return mostIdentTen;
	}
	
			
}
