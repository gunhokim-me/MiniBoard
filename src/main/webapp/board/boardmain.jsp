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
    .row.content {height: 500px}
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
											<td>${list.rn}</td>
												<c:if test="${list.lft > 0}">
													<td><c:forEach begin="0" end="${list.lft }">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>→ [삭제된 게시글 입니다.]</td>
												</c:if>
												<c:if test="${list.lft == 0}">
													<td>[삭제된 게시글 입니다.]</td>
												</c:if>
											<td>${list.user_id}</td>
											<td><fmt:formatDate value="${list.reg_dt}" pattern="yyyy-MM-dd" /></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td>${list.rn}</td>
												<c:if test="${list.lft > 0}">
													<td class="detailboard" data-postno="${list.post_no }"><c:forEach begin="0" end="${list.lft }">&nbsp;&nbsp;&nbsp;&nbsp;</c:forEach>→ ${list.title}</td>
												</c:if>
												<c:if test="${list.lft == 0}">
													<td class="detailboard" data-postno="${list.post_no }">${list.title}</td>
												</c:if>
											<td>${list.user_id}</td>
											<td><fmt:formatDate value="${list.reg_dt}" pattern="yyyy-MM-dd" /></td>
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
			<c:choose>
				<c:when test="${pageVo.getPage()-1 <= 1 }">
					<li class="page-item"><a class="page-link" href="${cp }/boardcontent?page=1&pageSize=10&bornum=${bornum}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${cp }/boardcontent?page=${pageVo.getPage() -1}&pageSize=10&bornum=${bornum}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${pagination }" var="i">
				<c:choose>
					<c:when test="${pageVo.getPage() == i }">
						<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link" href="${cp }/boardcontent?page=${i}&pageSize=10&bornum=${bornum}">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${pageVo.getPage()+1 >= pagination }">
					<li class="page-item"><a class="page-link" href="${cp }/boardcontent?page=${pagination}&pageSize=10&bornum=${bornum}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="${cp }/boardcontent?page=${pageVo.getPage() +1}&pageSize=10&bornum=${bornum}">Previous</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>

	<footer class="container-fluid text-center">
  <p>카피라이트</p>
</footer>
</body>
</html>
