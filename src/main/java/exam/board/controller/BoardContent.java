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
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;

@WebServlet("/boardcontent")
public class BoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bornum = req.getParameter("bornum");
		
		List<BoardPostVo> selectBoardList =  service.boardContent(Integer.parseInt(bornum));
		List<BoardVo> list = service.selectBoardInfo(Integer.parseInt(bornum));
		req.setAttribute("selectBoardList", selectBoardList);
		req.setAttribute("boardinfo", list);
		req.getRequestDispatcher("/board/boardmain.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bor_num = req.getParameter("num");
		
		List<BoardVo> list = service.selectBoardInfo(Integer.parseInt(bor_num));
		
		req.setAttribute("boardinfo", list);
		
		req.getRequestDispatcher("/board/newboardpost.jsp").forward(req, resp);
	}

}
