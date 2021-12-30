package kr.co.jsp.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.user.model.UserVO;
import kr.co.jsp.user.service.IUserService;
import kr.co.jsp.user.service.JoinService;
import kr.co.jsp.user.service.LoginService;
import kr.co.jsp.user.service.UpdateService;
import kr.co.jsp.user.service.deleteService;

@WebServlet("*.user")
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private IUserService sv;
	
	public UserController() {
		super();
	}
	@Override
	public void destroy() {
		super.destroy();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String uri = request.getRequestURI();
		uri = uri.substring(request.getContextPath().length() + 1, uri.lastIndexOf("."));
		
		System.out.println(uri);
		
		switch (uri) {
		case "joinPage": 
			System.out.println("회원가입페이지로 이동");
			response.sendRedirect("/Project01/user_join.jsp");//회원가입페이지 주소
			break;
			
		case "join":
			System.out.println("회원가입요청");
			sv = new JoinService();
			sv.execute(request, response);
			break;
			
		case "loginPage":
			System.out.println("로그인페이지");
			response.sendRedirect("/user/uesr_login.jsp");//주소값 로긘 페이지
			break;
			
		case "login":
			sv = new LoginService();
			sv.execute(request, response);
			break;
			
		case "myPage":
			System.out.println("마이페이지 이동");
			response.sendRedirect("/Project01/user_mypage.jsp");//마이페이지주소값
			break;
			
		case "delete":
			System.out.println("회원탈퇴");	
			sv = new deleteService();
			sv.execute(request, response);
			break;
			
		case "update":
			System.out.println("회원정보변경");
			sv = new UpdateService();
			sv.execute(request, response);
			break;
			
		}
		
		
		
	
	}

	
	
	
	
	
	
}
