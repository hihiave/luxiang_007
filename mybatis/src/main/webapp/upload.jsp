<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>文件上传</title>
  </head>
  
  <body>

 <form  name="form1" method="post" enctype="multipart/form-data" action="/mybatis/FileUploadController">
 <input type="text" id="filetype1" name="filetype1" >文件种类
<input type="text" id="author" name="author">作者
 <input type="file" id="file1" name="file1">
 <p></p>
 <input type="text" id="filetype2" name="filetype2" >文件种类
<input type="text" id="author" name="author">作者
 <input type="file" id="file2" name="file2">
 <p></p>
 <input type="submit" id="提交">
 </form>
  </body>
</html>