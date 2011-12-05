<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<p>Session ID:<%=session.getId()%></p>
<%
System.out.println(session.isNew());
session.getId();
out.println("Protocol(协议）: " + request.getProtocol() + "<br>"); 
out.println("Scheme（方式）: " + request.getScheme() + "<br>"); 
out.println("Server Name（服务器名称）: " + request.getServerName() + "<br>" ); 
out.println("Server Port（服务器端口）: " + request.getServerPort() + "<br>"); 
//out.println("Server Info（服务器信息）: " + this.getServletConfig().getServletContext().getServerInfo() + "<br>"); 
out.println("Remote Addr（远程地址）: " + request.getRemoteAddr() + "<br>"); 
out.println("Remote Host（远程主机）: " + request.getRemoteHost() + "<br>"); 
out.println("Character Encoding（字符编码）: " + request.getCharacterEncoding() + "<br>"); 
out.println("Content Length（内容长度）: " + request.getContentLength() + "<br>"); 
out.println("Content Type（内容类型）: "+ request.getContentType() + "<br>"); 
out.println("Auth Type（审查类型）: " + request.getAuthType() + "<br>"); 
out.println("HTTP Method（请求方法）: " + request.getMethod() + "<br>"); 
out.println("Path Info（路径信息）: " + request.getPathInfo() + "<br>"); 
out.println("Path Trans（路径转换）: " + request.getPathTranslated() + "<br>"); 
out.println("Query String（查询字串）: " + request.getQueryString() + "<br>"); 
out.println("Remote User（远程用户）: " + request.getRemoteUser() + "<br>"); 
out.println("Session Id（会话ID）: " + request.getRequestedSessionId() + "<br>"); 
out.println("Request URI（请求路径）: " + request.getRequestURI() + "<br>"); 
out.println("Servlet Path（Servlet路径）: " + request.getServletPath() + "<br>"); 
out.println("Accept（接收头信息）: " + request.getHeader("Accept") + "<br>"); 
out.println("Host（主机头信息）: " + request.getHeader("Host") + "<br>"); 
out.println("Referer （引用头信息）: " + request.getHeader("Referer") + "<br>"); 
out.println("Accept-Language （接收语言头信息）: " + request.getHeader("Accept-Language") + "<br>"); 
out.println("Accept-Encoding （接收编码头信息）: " + request.getHeader("Accept-Encoding") + "<br>"); 
out.println("User-Agent （用户代理头信息）: " + request.getHeader("User-Agent") + "<br>"); 
out.println("Connection （连接头信息）: " + request.getHeader("Connection") + "<br>"); 
out.println("Cookie （Cookie头信息）: " + request.getHeader("Cookie") + "<br>"); 
out.println("Created (创建时间）: " + new java.util.Date(session.getCreationTime()).toString() + "<br>"); 
out.println("LastAccessed （最后访问时间）: " + new java.util.Date(session.getLastAccessedTime()) + "<br>"); 
%>