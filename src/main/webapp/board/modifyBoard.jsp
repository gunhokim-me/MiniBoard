<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>게시판 생성</title>
  <!-- Bootstrap core CSS -->
  <%@ include file="/common/requireQuery.jsp" %>
  <!-- Custom styles for this template -->
  <link href="${cp}/css/main.css" rel="stylesheet">
  
  <style>
	table{
		width : 700px;
	}  	
	tr {
		height: 50px;
	}
  </style>
  <script>
  	$(function(){
  		$("#create").on("click",function(){
  				let boardname = $("#boardnm").val();
  				let act = $("#createsel").val();
  				$("#name").val(boardname);
  				$("#act").val(act);
  				$("#frm").attr("action", "${cp}/createboard");
  				$("#frm").attr("method", "POST");
  				$("#frm").submit();
  		});
  		$(".modify").on("click", function(){
  				let boardname = $(this).parent().prev().prev().children(".boardnm").val()
  				let boardnum = $(this).parent().prev().prev().children(".boardnm").next().val()
  				let act = $(this).parent().prev().children(".modifysel").val();
  				console.log(boardname)
  				console.log(boardnum)
  				console.log(act)
  				$("#name").val(boardname);
  				$("#num").val(boardnum);
  				$("#act").val(act);
  				$("#frm").attr("action", "${cp}/modifyboard");
  				$("#frm").attr("method", "get");
  				$("#frm").submit();
  		});
  	});
  </script>
</head>

<body>
<%@ include file="../common/header.jsp" %>
	<!-- Page Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<h1 class="my-4">게시판 생성</h1>
				<div class="list-group">
				</div>
			</div>
			
			<!-- /.col-lg-3 -->
			<div class="col-lg-9">
				<div class="card mt-4">
					<div class="card-body">
					<form id="frm">
						<input type="hidden" id = "name" name="boardname" />
						<input type="hidden" id = "act" name="boardact" />
						<input type="hidden" id = "num" name="boardnum" />
					</form>
						<table>
							<tr>
								<td>게시판 이름</td>
								<td><input type="text" id="boardnm" name="boardnm"/></td>
								<td>
									<select id="createsel">
										<option value="1" selected="selected">사용</option>
										<option value="0" >미사용</option>
									</select>
								</td>
								<td><button type="button" class="btn btn-primary" id="create" name="create" >생성</button></td>
							</tr>
							<c:forEach items="${allboard }" var="board">
								<tr>
									<td>게시판 이름</td>
									<td>
										<input type="text" class="boardnm" value="${board. bor_nm}"/>
										<input type="hidden" value="${board. bor_num}"/>
									</td>
									<td>
										<c:choose>
											<c:when test="${board.bor_act == 1 }">
												<select class="modifysel">
													<option value="1" selected="selected">사용</option>
													<option value="0">미사용</option>
												</select>
											</c:when>
											<c:otherwise>
												<select class="modifysel">
													<option value="1">사용</option>
													<option value="0" selected="selected">미사용</option>
												</select>
											</c:otherwise>
										</c:choose>
									</td>
									<td><input type="button" class="modify" value="수정"/></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
    </div>
    <!-- /.container -->
  </footer>

  <!-- Bootstrap core JavaScript -->
<%@ include file="../common/requireQuery.jsp" %>
</body>

</html>