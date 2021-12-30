package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;

public class SearchService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String keyword = request.getParameter("search");
		String category = request.getParameter("category");//작성자가 제목, 내용으로 검색할수있기떄문에
		List<BoardVO> list = BoardDAO.getInstance().searchBoard(keyword, category);
		
		/*
		if(list.size() == 0) {
			request.setAttribute("searchFail", true);
		} else {
			request.setAttribute("boardList", list);			
		}
		*/
		
		if(list.size() == 0) {
			//자바 클래스에서 HTML이나 JS 문법을 사용하는 방법: PrintWriter 객체를 사용.
			response.setContentType("text/html; charset=UTF-8");
			
			try {
				PrintWriter out = response.getWriter();
				
				//원하는 html / js 코드를 문자열 형태로 작성합니다.
				 
				String htmlCode = "<script> \r\n" + //줄 개행 \n , \r 커서앞으로 떙김
								  "alert('검색 결과에 따른 게시물이 없습니다.'); \r\n" +
								  "location.href='/MyWeb/list.board'; \r\n" +
								  "</script>";
				
				out.print(htmlCode);//
				
				out.flush(); //버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비웁니다.
//				내가 html이나 자바스크립트에 작성한 내용이 버퍼에 저장됨 -> flush를 부르면 브라우저로 한번에 전송됨-> 다음 응답을 위해 버퍼를 비운다
				return;//더 이상진행할꺼 없으니 return을 사용. 강제종료 메서드 return
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
		request.setAttribute("boardList", list);
		

 	}

}













