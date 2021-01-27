package exam.user.service;

import exam.user.dao.UserDao;
import exam.user.dao.UserDaoI;
import exam.user.vo.UserVo;

public class UserService implements UserServiceI {

	UserDaoI dao = new UserDao();
	
	/**
	 * 
	* Method : loginUserCount
	* 작성자 : HO
	* 변경이력 : 2021.01.23
	* @param UserVo vo
	* @return 아이디와 비밀번호가 일치하면 1, 일치하지 않으면 0 
	* Method 설명 : login.jsp에서 아이디와 비밀번호를 입력한 값이 있는지 확인(로그인)
	 */
	@Override
	public int loginUserCount(UserVo vo) {
		return dao.loginUserCount(vo);
	}

}
