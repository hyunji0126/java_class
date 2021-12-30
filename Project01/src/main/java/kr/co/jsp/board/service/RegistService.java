package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;

public class RegistService implements IBoardService {
// 글 등록하기(작성자 제목 내용)
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String author = request.getParameter("author");//작성자
		String title = request.getParameter("title");//제목
		String content = request.getParameter("content");//내용
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(author, title, content);
		
		
	}

}
