package kr.co.jsp.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.service.ContentService;
import kr.co.jsp.board.service.DeleteService;
import kr.co.jsp.board.service.GetListService;
import kr.co.jsp.board.service.IBoardService;
import kr.co.jsp.board.service.ModifyService;
import kr.co.jsp.board.service.RegistService;
import kr.co.jsp.board.service.SearchService;
import kr.co.jsp.board.service.UpdateService;
import kr.co.jsp.user.service.deleteService;

@WebServlet("*.board")
public class BoardController extends HttpServlet{

	private static final long serialversionUId = 1L;

	private RequestDispatcher dp;
	private IBoardService sv;

	public BoardController() {
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


	private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri= uri.substring(conPath.length()+1, uri.lastIndexOf("."));

		System.out.println(uri);

		switch(uri) {

		case "write":
			System.out.println("글쓰기");
			response.sendRedirect("/Project01/bbs_write.jsp");
			break;

		case "regist":
			System.out.println("글 등록");
			sv = new RegistService();
			sv.execute(request, response);
			response.sendRedirect("/Project01/list.board");
			break;

		case "list":
			System.out.println("목록보기");
			sv = new GetListService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("bbs.jsp");
			dp.forward(request, response);
			break;

		case "content":
			System.out.println("글상세보기");
			sv = new ContentService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("bbs_content.jsp");
			dp.forward(request, response);
			break;

		case "modify":
			System.out.println("글 수정요청 이동");
			sv = new ModifyService();
			sv.execute(request, response);
			
			if(request.getAttribute("flag") != null) {
				dp = request.getRequestDispatcher("bbs_modify.jsp");
				dp.forward(request, response);
			}
			break;

		case "update":
			System.out.println("글 수정하기");
			sv = new UpdateService();
			sv.execute(request, response);
			dp = request.getRequestDispatcher("content.board?bId=" + request.getParameter("boardId"));
			dp.forward(request, response);
			break;
			
		case "delete":
			System.out.println("글 삭제 요청이 들어옴.");
			sv = new DeleteService();
			sv.execute(request, response);
			break;
			
		case "search":
			System.out.println("검색하기");
			sv = new SearchService();
			sv.execute(request, response);

			if(request.getAttribute("boardList") != null) {
				dp = request.getRequestDispatcher("bbs_list.jsp");
				dp.forward(request, response);
				break;
			}


		}






	}}
