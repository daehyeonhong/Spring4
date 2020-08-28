<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ExUpload</title>
</head>
<body>
	<article>
		<h1>
			<em>ExUpload</em>
		</h1>
		<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data">
			<div>
				<input type="file" name="files" />
			</div>
			<div>
				<input type="file" name="files" />
			</div>
			<div>
				<input type="file" name="files" />
			</div>
			<div>
				<input type="file" name="files" />
			</div>
			<div>
				<input type="submit" value="submit" />
			</div>
		</form>
	</article>
</body>
</html>