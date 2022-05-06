
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门列表页面</title>

    <style>
        span{
        float:right
        }
    </style>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
</head>
<body>
<h6>当前在线人数${onlinecount}人</h6>
<h3>欢迎${user.username}访问！</h3>
<script type="text/javascript">
    function del(dno){
        if(window.confirm("确定要删除吗？")){
            document.location.href = "${pageContext.request.contextPath}/dept/delete?deptno="+dno;
        }
    }
</script>

    <h1 align="center">部门列表</h1>
    <hr>
    <table border="1px" align="center" width="50%">
        <tr>
            <th>序号</th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>操作</th>

        </tr>



        <c:forEach items="${deptList}" varStatus="deptStatus" var="dept">
            <tr align="center">
                <td>${deptStatus.count}</td>
                <td>${dept.deptno}</td>
                <td>${dept.dname}</td>
                <td>
                    <a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
                    <a href="dept/detail?f=m&dno=${dept.deptno}">修改</a>
                    <a href="dept/detail?f=d&dno=${dept.deptno}">详情</a>
                </td>
            </tr>
        </c:forEach>





    </table>
    <hr>
    <a href="add.jsp">新增部门</a>
<span>
      <a href="user/exit">[退出系统]</a>
</span>

</body>
</html>