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
import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.PostComVo;

@WebServlet("/boarddetail")
public class BoardDetail extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String postnum = req.getParameter("postnum");
		String bornum = req.getParameter("num");
		
		BoardPostVo vo = new BoardPostVo();
		BoardPostVo vo2 = new BoardPostVo();
		List<PostComVo> comlist = service.allPostCom(Integer.parseInt(postnum));
		List<AttachVo> attlist = service.selectattach(Integer.parseInt(postnum));
		
		vo.setPost_no(Integer.parseInt(postnum));
		vo.setBor_num(Integer.parseInt(bornum));
		
		vo2 = service.selectBoardDetail(vo);
		int lft = vo2.getLft();
		req.setAttribute("lft", lft);
		req.setAttribute("attlist", attlist);
		req.setAttribute("postDetail", vo2);
		req.setAttribute("postcom", comlist);
		req.getRequestDispatcher("/board/boardDetail.jsp").forward(req, resp);
	}
}
