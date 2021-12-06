package matchpackage.database;

import java.util.ArrayList;

public class CustomerList {
	
	
	ArrayList <Customer> customers= new ArrayList<Customer>();
	
	public CustomerList (){
		Customer cust1 = new Customer ("sanyam", "password1");
		Customer cust2 = new Customer ("jasneet", "password2");
		
		customers.add(cust1);
		customers.add(cust2);
	}
	
	public void addCustomer(Customer cust) {
		customers.add(cust);
		
	}
	
	

}
