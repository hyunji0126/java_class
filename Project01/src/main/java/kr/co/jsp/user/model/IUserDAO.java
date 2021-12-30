package kr.co.jsp.user.model;

public interface IUserDAO {
    void insertUser(UserVO vo);
   
    int userCheck(String id, String pw);
   
 	UserVO getUserInfo(String id);
 	
 	void changePassword(String id, String newPw);
 	
 	void updateUser(UserVO vo);
 
 	void deleteUser(String id);
}
