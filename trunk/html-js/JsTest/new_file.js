/**
 * @author Administrator
 */
function insertTR(){
	var tb = document.getElementById("tb");
	createTR(tb);
	if(tb.getElementsByTagName("tr").length<20){
		setTimeout("insertTR()",100);
	}
}

function createTR(tb){
	var tra = document.createElement("tr");
	/*while(tra.getElementsByTagName("td").length<10){
		setTimeout("createTD("+tra+")",100);
	}*/
	tb.appendChild(tra);
	var tda = document.createElement("td");
	tda.setAttribute("bgcolor","#ffff00");
	tda.bgColor = "#ffff00";
	var dataa = tb.getElementsByTagName("tr").length;
	tda.appendChild(document.createTextNode(dataa));
	tra.appendChild(tda);
	while(tra.getElementsByTagName("td").length<10){
		var tda = document.createElement("td");
		tda.setAttribute("bgcolor","#ffffff");
		tda.bgColor = "#ffffff";
		tda.appendChild(document.createTextNode("test"+tra.getElementsByTagName("td").length));
		tra.appendChild(tda);
	}
		
}

function createTD(tra){
		var tda = document.createElement("td");
		tda.setAttribute("bgcolor","#ffffff");
		tda.bgColor = "#ffff00";
		var dataa = document.createTextNode("Ò¶ºêÓî");
		tda.appendChild(dataa);
		tra.appendChild(tda);
}

