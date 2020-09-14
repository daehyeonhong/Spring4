<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글 수정</h1>
	</div>
	<!-- col-lg-12 -->
</div>
<!-- row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">글 수정하기</div>
			<!-- panel-heading -->

			<div class="panel-body">
				<form role="form" action="/board/modify" method="POST">
					<input type="hidden" name="pageNumber"
						value="<c:out value="${criteria.pageNumber}" />"> <input
						type="hidden" name="amount"
						value="<c:out value="${criteria.amount}" />"> <input
						type="hidden" name="type"
						value="<c:out value="${criteria.type}" />"> <input
						type="hidden" name="keyword"
						value="<c:out value="${criteria.keyword}" />">

					<div class="form-group">
						<label>글 번호</label> <input type="text" name="bno"
							value="${board.bno}" class="form-control" readonly />
					</div>

					<div class="form-group">
						<label>글 제목</label> <input type="text" name="title"
							value="${board.title}" class="form-control" />
					</div>

					<div class="form-group">
						<label>작성자</label> <input type="text" name="writer"
							value="${board.writer}" class="form-control" readonly />
					</div>

					<div class="form-group">
						<label>글 내용</label>
						<textarea rows="3" class="form-control" name="content">${board.content}</textarea>
					</div>

					<div class="form-group">
						<label>등록일시</label> <input type="text" name="regDate"
							value='<fmt:formatDate value="${board.regDate}" pattern="yyyy/MM/dd"/>'
							class="form-control" readonly />
					</div>

					<div class="form-group">
						<label>수정 일자</label> <input type="text" name="updateDate"
							value='<fmt:formatDate value="${board.updateDate}" pattern="yyyy/MM/dd"/>'
							class="form-control" readonly />
					</div>

					<button type="submit" data-a="modify" class="btn btn-success">수정</button>
					<button type="submit" data-a="remove" class="btn btn-danger">삭제</button>
					<button type="submit" data-a="list" class="btn btn-default">글
						목록</button>

				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$().ready(function() {
		let formObject = $('form');

		$('button').on('click', function(event) {
			event.preventDefault();

			let operation = $(this).data('a');
			if (operation === 'remove') {
				formObject.attr("action", "/board/remove");
			} else if (operation === 'list') {
				formObject.attr("action", "/board/list").attr("method", "GET");

				let pageNumberTag = $('input[name="pageNumber"]').clone();
				let amountTag = $('input[name="amount"]').clone();
				let typeTag = $('input[name="type"]').clone();
				let keywordTag = $('input[name="keyword"]').clone();

				formObject.empty();

				formObject.append(pageNumberTag);
				formObject.append(amountTag);
				formObject.append(typeTag);
				formObject.append(keywordTag);
			}
			formObject.submit();
		});
	});
</script>
<%@ include file="../includes/footer.jsp"%>