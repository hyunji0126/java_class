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

		String id = request.getParameter("id");
		UserDAO dao = UserDAO.getInstance();

		String htmlCode;
		

		
		
		try {
			PrintWriter out = response.getWriter();
			if(dao.confirmId(id)) { 
				htmlCode = 
						"<script>\r\n"+
						"alert('아이디가 중복되었습니다');" + 
						"history.back();\r\n" + 
						"</script>";
				out.print(htmlCode);
				out.flush();
				htmlCode=		"<script>\r\n"+
						"alert('회원가입을환영합니다');"+"location.href='/MyWeb/loginPage.user';\r\n"+"</script>";
				out.print(htmlCode);//버ㅠㅓ에 htmlcode작성
				out.flush();
				return;
			}else {
				UserVO vo = new UserVO(
						id,
						request.getParameter("pw"),
						request.getParameter("name"),
						request.getParameter("email"),
						request.getParameter("address")
						);
				dao.insertUser(vo);
				htmlCode = 
						"<script>\r\n"+
						"alert('회원가입을니다');" + 
						"location.href='/MyWeb/loginPage.user';\r\n" + 
						"</script>";
				out.print(htmlCode);//버ㅠㅓ에 htmlcode작성
				out.flush();
				return;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
