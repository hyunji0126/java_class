package kr.co.jsp.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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

	@Override
	public void insertUser(UserVO vo) {
		String sql = "INSERT INTO users VALUES(?,?,?,?,?,?,? )";
		try(Connection conn = ds.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getuId());
			pstmt.setString(2, vo.getuPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhoneNum());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getDetailAddress());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public int userCheck(String id, String pw) {
		int check = 0; //로그인 유효성 검사의 초기값, ID를 조회하여 결과가 없을 시 0 반환 (ID 불일치)
	      String sql = "SELECT * FROM users WHERE user_id = ?";
	      try(Connection conn = ds.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql);
	                    
	            ) { pstmt.setString(1, id);
	            ResultSet rs = pstmt.executeQuery();
	                  
	            if(rs.next()) { //아이디 존재
	               String dbPw = rs.getString("user_pw"); //DB에 저장되어 있는
	               //아이디의 실제 비밀번호
	               if(pw.equals(dbPw)) check = 1;
	               else check = 0;
	            }
	            
	             //사용자가 입력한 pw와 DB의 user_pw를 비교,
	               //일치 할 시 1(ID,PW 일치), 일치하지 않을 시 -1(PW 불일치) 반환
	               else check = -1;
	            


	      } catch (Exception e) {
	      
	      }
	      return check;
			}

	@Override
	public UserVO getUserInfo(String id) {
		UserVO user = null;
		String sql = "SELECT * FROM users WHERE user_id='" + id + "'";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				user = new UserVO(
						rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("name"),
						rs.getString("contact_num"),
						rs.getString("email"),
						rs.getString("address_basic"),
						rs.getString("address_detail")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public void changePassword(String id, String newPw) {
		String sql = "UPDATE users SET user_pw=? WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, newPw);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public boolean idConfirm(String id) {
		    boolean flag = false;
		    String sql = "SELECT * FROM users WHERE user_id = ?";
		    try(Connection conn = ds.getConnection();
		    PreparedStatement pstmt = conn.prepareStatement(sql)) {
		      pstmt.setString(1, id);
		      ResultSet rs = pstmt.executeQuery();
		      if(rs.next()) flag = true;
		      
		      
		   } catch (Exception e) {
			   e.printStackTrace();
		   }
		    return flag;
		    
		 }
	@Override
	public void updateUser(UserVO vo) { //ID를 제외한 모든 필드 수정 가능
		String sql = "UPDATE users "
				+ "SET name = ?, user_pw = ?,  contact_num = ?, email = ?, address_basic = ?, address_detail = ?  "
				+ "WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getuPw());
			pstmt.setString(3, vo.getPhoneNum());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getDetailAddress());
			pstmt.setString(7, vo.getuId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(String id) {
		String sql = "DELETE FROM users WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
