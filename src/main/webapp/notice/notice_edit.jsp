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
function validateForm(formname){
	var title = document.getElementById("title").value;
	var username = document.getElementById("username").value; 
	var starttime = document.getElementById("starttime").value;
	var endtime = document.getElementById("endtime").value;
	var content = document.getElementById("content").value; 
	if(title==""){
		alert('请填写标题!');
		document.getElementById('title').focus();
		return false;
	}
	if(username==""){
		alert('请填写发布人!');
		document.getElementById('username').focus();
		return false;
	}
	if(starttime==""){
		alert('请选择开始时间!');
		document.getElementById('starttime').focus();
		return false;
	}
	if(endtime==""){
		alert('请选择结束时间!');
		document.getElementById('endtime').focus();
		return false;
	}
	if(content==""){
		alert('请填写公告内容!');
		document.getElementById('content').focus();
		return false;
	}

}
</script>
<script language=JavaScript>
today=new Date();
function initArray(){
 this.length=initArray.arguments.length;
 for(var i=0;i<this.length;i++)
 this[i+1]=initArray.arguments[i] ; }
 var d=new initArray(" 星期日"," 星期一",
 " 星期二"," 星期三", " 星期四"," 星期五"," 星期六"); 
</script>

<script type="text/javascript">
function chageAction (obj){	
	noticeform.action="${pageContext.request.contextPath}/notice/noticeAction!editNotice?method=update";
	}
</script>

<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
</head>

<body>
<form action="${pageContext.request.contextPath}/notice/noticeAction!editNotice?method=add" name="noticeform" method="post" onsubmit="return validateForm(this)" >
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="${pageContext.request.contextPath}/resource/images/tab_05.gif">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
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
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"><font color="red">*</font>标题：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input name="notice.title" type="text" value="${notice.title }" id="title"/></td>
         	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"><font color="red">*</font>发布人：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input name="notice.execute_userName" type="text" value="${notice.execute_userName }" readonly="readonly" id="username"/>
            	<input name="notice.public_uuid" type="hidden" value="${notice.public_uuid }" />
            	<input name="notice.uuid" type="hidden" value="${notice.uuid }" />
            	<input name="notice.hold1" type="hidden" value="${notice.hold1 }" />
            </td>
          </tr>       
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>起始时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input  name="notice.start_time" type="text" id="starttime" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${notice.start_time }"/> </td>
         	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>结束时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<input  name="notice.end_time" type="text" id="endtime" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${notice.end_time }"/> </td>      
          </tr>      
          <tr>
        	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">发布时间：</td>
		    <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3"  >&nbsp;${notice.public_time }${systime }</td>
          	<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">收看级别：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;		
				<select name="notice.rank">
					<option value="1" ${notice.rank==1?"selected":"" }>${notice.execute_dept }</option>
					<option value="0" ${notice.rank==0?"selected":"" }>所有部门</option>
				</select>
			</td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" ><font color="red">*</font>公告内容</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;<textarea name="notice.content" cols="60" rows="10" id="content">${notice.content }</textarea> </td>	
          </tr>
		   <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">备注：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;<textarea name="notice.remark" cols="60" rows="3">${notice.remark }</textarea> </td>
          </tr>
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table> 
    </td>
 </tr>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr> <td >
                <c:if test="${editsub==0 }">
					<input  type="submit"  value="提交"/>
				</c:if>
				<c:if test="${editsub==1 }">
					<input  type="submit"  value="提交" onclick="chageAction(); ">
				</c:if>
					<input  type="reset"   value="清空"/>
					<input  type="button" value="返回" onclick="history.go(-1)"/>	
				</td>
                </tr>
            </table></td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    
    </td>
  </tr>
</table>
 </form>
</body>
</html>