package exam.board.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostComVo {

	private int com_no;
	private String com_user_id;
	private Date com_date;
	private String com_con;
	private int com_del;
	private int lft;
	private int post_no;
	private int bor_num;
	private String user_id;
}
