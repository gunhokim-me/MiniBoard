package exam.user.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exam.user.vo.UserVo;

public class UserDaoTest {
	

	@Before
	public void before() {
		
	}
	
	UserVo vo = new UserVo();
	
	@Test
	public void loginUserCountTest() {
		/***Given***/
		vo.setUser_id("test");
		vo.setUser_pass("test");
		
		/***When***/
		UserDaoI dao = new UserDao();
		int cnt = dao.loginUserCount(vo);
		/***Then***/
		assertEquals(1, cnt);
	}

}
