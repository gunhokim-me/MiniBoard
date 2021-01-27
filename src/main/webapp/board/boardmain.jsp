<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>메인페이지</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <%@ include file="/common/requireQuery.jsp" %>
  <style>
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    .row.content {height: 450px}
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
      .row.content {height:auto;} 
    }
  </style>
  <script>
  $(function(){
	  $(".detailboard").on("click", function(){
		  let num = $(this).data("postno");
		  let bornum = $("#bor_num").val();
		  $("#postnum").val(num);
		  $("#num").val(bornum);
		  $("#frm").attr("action", "${cp}/boarddetail")
		  $("#frm").submit();
	  });
	  
	  $("#newboard").on("click", function(){
		  let num = $("#bor_num").val();
		  $("#num").val(num);
		  $("#frm").attr("method", "POST")
		  $("#frm").attr("action", "${cp}/boardcontent")
		  $("#frm").submit();
	  });
  });
  </script>
</head>

<body>
 <%@include file="/common/header.jsp" %>
	<!-- Page Content -->
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
				<c:forEach items="${boardinfo }" var="select">
					<h2>${select.bor_nm}</h2>
					<input type="hidden" id="bor_num" value="${select.bor_num }" />
				</c:forEach>
				<form id="frm">
					<input type="hidden" id="postnum" name="postnum" />
					<input type="hidden" id="num" name="num" />
				</form>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>게시글 번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${selectBoardList }" var="list">
							<c:choose>
								<c:when test="${list.post_del == 0 }">
									<tr>
										<td>${list.post_no}</td>
										<td>[삭제된 게시글 입니다.]</td>
										<td>${list.user_id}</td>
										<td><fmt:formatDate value="${list.reg_dt}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td class="detailboard" data-postno="${list.post_no}">${list.post_no}</td>
										<td>${list.title}</td>
										<td>${list.user_id}</td>
										<td><fmt:formatDate value="${list.reg_dt}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-sm-2 sidenav">
				<button type="button" class="btn btn-info" name="newboard" id="newboard">새글 쓰기</button>
			</div>
		</div>
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#">Previous</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item active"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#">Next</a></li>
		</ul>
	</div>

	<footer class="container-fluid text-center">
  <p>카피라이트</p>
</footer>
</body>
</html>
