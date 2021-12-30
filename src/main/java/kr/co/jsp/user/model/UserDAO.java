package kr.co.jsp.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.DataSource;

public class UserDAO implements IUserDAO {

	private DataSource ds;
	
	private UserDAO() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/myOracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static UserDAO dao = new UserDAO();
	
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	
	
	////////////////////////////////////////////////////////////////
	
	@Override
	public boolean confirmId(String id) {
		boolean flag = false;
		String sql = "SELECT * FROM my_user WHERE user_id=?";
		/*
		 # try with resource
		 - try문을 작성할 떄 자원 객체를 소괄호 안에 전달하면 try 블록이 끝날 떄 자동으로 자원을 해제해 줍니다.
		 - 자동으로 종료되는 자원은 반드시 AutoCloseable 인터페이스의 구현체여야 합니다.
		 
		 try문 뒤에()를 쓰면 자동으로 try with resource로 자동인식함.
		 */
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt =  conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) flag = true;
		} catch (Exception e) {
		}//close를 안해도 되는 문법
		return flag;
	}

	@Override
	public void insertUser(UserVO vo) {
		String sql = "INSERT INTO my_user VALUES(?,?,?,?,?)";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public int userCheck(String id, String pw) {
		int check = 0;
		
		String sql = "SELECT user_pw FROM my_user WHERE user_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt =  conn.prepareStatement(sql)) {
			
				pstmt.setString(1, id);
				ResultSet rs = pstmt.executeQuery();
			
			//첫번쨰 if문은 id 유무 판단, 중첩if문에서는 pw
			if(rs.next()) {
				String dbPw = rs.getString("user_pw");
				if(dbPw.equals(pw)) {
					check = 1;
				} else check = 0;
			} else {
				check = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	
	@Override
	public UserVO getUserInfo(String id) {
		UserVO user = null;
		String sql ="SELECT * FROM my_user WHERE user_id='" + id + "'";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			if(rs.next()) {
				user = new UserVO(
							rs.getString("user_id"),
							rs.getString("user_pw"),
							rs.getString("user_name"),
							rs.getString("user_email"),
							rs.getString("user_address")
						);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return user;
	}

	@Override
	public void changePassword(String id, String newPw) {
		String sql ="UPDATE my_user SET user_pw=? WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(UserVO vo) {
		String sql ="UPDATE my_user SET user_name=?, user_email=?, user_address=? WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getId() );
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void deleteUser(String id) {
		String sql = "DELETE FROM my_user WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
