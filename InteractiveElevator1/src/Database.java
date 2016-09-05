
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private String driver;
	private String url;
	private String id, pw;
	private String query;

	private Statement stmt;
	private Connection conn;

	private int maxFloor;
	private int maxPerson;


	public Database(){


		maxFloor = 1;
		maxPerson = 0;
		
		try {
			driver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://localhost?useUnicode=true&characterEncoding=utf8";
			id = "root";
			pw = "apmsetup";
			query = null;
			stmt = null;

			Class.forName(driver); // Driver Loading
			conn = DriverManager.getConnection(url, id, pw);

		} catch (Exception e) {
			System.out.println("Error:: Database Connection Failure.");
		}

	}



	public void DBInsertion(int time, int[] floor_person)
	{
		String data_time = Integer.toString(time);
		String floor ="";
		String numOfPerson = "";

		
		for(int j=0; j<floor_person.length;j++)
		{
			if(floor_person[j] > maxPerson)
			{
				maxPerson = floor_person[j];
				maxFloor = j+1;
			}
		}
		
		
		try{
			stmt = conn.createStatement();

			query = "USE elevator";
			stmt.execute(query);


			for(int i=0; i<floor_person.length;i++)
			{
				floor = Integer.toString(i+1);
				numOfPerson = Integer.toString(floor_person[i]);
				query = "Insert into elevator_data(time, floor, person_count) values('" + data_time +"','" + floor +"','" + numOfPerson + "')";
				stmt.executeUpdate(query);

			}

		} catch (SQLException e) {
			System.out.println("Connection error : "+e);
		} finally
		{
			try {
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println("Close error!");
			}
		}

	}

	public void DBDeletion()
	{
		try{
			stmt = conn.createStatement();

			query = "USE elevator";
			stmt.execute(query);

			query = "Delete from elevator_data";
			stmt.executeUpdate(query);
		}catch (SQLException e) {
			System.out.println("Connection error : "+e);
		} finally
		{
			try {
				System.out.println("SUCCESS FOR DELETE IN DATABASE");
				if(stmt!=null) stmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				System.out.println("Close error!");
			}
		}
	}
	
	public int getMaxFloor()
	{
		return maxFloor;
	}
	
	public int getMaxPerson()
	{
		return maxPerson;
	}
	
}