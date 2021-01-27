package exam.board.service;

import java.util.List;

import exam.board.dao.BoardDao;
import exam.board.dao.BoardDaoI;
import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;

public class BoardService implements BoardServiceI {

	BoardDaoI dao = new BoardDao();
	
	//모든 게시판 조회
	@Override
	public List<BoardVo> allBoard() {
		return dao.allBoard();
	}

	//활성화 된 게시판 조회
	@Override
	public List<BoardVo> useBoard() {
		return dao.useBoard();
	}

	//새로운 게시판 생성
	@Override
	public int createBoardInfo(BoardVo vo) {
		//게시판 번호를 넣어준다
		int num = dao.allCountBoard();
		vo.setBor_num(num+1);
		return dao.createBoardInfo(vo);
	}

	//선택된 게시판 내용 조회
	@Override
	public List<BoardPostVo> boardContent(int bor_num) {
		return dao.boardContent(bor_num);
	}

	//선택한 게시판 이름 또는 상태 변경
	@Override
	public int modifyBoard(BoardVo vo) {
		return dao.modifyBoard(vo);
	}

	@Override
	public List<BoardVo> selectBoardInfo(int bor_num) {
		return dao.selectBoardInfo(bor_num);
	}

	//게시글 생성
	@Override
	public int createPost(BoardPostVo vo) {
		int bor_num = dao.selectContCount(vo.getBor_num());
		vo.setPost_no(bor_num+1);
		int lft = vo.getBor_num()-1;
		vo.setLft(lft);
		
		return dao.createPost(vo);
	}

	//첨부파일 저장
	@Override
	public int attachFile(AttachVo vo) {
		return dao.attachFile(vo);
	}

	//최신번호 가져오기
	@Override
	public int maxNumber(int bor_num) {
		return dao.maxNumber(bor_num);
	}

	//선택된 게시글 상세보기
	@Override
	public BoardPostVo selectBoardDetail(BoardPostVo vo) {
		return dao.selectBoardDetail(vo);
	}


}
