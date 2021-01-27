package exam.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardVo;

@WebServlet("/createboard")
public class CreateBoard extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	//전체 게시판
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BoardVo> allboard = service.allBoard();
		
		req.setAttribute("allboard", allboard);
		
		req.getRequestDispatcher("/board/modifyBoard.jsp").forward(req, resp);
	}
	
	//게시판 생성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardname = req.getParameter("boardname");
		String boardact = req.getParameter("boardact");
		
		BoardVo vo = new BoardVo();
		vo.setBor_nm(boardname);
		vo.setBor_act(Integer.parseInt(boardact));
		
		int cnt = service.createBoardInfo(vo);
		
		//게시판 생성이 성공하면 메인으로 실패하면 게시판 생성 페이지로 돌아간다.
		if(cnt == 1) {
			doGet(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/modifyboard");
		}
	}
}
