package matchpackage.search;

import matchpackage.database.ProviderList;
import matchpackage.database.Provider;

public class SortProvidersList {
	
	private ProviderList providers;
	private String keywords;
	
	public SortProvidersList(String keywords) {
		this.keywords = keywords;
		providers = new ProviderList();
	}
	
	

	public String sortProvidersList() {
		
		//Implement functionality here.
		
		return keywords;
		
	}
	
	

}
