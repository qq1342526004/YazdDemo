<%
/**
 * Yazd Setup Tool
 */
%>

<%@ page import="java.io.*, java.util.*, java.lang.reflect.* "%>
<html>
<head>
	<title>Yazd Setup</title>
		<link rel="stylesheet" href="style/global.css">
</head>

<body bgcolor="#FFFFFF" text="#000000" link="#0000FF" vlink="#800080" alink="#FF0000">
<img src="images/setup.gif" width="210" height="38" alt="Yazd Setup" border="0">

<hr size="0"><p>

<b>Welcome to Yazd Setup</b>
<p>

We've detected that your system needs to be setup for Yazd. This tool will
guide you through making a connection to your database and setting up an administrator
account. After completing setup, you'll be able to use the admin tool to create
forums and start using Yazd.
<p>
If you encounter problems with the setup process, please report them to 
<a href="mailto:yazd@Yasna.com">yazd@Yasna.com</a>.
If you'd like to return here at some point in the future after
completing the intial setup, delete the 
"setup=true" field from your yazd.properties file.
<p>
<b>Setup Checklist</b>
<ul>
<table border=0>
		
	<tr><td valign=top><img src="images/check.gif" width="13" height="13"></td><td>
		Setup has detected that you are running <%= application.getServerInfo() %>.
	</td></tr>
	
	<%
		//See if they have Java2 or later installed by trying to load java.util.HashMap.
		boolean isJava2 = true;
		try {
			Class.forName("java.util.HashMap");
		}
		catch (ClassNotFoundException cnfe) {
			isJava2 = false;
		}
		if (isJava2) {
	%>	
	<tr><td valign=top><img src="images/check.gif" width="13" height="13"></td><td>
		You are running Java 2 or later.
	</td></tr>
	<%
		}
		else {
	%>
	<tr><td valign=top><img src="images/x.gif" width="13" height="13"></td><td>
		<font color="red">
		You do not appear to be running Java 2 or later. Therefore, setup cannot continue. 
		If possible, upgrade your version of Java and restart this process.
		</font>
	</td></tr>	
	<%
		}
	%>
	
	<%
		//See if the Yazd classes are installed
		boolean yazdInstalled = true;
		try {
			Class.forName("com.Yasna.forum.Forum");
		}
		catch (ClassNotFoundException cnfe) {
			yazdInstalled = false;
		}
		if (yazdInstalled) {
	%>
	<tr><td valign=top><img src="images/check.gif" width="13" height="13"></td><td>
		The Yazd application files are installed.
	</td></tr>
	<%
		}
		else {
	%>
	<tr><td valign=top><img src="images/x.gif" width="13" height="13"></td><td>
		<font color="red">
		The Yazd application files could not be loaded. Follow the installation documentation
		instructions and ensure that the class files are in the classpath of your
		application server.
		</font>
	</td></tr>	
	<%
		}
	%>
		
	<%
		//See if the Lucene classes are installed
		boolean luceneInstalled = true;
		try {
			Class.forName("org.apache.lucene.document.Document");
		}
		catch (ClassNotFoundException cnfe) {
			luceneInstalled = false;
		}
		if (luceneInstalled) {
	%>
	<tr><td valign=top><img src="images/check.gif" width="13" height="13"></td><td>
		The Lucene application files are installed.
	</td></tr>
	<%
		}
		else {
	%>
	<tr><td valign=top><img src="images/x.gif" width="13" height="13"></td><td>
		<font color="red">
		The Lucene application files could not be loaded. Follow the installation 
		documentation instructions and ensure that the class files are in the classpath 
		of your	application server.
		</font>
	</td></tr>	
	<%
		}
	%>
		
</table>
</ul>

<% 
	if (!yazdInstalled || !isJava2) {
%>
	<font color="red"><b>One or more errors occured in the initial setup checklist. Please correct 
	them, restart your application server, and then reload this page.</b></font>
<%
	}
	else {
%>
</font>

<form action="setup2.jsp" method=post>

<center>
<input type="submit" value="Continue &gt;">
</center>

<%
	}
%>

<p>
<hr size="0">
<center><font size="-1"><i>www.forumsoftware.ca</i></font></center>
</font>
</body>
</html>

