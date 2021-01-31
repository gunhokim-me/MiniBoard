<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>상세페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/common/requireQuery.jsp"%>
<style>
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.row.content {
	height: 450px
}

.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

#btngroup{
	float: right;
}
</style>

<script>
  $(function(){
	  //수정
	  $("#modifypost").on("click", function(){
		  $("#frm").attr("action", "${cp}/modifypost");
		  $("#frm").submit();
	  });
	  
	  //삭제
	  $("#deletepost").on("click", function(){
		  $("#frm").attr("action", "${cp}/deletepost");
		  $("#frm").submit();
	  });
	  
	  //답글
	  $("#anspost").on("click", function(){
		  $("#frm").attr("action", "${cp}/anspost");
		  $("#frm").submit();
	  });
	  
	  //댓글 등록
	  $("#comment_save").on("click", function(){
		  let cont = $("#comment_content").val();
		  if(cont == null || cont.length == 0){
			  return alert("내용을 입력해주세요.");
		  }else{
			  $("#comm").val(cont);
			  $("#frm").attr("method", "POST")
			  $("#frm").attr("action", "${cp}/comment_save");
			  $("#frm").submit();
		  }
	  });
	  
  });
  </script>
</head>

<body>
	<%@include file="/common/header.jsp"%>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<c:forEach items="${useboard }" var="board">
					<p>
						<a href="${cp }/boardcontent?bornum=${board.bor_num }">${board.bor_nm }</a>
					</p>
				</c:forEach>
			</div>
			<div class="col-sm-8 text-left">
			<br><br><br>
				제목 : <label>${postDetail.title }</label><br>
				내용 : <label>${postDetail.cont }</label><br>
				첨부파일 :<br>
				<c:forEach items="${attlist }" var="list">
					<label>${list.file_nm }</label><br>
				</c:forEach> 
					<form id="frm">
						<input type="hidden" name="userid" value="${postDetail.user_id }"/>	
						<input type="hidden" name="bornum" value="${postDetail.bor_num }"/>	
						<input type="hidden" name="postno" value="${postDetail.post_no }"/>	
						<input type="hidden" name="title" value="${postDetail.title }"/>	
						<input type="hidden" name="cont" value="${postDetail.cont }"/>	
						<input type="hidden" id="comm" name="commcont"/>	
					</form>
				<c:choose>
					<c:when test="${postDetail.user_id == sessionScope.S_USER }">
						<div id="btngroup">
							<p>
								<button id="modifypost">수정</button>
								<button id="deletepost">삭제</button>
								<button id="anspost">답글</button>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<div id="btngroup">
							<p>
								<button id="anspost">답글</button>
							</p>
						</div>
					</c:otherwise>
				</c:choose>
				
				<div>
				<!-- 댓글이 있으면 출력하는 곳 -->
				<c:forEach items="${postcom }" var="com">
					<c:if test="${com.com_del == 1 }">
						<p>${com.com_con } [${com.user_id } / <fmt:formatDate value="${com.com_date }" pattern="yyyy.MM.dd"/>]</p>
					</c:if>
					<c:if test="${com.com_del == 0 }">
						<p>[삭제된 댓글 입니다.]</p>
					</c:if>
				</c:forEach>
				</div>
				<div>
					<textarea rows="4" cols="70" name="comment_content" id="comment_content"></textarea>
					<p><button id="comment_save">댓글등록</button></p>
				</div>
				<div>
				</div>
			</div>
			<div class="col-sm-2 sidenav"></div>
		</div>
	</div>
	<footer class="container-fluid text-center">
		<p>카피라이트</p>
	</footer>
</body>
</html>
