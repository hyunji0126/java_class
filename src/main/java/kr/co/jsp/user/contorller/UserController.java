package kr.co.jsp.user.contorller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.service.ChangePwService;
import kr.co.jsp.user.service.IUserService;
import kr.co.jsp.user.service.JoinService;
import kr.co.jsp.user.service.LoignService;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IUserService sv;

	public UserController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dorequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		dorequest(request, response);
	}

	protected void dorequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length()+1, uri.lastIndexOf("."));

		System.out.println(uri);

		switch(uri) {
		case "joinPage":
			System.out.println("회원 가입 페이지로 이동 요청");
			response.sendRedirect("user/user_join.jsp");
			break;

		case "join":
			System.out.println("회원가입요청이 들어옴!");
			sv = new JoinService();
			sv.execute(request, response);
			break;

		case "loginPage":
			System.out.println("로그인");
			response.sendRedirect("user/user_login.jsp");
			break;

		case "login":
			System.out.println("로그인 요청이 들어옴!");
			sv = new LoignService();
			break;

		case "myPage":
			System.out.println("마이페이지로 이동 요청!");
			response.sendRedirect("user/user_mypage.jsp");
			break;
		
		case "pwPage":
			System.out.println("비밀번호 변경 페이지 이동요청!");
			response.sendRedirect("user/user_change_pw.jsp");
			break;
			
		case "changePw":
			System.out.println("비밀번호 변경 요청!");
			sv = new ChangePwService();
			sv.execute(request, response);
			
		}
		doGet(request, response);
	}

}
