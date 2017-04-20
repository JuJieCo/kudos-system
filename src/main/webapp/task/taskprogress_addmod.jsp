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
			}
	});
	
	function valitForm(){
		var content = $("input[name=workProgress.content]").val();
		var inputTime = $("input[name=workProgress.inputTime]").val();
		var uname = $("input[name=user.executeUserName]").val();
	
	if(jQuery.trim(content)==""){
		alert("进度说明不能为空！");
		return false;
	}
	if(jQuery.trim(inputTime)==""){
		alert("录入时间不能为空！");
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
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：我的工作-编辑进度</td>
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
	<form action="${pageContext.request.contextPath}/task/taskAction!${type=='edit'?'editTaskProgess':'saveTaskProgess'}?forward=${forward}" name="" method="post" onsubmit="return valitForm();">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
        <td width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
        <td>
        <input type="hidden" name="workProgress.uuid" value="${workProgress.uuid}" />
        <input type="hidden" name="workProgress.task.uuid" value="${workProgress.task.uuid}" />
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <fmt:formatDate value="${workProgress.inputTime}"  var="inputtime" pattern="yyyy-MM-dd HH:mm:ss" type="date"/> 
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif" width="20%"><font color="red">*</font>进度说明：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" width="30%">&nbsp;<input  name="workProgress.content" type="text" value="${workProgress.content}"/></td>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">录入时间：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" >
            <!--  &nbsp;<input  name="workProgress.inputTime" type="text" id="gettime0"  readonly="readonly" value="${inputtime}"/> </td> -->
            &nbsp;<input name="workProgress.inputTime" type="text" onfocus="WdatePicker({readOnly:true,startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" value="${inputtime}" style="width:160px"/>
          </tr>
		  <tr>
             <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">执行进度：</td>
		    <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;
		    	<select name="workProgress.progress">
		    		<option value="0" ${workProgress.progress==0?'selected':''}>0%</option>
		    		<option value="5" ${workProgress.progress==5?'selected':''}>5%</option>
		    		<option value="10" ${workProgress.progress==10?'selected':''}>10%</option>
		    		<option value="15" ${workProgress.progress==15?'selected':''}>15%</option>
		    		<option value="20" ${workProgress.progress==20?'selected':''}>20%</option>
		    		<option value="25" ${workProgress.progress==25?'selected':''}>25%</option>
		    		<option value="30" ${workProgress.progress==30?'selected':''}>30%</option>
		    		<option value="35" ${workProgress.progress==35?'selected':''}>35%</option>
		    		<option value="40" ${workProgress.progress==40?'selected':''}>40%</option>
		    		<option value="45" ${workProgress.progress==45?'selected':''}>45%</option>
		    		<option value="50" ${workProgress.progress==50?'selected':''}>50%</option>
		    		<option value="55" ${workProgress.progress==55?'selected':''}>55%</option>
		    		<option value="60" ${workProgress.progress==60?'selected':''}>60%</option>
		    		<option value="65" ${workProgress.progress==65?'selected':''}>65%</option>
		    		<option value="70" ${workProgress.progress==70?'selected':''}>70%</option>
		    		<option value="75" ${workProgress.progress==75?'selected':''}>75%</option>
		    		<option value="80" ${workProgress.progress==80?'selected':''}>80%</option>
		    		<option value="85" ${workProgress.progress==85?'selected':''}>85%</option>
		    		<option value="90" ${workProgress.progress==90?'selected':''}>90%</option>
		    		<option value="95" ${workProgress.progress==95?'selected':''}>95%</option>
		    		<option value="100" ${workProgress.progress==100?'selected':''}>100%</option>
		    	</select>
		    </td>
          </tr>
		   <tr>
            <td height="22" align="right" bgcolor="#FFFFFF" class="STYLE3" background="${pageContext.request.contextPath}/resource/images/bg.gif">备注：</td>
            <td height="22" align="left" bgcolor="#FFFFFF" class="STYLE3" colspan="3">&nbsp;<textarea name="workProgress.remark">${workProgress.remark}</textarea> </td>
          </tr>
        </table>
		</td>
        <td  width="8" background="${pageContext.request.contextPath}/resource/images/tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="35">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="${pageContext.request.contextPath}/resource/images/tab_18.gif" width="12" height="35" /></td>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4" background="${pageContext.request.contextPath}/resource/images/tab_19.gif"></td>
            <td height="35" background="${pageContext.request.contextPath}/resource/images/tab_19.gif">
              <table border="0" align="center" cellpadding="0" cellspacing="0" id="form_oper">
                <tr> <td >
				<input  type="submit"  value="提交"/>
				<input  type="reset"   value="清空"/>
				<input  type="button"  value="返回" onclick="history.back()"/>
				</td>
                </tr>
            </table>
            <table border="0" align="center" cellpadding="0" cellspacing="0" id="showback" style="display: none;">
                <tr> <td >
				  <input  type="button" id="bk" value="返回" onclick="history.back()"/>
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

</body>
</html>