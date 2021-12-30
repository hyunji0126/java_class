package kr.co.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class ContentService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int id  =Integer.parseInt(request.getParameter("boardId"));
		
		BoardVO vo = BoardDAO.getInstance().contentBoard(id);
		request.setAttribute("vo", vo);
		
		
		
		
		
		
		
		
		
		
	}

	
}
