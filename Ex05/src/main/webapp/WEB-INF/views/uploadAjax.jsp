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

			$('.uploadResult').on('click', 'span', function (event) {
				let targetFile = $(this).data('file');
				let type = $(this).data('type');
				console.log(targetFile);
				$.ajax({
					url: '/deleteFile',
					data: {fileName: targetFile, type: type},
					dataType: 'text',
					type: 'POST',
					success: function (result) {
						alert(result);
						}
					});
				});
			
			let uploadResult = $('.uploadResult ul');

			function showUploadFile(uploadResultArray) {
				let str = '';
				$(uploadResultArray).each(function(i, object) {
					if (!object.image) {
					let fileCallPath = encodeURIComponent(object.uploadPath + '/' + object.uuid + '_' + object.fileName);
					let fileLink = fileCallPath.replace(new RegExp(/\\/g), '/');
					str += "<li><div><a href='/download?fileName=" + fileCallPath + "'>"
					+ "<img src='/resources/img/attach.png'>" + object.fileName + "</a>"
					+ "<span data-file=\'" + fileCallPath + "\' data-type='file'>x</span></div></li>";
					} else {
						let fileCallPath = encodeURIComponent(object.uploadPath + '/s_' + object.uuid + '_' + object.fileName);
						let originPathal = object.uploadPath + "\\" + object.uuid + "_" + object.fileName; 

						originPathal = originPathal.replace(new RegExp(/\\/g), "/");

						fileCallPath = fileCallPath.replace(new RegExp(/\\/g), "/");

						console.log(fileCallPath);
						str+="<li><a href=\"javascript:showImage(\'"+originPathal+"\')\">"
								+ "<img src='display?fileName="+fileCallPath+"'></a>"
								+ "<span data-file=\' "+fileCallPath+"\' data-type='image'>x</span></li>";
							}
						});
								uploadResult.append(str);
							}
						});

		function showImage(fileCallPath) {
			$('.bigPictureWrapper').css('display', 'flex').show();
			$('.bigPicture').html(
					'<img src="/display?fileName=' + fileCallPath + '">')
					.animate({
						width : '100%',
						height : '100'
					}, 1000);
		}
	</script>
</body>
</html>