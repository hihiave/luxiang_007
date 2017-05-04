<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<TITLE>文档搜索结果</TITLE>
<!--STATUS OK-->
<META http-equiv=content-type content=text/html;charset=UTF-8">
<link href="css/result.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function check() {
		var val = document.forms[0].queryStr.value.trim();
		if (val == "") {
			return false;
		} else {
			document.forms[0].submit();
		}
	}
</script>
</HEAD>
<BODY>
	<TABLE height=54 cellSpacing=0 cellPadding=0 width="100%" align=center>
		<TBODY>
			<TR vAlign=center>
				<TD style="PADDING-LEFT: 8px; WIDTH: 137px" vAlign=top noWrap
					width="100%"><IMG height="46" alt="到搜索首页" src="img/3.png"
					width=137 border=0></TD>
				<TD>&nbsp;&nbsp;&nbsp;</TD>
				<TD vAlign="top" align="left" width="100%">
					<DIV class="Tit"></DIV>
					<TABLE style="MARGIN-LEFT: 18px; HEIGHT: 60px" cellSpacing=0
						cellPadding=0>
						<TBODY>
							<TR>
								<TD vAlign="top" noWrap>
									<form action="searchIndex.do">
										<input type="text" name="queryStr" size="42" maxlength="100"
											value="${queryStr}" />
										<input type="hidden" name="searchType" value="${searchType}">
										<input type="hidden" name="filetype" value="${filetype}">
										<input type="hidden" name="topNum" value="${topNum}">
										<input type="hidden" name="pageSize" value="${pageSize}">
										<input type="button" value="搜索" onclick="check()" />
									</form>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	<TABLE class=bi cellSpacing=0 cellPadding=0 width="100%" align=center
		border=0>
		<TBODY>
			<tr>
				<td noWrap align="left" style="font-size: small;">&nbsp;&nbsp;搜到相关文档约
					&nbsp;${totalCount}&nbsp;篇&nbsp;&nbsp;&nbsp;&nbsp;</td>
			</tr>
		</TBODY>
	</TABLE>
	<!--开始填充检索到的文档documentEntities -->
	<BR>
	<c:forEach items="${documentEntities}" var="DocumentEntity">
		<table cellspacing="0" cellpadding="0" border="0" width="100%">
		<tr>
			<td class="f EC_PP">
			<a href="${DocumentEntity.fileUrl}" target=_blank >${DocumentEntity.filename} </a>
			<br>关键词 ：${DocumentEntity.fileKeywords}
			<br>摘要：${DocumentEntity.fileDesc}
			</td>
		</tr>
			<!-- <TBODY></TBODY> -->
		</table>
		<hr width="100%" size="0.1">
	</c:forEach>
	<BR>
	<c:choose>
		<c:when test="${not empty requestScope.pageNumBean.upPageNum}">
			<a
				href="${pageUrl}${requestScope.pageNumBean.upPageNum}&searchType=${searchType}&queryStr=${queryStr}&pageSize=${pageSize}&topNum=${topNum}&filetype=${filetype}">上一页</a>
		</c:when>
		<c:otherwise>  
 			  上一页 
   		</c:otherwise>
	</c:choose>

	<c:forEach items="${requestScope.pageNumBean.pages}" var="item">
		<c:choose>
			<c:when test="${item == requestScope.pageNumBean.pageNow}">
				<a href="${pageUrl}${item}&searchType=${searchType}&queryStr=${queryStr}&pageSize=${pageSize}&topNum=${topNum}&filetype=${filetype}">${item}</a>
			</c:when>
			<c:otherwise>
				<a href="${pageUrl}${item}&searchType=${searchType}&queryStr=${queryStr}&pageSize=${pageSize}&topNum=${topNum}&filetype=${filetype}">[${item}]</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${not empty requestScope.pageNumBean.downPageNum}">
			<a
				href="${pageUrl}${requestScope.pageNumBean.downPageNum}&searchType=${searchType}&queryStr=${queryStr}&pageSize=${pageSize}&topNum=${topNum}&filetype=${filetype}">下一页</a>
		</c:when>
		<c:otherwise>  
    		下一页 
    	</c:otherwise>
	</c:choose>

	<br>
	<br>

	<DIV id=ft>
		<span>Copyright © &copy; 1956 - 2016&nbsp; All Rights Reserved
			电子科技大学</span>
	</DIV>
	<IMG style="DISPLAY: none" src="">
</BODY>
</HTML>
