package exam.user.dao;

import org.apache.ibatis.session.SqlSession;

import exam.db.MyBatisUtil;
import exam.user.vo.UserVo;

public class UserDao implements UserDaoI{
	
	/**
	 * 
	* Method : loginUserCount
	* 작성자 : HO
	* 변경이력 : 2021.01.23
	* @param UserVo
	* @return 아이디와 비밀번호가 일치하면 1, 일치하지 않으면 0 
	* Method 설명 : login.jsp에서 아이디와 비밀번호를 입력한 값이 있는지 확인(로그인)
	 */
	@Override
	public int loginUserCount(UserVo vo) {
		SqlSession  session = MyBatisUtil.getSession();
		int cnt = session.selectOne("users.loginCount",vo);
		session.close();
		return cnt;
	}

}
