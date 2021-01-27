package exam.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exam.user.service.UserService;
import exam.user.service.UserServiceI;
import exam.user.vo.UserVo;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserServiceI service = new UserService();
		UserVo vo = new UserVo();
		
		String userid = req.getParameter("userid");
		String pass =req.getParameter("pass");
		
		vo.setUser_id(userid);
		vo.setUser_pass(pass);
		
		int cnt = service.loginUserCount(vo);
		
		if(cnt == 1) {
			HttpSession session = req.getSession();
			session.setAttribute("S_USER", userid);
			resp.sendRedirect(req.getContextPath() + "/boardlist");
		}else {
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}
	}

}
