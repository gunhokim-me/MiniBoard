package exam.user.dao;

import exam.user.vo.UserVo;

public interface UserDaoI {
	
	//로그인 시 입력한 회원이 있는지 확인
	int loginUserCount(UserVo vo);
}
