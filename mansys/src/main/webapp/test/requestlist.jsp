<%@ page language="java" contentType="text/html; charset=GB2312" pageEncoding="GB2312"%>
<p>Session ID:<%=session.getId()%></p>
<%
System.out.println(session.isNew());
session.getId();
out.println("Protocol(Э�飩: " + request.getProtocol() + "<br>"); 
out.println("Scheme����ʽ��: " + request.getScheme() + "<br>"); 
out.println("Server Name�����������ƣ�: " + request.getServerName() + "<br>" ); 
out.println("Server Port���������˿ڣ�: " + request.getServerPort() + "<br>"); 
//out.println("Server Info����������Ϣ��: " + this.getServletConfig().getServletContext().getServerInfo() + "<br>"); 
out.println("Remote Addr��Զ�̵�ַ��: " + request.getRemoteAddr() + "<br>"); 
out.println("Remote Host��Զ��������: " + request.getRemoteHost() + "<br>"); 
out.println("Character Encoding���ַ����룩: " + request.getCharacterEncoding() + "<br>"); 
out.println("Content Length�����ݳ��ȣ�: " + request.getContentLength() + "<br>"); 
out.println("Content Type���������ͣ�: "+ request.getContentType() + "<br>"); 
out.println("Auth Type��������ͣ�: " + request.getAuthType() + "<br>"); 
out.println("HTTP Method�����󷽷���: " + request.getMethod() + "<br>"); 
out.println("Path Info��·����Ϣ��: " + request.getPathInfo() + "<br>"); 
out.println("Path Trans��·��ת����: " + request.getPathTranslated() + "<br>"); 
out.println("Query String����ѯ�ִ���: " + request.getQueryString() + "<br>"); 
out.println("Remote User��Զ���û���: " + request.getRemoteUser() + "<br>"); 
out.println("Session Id���ỰID��: " + request.getRequestedSessionId() + "<br>"); 
out.println("Request URI������·����: " + request.getRequestURI() + "<br>"); 
out.println("Servlet Path��Servlet·����: " + request.getServletPath() + "<br>"); 
out.println("Accept������ͷ��Ϣ��: " + request.getHeader("Accept") + "<br>"); 
out.println("Host������ͷ��Ϣ��: " + request.getHeader("Host") + "<br>"); 
out.println("Referer ������ͷ��Ϣ��: " + request.getHeader("Referer") + "<br>"); 
out.println("Accept-Language ����������ͷ��Ϣ��: " + request.getHeader("Accept-Language") + "<br>"); 
out.println("Accept-Encoding �����ձ���ͷ��Ϣ��: " + request.getHeader("Accept-Encoding") + "<br>"); 
out.println("User-Agent ���û�����ͷ��Ϣ��: " + request.getHeader("User-Agent") + "<br>"); 
out.println("Connection ������ͷ��Ϣ��: " + request.getHeader("Connection") + "<br>"); 
out.println("Cookie ��Cookieͷ��Ϣ��: " + request.getHeader("Cookie") + "<br>"); 
out.println("Created (����ʱ�䣩: " + new java.util.Date(session.getCreationTime()).toString() + "<br>"); 
out.println("LastAccessed ��������ʱ�䣩: " + new java.util.Date(session.getLastAccessedTime()) + "<br>"); 
%>