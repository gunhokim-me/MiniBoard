package exam.board.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardVo {
	private String bor_nm; //게시판 분류
	private int bor_act; //활성화 여부
	private int bor_num; //게시판 번호
}
