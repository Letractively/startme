<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
		boolean success = false;
		String menuid = request.getParameter("menuid");
		String menucode = request.getParameter("menucode");
		
		ConnectDB cdb = new ConnectDB();
		Connection con = cdb.getConnectionFromDS();
		try {
			Statement stmt = con.createStatement();
			int i = stmt.executeUpdate("delete from sys_menu where menuid="+Long.parseLong(menuid));
			if(i>=1){
				success = true;
			}
			stmt.executeUpdate("delete from sys_menu where parentscode='"+menucode+"'");
			stmt.executeUpdate("delete from sys_menu where rootcode='"+menucode+"'");
			
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(success==true){
			%>
			<script>
			alert("ɾ���ɹ���");
			window.location = "./menulist.jsp";
			</script>
			<%
		}else{
			%>
			<script>
			alert("ɾ��ʧ��!");
			window.location = "./menulist.jsp";
			</script>
			<%
		}
%>