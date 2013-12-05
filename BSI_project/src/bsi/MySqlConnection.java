package bsi;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class MySqlConnection {
	
	private Object result = null;

	public MySqlConnection(Object msg) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error */
			System.out.println("Im here: " + ex.getMessage());
		}

		try {
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/vcp_db", "root", "Op8448060");
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
			if (command[0].compareTo("readDB") == 0) {
				readDB(conn, msg);
			}

			else if (command[0].compareTo("updateDB") == 0) {
				updateDB(conn, msg);
			}
			conn.close();
		} catch (Exception e) {
			setResult("update error:" + e.getMessage());
		}

	}

	private void readDB(Connection con, Object msg) {
		String[] getMsg = (String[]) msg;
		String ans = "";
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(getMsg[1]);
			while (rs.next()) {
				ans = ans + rs.getString(1);
			}
			rs.close();
			setResult(ans);
		} catch (Exception e) {
			System.out.println("readDB error:" + e.getMessage());
			setResult("readDB error:" + e.getMessage());
		}

	}

	private void updateDB(Connection con, Object msg) {

		String[] getStatment = (String[]) msg;
		String setMsg = getStatment[1];
		try {
			PreparedStatement updataData=(PreparedStatement) con.prepareStatement("UPDATE prototype SET val= ? WHERE val= 'A';");
			updataData.setString(1,setMsg);
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("updateDB error:" + e.getMessage());
			setResult("updateDB error:" + e.getMessage());
		}
		setResult(true);
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return this.result;
	}

}


