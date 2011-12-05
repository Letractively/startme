<%@ taglib uri="http://fckeditor.net/tags-fckeditor" prefix="FCK" %>
<!--
 * FCKeditor - The text editor for internet
 * Copyright (C) 2003-2005 Frederico Caldeira Knabben
 * 
 * Licensed under the terms of the GNU Lesser General Public License:
 * 		http://www.opensource.org/licenses/lgpl-license.php
 * 
 * For further information visit:
 * 		http://www.fckeditor.net/
 * 
 * File Name: sample07.jsp
 * 	FCKeditor sample file 7.
 * 
 * Version:  2.3
 * Modified: 2005-07-19 13:57:00
 * 
 * File Authors:
 * 		Simone Chiaretta (simo@users.sourceforge.net)
-->
<html>
	<head>
		<title>FCKeditor - Sample</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="robots" content="noindex, nofollow">
		<link href="../sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../fckeditor.js"></script>
	</head>
	<body>
		<h1>FCKeditor - JSP - Sample 7</h1>
		In this sample the user can edit the complete page contents and header (from 
		&lt;HTML&gt; to &lt;/HTML&gt;).
		<hr>
		<%
		String basepath = request.getContextPath()+"/fckeditor/"; 
		String configpath = basepath+"_samples/jsp/sample06.config.js";
		%>
		<form action="sampleposteddata.jsp" method="post" target="_blank">
			<FCK:editor id="EditorDefault" basePath="<%=basepath%>"
				fullPage="true"
				imageBrowserURL="/websale/fckeditor/editor/filemanager/browser/default/browser.html?Type=Image&Connector=connectors/jsp/connector"
				linkBrowserURL="/websale/fckeditor/editor/filemanager/browser/default/browser.html?Connector=connectors/jsp/connector"
				flashBrowserURL="/websale/fckeditor/editor/filemanager/browser/default/browser.html?Type=Flash&Connector=connectors/jsp/connector"
				imageUploadURL="/websale/fckeditor/editor/filemanager/upload/simpleuploader?Type=Image"
				linkUploadURL="/websale/fckeditor/editor/filemanager/upload/simpleuploader?Type=File"
				flashUploadURL="/websale/fckeditor/editor/filemanager/upload/simpleuploader?Type=Flash">
				<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html><head><title>Full Page Test</title><meta content="text/html; charset=utf-8" http-equiv="Content-Type"/></head><body>This is some <strong>sample text</strong>. You are using <a href="http://www.fckeditor.net/">FCKeditor</a>.</body></html>
			</FCK:editor>
			<br>
			<input type="submit" value="Submit">
		</form>
	</body>
</html>
