<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

<style>
.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>
</head>
<body>
	<article>
		<h1>Upload AJAX</h1>
		<div class="bigPictureWrapper">
			<div class="bigPicture"></div>
		</div>

		<div class="uploadDiv">
			<input type="file" name="uploadFile" multiple="multiple" />
		</div>

		<div class="uploadResult">
			<ul></ul>
		</div>

		<button id="uploadBtn">Upload</button>
	</article>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			/*	$('#uploadBtn').on('click', function(event) {
					let formData = new FormData();
					let inputFile = $('input[name="uploadFile"]');
					let files = inputFile[0].files;

					console.log(files);

					for (let i = 0; i < files.length; i++) {
						formData.append('uploadFile', files[i]);
					}

					$.ajax({
						url : '/uploadAjaxAction',
						processData : false,
						contentType : false,
						data : formData,
						type : 'POST',
						success : function(result) {
							alert("Uploaded")
						}
					});
				}); */

			const regEx = new RegExp('(.*?)\.(exe|sh|zip|alz|jar)$');
			const maxSize = 5242880;/* 5MB */

			function checkExtension(fileName, fileSize) {
				let result = true;
				if (fileSize >= maxSize) {
					alert('파일가 최대 사이즈[' + maxSize + '] 초과');
					result = false;
				}
				if (regEx.test(fileName)) {
					alert('해당 종류의 파일은 업로드 할 수 없습니다.');
					result = false;
				}
				return result;
			}

			let cloneObject = $('.uploadDiv').clone();

			$('#uploadBtn').on('click', function(event) {
				let formData = new FormData();
				let inputFile = $('input[name="uploadFile"]');
				let files = inputFile[0].files;

				console.log(files);

				for (let i = 0; i < files.length; i++) {
					if (!checkExtension(files[i].name, files[i].size)) {
						return false;
					}
					formData.append('uploadFile', files[i]);
				}

				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					success : function(result) {
						/* alert("Uploaded") */
						console.log(result);

						showUploadFile(result);

						$('.uploadDiv').html(cloneObject.html());
					}
				});
			});

			let uploadResult = $('.uploadResult ul');

			function showUploadFile(uploadResultArray) {
				let str = '';
				$(uploadResultArray).each(function(i, object) {
					if (!object.image) {
						str += '<li><img src="/resources/img/attach.png">' + object.fileName+ '</li>';
					} else {
					str += '<li>' + object.fileName + "</li>";
					let fileCallPath = encodeURIComponent(object.uploadPath + '/s_' + object.uuid + '_' + object.fileName);
					let originalPath = object.uploadPath + '\\' + object.uuid + '_' + object.fileName;
					str += '<li><a href=\'javascript:showImage(\'' + originalPath +'\')\'>'
							+ '<img src=\'display?fileName=' + fileCallPath + '\'></a>'
							+ '<span data-file=\'' + fileCallPath + '\'data-type=\'image\'></span></li>';
					}
				});
				uploadResult.append(str);
			}
		});
	</script>
</body>
</html>