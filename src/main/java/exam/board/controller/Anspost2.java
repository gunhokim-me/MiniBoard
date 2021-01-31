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
@WebServlet("/anspost2")
public class Anspost2 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	BoardServiceI service = new BoardService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String userid2 = req.getParameter("userid2");
		String postno2 = req.getParameter("postno2");
		String title = req.getParameter("title2");
		String cont = req.getParameter("editordata2");
		String bor_num = req.getParameter("bornum2");
		int post_del = 1;
		
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
		if(parts.size() != 0) {
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
						avo.setUser_id((String)session.getAttribute("S_USER"));
						avo.setFile_nm(filename);
						avo.setFile_path(realfilename);
						service.attachFile(avo);
					}
				}
			}
		}
		
		
		if(cnt == 1) {
			resp.sendRedirect(req.getContextPath() + "/boardcontent?bornum="+ bor_num);
		}else {
			resp.sendRedirect(req.getContextPath() + "/saveboard");
		}
	}
}
