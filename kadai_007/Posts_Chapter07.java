package text_2.Issue.kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {
	private static final String PASSWORD = "rGop5vXedKDhzUS6Lu4Za";
	public static void main(String[] args) {
		short port = 8609;
		String db_name = "challenge_java";
		PreparedStatement state = null;
		Statement state_s = null;
		Object[][] userList = {
			{1003, "2023-02-08", "機能の夜は徹夜でした...", 13},
			{1002, "2023-02-08", "お疲れ様です！", 12},
			{1003, "2023-02-09", "今日も頑張ります！", 18},
			{1001, "2023-03-09", "無理は禁物ですよ！", 17},
			{1002, "2023-03-10", "明日から連休ですね！", 20},
		};
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
			
			// insert
			String sql =
				"INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?)";
			state = con.prepareStatement(sql);
			
			int rowCnt = 0;
			for (int c = 0; c < userList.length; c++) {
				state.setObject(1, userList[c][0]);
				state.setObject(2, userList[c][1]);
				state.setObject(3, userList[c][2]);
				state.setObject(4, userList[c][3]);
				rowCnt += state.executeUpdate();
			}
			System.out.println(rowCnt + "件のレコードが追加されました");
			
			// select
			state_s = con.createStatement();
			String sql = "SELECT * FROM posts;";
			
			ResultSet result = state_s.executeQuery(sql);
			
			while(result.next()) {
				Date date = result.getDate("posted_at");
				String str = result.getString("post_content");
				int likes = result.getInt("likes");
				System.out.println(result.getRow() + "件目：登校日" + date + "／投稿内容=" + str + "／いいね数=" + likes);
			}
			
			state_s.close();
//			state.close();
			// データベース接続を解除
			con.close();
		} catch (SQLException e) {
			System.out.println("接続失敗 >> " + e.getMessage());
		}
	}
}
