package kr.co.jsp.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.commons.PageCreator;
import kr.co.jsp.board.commons.PageVO;
import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class GetListService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		PageVO paging = new PageVO();
		
		//사용자가 게시판에 처음들어올떄는 페이지 선택을 하지 않기 떄문에
		//페이지 선택을 1페이지로, 게시물 개수를 10개로 지정해 주겠습니다.
		/*
		  if(request.getParameter("page") == null) {
		 	paging.setPage(1);
			paging.setCountPerPage(10);
		} else { // 사용자가 직접 페이지를 선택했다면
			paging.setPage(Integer.parseInt(request.getParameter("page")));
			paging.setCountPerPage(Integer.parseInt(request.getParameter("countPerPage")));
		}
		 */
		
		// PageVO에 생성자 선언함 생성자에 기본값을 1페이지로 들어오게 선언함
		if(request.getParameter("page") != null) {
			paging.setPage(Integer.parseInt(request.getParameter("page")));
			paging.setCountPerPage(Integer.parseInt(request.getParameter("countPerPage")));
		}
			
	
		System.out.println("페이지 : " + paging.getPage());
		System.out.println("게시물 수 : " + paging.getCountPerPage());
		
		List<BoardVO> boardList = dao.listBoard(paging);
		
		//페이지 버튼 배치를 위해 PageCreator 객체를 생성
		PageCreator pc = new PageCreator();
		//페이징 알고리즘을 위해 PageVO 객체와 총 게시물 수를 setter로 전달
		pc.setPaging(paging);
		pc.setArticleTotalCount(dao.countArticles());//ㅠㅔ이징 알고리즘 발동
		
		System.out.println("시작페이지 : " + pc.getBeginPage());
		System.out.println("끝페이지 : " + pc.getEndPage());
		
		
		//1일 이내 신규글 new마크 처리 로직
		for(BoardVO vo : boardList) {
			//현재 시간 읽어오기 (밀리초)
			long now = System.currentTimeMillis();
			//게시물의 작성 시간 밀리초로 읽어오기
			long regTime = vo.getRegDate().getTime();
			
			if(now - regTime < 60 * 60 * 24 * 1000) {
				vo.setNewMark(true);
			}
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pc", pc);
	}

}













