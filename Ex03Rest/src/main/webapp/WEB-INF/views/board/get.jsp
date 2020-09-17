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
						<label>글번호</label> <input class="form-control" name="bno"
							value="${board.bno}" readonly="readonly" />
					</div>
					<div class="form-group">
						<label>글 제목</label> <input class="form-control" name="title"
							value="${board.title}" readonly="readonly" />
					</div>
					<div class="form-group">
						<label>글내용</label>
						<textarea class="form-control" rows="3" readonly="readonly">${board.content}</textarea>
					</div>
					<div class="form-group">
						<label>작성자</label> <input class="form-control" name="writer"
							value="${board.writer}" readonly="readonly" />
					</div>

					<button data-oper="modify" class="btn btn-info">수정</button>
					<button data-oper="list" class="btn btn-default">글목록</button>

					<form id="operForm" action="/board/modify" method="get">
						<input type="hidden" id="bno" name="bno" value="${board.bno}" />
						<input type="hidden" id="pageNumber" name="pageNumber"
							value="${criteria.pageNumber}" /> <input type="hidden"
							id="amount" name="amount" value="${criteria.amount}" /> <input
							type="hidden" id="keyword" name="keyword"
							value="${criteria.keyword}" /> <input type="hidden" id="type"
							name="type" value="${criteria.type}" />
					</form>
				</div>
			</div>
		</div>
	</div>
</article>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 팝업창</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>답변내용</label> <input type="text" class="form-control"
						name="reply" value="새로운 댓글!!" />
				</div>
				<div class="form-group">
					<label>작성자</label> <input type="text" class="form-control"
						name="replyer" value="aa" />
				</div>
				<div class="form-group">
					<label>작성일자</label> <input type="text" class="form-control"
						name="reply_date" value="2020-09-15 16:55" />
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">
					수정</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">
					삭제</button>
				<button id="modalCloseBtn" type="button" class="btn btn-primary">
					닫기</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-rw"></i>댓글
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">댓글등록</button>
			</div>
			<div class="panel-body">
				<ul class="chat"></ul>
			</div>
			<div class="panel-footer"></div>
		</div>
	</div>
</div>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
	$().ready(function() {
		let operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});

		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list").submit();
		});
	});
</script>
<script type="text/javascript">
	console.log(":::::::::::::::::");
	console.log("JS TEST");

	let bnoValue = "${board.bno}";
	replyService.add({
		reply : "JS TEST",
		replyer : "tester",
		bno : bnoValue,
	}, function(result) {
		alert("RESULT:==> " + result);
	});
</script>
<script type="text/javascript">
	replyService.getList({
		bno : bnoValue,
		page : 1,
	}, function(list) {
		for (var i = 0, len = list.length || 0; i < len; i++) {
			console.log(list[i]);
		}
	});
</script>
<script type="text/javascript">
	replyService.remove(4, function(conut) {
		console.log(count);

		if (count === "success") {
			alert("REMOVED");
		}
	}, function(err) {
		alert("ERROR...");
	});
</script>
<script type="text/javascript">
	replyService.update({
		rno : 22,
		bno : bnoValue,
		reply : "ajax로 ss댓글 수정...",
	}, function(result) {
		alert("수정완료...");
	});
</script>
<script type="text/javascript">
	replyService.get(5, function(data) {
		console.log(data);
	});
</script>
<script type="text/javascript">
	let replyUL = $(".chat");
	showList(1);
	function showList(page) {
		console.log("Show List" + page);
		replyService
				.getList(
						{
							bno : bnoValue,
							page : page || 1,
						},
						function(replyCount, list) {
							console.log("ReplyCount: ==> " + replyCount);
							console.log("List: ==> " + list);
							console.log(list);

							/* PageNumber==-1? 전체 댓글 수 재 계산 후, showList() 재 호출 */
							if (page == -1) {
								pageNumber = Math.ceil(replyCount / 10.0);
								showList(pageNumber);
								return;
							}

							let str = "";

							/* 결과가 없을 때 return */
							if (list == null || list.length == 0) {
								return;
							}
							/* 리스트가 있으면 */
							for (let i = 0, len = list.length || 0; i < len; i++) {
								str += "<li class='left clearfix' data-rno='" +
            list[i].rno +
            "'>"
										+ "<div><div class='header'><strong class='primary-font'>["
										+ list[i].rno
										+ "]"
										+ list[i].replyer
										+ "</strong><small class='pull-right text-muted'>"
										+ replyService
												.displayTime(list[i].reply_date)
										+ "</small></div><p>"
										+ list[i].reply
										+ "</p></div></li>";
							}
							/* ul에 추가 */
							replyUL.html(str);

							showReplyPage(replyCount);
						});
	}
</script>
<script type="text/javascript">
	let pageNumber = 1;
	let replyPageFooter = $(".panel-footer");
	function showReplyPage(replyCount) {
		let endNumber = Math.ceil(pageNumber / 10.0) * 10;

		let startNumber = endNumber - 9;

		let previous = startNumber != 1;
		let next = false;

		console.log(endNumber, startNumber, previous, next);

		if (endNumber * 10 >= replyCount) {
			endNumber = Math.ceil(replyCount / 10.0);
		}
		if (endNumber * 10 < replyCount) {
			next = true;
		}
		let str = "<ul class='pagination pull-right'>";
		if (previous) {
			str += '<li class="page-item"><a class="page-link" href="'
					+ (startNumber - 1) + '">이전페이지</a></li>';
		}
		for (let i = startNumber; i <= endNumber; i++) {
			let active = pageNumber == 1 ? "active" : "";
			str += "<li class='page-item '" + active + "'>";
			str += "<a class='page-link' href='" + i + "'>";
			str += i + "</a></li>";
		}
		if (next) {
			str += '<li class="page-item"><a class="page-link" href="'
					+ (endNumber + 1) + '">다음페이지</a></li></ul></div>';
			console.log(str);
			replyPageFooter.html(str);
		}
		replyPageFooter.on("click", "li a", function(e) {
			e.preventDefault();
			console.log("page click");
			let targetPageNumber = $(this).attr("herf");
			console.log("targetPageNuber: " + targetPageNumber);
			pageNumber = targetPageNumber;
			showList(pageNumber);
		});
	}
</script>
<script type="text/javascript">
	let modal = $(".modal");
	let modalInputReply = modal.find('input[name="reply"]');
	let modalInputReplyer = modal.find('input[name="replyer"]');
	let modalInputReply_date = modal.find('input[name="reply_date"]');

	let modalModBtn = $("#modalModBtn");
	let modalRemoveBtn = $("#modalRemoveBtn");
	let modalRegisterBtn = $("#modalRegisterBtn");

	$(".chat").on(
			"click",
			"li",
			function(e) {
				let rno = $(this).data("rno"); /* this==>클릭한 li */

				replyService.get(rno, function(reply) {
					modalInputReply.val(reply.reply);
					modalInputReplyer.val(reply.replyer);
					modalInputReply_date.val(
							replyService.displayTime(reply.reply_date)).attr(
							"readonly", "readonly");
					modal.data("rno", reply.rno);

					modal.find('button[id!="modalCloseBtn"]').hide();
					modalModBtn.show();
					modalRemoveBtn.show();

					$(".modal").modal("show");
				});
			});
</script>
<script type="text/javascript">
	$("#addReplyBtn").on("click", function(e) {

		modal.find("input").val("");
		modalInputReply_date.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();

		modalRegisterBtn.show();

		$(".modal").modal("show");

	});
	modalRegisterBtn.on("click", function(e) {

		let reply = {
			rno : modal.data('rno'),
			reply : modalInputReply.val(),
			bno : bnoValue
		};
		replyService.add(reply, function(result) {

			alert(result);

			modal.find("input").val("");
			modal.modal("hide");

			//showList(1);
			showList(-1);

		});

	});
	modalModBtn.on("click", function(e) {

		let reply = {
			rno : modal.data("rno"),
			reply : modalInputReply.val(),
			replyer : modalInputReplyer.val()
		};

		replyService.update(reply, function(result) {

			alert(result);
			modal.modal("hide");
			showList(pageNumber);

		});

	});
	modalRemoveBtn.on("click", function(e) {

		var rno = modal.data("rno");

		replyService.remove(rno, function(result) {

			alert(result);
			modal.modal("hide");
			showList(pageNumber);

		});

	});
</script>
<%@ include file="../includes/footer.jsp"%>
