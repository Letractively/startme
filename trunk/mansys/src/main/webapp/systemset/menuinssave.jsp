<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
		int i = 0;
		String menucode = request.getParameter("menucode");
		menucode = new String(menucode.getBytes("ISO-8859-1"),"GB2312");
		String menuname = request.getParameter("menuname");
		menuname = new String(menuname.getBytes("ISO-8859-1"),"GB2312");
		String menuurl = request.getParameter("menuurl");
		menuurl = new String(menuurl.getBytes("ISO-8859-1"),"GB2312");
		String displayorder = request.getParameter("displayorder");
		String parentscode = request.getParameter("parentscode");
		String rootcode = request.getParameter("rootcode");
		String menulevel = request.getParameter("menulevel");
		String isleaf = request.getParameter("isleaf");	
			
		ConnectDB cdb = new ConnectDB();
		Connection con = cdb.getConnectionFromDS();
		try {
			Statement stmt = con.createStatement();
			String querySQL = "select * from sys_menu where menucode='"+menucode.trim()+"' and status=1";
			ResultSet rs = stmt.executeQuery(querySQL);
			if(rs.next()){
				i = 2;
			}else{
				String strSQL = "insert into sys_menu(menuid,menucode,menuname,menuurl,menulevel,isleaf,"+
					"parentscode,rootcode,displayorder,status,issys) values(sys_menu_seq.nextval,'"+
					menucode+"','"+menuname+"','"+menuurl+"',"+menulevel+","+isleaf+",'"+parentscode+
					"','"+rootcode+"',"+displayorder+",1,0)";
				i = stmt.executeUpdate(strSQL);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i==1){
			%>
			<script>
			alert("添加菜单成功！");
			window.location = "./menulist.jsp";
			</script>
			<%
		}else{
			%>
			<script>
			alert("菜单编码<<%=menucode%>>已存在！");
			window.location = "./menulist.jsp";
			</script>
			<%
		}
%>