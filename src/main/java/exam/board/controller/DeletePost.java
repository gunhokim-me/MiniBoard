package exam.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardPostVo;

@WebServlet("/deletepost")
public class DeletePost extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid"); 
		String bornum = req.getParameter("bornum"); 
		String postno = req.getParameter("postno");
		
		BoardPostVo vo = new BoardPostVo();
		vo.setUser_id(userid);
		vo.setBor_num(Integer.parseInt(bornum));
		vo.setPost_no(Integer.parseInt(postno));
		
		int cnt = service.deleteContent(vo);
		
		if(cnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/boardcontent?bornum="+ Integer.parseInt(bornum));
		}else {
			req.getRequestDispatcher("/boarddetail?postnum="+Integer.parseInt(postno) +"&bornum=" +Integer.parseInt(bornum));
		}
	}

}
