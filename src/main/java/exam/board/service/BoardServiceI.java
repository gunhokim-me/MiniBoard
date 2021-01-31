package exam.board.service;

import java.util.List;
import java.util.Map;

import exam.board.vo.AttachVo;
import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;
import exam.board.vo.PageVo;
import exam.board.vo.PostComVo;

public interface BoardServiceI {

	//모든 게시판 조회
	List<BoardVo> allBoard();
	
	//활성화 된 게시판 조회
	List<BoardVo> useBoard();
	
	//새로운 게시판 생성
	int createBoardInfo(BoardVo vo);
	
	//선택한 게시판 정보
	List<BoardVo> selectBoardInfo(int bor_num);
	
	//선택된 게시판 내용 조회
	Map<String, Object> boardContent(PageVo vo);
	
	//선택한 게시판 이름 또는 상태 변경
	int modifyBoard(BoardVo vo);
	
	//게시글 생성
	int createPost(BoardPostVo vo);
	
	//최신번호 가져오기
	int maxNumber(int bor_num);
	
	//첨부파일 저장
	int attachFile(AttachVo vo);
	
	//선택한 게시글 상세보기
	BoardPostVo selectBoardDetail(BoardPostVo vo);
	
	//게시글 삭제
	int deleteContent(BoardPostVo vo);
	
	//게시글 수정
	int modifyContent(BoardPostVo vo);
	
	//첨부파일 가져오기
	List<AttachVo> selectattach(int post_no);
	
	//현재 게시글에 등록된 댓글 가져오기
	List<PostComVo> allPostCom(int post_no);
	
	//댓글 생성
	int savePostCom(PostComVo vo);
	
	//현재 게시글에 등록된 댓글 수
	int countPostCom();
	
	//답글 등록
	int createAnsPost(BoardPostVo vo);
	
	//첨부파일 삭제
	int deleteAttach(AttachVo vo);
}
