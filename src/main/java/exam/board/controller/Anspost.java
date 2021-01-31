package exam.board.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import exam.board.service.BoardService;
import exam.board.service.BoardServiceI;
import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;
import exam.util.FileUtil;

@WebServlet("/anspost")
public class Anspost extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		String userid2 = req.getParameter("userid2");
		String bornum2 = req.getParameter("bornum2");
		String postno2 = req.getParameter("postno2");
		
		String title = req.getParameter("title2");
		String cont = req.getParameter("editordata2");
		String bor_num = req.getParameter("bornum2");
		int post_del = 1;
		String user_id = (String)session.getAttribute("S_USER");
		
		
		BoardPostVo vo = new BoardPostVo();
		BoardPostVo detailvo = new BoardPostVo();
		detailvo.setBor_num(Integer.parseInt(bor_num));
		detailvo.setPost_no(Integer.parseInt(postno2));
		
		BoardPostVo detail = service.selectBoardDetail(detailvo);
		
		int lft = (detail.getLft()+1);
		
		vo.setBor_num(Integer.parseInt(bor_num));
		vo.setUser_id((String)session.getAttribute("S_USER"));
		vo.setTitle(title);
		vo.setCont(cont);
		vo.setPost_del(post_del);
		vo.setLft(lft);
		vo.setPost_no2(Integer.parseInt(postno2));
		vo.setUser_id2(userid2);
		vo.setBor_num2(Integer.parseInt(bor_num));
		int cnt = service.createPost(vo);
		
		
		int num = service.maxNumber(Integer.parseInt(bor_num));
		Collection<Part> parts = req.getParts();
		String filename="";
		String realfilename="";
		for(Part part : parts) {
			if(part.getSize() > 0) {
				if(part.getHeader("Content-Disposition").contains("name=\"files\";")) {
					filename = FileUtil.getFileName(part.getHeader("Content-Disposition"));
					String fileextension = FileUtil.getFileExtension(filename);
					realfilename=UUID.randomUUID().toString() + fileextension;
					
					part.write("D:/uploadboard/" + realfilename);
					AttachVo avo = new AttachVo();
					avo.setPost_no(num);
					avo.setBor_num(Integer.parseInt(bor_num));
					avo.setUser_id(user_id);
					avo.setFile_nm(filename);
					avo.setFile_path(realfilename);
					service.attachFile(avo);
				}
			}
		}
		
		
		if(cnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/boardcontent?bornum="+ bor_num);
		}else {
			resp.sendRedirect(req.getContextPath() + "/saveboard");
		}
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid");
		String bornum = req.getParameter("bornum");
		String postno = req.getParameter("postno");
		
		List<BoardVo> vo = service.selectBoardInfo(Integer.parseInt(bornum));
		
		
		req.setAttribute("boardinfo", vo);
		req.setAttribute("userid", userid);
		req.setAttribute("bornum", bornum);
		req.setAttribute("postno", postno);
		
		req.getRequestDispatcher("/board/anspost.jsp").forward(req, resp);
	}
}
