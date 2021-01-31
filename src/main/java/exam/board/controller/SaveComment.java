package exam.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.BoardPostVo;
import exam.board.vo.PostComVo;

@WebServlet("/comment_save")
public class SaveComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	int post_no = 0;
	int bor_num = 0;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		post_no = Integer.parseInt(req.getParameter("postno"));
		bor_num = Integer.parseInt(req.getParameter("bornum"));
		String com_con = req.getParameter("commcont");
		String user_id = req.getParameter("userid");
		int com_no = service.countPostCom();
		
		PostComVo vo = new PostComVo();
		BoardPostVo vo2 = new BoardPostVo();
		HttpSession session = req.getSession();
		
		vo2.setBor_num(bor_num);
		vo2.setPost_no(post_no);
		
		BoardPostVo vo3 = service.selectBoardDetail(vo2);
		int lft = vo3.getLft();
		
		int no = com_no +1;
		int no2 = no == 1? 1 : no-1;
		
		vo.setCom_no(no);
		vo.setCom_user_id(user_id);
		vo.setCom_con(com_con);
		vo.setLft(lft);
		vo.setPost_no(post_no);
		vo.setBor_num(bor_num);
		vo.setUser_id((String)session.getAttribute("S_USER"));
		int cnt = service.savePostCom(vo);
		if(cnt == 1) {
			doGet(req, resp);
			//resp.sendRedirect(req.getContextPath() + "/boarddetail?postnum="+ post_no +"&num="+bor_num);
			//req.getRequestDispatcher("/boarddetail?postnum="+ post_no +"&num="+bor_num).forward(req, resp);
		}else {
			
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/boarddetail?postnum="+ post_no +"&num="+bor_num);
	}
}
