package exam.board.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachVo {

	private int att_no;
	private int post_no;
	private String user_id;
	private String file_nm;
	private String file_path;
	
}
