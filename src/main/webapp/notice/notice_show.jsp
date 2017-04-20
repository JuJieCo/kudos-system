<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/WEB-INF/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

.btn_mouseout {
 BORDER-RIGHT: #2C59AA 1px solid; PADDING-RIGHT: 2px; BORDER-TOP: #2C59AA 1px solid; PADDING-LEFT: 2px; FONT-SIZE: 12px; FILTER: progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr=#ffffff, EndColorStr=#AAC8F0); BORDER-LEFT: #2C59AA 1px solid; CURSOR: hand; COLOR: black; PADDING-TOP: 2px; BORDER-BOTTOM: #2C59AA 1px solid
}
input     {
           background-color: #ffffff;
           border: 1px solid #669ACC;
           font-size: 12px;
           font-style: normal;
           font-variant: normal;
           line-height: normal;
          }
SELECT{
           font-size: 12px;     
}

-->
</style>

<script>

</script>
<script language=JavaScript>
today=new Date();
function initArray(){
 this.length=initArray.arguments.length
 for(var i=0;i<this.length;i++)
 this[i+1]=initArray.arguments[i]  }
 var d=new initArray(" 星期日"," 星期一",
 " 星期二"," 星期三", " 星期四"," 星期五"," 星期六"); 
</script>


		<link href="${pageContext.request.contextPath}/resource/css/date/date.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/resource/css/css.css" rel="stylesheet" type="text/css" />
		<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resource/js/date/jquery.datepicker.js" type="text/javascript"></script>
    	<script src="${pageContext.request.contextPath}/resource/js/date/datepicker_lang_CN.js" type="text/javascript"></script>
    	<script src="${pageContext.request.contextPath}/resource/js/date/loading.js" type="text/javascript"></script>
    	<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="${pageContext.request.contextPath}/resource/images/tab_03.gif" width="12" height="30" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%"  valign="middle"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="5%"><div align="center"><img src="${pageContext.request.contextPath}/resource/images/tb.gif" width="16" height="16" /></div></td>
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：公告管理-
                	<c:if test="${noticeType==1 }">发布公告</c:if><c:if test="${noticeType==2 }">审批公告</c:if><c:if test="${noticeType==3 }">收看公告</c:if>
                </td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"> 
						<script language=JavaScript>
					 		document.write("", today.getYear(),"年", today.getMonth()+1,"月",today.getDate(),"日 ",d[today.getDay()+1], "" ); 
						</script>
					</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_07.gif" width="16" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td>
	<form action="" name="notice" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">标题：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;${notice.title }</td>
         	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">发布人：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;${notice.execute_userName }</td>
          </tr>       
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">起始时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${notice.start_time }</td>
         	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">结束时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${notice.end_time } </td>      
          </tr>      
          <tr>
        	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">发布时间：</td>
		    <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3"  >&nbsp;${notice.public_time }</td>
          	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">收看级别：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
			<c:if test="${notice.rank==0 }">所有部门</c:if>
            <c:if test="${notice.rank==1 }">${notice.execute_dept }</c:if>
			</td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">内容：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;<textarea name="notice.content" cols="60" rows="10" readonly="readonly">${notice.content }</textarea> </td>
			
          </tr>
		   <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">备注：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;<textarea name="notice.remark" cols="60" rows="3" readonly="readonly">${notice.remark }</textarea> </td>
          </tr>
	 	<tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">审批意见：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;
            <c:if test="${notice.status==0 }">公告已录入</c:if>
            <c:if test="${notice.status==1 }">公告已送审</c:if>
            <c:if test="${notice.status==2 }">审批已通过</c:if>
            <c:if test="${notice.status==3 }">审批未通过</c:if>
          	</td>
          </tr>
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr><td >
				<input type="button" value="返回" onclick="history.go(-1)"/>	
				</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>