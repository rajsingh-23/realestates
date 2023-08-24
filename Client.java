package valorVistaEstates;

import java.util.List;

public class Client {
	String userId;
	String name;
	String phoneNo;
	String password;
	Address address;
	List<Property> listOfPropertiesOwned;
	
	public Client() {
		super();
		this.userId = null;
		this.name = null;
		this.phoneNo = null;
		this.password = null;
		this.address = new Address();
		this.listOfPropertiesOwned = null;
	}

	@Override
	public String toString() {
		return "Client [userId=" + userId + ", name=" + name + ", phoneNo=" + phoneNo + ", password=" + password
				+ ", address=" + address + ", listOfPropertiesOwned=" + listOfPropertiesOwned + "]";
	}
}
