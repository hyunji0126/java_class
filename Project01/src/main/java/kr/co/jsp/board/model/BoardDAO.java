package kr.co.jsp.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.jsp.board.commons.PageVO;

public class BoardDAO implements IBoardDAO {
	private DataSource ds;

private BoardDAO() {
	try {
		InitialContext ct = new InitialContext();
		ds = (DataSource) ct.lookup("java:comp/env/jdbc/myOracle");
	} catch (NamingException e) {
		e.printStackTrace();
	}
}
private static BoardDAO dao = new BoardDAO();

public static BoardDAO getInstance() {
	if(dao == null) {
		dao = new BoardDAO();
	}
	return dao;
}

	@Override
	public void regist(String author, String title, String content) {
        String sql = "INSERT INTO board(board_id, author, title, content)"
        		+ "VALUES(board_seq.NEXTVAL, ?, ?, ?)";
        try(Connection conn = ds.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, author);
        	pstmt.setString(2, title);
        	pstmt.setString(3, content);
        	pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<BoardVO> listBoard(PageVO vo)
	{
		List<BoardVO> articles = new ArrayList<>();
		   String sql = "SELECT * FROM"
		            + "   ("
		            + "   SELECT ROWNUM AS rn, tbl.* FROM "
		            + "      ("
		            + "      SELECT * FROM board"
		            + "      ORDER BY board_id DESC"
		            + "      ) tbl"
		            + "   )"
		            + "WHERE rn > " + (vo.getPage()-1) * vo.getCountPerPage()
		            + " AND rn <= " + vo.getPage() * vo.getCountPerPage();
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery())
		{
			while(rs.next()) {
				BoardVO vv = new BoardVO(
						rs.getInt("board_id"),
						rs.getString("author"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("reg_date"));
				articles.add(vv);
			}
		}
		catch(Exception e) {}
			
		return articles;
	}

	@Override
	public BoardVO contentBoard(int bId) {
		BoardVO vo = null;
		String sql = "SELECT * FROM board WHERE board_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO(
							rs.getInt("board_id"),
							rs.getString("author"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getTimestamp("reg_date")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	

	@Override
	public void updateBoard(String title, String content, int bId) {
		String sql = "UPDATE board "
				+ "SET title=?, content=? "
				+ "WHERE board_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void deleteBoard(int bId) {
		String sql = "DELETE FROM board WHERE board_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	   public List<BoardVO> searchBoard(String keyword, String category) {
	      List<BoardVO> articles = new ArrayList<>();
	      String sql = "SELECT * FROM my_board WHERE " + category + " LIKE ? "
	            + "ORDER BY board_id DESC";
	      try(Connection conn = ds.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql)) {
	         pstmt.setString(1, "%" + keyword + "%");
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {
	            BoardVO vo = new BoardVO(
	                     rs.getInt("board_id"),
	                     rs.getString("author"),
	                     rs.getString("title"),
	                     rs.getString("content"),
	                     rs.getTimestamp("reg_date")
	                  );
	            articles.add(vo);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return articles;
	   }


	@Override
	public int countArticles() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM board";
		
		try(Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()){
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return count;
	}

}
