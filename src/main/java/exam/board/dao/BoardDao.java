package exam.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;
import exam.db.MyBatisUtil;

public class BoardDao implements BoardDaoI{

	/**
	 * 
	* Method : allBoard
	* 작성자 : HO
	* 변경이력 : 2021.01.23
	* @return 등록된 모든 게시판 리스트
	* Method 설명 : DB에 등록된 모든 게시판 리스트를 가지고 온다
	 */
	@Override
	public List<BoardVo> allBoard() {
		SqlSession session = MyBatisUtil.getSession();
		List<BoardVo> list = session.selectList("board.allBoard");
		session.close();
		return list;
	}

	/**
	 * 
	* Method : useBoard
	* 작성자 : HO
	* 변경이력 :2021.01.23
	* @return 활성화 되어있는 게시판 리스트
	* Method 설명 : DB에 등록된 모든 게시판 중 활성화 된 게시판 리스트만 가지고 온다.
	 */
	@Override
	public List<BoardVo> useBoard() {
		SqlSession session = MyBatisUtil.getSession();
		List<BoardVo> list = session.selectList("board.useBoard");
		session.close();
		return list;
	}

	/**
	 * 
	* Method : allCountBoard
	* 작성자 : HO
	* 변경이력 : 2021.01.24
	* @return  모든 게시판 수
	* Method 설명 : 생성되어 있는 모든 게시판의 수량을 가져온다
	 */
	@Override
	public int allCountBoard() {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.selectOne("board.allCountBoard");
		return cnt;
	}
	
	/**
	 * 
	* Method : createBoardInfo
	* 작성자 : HO
	* 변경이력 : 2021.01.24
	* @param BoardVo vo
	* @return 생성된 게시판 수량
	* Method 설명 : 게시판 이름과 상태를 받아서 새로운 게시판을 생성한다.
	 */
	@Override
	public int createBoardInfo(BoardVo vo) {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.insert("board.createBoardInfo",vo);
		if (cnt == 1) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return cnt;
	}

	/**
	 * 
	* Method : boardContent
	* 작성자 : HO
	* 변경이력 : 2021.01.24
	* @param 게시판 번호
	* @return 게시판 번호에 맞는 게시물
	* Method 설명 : 게시판 번호에 맞는 게시물 전체를 가지고온다
	 */
	@Override
	public List<BoardPostVo> boardContent(int bor_num) {
		SqlSession session = MyBatisUtil.getSession();
		List<BoardPostVo> list = session.selectList("board.selectBoardContent",bor_num);
		session.close();
		return list;
	}

	/**
	 * 
	* Method : modifyBoard
	* 작성자 : HO
	* 변경이력 : 2021.01.24
	* @param BoardVo
	* @return 변경된 행의 수
	* Method 설명 : 선택된 게시판의 이름 또는 상태를 변경하는 메서드
	 */
	@Override
	public int modifyBoard(BoardVo vo) {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.update("board.updateBoard",vo);
		if (cnt == 1) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return cnt;
	}

	/**
	 * 
	 * Method : createPost
	 * 작성자 : PC-01
	 * 변경이력 : 2021.01.24
	 * @param BoardPostVo
	 * @return 추가된 행의 개수
	 * Method 설명 : 회원이 입력을 하게되면 해당 게시판에 게시글이 입력된다. 
	 */
	@Override
	public int createPost(BoardPostVo vo) {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.insert("board.newContent",vo);
		if (cnt == 1) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return cnt;
	}

	/**
	 * 
	 * Method : selectBoardInfo
	 * 작성자 : PC-01
	 * 변경이력 :
	 * @param bor_num
	 * @return
	 * Method 설명 :
	 */
	@Override
	public List<BoardVo> selectBoardInfo(int bor_num) {
		SqlSession session = MyBatisUtil.getSession();
		List<BoardVo> list = session.selectList("board.selectboardinfo", bor_num);
		session.close();
		return list;
	}

	/**
	 * 
	 * Method : selectContCount
	 * 작성자 : PC-01
	 * 변경이력 : 2021.01.25
	 * @param bor_num
	 * @return 검색된 행의 개수
	 * Method 설명 : 선택된 게시판의 게시물 개수를 구한다.
	 */
	@Override
	public int selectContCount(int bor_num) {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.selectOne("board.selectContCount", bor_num);
		session.close();
		return cnt;
	}

	/**
	 * 
	 * Method : attachFile
	 * 작성자 : PC-01
	 * 변경이력 : 2021.01.25
	 * @param vo
	 * @return 저장이 되면 1을 반환
	 * Method 설명 : 게시글 생성시 첨부 파일이 있으면 저장 한다.
	 */
	@Override
	public int attachFile(AttachVo vo) {
		SqlSession session = MyBatisUtil.getSession();
		int cnt = session.insert("board.attachFile",vo);
		if (cnt == 1) {
			session.commit();
		}else {
			session.rollback();
		}
		session.close();
		return cnt;
	}

	/**
	 * 
	 * Method : maxNumber
	 * 작성자 : PC-01
	 * 변경이력 :
	 * @param bor_num
	 * @return
	 * Method 설명 :
	 */
	@Override
	public int maxNumber(int bor_num) {
		SqlSession session = MyBatisUtil.getSession();
		int num = session.selectOne("board.maxNumber", bor_num);
		session.close();
		return num;
	}

	/**
	 * 
	 * Method : selectBoardDetail
	 * 작성자 : PC-01
	 * 변경이력 : 2021.01.25
	 * @param vo
	 * @return
	 * Method 설명 : 선택된 게시글에 대한 데이터를 가져옵니다.
	 */
	@Override
	public BoardPostVo selectBoardDetail(BoardPostVo vo) {
		SqlSession session = MyBatisUtil.getSession();
		BoardPostVo detailvo = session.selectOne("board.selectBoardDetail",vo);
		session.close();
		return detailvo;
	}


}
