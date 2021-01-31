package exam.board.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
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
@WebServlet("/modifypost")
public class ModifyPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoardServiceI service = new BoardService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userid = req.getParameter("userid"); 
		int bornum = Integer.parseInt(req.getParameter("bornum")); 
		int postno = Integer.parseInt(req.getParameter("postno"));
		String title = req.getParameter("title");
		String cont = req.getParameter("cont");
		
		BoardPostVo vo = new BoardPostVo();
		List<AttachVo> attlist = service.selectattach(postno);
		
		vo.setUser_id(userid);
		vo.setBor_num(bornum);
		vo.setPost_no(postno);
		vo.setTitle(title);
		vo.setCont(cont);
		
		int size = attlist.size();
		
		req.setAttribute("filesize", size);
		req.setAttribute("attlist", attlist);
		req.setAttribute("summer", vo);
		req.setAttribute("userid", userid);
		req.setAttribute("bornum", bornum);
		req.setAttribute("postno", postno);
		req.getRequestDispatcher("/board/modifypost.jsp").forward(req, resp);
	}

	@Override
	protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String[] deletefilearray =req.getParameterValues("filename");
		String title = req.getParameter("title3");
		String cont = req.getParameter("cont3");
		String userid = req.getParameter("user3"); 
		int bornum = Integer.parseInt(req.getParameter("bor3")); 
		int postno = Integer.parseInt(req.getParameter("post3"));
		
		BoardPostVo vo = new BoardPostVo();
		AttachVo attvo = new AttachVo();
		vo.setUser_id(userid);
		vo.setBor_num(bornum);
		vo.setTitle(title);
		vo.setCont(cont);
		vo.setPost_no(postno);
		
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
						avo.setPost_no(postno);
						avo.setBor_num(bornum);
						avo.setUser_id((String)session.getAttribute("S_USER"));
						avo.setFile_nm(filename);
						avo.setFile_path(realfilename);
						service.attachFile(avo);
					}
				}
			}
		}
		
		int cnt = service.modifyContent(vo);

		if (cnt == 1) {
			attvo.setUser_id(userid);
			attvo.setPost_no(postno);
			int res = 0;
			if (deletefilearray == null) {

			} else if (deletefilearray.length == 0) {

			} else {
				for (int i = 0; i < deletefilearray.length; i++) {
					BoardServiceI service2 = new BoardService();
					attvo.setFile_path(deletefilearray[i]);
					res = service2.deleteAttach(attvo);
				}
			}
			resp.sendRedirect(req.getContextPath() + "/boarddetail?postnum=" + postno + "&num=" + bornum);
		} else {
			doGet(req, resp);
		}
	}
}
