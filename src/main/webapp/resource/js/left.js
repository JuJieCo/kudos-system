function showDiv(n){
		$("#c"+n).show();
		$("#f"+n).attr("class",'STYLE5');
		
		if(n!=1){
			$("#c1").hide();
			$("#f1").attr("class",'STYLE4');
		}
		if(n!=2){
			$("#c2").hide();
			$("#f2").attr("class",'STYLE4');
		}
		if(n!=3){
			$("#c3").hide();
			$("#f3").attr("class",'STYLE4');
		}
		if(n!=4){
			$("#c4").hide();
			$("#f4").attr("class",'STYLE4');
		}
		if(n!=5){
			$("#c5").hide();
			$("#f5").attr("class",'STYLE4');
		}
	}

	function addUrl(url,sign){
		var strA = '<a id="'+sign+'" href="..'+url+'" target="mainFrame" style="display:none;"></a>';
		var newDiv = document.createElement("div");
		$(newDiv).html(strA);
		document.body.appendChild(newDiv);
	}
 
	function toPage(e,type){	
		$("td").removeClass("sty_bg");
		$(e).addClass("sty_bg");

	   var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
       if(ie){
			document.getElementById(type).click();
		}
       else{
			var a=document.createEvent("MouseEvents");
            a.initEvent("click", true, true);
			document.getElementById(type).dispatchEvent(a);
		}
	}