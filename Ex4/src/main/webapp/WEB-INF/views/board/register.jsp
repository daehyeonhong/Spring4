<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../includes/header.jsp"%>
<!-- article -->
<article>
	<div class="row">
		<div class="col-log-12">
			<h1 class="page-header">게시글 등록</h1>
		</div>
		<!-- col-log-12 -->
	</div>
	<!-- row -->

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span>게시글 등록</span>
				</div>
				<!-- panel-heading -->
				<div class="panel-body">
					<form action="/board/register" method="post">
						<div class="form-group">
							<label>제목</label>
							<input class="form-control" type="text" name="title" />
						</div>
						<div class="form-group">
							<label>내용</label>
							<textarea class="form-control" rows="3" name="content"></textarea>
						</div>
						<div class="form-group">
							<label>작성자</label>
							<input class="form-control" type="text" name="writer" />
						</div>
						<button type="submit" class="btn btn-success">등록</button>
						<button type="reset" class="btn btn-warning">초기화</button>
					</form>
				</div>
				<!-- panel-body -->
			</div>
		</div>
	</div>
</article>
<%@ include file="../includes/footer.jsp"%>