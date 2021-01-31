package exam.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;
import exam.board.vo.PageVo;

@WebServlet("/boardcontent")
public class BoardContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bornum = Integer.parseInt(req.getParameter("bornum"));
		String pageparam = req.getParameter("page");
		String pageSizeParam = req.getParameter("pageSize");
		
		PageVo vo = new PageVo();
		
		int page = pageparam == null? 1 :  Integer.parseInt(pageparam) ;
		int pageSize = pageSizeParam == null ? 10 : Integer.parseInt(pageSizeParam);
		
		vo.setPage(page);
		vo.setPageSize(pageSize);
		vo.setBor_num(req.getParameter("bornum"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map = service.boardContent(vo);
		
		int cnt = (int)map.get("listcnt");
		int pagination = (int)Math.ceil((double)cnt / pageSize);
		
		List<BoardPostVo> selectBoardList = (List<BoardPostVo>) map.get("postlist");
		
		if(selectBoardList != null) {
			req.setAttribute("selectBoardList", selectBoardList);
		}
		List<BoardVo> list = service.selectBoardInfo(bornum);
		req.setAttribute("pagination", pagination);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("pageVo", vo);
		req.setAttribute("boardinfo", list);
		req.setAttribute("bornum", bornum);
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
