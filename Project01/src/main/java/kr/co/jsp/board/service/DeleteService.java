package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;


public class DeleteService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {


//
		try {
			PrintWriter out = response.getWriter();
			int bId = Integer.parseInt(request.getParameter("bId"));

			BoardDAO.getInstance().deleteBoard(bId);
			response.setContentType("text/html; charset=UTF-8");

			String htmlCode = 
					"<script> \r\n" +
					"alert('삭제가 완료되었습니다.'); \r\n" +
					"location.href='list.board'; \r\n" +
					"</script>";
			out.print(htmlCode);
			out.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
