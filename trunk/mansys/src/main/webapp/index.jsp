<html>
	<head>
		<title>Example :: Spring Application</title>
	</head>
	<body>
		<h1>
			Example - Spring Application
		</h1>
		<p>
			This is my test.
		</p>
	</body>
</html>
<%@ include file="/WEB-INF/views/include.jsp" %>
<%-- Redirected because we can't set the welcome page to a virtual URL. --%>
<c:redirect url="/hello.htm"/>
