package kr.co.jsp.board.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.jsp.board.model.BoardDAO;
import kr.co.jsp.board.model.BoardVO;
import kr.co.jsp.user.model.UserVO;
//글 수정하기
public class ModifyService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/html; charset=UTF-8");
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		UserVO uvo = (UserVO) request.getSession().getAttribute("user");
		BoardVO bvo = BoardDAO.getInstance().contentBoard(bId);
		boolean flag = false;

		if(uvo.getuId().equals(bvo.getAuthor())) {

			BoardVO vo = BoardDAO.getInstance().contentBoard(bId);
			flag = true;
			request.setAttribute("modi", vo);
			request.setAttribute("flag", flag);

		}else {
			try {
				PrintWriter out = response.getWriter();
				String htmlCode = "<script> \r\n" +
						"alert('접근 권한이 없습니다.'); \r\n" +
						"history.back(); \r\n" +
						"</script>";
				out.print(htmlCode);
				out.flush();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
