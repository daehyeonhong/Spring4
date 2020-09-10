<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>
<!-- article -->
<article>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">글 상세 보기</h1>
		</div>
		<!-- col-lg-12 -->
	</div>
	<!-- row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<span>글 상세 보기 페이지</span>
				</div>
				
				<div class="panel-body">
					<div class="form-group">
						<label>글번호</label>
						<input class="form-control" name="bno" value="${board.bno}" readonly="readonly" />
					</div>
					<div class="form-group">
						<label>글 제목</label>
						<input class="form-control" name="title" value="${board.title}" readonly="readonly" />
					</div>
					<div class="form-group">
						<label>글내용</label>
						<textarea class="form-control" rows="3" readonly="readonly">${board.content}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label>
						<input class="form-control" name="writer" value="${board.writer}" readonly="readonly" />
					</div>
					
					<button data-oper="modify" class="btn btn-info">수정</button>
					<button data-oper="list" class="btn btn-default">글목록</button>
					
					<form id="operForm" action="/board/modify" method="get">
						<input type="hidden" id="bno" name="bno" value="${board.bno}" />
						<input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum}" />
						<input type="hidden" id="amount" name="amount" value="${cri.amount}" />
						<input type="hidden" id="keyword" name="keyword" value="${cri.keyword}" />
						<input type="hidden" id="type" name="type" value="${cri.type}" />
					</form>
				</div>
			</div>
		</div>
	</div>
</article>
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript">
	$().ready(function(){
		let operForm=$("#operForm");

		$("button[data-oper='modify']").on("click",function(e){
			operForm.attr("action","/board/modify").submit();
			});

		$("button[data-oper='list']").on("click",function(e){
			operForm.find("#bno").remove();
			operForm.attr("action","/board/list").submit();
			});
		});
</script>