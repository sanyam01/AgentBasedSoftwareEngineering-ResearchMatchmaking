package matchpackage.search;

import matchpackage.database.ProviderList;

import java.util.ArrayList;

import matchpackage.database.Provider;
import java.util.*;

public class SortProvidersList {
	
	private ProviderList providers;
	private String keywords;
	private ArrayList<Provider> sortedProviders;
	private ArrayList<Provider> leftProviders;
	
	public SortProvidersList(String keywords) {
		this.keywords = keywords;
		providers = new ProviderList();
		 sortedProviders = new ArrayList<Provider>();
		 leftProviders = new ArrayList<Provider>();
		 
	}
	
	

	public String sortProvidersList() {
		
		
		String[] kewywordsSplitArray = keywords.split(",");
		ArrayList<String> keywordsSplit = new ArrayList<String>();
		
		
		
		for (String i : kewywordsSplitArray) {
			keywordsSplit.add(i);
		}
		
		for (Provider provider : providers.getProviders()) {
			
			ArrayList<String> dataKeywords = provider.getKeywords();
			
			if(!(Collections.disjoint(dataKeywords, keywordsSplit)) )
				sortedProviders.add(provider);
			else
				leftProviders.add(provider);
			
			
		}
		
		sortedProviders.addAll(leftProviders);
		
	
		
		return providers.getStringProvidersGuest(sortedProviders);
		
	}
	
	

}
