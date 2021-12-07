package matchpackage.database;

import java.util.ArrayList;

public class ProviderList {

	ArrayList<Provider> providers = new ArrayList<Provider>();

	public ProviderList() {

		ArrayList<String> pro1List = new ArrayList<String>();
		pro1List.add("html");
		pro1List.add("python");
		pro1List.add("css");

		ArrayList<String> pro2List = new ArrayList<String>();
		pro2List.add("java script");
		pro2List.add("html");
		pro2List.add("react");
		pro2List.add("angular");

		Provider pro1 = new Provider("codezero", "codepassword", "https://codezero.io/", "logo1", 10, pro1List,
				"resume1");
		pro1.setPlan("Basic");
		Provider pro2 = new Provider("thehack", "hackpassword", "https://hackdesign.org/", "logo2", 15, pro2List,
				"resume2");
		pro2.setPlan("Premium");

		providers.add(pro1);
		providers.add(pro2);
	}

	public void addProvider(Provider pro) {
		providers.add(pro);

	}

	public ArrayList<Provider> getProviders() {
		return providers;
	}

	public void setProviders(ArrayList<Provider> providers) {
		this.providers = providers;
	}

	public String getStringProviders() {
		String providersStringList = "";
		String sortedProvidersStringList = "";
		for (Provider i : providers) {
			if (i.getPlan().contentEquals("Premium")) {
				sortedProvidersStringList = sortedProvidersStringList + i.getStringProvider() + "\n";
				
			}
			else
				providersStringList = providersStringList + i.getStringProvider() + "\n";
		}
		
		sortedProvidersStringList = sortedProvidersStringList + providersStringList;

		return sortedProvidersStringList;
	}

	public String getStringProvidersGuest() {
		String providersStringList = "";
		String sortedProvidersStringList = "";
		for (Provider i : providers) {
			if (i.getPlan().contentEquals("Premium")) {
				sortedProvidersStringList = sortedProvidersStringList + i.getStringProviderGuest() + "\n";
				
			}
			else
				providersStringList = providersStringList + i.getStringProviderGuest() + "\n";
		}
		
		sortedProvidersStringList = sortedProvidersStringList + providersStringList;

		return sortedProvidersStringList;
	}

	public String getStringProvidersGuest(ArrayList<Provider> newList) {
		String providersStringList = "";
		for (Provider i : newList) {
			providersStringList = providersStringList + i.getStringProviderGuest() + "\n";
		}

		return providersStringList;
	}

	public String getStringProviders(ArrayList<Provider> newList) {
		String providersStringList = "";
		for (Provider i : newList) {
			providersStringList = providersStringList + i.getStringProvider() + "\n";
		}

		return providersStringList;
	}

}
