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

		Provider pro1 = new Provider("codezero", "codepassword", "https://codezero.io/", "logo1", 10, "icon1", pro1List,
				"resume1", "basic");
		Provider pro2 = new Provider("thehack", "hackpassword", "https://hackdesign.org/", "logo2", 15, "icon2",
				pro2List, "resume2", "premium");

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
		for (Provider i : providers) {
			providersStringList = providersStringList + i.getStringProvider() + "\n";
		}

		return providersStringList;
	}
}