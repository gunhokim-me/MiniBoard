package exam.board.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardPostVo {
	private int post_no; //게시글 번호
	private String user_id; //작성자 아이디
	private String title; //게시글 제목
	private String cont; //게시글 내용
	private Date reg_dt; //작성일시
	private int post_del; //삭제 여부
	private int lft; //계층 레벨
	private int post_c_no; //이전 게시글번호
	private String b_user_id; //이전 게시글 아이디
	private int bor_num; //게시판 번호
	
}
