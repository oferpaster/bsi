package bsi;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

public class MySqlConnection {
	
	private Object result = null;

	public MySqlConnection(Object msg) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error */
			System.out.println("Im here: " + ex.getMessage());
		}

		try {
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/bsi_db", "root", "Op8448060");
			conn.setAutoCommit(false);
			update(conn, msg);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			setResult("SQLException: " + ex.getMessage());
			setResult("SQLState: " + ex.getSQLState());
			setResult("VendorError: " + ex.getErrorCode());
		}
	}

	public void update(Connection conn, Object msg) {

		String[] command = (String[]) msg;
		try {
			if (command[0].compareTo("SELECT") == 0) {
				readDB(conn, msg);
			}

			else if (command[0].compareTo("UPDATE") == 0) {
				updateDB(conn, msg);
			}
			else if (command[0].compareTo("INSERT") == 0){
				insertDB(conn, msg);
			}
			conn.close();
		} catch (Exception e) {
			setResult("update error:" + e.getMessage());
		}

	}

	private void readDB(Connection con, Object msg) {
		String[] getStatment = (String[]) msg;
		try {
			String selectSQL = "SELECT ? FROM ?;";
			PreparedStatement selectData=(PreparedStatement) con.prepareStatement(selectSQL);
			selectData.setString(1,getStatment[1]);
			selectData.setString(2,getStatment[2]);
			ResultSet rs = (ResultSet) selectData.executeQuery(selectSQL);
			ArrayList<String> stringList = new ArrayList<String>();
			while (rs.next()) {
				stringList.add(rs.getString(1));;
			}
			rs.close();
			setResult(stringList);
		} catch (Exception e) {
			System.out.println("readDB error:" + e.getMessage());
			setResult("readDB error:" + e.getMessage());
		}

	}

	private void updateDB(Connection con, Object msg) {

		String[] getStatment = (String[]) msg;
		try {
			PreparedStatement updataData=(PreparedStatement) con.prepareStatement("UPDATE ? SET val= ? WHERE val= ?;");
			updataData.setString(1,getStatment[1]);
			updataData.setString(2,getStatment[2]);
			updataData.setString(3,getStatment[3]);
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("updateDB error:" + e.getMessage());
			setResult("updateDB error:" + e.getMessage());
		}
		setResult(true);
	}
	
	private void insertDB(Connection con, Object msg) {
		String[] getStatment = (String[]) msg;
		try {
			PreparedStatement updataData=(PreparedStatement) con.prepareStatement("INSERT INTO ? VALUES(?);");
			updataData.setString(1,getStatment[1]);
			updataData.setString(2,getStatment[2]);
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("insertDB error:" + e.getMessage());
			setResult("insertDB error:" + e.getMessage());
		}
		setResult(true);
		
	}

	private void setResult(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return this.result;
	}

}


