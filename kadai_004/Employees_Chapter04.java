package text_2.Issue.kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {
	private static final String PASSWORD = "rGop5vXedKDhzUS6Lu4Za";
	
	public static void main(String[] args) {
		short port = 8609;
		String db_name = "challenge_java";
		Statement state = null;
		try {
			// データベースに接続
			Connection con = DriverManager.getConnection(
				// port
				"jdbc:mysql://localhost:" + port + "/" + db_name,
				// user
				"root",
				// pass word
				PASSWORD
			);
			System.out.println("データベース接続成功");
			System.out.println(con);
			
			state = con.createStatement();
			String sql =
				"CREATE TABLE employees" + "("
				+ "id INT(11) AUTO_INCREMENT PRIMARY KEY,"
				+ "name VARCHAR(60) NOT NULL,"
				+ "email VARCHAR(255) NOT NULL,"
				+ "age INT(11),"
				+ "address VARCHAR(255)"
				+ ");";
			int rowCnt = state.executeUpdate(sql);
			System.out.println("テーブルを作成:rowCnt=" + rowCnt );
			
			state.close();
			// データベース接続を解除
			con.close();
		} catch (SQLException e) {
			System.out.println("接続失敗 >> " + e.getMessage());
		}
	}
	
	
}