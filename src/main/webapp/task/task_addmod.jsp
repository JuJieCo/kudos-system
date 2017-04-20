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

<script src="${pageContext.request.contextPath}/resource/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resource/js/constant.js" type="text/javascript"></script>
<script type="text/javascript">

   var saffer = [];

   function findStaffByDept(obj){
		if($(obj).attr("checked")==true){
			$.getJSON("${pageContext.request.contextPath}/task/taskAction!getAjaxSaffer",{deptName:$(obj).val()},function(data){
				 $("#saffer").append("<div style='color:blue' sign="+$(obj).attr("[ct]")+">----"+$(obj).val()+"-----</div>");
				 $(data).each(function(i,v){
					var ced = "";
					var flag = false;
					for(n=0;n<saffer.length;n++){
						if(v.uuid==saffer[n].id){
							flag = true;
							break;
						}
					}
					if(flag){
						ced = "checked";
					}
					if('show'=='${type}'){
						if(flag){
							$("#saffer").append("<div sign="+$(obj).attr("[ct]")+">&nbsp;"+v.executeUserName+"</div>");
						}
					}else if('add'=='${type}'){
						$("#saffer").append("<div sign="+$(obj).attr("[ct]")+">&nbsp;<input type='checkbox' name='saffer' value='"+v.uuid+"' checked >"+v.executeUserName+"</input></div>");
					}else{
						$("#saffer").append("<div sign="+$(obj).attr("[ct]")+">&nbsp;<input type='checkbox' name='saffer' value='"+v.uuid+"' "+ced+" >"+v.executeUserName+"</input></div>");
					}
					
				});
			});
		}else{
			$("div[sign="+$(obj).attr("[ct]")+"]").remove();
		}
   }
   
   jQuery(function($) {
	   $("input[name=checkbox_uuid]").each(function(){
		   for(n=0;n<saffer.length;n++){
				if($(this).val()==saffer[n].dept){
					$(this).attr("checked",true);
					$(this).click();
					$(this).attr("checked",true);
					break;
				}
			}
	   });
   });
   
   jQuery(function($) {
	   if('show'=='${type}'){
			$("#form_oper").hide();
			$("#showback").show();

			$("input,select,textarea").each(function(){
				if($(this).is("input")&&$(this).attr("type")=="checkbox"){
					if($(this).attr("checked")==false){
						$(this).parent().remove();
					}else{
						$(this).remove();
					}
				}else if($(this).is("select")){
					$(this).replaceWith($(this).find(":selected").text());
				}else if($(this).attr("type")!="button"&&$(this).attr("type")!="hidden"){
					$(this).replaceWith($(this).val());
				}
			});
			
			$("span[name=ada]").remove();
			$("span[name=dea]").remove();
		}
   });
   
   function addaccessory(){
	   var str = "<span>&nbsp;<input name=accessory type=file > <span  style='cursor:pointer' onclick=removeaccessory(this)>移除</span> </span>";
  	   $("#div_file").append(str);
   }
   
   function removeaccessory(obj){
	   $(obj).parent().remove();
   }
   
   function deleteFile(id,obj){
	   $.post("${pageContext.request.contextPath}/task/taskAction!deleteAjaxAccessory",{aid:id},function(){
		   $(obj).parent().remove();
	   });
   }
   function valitForm(){
	   var title = $("input[name=worksTask.title]").val();
	   var tc = $("textarea[name=worksTask.taskContent]").val();
	   var stime = $("input[name=worksTask.startTime]").val();
	   var ftime = $("input[name=worksTask.finishTime]").val();
	  
	   if(jQuery.trim(title)==""){
			alert("标题不能为空！");
			document.getElementById('tt').focus();
			return false;
		    } 
	   if(jQuery.trim(tc)==""){
				alert("任务描述不能为空！");
				document.getElementById('tc').focus();
				return false;
			 } 
	   
	   var falg = 0; 
	   $("input[name='checkbox_uuid']:checkbox").each(function () { 
	   if ($(this).attr("checked")) { 
	   falg += 1; 
	   } 
	   });
	   if (falg <= 0){
		   alert("请至少选中一个执行部门！");
		   return false; 
	   }
	   
	   var falg1 = 0; 
	   $("input[name='saffer']:checkbox").each(function () { 
	   if ($(this).attr("checked")) { 
	   falg1 += 1; 
	   } 
	   });
	   if (falg1 <= 0){
		   alert("请至少选中一名执行人员！");
		   return false; 
	   }
	   
	   if(jQuery.trim(stime)==""){
		alert("开始时间不能为空！");
		document.getElementById('stime').focus();
		return false;
		}
	   if(jQuery.trim(ftime)==""){
		alert("完成时间不能为空！");
		document.getElementById('ftime').focus();
		return false;
		}
	return true;
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：起草工作-编辑工作</td>
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
	<form action="${pageContext.request.contextPath}/task/taskAction!${type=='edit'?'editTask':'saveTask'}?forward=${forward}" name="" method="post" enctype="multipart/form-data" onsubmit="return valitForm();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        
        <input type="hidden" name="worksTask.leader.uuid" value="${worksTask.leader.uuid}" />
        <input type="hidden" name="worksTask.uuid" value="${worksTask.uuid}" />
        <input type="hidden" name="worksTask.status" value="${worksTask.status}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%">任务编号：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="worksTask.taskCode" type="hidden" value="${worksTask.taskCode}"/>${worksTask.taskCode}</td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"><font color="red">*</font>标题：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="worksTask.title" type="text" value="${worksTask.title}"  id="tt"/></td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>任务描述：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<textarea name="worksTask.taskContent" cols="30" rows="10" id="tc">${worksTask.taskContent}</textarea></td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">任务级别：</td>
		    <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;
		    	<select name="worksTask.taskLevel">
		    		<option value="0" ${worksTask.taskLevel==0?'selected':''}>默认</option>
		    		<option value="1" ${worksTask.taskLevel==1?'selected':''}>缓</option>
		    		<option value="2" ${worksTask.taskLevel==2?'selected':''}>急</option>
		    		<option value="3" ${worksTask.taskLevel==3?'selected':''}>紧急</option>
		    	</select>
		    </td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>执行部门：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >
            	 <div>&nbsp;<input type="checkbox" name="checkbox_uuid" value="零售事业部信贷中心" [ct]='a2' onclick="findStaffByDept(this)">零售事业部信贷中心 </div>
            	 <div>&nbsp;<input type="checkbox" name="checkbox_uuid" value="零售事业部产品营销中心" [ct]='a3' onclick="findStaffByDept(this)">零售事业部产品营销中心</div>
            	 <div>&nbsp;<input type="checkbox" name="checkbox_uuid" value="零售事业部财富管理部" [ct]='a4' onclick="findStaffByDept(this)">零售事业部财富管理部</div>
            	 <div>&nbsp;<input type="checkbox" name="checkbox_uuid" value="零售事业部银行卡与渠道管理部" [ct]='a5' onclick="findStaffByDept(this)">零售事业部银行卡与渠道管理部</div>
            	 <div>&nbsp;<input type="checkbox" name="checkbox_uuid" value="零售事业部综合管理部" [ct]='a6' onclick="findStaffByDept(this)">零售事业部综合管理部</div>
            </td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>执行人员名称：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3">
            	<div style="width:100%;height:150px; overflow-y:scroll;" id="saffer" ></div>
            </td>
          </tr>
		  <tr>
		    <fmt:formatDate value="${worksTask.startTime}"  var="st" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> 
		    <fmt:formatDate value="${worksTask.finishTime}"  var="ft" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> 
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>开始时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >
            &nbsp;<input name="worksTask.startTime" type="text" onfocus="WdatePicker({readOnly:true,startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" value="${st}" style="width:160px" id="stime"/>
<!--        &nbsp;<input  name="worksTask.startTime" type="text" id="gettime0"  readonly="readonly" value="${st}"/>  -->
            </td>
			<td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif"><font color="red">*</font>完成时间：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >
			&nbsp;<input name="worksTask.finishTime" type="text" onfocus="WdatePicker({readOnly:true,startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" value="${ft}" style="width:160px" id="ftime"/>
<!--        &nbsp;<input  name="worksTask.finishTime" type="text" id="gettime1" readonly="readonly" value="${ft}"/>  -->
			</td>
          </tr>
		  <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">下达领导名称：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${worksTask.leader.executeUserName}</td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">下达领导级别：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<script>document.write(TranslationUserRank('${worksTask.leader.executeRank}'));</script></td>
          </tr>
		 <tr>
		 	<fmt:formatDate value="${worksTask.issuedTime}"  var="it" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> 
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">下达时间：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;${it}</td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">任务状态：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >&nbsp;<script>document.write(TranslationTaskStatus('${worksTask.status}'));</script></td>
          </tr>
		 <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" >附件：</td>
			<td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">
				<div style="float:left;width:300px" id="div_file">
					<span><input  name="accessory"  type="file"/> <span style="cursor:pointer" onclick="addaccessory();" name="ada">添加附件</span></span>
				</div>
				<div style="float:left">
					<s:iterator value="worksTask.worksAccessoryList" var="accessory">
						<span style="float:left">&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="${pageContext.request.contextPath}/task/taskAction!downAccessory?aid=${accessory.uuid}">${accessory.filename}</a>
							&nbsp;&nbsp;
							<span onclick="deleteFile(${accessory.uuid},this)" style="font-size:12px;color:red;cursor:pointer" name="dea">[删除]</span>
						</span>
					</s:iterator>
					<span style="clear:both"></span>
				</div>
				<div style="clear:both"></div>
			</td>
          </tr>
		   <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">备注：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3"><textarea name="worksTask.remark" cols="55" rows="3">${worksTask.remark}</textarea> </td>
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
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif" >
           
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr> <td >
				<input  type="submit"  value="提交"/>
				<input  type="reset"   value="清空"/>
				 <input  type="button"  value="返回" onclick="history.back()"/>
				</td>
                </tr>
            </table>
            
             <table border="0" align="center" cellpadding="0" cellspacing="0" id="showback" style="display: none;">
                <tr>
                <td >
              
				  <input  type="button" id="bk" value="返回" onclick="history.go(-1)">
				
				</td>
                </tr>
            </table>
           
            </td>
          </tr>
        </table></td>
        <td width="16"><img src="${pageContext.request.contextPath}/resource/images/tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>

<s:iterator value="worksTask.safferList" var="saffer" status="i">
	<script>saffer[${i.index}]={id:${saffer.uuid},dept:'${saffer.executeDept}'}</script>
</s:iterator>

<script type="text/javascript">
	
</script>

</body>
</html>