package flight.ctl;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.SimpleListModel;

import flight.bo.Airport;
import flight.dao.QueryDAO;;

/**
 * Created by math on 17/01/17.
 */

public class QueryCtl {
	private SimpleListModel<String> simpleListModel;
	private String searchString;
	private QueryDAO queryDAO = new QueryDAO();
	private List<Airport> airports;
	

	private String errorSearchMessage ="";
	public QueryCtl(){		
		ArrayList<String> arrayListAirportNameAndCode = queryDAO.getAirportNameAndCode();
		String[] arrayAirportNameAndCode =  arrayListAirportNameAndCode.stream().toArray(String[]::new);
		simpleListModel = new SimpleListModel<String>(arrayAirportNameAndCode);
	}	
	
	public SimpleListModel<String> getSimpleListModel() {
		return simpleListModel;
	}

	public void setSimpleListModel(SimpleListModel<String> simpleListModel) {
		this.simpleListModel = simpleListModel;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	
	@Command @NotifyChange("airports")
	public void search(){
		// @NotifyChange("airports")
		if(searchString!=null){
			airports = queryDAO.getAirportsAndRunways(searchString.trim());
		}else{
			airports= new ArrayList<Airport>();
		}
		//System.out.println("searchaaa");
	}

	public String getErrorSearchMessage() {
		return errorSearchMessage;
	}

	public void setErrorSearchMessage(String errorSearchMessage) {
		this.errorSearchMessage = errorSearchMessage;
	}
	
	public List<Airport> getAirports() {
		return airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}
}
