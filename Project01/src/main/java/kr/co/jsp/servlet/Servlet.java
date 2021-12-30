package kr.co.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {

	public Servlet() {
		
		System.out.println("페이지 요청 발생");
		System.out.println("서블릿 생성");
	}
	
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("init 메서드 호출");
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet 메서드 호출");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost메서드 호출");
		
		
		super.doPost(request, response);
	}
	
	private void destory() {
		System.out.println("destory메서드호출");
	}
}
