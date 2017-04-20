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

.porg_td{
	height:22px;
	background:url('../resource/images/bg.gif');
	font-size: 12px;  
}
.porg_td2{
	height:22px;
	background-color:#f8e5c6;
	font-size: 12px;  
}
-->
</style>

<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
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

<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
<script language=JavaScript>
	jQuery(function($) {
	  	var checkboxAll = $("input[name=checkboxAll][type=checkbox]");
	  	checkboxAll.unbind().click(function(){
	  		if($(this).attr("checked")==true){
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",true);
	  		}else{
	  			$("input[name=checkbox_uuid][type=checkbox]").attr("checked",false);
	  		}
	  	});
	  	var checkboxUUid = $("input[name=checkbox_uuid][type=checkbox]");
	  	checkboxUUid.unbind().click(function(){
	  		var checked_num = $("input[name=checkbox_uuid][type=checkbox]:checked").size();
	  		var checkbox_num = $("input[name=checkbox_uuid][type=checkbox]").size();
	  		if(checked_num==checkbox_num){
	  			checkboxAll.attr("checked",true);
	  		}else{
	  			checkboxAll.attr("checked",false);
	  		}
	  	});
	  	$("span[name=ltp]").toggle(
	  		function(){
	  			var tr = $(this).parent().parent();
	  			if(tr.next().attr("[trn]")=='prog'){
					tr.next().remove();
				}
				var str = "<tr [trn]=prog><td colspan=9><table cellspacing=0 cellpadding=0 width=100%>";
				str +="<tr><td class=porg_td>&nbsp;&nbsp;</td><td class=porg_td>进度说明</td><td class=porg_td>执行进度</td><td class=porg_td>执行人</td><td class=porg_td>执行人部门</td><td class=porg_td>录入时间</td><td class=porg_td>进度描述</td></tr>";
				$.getJSON("${pageContext.request.contextPath}/task/taskAction!getAjaxProgess",{taskUuid:$(this).attr("data")},function(data){
					$(data).each(function(i,v){
						str += "<tr><td class=porg_td2>&nbsp;&nbsp;</td><td class=porg_td2>"+v.content+"</td><td class=porg_td2>"+v.progress+"%</td>"
						+"<td class=porg_td2>"+v.inputUser.executeUserName+"</td><td class=porg_td2>"+v.inputUser.executeDept+"</td><td class=porg_td2>"+v.inputTime+"</td><td class=porg_td2>"+v.remark+"</td></tr>";
					});
					str += "</table></td></tr>";
					tr.after($(str));
			 	});
	  		},
	  		function(){
	  			var tr = $(this).parent().parent();
	  			tr.next().hide();
	  		}
	  	);
	  	
	}); 
	
</script>
<script type="text/javascript">

	function selectPage(currentPage,nowEnt){
		var form = document.forms.form1;
		form.action = form.action+"?page.currentPage="+currentPage;
		form.submit();
	}

	function showTitEndTime(et,nowEnt){
		var nowTime = new Date().getTime();
		var myDate = new Date();
		var ets = et.split("-");
		myDate.setFullYear(ets[0],ets[1]-1,ets[2]);
		
		var dz = (parseInt(myDate.getTime())-parseInt(nowTime))/(1000*24*3600);
		if(dz<0){
			nowEnt = "<font style='color:grey'>"+nowEnt+"</font>";
		}
		if(dz<=7&&dz>=0){
			nowEnt = "<font style='color:red'>"+nowEnt+"</font>";
		}
		if(dz<=30&&dz>7){
			nowEnt = "<font style='color:green'>"+nowEnt+"</font>";
		}
		return nowEnt;
	}
</script>

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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：工作管理-管理工作列表</td>
              </tr>
            </table></td>
            <td width="54%"><table border="0" align="right" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"></td>
                    <td class="STYLE1"></td>
                  </tr>
                </table></td>
                <td ><table width="100%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"> 
						<script language=JavaScript>
					 		document.write("", today.getYear(),"年", today.getMonth()+1,"月",today.getDate(),"日 ",d[today.getDay()+1], "" ); 
						</script>
					</td>
                    <td class="STYLE1"></td>
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
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr height="30">
	  <td width="8"  background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  <td >
	  <table width="100%"  bgcolor="#EFF6FE">
	  <tr >
	  <form action="${pageContext.request.contextPath}/task/taskAction!getOngoingTaskList" name="form1" method="post">
	  <td width="58%"  class="STYLE4" > 
	  <fmt:formatDate value="${worksTask.startTime}"  var="st" pattern="yyyy-MM-dd" type="date"/> 
	  <fmt:formatDate value="${worksTask.finishTime}" var="ft" pattern="yyyy-MM-dd" type="date"/> 
	  &nbsp;开始时间：<input name="worksTask.startTime" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${st}"/>
	  &nbsp;完成时间：<input name="worksTask.finishTime" type="text" onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" class="Wdate" value="${ft}"/>
	  &nbsp;<input type="button"  value="查询" class="btn_mouseout" onclick="document.forms.form1.submit();">&nbsp;</td>
	  </form>
	  <td width="24%">&nbsp;</td>
	   </tr>  
		</table></td>
	
				
	  <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
	  </tr>
	  
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <form action="${pageContext.request.contextPath}/task/taskAction!deleteTask" name="form2" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">序号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">任务标题</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">任务编号</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">执行人姓名</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">任务级别</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">任务状态</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">开始时间</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">结束时间</span></td>
            <td  height="22" align="center" background="${pageContext.request.contextPath}/resource/images/bg.gif" bgcolor="#FFFFFF"><span class="STYLE1">操作</span></td>
          </tr>
		  <!-- 迭代-->
		 <s:iterator value="worksTaskList" var="one">
		 <tr>
             <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.uuid}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.title}</span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.taskCode}</span></td>
             <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">
            <c:forEach items="${one.safferList}" var="saffer">
            ${saffer.executeUserName}
            </c:forEach>
            </span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><script>document.write(TranslationTaskLevel('${one.taskLevel}'));</script></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><script>document.write(TranslationTaskStatus('${one.status}'));</script></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1">${one.startTime}</span></td>
            <fmt:formatDate value="${one.finishTime}"  var="etv" pattern="yyyy-MM-dd" type="date"/> 
            <td height="20" bgcolor="#FFFFFF" align="center"><span class="STYLE1"><script>document.write(showTitEndTime('${etv}','${one.finishTime}'));</script></span></td>
            <td height="20" bgcolor="#FFFFFF" align="center">
            	<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!showTask?worksTask.uuid=${one.uuid}&type=show&forward=ongo" >查看</a></span>
            	<c:if test="${one.status==0||one.status==-1}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!showTask?worksTask.uuid=${one.uuid}&type=edit&forward=ongo" >修改</a></span>
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!deleteTask?worksTask.uuid=${one.uuid}&forward=ongo" >删除</a></span>
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!updateTaskStatu?worksTask.uuid=${one.uuid}&forward=ongo&worksTask.status=1" >下达</a></span>
            	</c:if>
            	<c:if test="${one.status==1||one.status==-8}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!updateTaskStatu?worksTask.uuid=${one.uuid}&forward=ongo&worksTask.status=-1" >撤回</a></span>
            	</c:if>
            	<c:if test="${one.status==8||one.status==-9}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!updateTaskStatu?worksTask.uuid=${one.uuid}&forward=ongo&worksTask.status=9" >归档</a></span>
            	</c:if>	
            	<c:if test="${one.status==9}">
            		<span class="STYLE1"><a href="${pageContext.request.contextPath}/task/taskAction!updateTaskStatu?worksTask.uuid=${one.uuid}&forward=ongo&worksTask.status=-9" >撤档</a></span>
            	</c:if>	
            	<span name=ltp data=${one.uuid} class="STYLE1" style="cursor:pointer" >查看工作进度</span>
            </td>
          </tr> 
          </s:iterator>
		 <!--迭代 end  -->
        </table>
        </form>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><%@include file="../common/page.jsp" %></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>