package text_2.Issue.kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
	private static final int 
		ID = 0,
		NAME = ID+1,
		MTH_TEST = NAME+1,
		ENG_TEST = MTH_TEST+1;
	public static void main (String[] args) {
		
		Object[] yuki_score = {5, "武者小路勇気", 95, 80};
		String table_name = "scores";
		Connection con = null;
		Statement state_u = null;
		try {
			// connect 1
			con = DriverManager.getConnection(
				// port
				"jdbc:mysql://localhost:8609/challenge_java",
				// user
				"root",
				// pass word
				"rGop5vXedKDhzUS6Lu4Za"
			);
			System.out.println("データベース接続成功 : "+con);
			
			// update 2, 3
			state_u = con.createStatement();
			String sql_u=
				"UPDATE "+table_name+
				" SET score_math="+yuki_score[MTH_TEST]+
				", score_english="+yuki_score[ENG_TEST]+
				" WHERE id="+yuki_score[ID]+";";
			System.out.println("レコード更新を実行します");
			// 更新
			int rowCnt = state_u.executeUpdate(sql_u);
			System.out.println(rowCnt + "件のレコードが更新されました");
			
			// select 4, 5
			String sql_s = "SELECT * FROM "+table_name+" ORDER BY id DESC";
			ResultSet result = state_u.executeQuery(sql_s);
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int point_m = result.getInt("score_math");
				int point_e = result.getInt("score_english");
				// 取得したデータを用いる処理を必要に応じて記述
				System.out.println(
					result.getRow()+"件目："+
					"生徒ID="+id+
					"／氏名="+name+
					"／数学="+point_m+
					"／英語="+point_e);
			}
		} catch (SQLException e) {
			System.out.println("接続失敗 >> " + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if( state_u != null ) { try { state_u.close(); } catch(SQLException ignore) {} }
			if( con != null ) { try { con.close(); } catch(SQLException ignore) {} }
		}
	}
	
	
}