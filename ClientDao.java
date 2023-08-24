package valorVistaEstates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {
	
	public static void createClient(Client client) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.insertClient;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, client.userId);
		statement.setString(2, client.name);
		statement.setString(3, client.phoneNo);
		statement.setString(4, client.password);
		statement.setString(5, client.address.country);
		statement.setString(6, client.address.state);
		statement.setString(7, client.address.city);
		statement.setString(8, client.address.pincode);
		
		statement.executeUpdate();
		statement.close();
//		try connection close
	}

	public static void authenticate(String userId, String password) throws SQLException, InterruptedException {
		Connection connection = DB.connect();
		String query = Query.authClient;
		
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, userId);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();
	
		while(resultSet.next()) {
			System.out.println("userId : "+resultSet.getString(1));
			System.out.println("password : "+resultSet.getString(2));
		}

		statement.close();
		Thread.sleep(2000);
	}

	public static void addVilla(Property property) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.insertProperty;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, property.propertyOwner);
		statement.setString(2, property.propertyName);
		statement.setDouble(3, property.propertyPrice);
		statement.setString(4, property.country);
		statement.setString(5, property.state);
		statement.setString(6, property.city);
		statement.setString(7, property.pincode);
		statement.setString(8, property.propertyType);
		statement.setString(9, property.propertyStatus);
		
		statement.executeUpdate();
		statement.close();
		
	}

	public static void addPlot(Property property) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.insertProperty;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, property.propertyOwner);
		statement.setString(2, property.propertyName);
		statement.setDouble(3, property.propertyPrice);
		statement.setString(4, property.country);
		statement.setString(5, property.state);
		statement.setString(6, property.city);
		statement.setString(7, property.pincode);
		statement.setString(8, property.propertyType);
		statement.setString(9, property.propertyStatus);
		
		statement.executeUpdate();
		statement.close();
		
	}

	public static void addFlat(Property property) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.insertProperty;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, property.propertyOwner);
		statement.setString(2, property.propertyName);
		statement.setDouble(3, property.propertyPrice);
		statement.setString(4, property.country);
		statement.setString(5, property.state);
		statement.setString(6, property.city);
		statement.setString(7, property.pincode);
		statement.setString(8, property.propertyType);
		statement.setString(9, property.propertyStatus);
		
		statement.executeUpdate();
		statement.close();
		
	}

	public static void showProperties(String userId) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.searchProperty;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1,userId);
		ResultSet resultSet = statement.executeQuery();
		System.out.println("+-------------------------------------------------------------------------------------------------------------+");
		System.out.println("| Property Owner | Property Name | Price | Country | State | City | Pincode | Property Type | Property Status |");
		System.out.println("+-------------------------------------------------------------------------------------------------------------+");
		while(resultSet.next()) {
			System.out.println("| "+resultSet.getString(1)+ "|\t"+resultSet.getString(2)+"|\t"+resultSet.getDouble(3)+"|\t"+resultSet.getString(4)+"|\t"+resultSet.getString(5)+"|\t"+resultSet.getString(6)+"|\t"+resultSet.getString(7)+"|\t"+resultSet.getString(8)+"|\t"+resultSet.getString(9)+" |");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
		}
		statement.close();
	}

	public static void addPropertyToSale(String userId, String pname) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.changePropertyStatus;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pname);
		statement.executeUpdate();
		statement.close();
	}

	public static int showPropertiesOnSale(String userId) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.searchPropertyOnSale;
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet resultSet = statement.executeQuery();
		int count=0;
			while(resultSet.next()) {
				count++;
			}
			if(count==0) {
				return 0;
			}
			resultSet = statement.executeQuery();
		System.out.println("+-------------------------------------------------------------------------------------------------------------+");
		System.out.println("| Property Owner | Property Name | Price | Country | State | City | Pincode | Property Type | Property Status |");
		System.out.println("+-------------------------------------------------------------------------------------------------------------+");
		
		while(resultSet.next()) {
			System.out.println("| "+resultSet.getString(1)+ "|\t"+resultSet.getString(2)+"|\t"+resultSet.getDouble(3)+"|\t"+resultSet.getString(4)+"|\t"+resultSet.getString(5)+"|\t"+resultSet.getString(6)+"|\t"+resultSet.getString(7)+"|\t"+resultSet.getString(8)+"|\t"+resultSet.getString(9)+" |");
			System.out.println("---------------------------------------------------------------------------------------------------------------");
		}
		
		statement.close();
		return 1;
	}

	public static void buyProperty(String userId,String pname) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.changePropertyOwner;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, userId);
		statement.setString(2, pname);
		statement.executeUpdate();
		statement.close();
		System.out.println("Property "+pname+" is now owned by "+userId+" transaction sucessfull");
	}

	public static void interested(Interested obj) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.interestedInProperty;
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.setString(1, obj.interestedClient);
		statement.setString(2, obj.propertyName);
		statement.setString(3, obj.propertyOwner);
		statement.setDouble(4, obj.hisOffer);
		
		statement.executeUpdate();
		statement.close();
	}

	public static int showInterested(String userId) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.showInterested;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, userId);
		ResultSet resultSet = statement.executeQuery();
		int count=0;
		while(resultSet.next()) {
			count++;
		}
		if(count==0) {
			return 0;
		}
		resultSet = statement.executeQuery();
		System.out.println("+--------------------------------------------------------------------+");
		System.out.println("| Interested Client | Property Name | Property Owner | Offered Price |");
		System.out.println("+--------------------------------------------------------------------+");
		
		while(resultSet.next()) {
			System.out.println("| "+resultSet.getString(1)+ "|\t"+resultSet.getString(2)+"|\t"+resultSet.getString(3)+"|\t"+resultSet.getDouble(4)+" |");
			System.out.println("-----------------------------------------------------------------------");
		}
		
		statement.close();
		return 1;
	}

	public static void rejectOffer(String userId, String s) throws SQLException {
		Connection connection = DB.connect();
		String query = Query.rejectInterested;
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, userId);
		statement.setString(2, s);
		statement.executeUpdate();
		statement.close();
	}
}
