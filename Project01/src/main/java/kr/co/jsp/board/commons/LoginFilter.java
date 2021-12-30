package kr.co.jsp.board.commons;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter( filterName = "loginAuth",
urlPatterns = {"/write.board", "/modify.board", "/delete.board", "/myPage.user"},
servletNames = {"basic"})
public class LoginFilter implements Filter {

	public LoginFilter() {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("dofilter 실행");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();//세션가져오기
		res.setContentType("text/html; charset=UTF-8");
		
		if(session.getAttribute("user") == null) {
			PrintWriter out = res.getWriter();
			String html = "<script> \r\n" +
					"alert('로그인이 필요합니다.'); \r\n" +
					"location.href='user_login.jsp';" +
					"</script>";
			out.print(html);
			out.flush();
			return;
		}

		chain.doFilter(request, response);
		}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("로긘 권한 필터 객체 생성됨");
	}
}
