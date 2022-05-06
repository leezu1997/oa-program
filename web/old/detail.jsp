<%@ page import="com.bipowernode.oa.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<%
    Dept dept = (Dept) request.getAttribute("dept");
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门详情</title>

</head>
<body>
<h1 align="center">部门详情</h1>
<hr>

<table border="1px" align="center" width="50%">
    <tr>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>部门位置</th>
    </tr>
    <tr align="center">
        <td><%=dept.getDeptno()%></td>
        <td><%=dept.getDname()%></td>
        <td><%=dept.getLoc()%></td>
    </tr>

</table>
<br>
<div align="center">
    <input type="button" value="后退到主页" onclick="window.history.back()" />
</div>

</body>
</html>