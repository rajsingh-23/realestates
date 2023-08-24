package valorVistaEstates;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		while(true) {
			int n = startMenu();
			if(n == 101) {
				break;
			}else if (n == 1){
				clientMenu(login());
			}else {
				SignUp();
				login();
			}
		}

	}

	private static void clientMenu(String userId) throws SQLException, InterruptedException {
		System.out.println("Select an option:\n\t0 -> search a new property and place an offer\n\t1 -> add an property\n\t2 -> owned property\n\t3 -> sell a property\n\t4 -> look for interested people on your property\n\t6 -> exit");
		switch(scanner.nextInt()) {
		case 0:	interestedProperty(userId);
				clientMenu(userId);
			break;
		case 1:	addProperty(userId);
				clientMenu(userId);
			break;
		case 2:	showOwnedProperties(userId);
				clientMenu(userId);
			break;
		case 3: sellProperty(userId);
				clientMenu(userId);
			break;
		case 4:	lookForInterested(userId);
				clientMenu(userId);
			break;
		case 5: login();
			break;
		}
	}

	private static void lookForInterested(String userId) throws SQLException {
		System.out.println("This are the people interested to buy and their offers.....");
		int i=ClientDao.showInterested(userId);
		if(i==0) {
			return;
		}
		System.out.println("Do you accept the offer");
		int n = scanner.nextInt();
		
		if(n == 1) {
			System.out.println("for confirmation enter the property name ");
			String s = scanner.next();
			System.out.println("enter the other party id ");
			String ouserId = scanner.next(); 
			ClientDao.buyProperty(ouserId, s);
			ClientDao.rejectOffer(ouserId, s);
//			check the userid here once if it fails
		}else {
			System.out.println("Enter the other party name");
			String iuserId = scanner.next();
			System.out.println("for confirmation enter the property name ");
			String s = scanner.next();
			ClientDao.rejectOffer(iuserId, s);
		}
	}

	private static void sellProperty(String userId) throws SQLException {
		ClientDao.showProperties(userId);
		System.out.println("select one from the above:");
		String pname = scanner.next();
		ClientDao.addPropertyToSale(userId,pname);
		
	}

	private static void showOwnedProperties(String userId) throws SQLException {
		ClientDao.showProperties(userId);
	}

	private static void interestedProperty(String userId) throws SQLException {
		int n = ClientDao.showPropertiesOnSale(userId);
		if(n==0) {
			return;
		}else {
			System.out.println("select one from the above to offer/buy :");
			Interested obj = new Interested();
			obj.propertyName = scanner.next();
			obj.interestedClient = userId;
			System.out.println("what's your offer on this property ?");
			obj.hisOffer = scanner.nextDouble();
			System.out.println("enter the property owner id:");
			obj.propertyOwner= scanner.next();
			ClientDao.interested(obj);
//			ClientDao.buyProperty(obj);
		}
	}

	private static void addProperty(String userId) throws SQLException {
		System.out.println("Select the property type : \n\t0 -> Villa\n\t1 -> Plot\n\t2 -> Flat");
		switch(scanner.nextInt()) {
		case 0:	
			ClientDao.addVilla(fetchPropertyData(0, userId));
			break;
		case 1:	
			ClientDao.addPlot(fetchPropertyData(1, userId));
			break;
		case 2:	
			ClientDao.addFlat(fetchPropertyData(2,userId));
			break;
		}
	}

	private static Property fetchPropertyData(int option, String userId) {
		Property newProperty = null;
		if(option == 0) {
			newProperty = new Villa();
			newProperty.propertyType = "villa";
		}else if(option == 1) {
			newProperty = new Plot();
			newProperty.propertyType = "plot";
		}else {
			newProperty = new Flat();
			newProperty.propertyType = "flat";
		}
		System.out.print("Enter your Property Name : ");
		newProperty.propertyName = scanner.next();
		System.out.println("Enter your Property Price : ");
		newProperty.propertyPrice = scanner.nextDouble();
		System.out.println("Enter the country in where the property is located : ");
		newProperty.country = scanner.next();
		System.out.println("Enter the state in which the property is present : ");
		newProperty.state = scanner.next();
		System.out.println("Enter the city where the property is : ");
		newProperty.city = scanner.next();
		System.out.println("Enter the pincode : ");
		newProperty.pincode = scanner.next();
		newProperty.propertyOwner = userId;
		newProperty.propertyStatus = "owned";
		
		return newProperty;
	}

	private static void SignUp() throws SQLException {
		Client newUser = new Client();
		System.out.print("Enter your Name : ");
		newUser.name = scanner.next();
		System.out.println("Enter your Phone no : ");
		newUser.phoneNo = scanner.next();
		System.out.println("Create a New User ID : ");
		newUser.userId = scanner.next();
		System.out.println("Create a New Password : ");
		newUser.password = scanner.next();
		System.out.println("Enter the country you live in : ");
		newUser.address.country = scanner.next();
		System.out.println("Enter the state you live in : ");
		newUser.address.state = scanner.next();
		System.out.println("Enter the city you live in : ");
		newUser.address.city = scanner.next();
		System.out.println("Enter the pincode : ");
		newUser.address.pincode = scanner.next();
		
		ClientDao.createClient(newUser);
		System.out.println("New User created successfully Please Login now !");
	}

	private static int startMenu() {
		System.out.println("Welcome to Valor Vista Estates..");
		System.out.println("Select one option : \n\t0 -> Sign Up \n\t1 -> Login\n\t101->LogOut");
		int selectedOption = scanner.nextInt();
		return selectedOption;
	}

	private static String login() throws SQLException, InterruptedException {
		String userId="";
		String password;
		
			
			System.out.println("Enter your User ID : ");
			userId = scanner.next();
			System.out.println("Enter your Password : ");
			password = scanner.next();
			ClientDao.authenticate(userId, password);
			System.out.println("Login sucessfull");
			
	
		return userId;
	}

}
