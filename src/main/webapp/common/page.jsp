<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td class="STYLE4">&nbsp;&nbsp;共有${page.maxSize}条记录，当前第 ${page.currentPage}/${page.maxPage==0?1:page.maxPage} 页</td>
    <td><table border="0" align="right" cellpadding="0" cellspacing="0">
        <tr>
          <td width="40"><img style="cursor:pointer" src="${pageContext.request.contextPath}/resource/images/first.gif" width="37" height="15" onclick="selectPage(1)"/></td>
          <td width="45"><img style="cursor:pointer" src="${pageContext.request.contextPath}/resource/images/back.gif" width="43" height="15" onclick="selectPage(${page.currentPage-1<=0?1:page.currentPage-1})"/></td>
          <td width="45"><img style="cursor:pointer" src="${pageContext.request.contextPath}/resource/images/next.gif" width="43" height="15" onclick="selectPage(${page.currentPage+1>=page.maxPage?page.maxPage:page.currentPage+1})"/></td>
          <td width="40"><img style="cursor:pointer" src="${pageContext.request.contextPath}/resource/images/last.gif" width="37" height="15" onclick="selectPage(${page.maxPage})"/></td>
          <td width="100"><div align="center"><span class="STYLE1">转到第
            <input id="fgo" name="" value="${page.currentPage}" type="text" size="4" style="height:12px; width:20px; border:1px solid #999999;" /> 
            页 </span></div></td>
          <td width="40"><img style="cursor:pointer" src="${pageContext.request.contextPath}/resource/images/go.gif" width="37" height="15" onclick="selectPage(document.getElementById('fgo').value>=${page.maxPage}?${page.maxPage}:document.getElementById('fgo').value);"/></td>
        </tr>
    </table></td>
  </tr>
</table>