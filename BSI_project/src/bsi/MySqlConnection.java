package bsi;

import java.util.ArrayList;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlConnection {

	private Object result = null;
	private Connection conn;
	private ResultSet rs = null;

	public MySqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {/* handle the error */
			System.out.println("Im here: " + ex.getMessage());
		}

		try {
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/bsi_db", "root", "Op8448060");
			conn.setAutoCommit(false);
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

	public Connection getConn() {
		return conn;
	}

	public void update(Connection conn, String[] msg) {

		String[] command = (String[]) msg;
		try {
			if (command[0].contains("SELECT")) {
				readDB(conn, msg);
			}

			else if (command[0].contains("UPDATE")) {
				updateDB(conn, msg);
			} else if (command[0].contains("INSERT")) {
				insertDB(conn, msg);
			}
			conn.close();
		} catch (Exception e) {
			setResult("update error:" + e.getMessage());
		}

	}

	private void readDB(Connection con, String[] getStatment) {
		boolean thereIsRslt = false;
		try {
			String selectSQL = getStatment[0];
			PreparedStatement selectData = con.prepareStatement(selectSQL);
			for(int i=1; i<getStatment.length;i++ )
				selectData.setString(i, getStatment[i]);
			rs = selectData.executeQuery();
			ArrayList<Object> list = new ArrayList<Object>();
			while (rs.next()) {
				list.add(rs.getString(1));
				thereIsRslt = true;
			}
			if (thereIsRslt) {
				rs.close();
				setResult(list);
				thereIsRslt = false;
			} else {
				rs.close();
				setResult("No Result");
			}
		} catch (Exception e) {
			System.out.println("readDB error:" + e.getMessage());
			setResult("readDB error:" + e.getMessage());
		}

	}

	private void updateDB(Connection con, String[] getStatment) {
		try {
			PreparedStatement updataData = con
					.prepareStatement("UPDATE ? SET val= ? WHERE val= ?;");
			updataData.setString(1, getStatment[1]);
			updataData.setString(2, getStatment[2]);
			updataData.setString(3, getStatment[3]);
			updataData.executeUpdate();
			con.commit();
		} catch (Exception e) {
			System.out.println("updateDB error:" + e.getMessage());
			setResult("updateDB error:" + e.getMessage());
		}
		setResult(true);
	}

	private void insertDB(Connection con, String[] getStatment) {
		try {
			PreparedStatement updataData = con
					.prepareStatement("INSERT INTO ? VALUES(?);");
			updataData.setString(1, getStatment[1]);
			updataData.setString(2, getStatment[2]);
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
