package exam.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardPostVo;

@MultipartConfig
@WebServlet("/modifty2")
public class ModifyPost2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title3");
		String cont = req.getParameter("cont3");
		String userid = req.getParameter("user3"); 
		int bornum = Integer.parseInt(req.getParameter("bor3")); 
		int postno = Integer.parseInt(req.getParameter("post3"));
		
		BoardPostVo vo = new BoardPostVo();
		vo.setUser_id(userid);
		vo.setBor_num(bornum);
		vo.setTitle(title);
		vo.setCont(cont);
		vo.setPost_no(postno);
		
		int cnt = service.modifyContent(vo);
		
		if(cnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/boarddetail?postnum="+postno+"&num=" + bornum);
		}else {
			doGet(req, resp);
		}
	}
}
