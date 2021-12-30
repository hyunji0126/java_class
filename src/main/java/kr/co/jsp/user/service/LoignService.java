package kr.co.jsp.user.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.model.UserDAO;
import kr.co.jsp.user.model.UserVO;

public class LoignService implements IUserService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		UserDAO dao = UserDAO.getInstance();
		UserVO vo = new UserVO();
		
		int result =  dao.userCheck(id, pw);
		String htmlCode;
		try {
			PrintWriter out = response.getWriter();
			if(result == -1) { 
				htmlCode = "<script>\r\n"+

		"alert('아이디가 존재하지 않습니다');"+
		"location.href='user_login.jsp';"+
		"		</script>";
				out.print(htmlCode);
				out.flush();
			} else if (result == 0){
				htmlCode = "<script>\r\n"+

		"alert('비밀번호가 틀렸습니다');"+
		"	history.back();"+"location.href='/MyWeb/loginPager.user';r\n"
		+"		</script>";
			} else { 
				request.getSession().setAttribute("user", vo);
				response.sendRedirect("/MyWeb/myaPage.user");
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
