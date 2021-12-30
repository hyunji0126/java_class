package kr.co.jsp.board.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.commons.PageCreator;
import kr.co.jsp.board.commons.PageVO;
import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;
//목록보기
public class GetListService implements IBoardService {
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		PageVO paging = new PageVO();
		
		if(request.getParameter("page") != null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
			paging.setCountPerPage(Integer.parseInt(request.getParameter("countPerPage")));
		}

		List<BoardVO> boardList = dao.listBoard(paging);

		
		PageCreator pc = new PageCreator();
		pc.setPaging(paging);
		pc.setArticleTotalCount(dao.countArticles()); 
		
		System.out.println("시작페이지: " + pc.getBeginPage());
		System.out.println("끝페이지: " + pc.getEndPage());
		
		//new mark--강사님 구두상으로 뉴마크처리 말씀하심
		
		for(BoardVO vo : boardList) {
			long now = System.currentTimeMillis();
			long regTime = vo.getRegDate().getTime();
			
			if(now - regTime < 60 * 60 * 24 * 1000){
				vo.setNewMark(true);
			}
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pc", pc);
		
	}
}
