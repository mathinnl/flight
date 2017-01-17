package flight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import flight.bo.Airport;
import flight.bo.Runway;

/**
 * Created by math on 17/01/17.
 */

public class QueryDAO {

	public ArrayList<Airport> getAirportsAndRunways(String searchString){
		ArrayList<Airport> airports = new ArrayList<Airport>();
		DataSource ds=null;
	    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
			stmt = conn.prepareStatement("select  airports.name, runways.id, airports.ident from countries, airports left outer join runways on airports.ident= runways.airport_ident  where countries.code=airports.iso_country and  (countries.code=? or countries.name=? )  order by airports.name ");
			stmt.setString(1, searchString);
		    stmt.setString(2, searchString);
		    rs =  stmt.executeQuery();
			String airportNameMem ="";
			Airport airport=null;
			while (rs.next()) {
				//String name = rs.getString(1);
				String airportName = rs.getString(1);
				String id = rs.getString(2);				
				String airportIdent = rs.getString(3);				
				
				if(!airportNameMem.equals(airportName)){
					airport = new Airport();
					airport.setName(airportName);
					airport.setIdent(airportIdent);
					airports.add(airport);
					airportNameMem=airportName;
				}
				Runway runway = new Runway();
				runway.setId(id);
				airport.getRunways().add(runway);		
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
		return airports;
	}
	
	public ArrayList<String> getAirportNameAndCode(){	
		ArrayList<String> arrayList = new ArrayList<String>();
	    DataSource ds=null;
	    Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {		
			ds = (DataSource)new InitialContext().lookup("java:comp/env/jdbc/MyDB");
			conn = ds.getConnection();
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery("SELECT code, name FROM countries order by name" );
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				arrayList.add(name.toLowerCase());
				arrayList.add(code.toLowerCase());			
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
		return arrayList;
	}
}
