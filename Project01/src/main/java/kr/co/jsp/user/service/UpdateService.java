package kr.co.jsp.user.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import kr.co.jsp.user.model.UserDAO;
import kr.co.jsp.user.model.UserVO;

public class UpdateService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		UserVO vo = new UserVO();
		vo.setuId(((UserVO)request.getSession().getAttribute("user")).getuId());
		vo.setuPw(request.getParameter("uPw"));
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPhoneNum(request.getParameter("contactNum"));
		vo.setAddress(request.getParameter("basicAddress"));
		vo.setDetailAddress(request.getParameter("detailAddress"));


		UserDAO dao = UserDAO.getInstance();

		dao.updateUser(vo);
		request.getSession().setAttribute("user", dao.getUserInfo(vo.getuId()));

		String htmlCode;

		try {
			PrintWriter out = response.getWriter();

//			dao.changePassword(vo.getuId(), vo.getuPw());
			htmlCode =
					"<script>\r\n"
					+ "alert('회원정보가 변경되었습니다.')\r\n"
					+ "location.href='user_mypage.jsp';"
					+ "</script>";
			out.print(htmlCode);
			out.flush();
			

	} catch (Exception e) {
		e.printStackTrace();
	}



}

}
