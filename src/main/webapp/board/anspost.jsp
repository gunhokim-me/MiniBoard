<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
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
</style>
<script	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<script>
  	$(function(){
  		$("#save2").on("click", function(){
  			let text =$("#summernote2").summernote('editor.insertText', "${board_data.BOARD_CONTENT}");
  			$("#text2").val(text);
  			let bor_num = $("#num").val();
  			$("#bor_num2").val(bor_num);
  			$("#sumform2").attr("action" , "${cp}/anspost2");
  			$("#sumform2").submit();
  		});
  		 
  		$("#attachbnt").on("click", function(){
  			if($("input[name=files]").length <= 5){
	  			$("#atdiv").append("<input type='file' name='files'>");
  			}else{
  				alert("최대 첨부파일은 5개 입니다.")
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
				<c:forEach items="${boardinfo }" var="board">
					<p>
						<a href="${cp }/boardcontent?bornum=${board.bor_num }">${board.bor_nm }</a>
					</p>
				</c:forEach>
			</div>
			<div class="col-sm-8 text-left">
				<c:forEach items="${boardinfo }" var="select">
					<h2>${select.bor_nm} 답글 작성</h2>
					<input type="hidden" id="num2" name="num" value="${select.bor_num }" />
				</c:forEach>
				<form id="sumform2" method="post" enctype="multipart/form-data">
					<div style="margin: 10px;">
						제목 : <input type="text" name="title2" id="title2" /><br> 
						작성자 :	<label>${S_USER }</label>

					</div>
					<textarea id="summernote2" name="editordata2"></textarea>
					<div id="atdiv" style="width: 400px;">
						<button type="button" style="float: right;" id="attachbnt">첨부파일 추가</button>
						<input type="file" name="files" >
					</div>
					<div style="width: 370px;">
						<button style="float: right;" type="button" class="btn btn-info" name="save" id="save2">저장</button>
					</div>
					<input type="hidden" id="text2" name="cont" />
					<input type="hidden" name="userid2" value="${userid }" />
					<input type="hidden" name="bornum2" value="${bornum }"  />
					<input type="hidden" name="postno2" value="${postno }"  />
					<input type="hidden" id="comm2" name="commcont"/>	
				</form>
				<script>
							$(document).ready(function() {
								$("#summernote2").summernote({
									height: 200,
									placeholder: '최대 2048자까지 쓸 수 있습니다'
								});
							})
						</script>
			</div>
			<div class="col-sm-2 sidenav"></div>
		</div>
	</div>
	<footer class="container-fluid text-center">
		<p>카피라이트</p>
	</footer>
</body>
</html>
