package kr.co.jsp.user.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.model.UserDAO;
import kr.co.jsp.user.model.UserVO;

public class JoinService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("uId");
		String pw = request.getParameter("uPw");
		
		UserDAO dao = UserDAO.getInstance();
		
		String htmlCode;
		
		try {
			PrintWriter out = response.getWriter();
			if(dao.idConfirm(id)) {//아이디 비번둘다 가져옴
				htmlCode =
						"<script>\r\n"
						+ "alert('아이디가 중복되었습니다.');\r\n"
						+ "history.back();\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				return;
			}else {
				UserVO vo = new UserVO (
						id,
						pw,
						request.getParameter("name"),
						request.getParameter("email"),
						request.getParameter("contactNum"),
						request.getParameter("basicAddress"),
						request.getParameter("detailAddress")
						);
				dao.insertUser(vo);//회원추가
			System.out.println("insertUser 실행 성공");
				
			htmlCode = 
						"<script>\r\n"
						+ "alert('회원가입을 축하드립니다!');\r\n"
						+ "location.href='/Project01/user_login.jsp';\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
