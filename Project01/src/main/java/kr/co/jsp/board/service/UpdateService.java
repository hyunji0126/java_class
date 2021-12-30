package kr.co.jsp.board.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;


public class UpdateService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
//  	    int bId = Integer.parseInt(request.getParameter("bId"));
//		BoardDAO.getInstance().updateBoard(title,content,bId);
		
		BoardVO vo = new BoardVO();
		vo.setBoardId(Integer.parseInt(request.getParameter("boardId")));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		BoardDAO.getInstance().updateBoard(vo.getTitle(),vo.getContent(),vo.getBoardId());
	}

}
