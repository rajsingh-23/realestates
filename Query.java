package valorVistaEstates;

public class Query {

	public static String insertClient = "INSERT INTO Clients VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	public static String authClient = "SELECT userId, cpassword FROM Clients WHERE userId = ? AND cpassword = ?;";
	public static String insertProperty = "INSERT INTO Properties VALUES (?,?,?,?,?,?,?,?,?);";
	public static String searchProperty = "SELECT * FROM Properties WHERE propertyOwner=?";
	public static String changePropertyStatus = "UPDATE Properties SET propertyStatus='sale' WHERE propertyName=?";
	public static String searchPropertyOnSale="SELECT * FROM Properties WHERE propertyStatus='sale'";
	public static String changePropertyOwner = "UPDATE Properties SET propertyOwner=?,propertyStatus='owned' where propertyName=?";
	public static String interestedInProperty= "INSERT INTO Interested VALUES(?,?,?,?);";
	public static String showInterested = "SELECT * FROM Interested WHERE propertyCurrentOwner=?";
	public static String rejectInterested = "DELETE FROM Interested WHERE interestedClient=? AND propertyName=?";
	

}
