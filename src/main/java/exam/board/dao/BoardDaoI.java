package exam.board.dao;

import java.util.List;

import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;

public interface BoardDaoI {
	
	//모든 게시판 조회
	List<BoardVo> allBoard();
	
	//모든 게시판 수 조회
	int allCountBoard();
	
	//활성화 된 게시판 조회
	List<BoardVo> useBoard();
	
	//새로운 게시판 생성
	int createBoardInfo(BoardVo vo);
	
	//선택한 게시판 정보
	List<BoardVo> selectBoardInfo(int bor_num);
	
	//선택된 게시판 내용 조회
	List<BoardPostVo> boardContent(int bor_num);
	
	//선택한 게시판 이름 또는 상태 변경
	int modifyBoard(BoardVo vo);
	
	//게시글 생성
	int createPost(BoardPostVo vo);
	
	//최신번호 가져오기
	int maxNumber(int bor_num);
	
	//첨부파일 저장
	int attachFile(AttachVo vo);
	
	//선택된 게시판 게시물 개수
	int selectContCount(int bor_num);
	
	//선택한 게시글 상세보기
	BoardPostVo selectBoardDetail(BoardPostVo vo);
	
}
