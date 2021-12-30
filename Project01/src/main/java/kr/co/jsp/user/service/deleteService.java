package kr.co.jsp.user.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.model.UserDAO;
import kr.co.jsp.user.model.UserVO;

public class deleteService implements IUserService {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String id = ((UserVO) request.getSession().getAttribute("user")).getuId();
		String pw = request.getParameter("uPw");
		UserDAO dao = UserDAO.getInstance();

		String htmlCode;

		try {
			PrintWriter out = response.getWriter();
			if(dao.userCheck(id, pw) != 1) {
				htmlCode = 
						"<script>\r\n"
						+ "alert('비밀번호가 틀렸습니다')\r\n"
						+ "location.href='/Project01/user_myPage.jsp'\r\n"
						+ "</script>";
				out.print(htmlCode);
				out.flush();
				return;
			} else {
				dao.deleteUser(id);
				request.getSession().invalidate();
				htmlCode =
						"<script>\r\n"
						+ "alert('회원 탈퇴가 정상적으로 처리되었습니다.');\r\n"
						+ "location.href='index.jsp'\r\n"
						+ "</script>\r\n";
				out.print(htmlCode);
				out.flush();
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
