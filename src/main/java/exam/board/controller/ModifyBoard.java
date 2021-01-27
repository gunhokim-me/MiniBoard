package exam.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardVo;

@WebServlet("/modifyboard")
public class ModifyBoard extends HttpServlet{
	private static final long serialVersionUID = 1L;

	BoardServiceI service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String boardnum = req.getParameter("boardnum");
		String boardname = req.getParameter("boardname");
		String boardact = req.getParameter("boardact");
		
		BoardVo vo = new BoardVo();
		
		vo.setBor_nm(boardname);
		vo.setBor_act(Integer.parseInt(boardact));
		vo.setBor_num(Integer.parseInt(boardnum));
		
		service.modifyBoard(vo);
		
		resp.sendRedirect(req.getContextPath() + "/createboard");
	}
}
