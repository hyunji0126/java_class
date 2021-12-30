package kr.co.jsp.user.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.model.UserDAO;
import kr.co.jsp.user.model.UserVO;

public class LoginService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("uId");//id받기
		String pw = request.getParameter("uPw");//pw받기
		System.out.println(id);
		System.out.println(pw);
		UserDAO dao = UserDAO.getInstance();
				
		String htmlCode;
		int result = dao.userCheck(id, pw);
		try {
			PrintWriter out = response.getWriter();
			if(result == -1 ) {
				System.out.println(id);
				htmlCode = 
						"<script>"
						+ "alert('아이디가 존재하지 않습니다.');"
						+ "location.href='user_login.jsp';"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
			}else if(result == 0) {
				System.out.println("pw");
				htmlCode = 
						"<script>"
						+ "alert('비밀번호가 불일치합니다.');"
						+ "history.back();"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				return;
			}else if(result == 1){//1일떄
				UserVO vo = dao.getUserInfo(id);
				System.out.println("접근완료");
				request.getSession().setAttribute("user", vo);
//				response.sendRedirect("/Project01/user_login.jsp");
				
			htmlCode = 
					"<script>"
						+ "alert('환영합니다. 로그인 성공!');"
						+ "location.href='/Project01/index.jsp';\r\n"
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
