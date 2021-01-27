package exam.board.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import exam.util.FileUtil;

@MultipartConfig
@WebServlet("/saveboard")
public class SaveBoard extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		
		String title = req.getParameter("title");
		String cont = req.getParameter("editordata");
		String bor_num = req.getParameter("bor_num");
		int post_del = 1;
		int post_c_no = 0;
		String b_user_id = "";
		String user_id = (String)session.getAttribute("S_USER");
		
		BoardPostVo vo = new BoardPostVo();
		
		vo.setTitle(title);
		vo.setCont(cont);
		vo.setUser_id(user_id);
		vo.setPost_del(post_del);
		vo.setBor_num(Integer.parseInt(bor_num));
		vo.setPost_c_no(post_c_no);
		vo.setB_user_id(b_user_id);
		int cnt = service.createPost(vo);
		
		
		int num = service.maxNumber(Integer.parseInt(bor_num));
		Collection<Part> parts = req.getParts();
		String filename="";
		String realfilename="";
		for(Part part : parts) {
			if(part.getHeader("Content-Disposition").contains("filename")) {
				if(part.getSize() > 0) {
					filename = FileUtil.getFileName(part.getHeader("Content-Disposition"));
					String fileextension = FileUtil.getFileExtension(filename);
					realfilename=UUID.randomUUID().toString() + fileextension;
					
					part.write("D:/uploadboard/" + realfilename);
				}
				AttachVo avo = new AttachVo();
				avo.setFile_nm(realfilename);
				avo.setFile_path(filename);
				avo.setUser_id(user_id);
				avo.setPost_no(num);
				service.attachFile(avo);
			}
		}
		
		
		if(cnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/boardcontent?bornum="+ bor_num);
		}else {
			resp.sendRedirect(req.getContextPath() + "/saveboard");
		}
	}

}
