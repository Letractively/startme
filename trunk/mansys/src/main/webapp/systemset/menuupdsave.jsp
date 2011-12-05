<%@ page contentType="text/html; charset=GB2312" %>
<%@ page import="com.yehongyu.mansys.util.*"%>
<%@ page import="java.sql.*" %>
<%@ page session="true" %>
<%
		int i = 0;
		String menuid = request.getParameter("menuid");
		String menucode = request.getParameter("menucode");
		menucode = new String(menucode.getBytes("ISO-8859-1"),"GB2312");
		String menuname = request.getParameter("menuname");
		menuname = new String(menuname.getBytes("ISO-8859-1"),"GB2312");
		String menuurl = request.getParameter("menuurl");
		menuurl = new String(menuurl.getBytes("ISO-8859-1"),"GB2312");
		String displayorder = request.getParameter("displayorder");
		
		
		ConnectDB cdb = new ConnectDB();
		Connection con = cdb.getConnectionFromDS();
		try {
			Statement stmt = con.createStatement();
			if(menuid!=null&&!menuid.equals("")){
				String querySQL = "select * from sys_menu where menucode='"+menucode.trim()+"' and menuid<>"+Long.parseLong(menuid)+" and status=1";
				ResultSet rs = stmt.executeQuery(querySQL);
				if(rs.next()){
					i = 2;
				}else{
					String strSQL = "update sys_menu set menucode='"+menucode+
						"',menuname='"+menuname+"',menuurl='"+menuurl+"',displayorder="+Long.parseLong(displayorder)+
						" where menuid="+Long.parseLong(menuid);
					i = stmt.executeUpdate(strSQL);
				}
				rs.close();
			}
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(i==1){
			%>
			<script>
			alert("修改成功！");
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