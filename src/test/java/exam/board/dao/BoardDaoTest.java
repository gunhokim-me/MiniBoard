package exam.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;

import exam.board.vo.BoardPostVo;
import exam.board.vo.BoardVo;

public class BoardDaoTest {

	BoardDaoI dao = new BoardDao();
	
	//모든 게시판 조회
	@Test
	public void allBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVo> list = dao.allBoard();
		/***Then***/
		assertEquals(3, list.size());
	}
	
	//게시판 수량
	@Test
	public void allCountBoardTest() {
		/***Given***/
		
		/***When***/
		int cnt = dao.allCountBoard();
		/***Then***/
		assertEquals(cnt, 3);
	}
	
	//활성화 된 게시판 조회
	@Test
	public void useBoardTest() {
		/***Given***/
		
		/***When***/
		List<BoardVo> list = dao.useBoard();
		/***Then***/
		assertEquals(2, list.size());
	}
	
	//게시판 생성
	@Test
	public void createBoardTest() {
		/***Given***/
		BoardVo vo = new BoardVo();
		vo.setBor_nm("테스트 게시판");
		vo.setBor_act(1);
		vo.setBor_num(4);
		/***When***/
		int cnt = dao.createBoardInfo(vo);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	//선택된 게시판 게시글 가져오기
	@Test
	public void boardContentTest() {
		/***Given***/
		int num = 1;
		/***When***/
		List<BoardPostVo> list = dao.boardContent(num);
		/***Then***/
		assertEquals(1, list.size());
	}
	
	
	//게시글 등록
	@Test
	public void createContentTest() {
		/***Given***/
		int post_no = 1;
		String user_id = "test";
		String title ="test";
		String cont = "test";
		int post_del = 1;
		int post_c_no = 0; 
		String b_user_id = "";
		int lft = 0;
		int bor_num = 4;
		
		BoardPostVo vo = new BoardPostVo();
		
		vo.setPost_no(post_no);
		vo.setUser_id(user_id);
		vo.setTitle(title);
		vo.setCont(cont);
		vo.setPost_del(post_del);
		vo.setLft(lft);
		vo.setPost_c_no(post_c_no);
		vo.setB_user_id(b_user_id);
		vo.setBor_num(bor_num);
		
		/***When***/
		int cnt = dao.createPost(vo);
		/***Then***/
		assertEquals(1, cnt);
	}
	
	
	@After
	public void afterTest() {
		
	}
}
