<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title></title>
<style type="text/css">

#side{
display: block;
}



</style>
</head>
<body>
파일업로드테스트입니다.

<form id="fileForm" method="post" enctype="multipart/form-data">
    <input type="file" name="file" multiple="true">
</form>
</body>
</html>