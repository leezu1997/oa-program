<%@ page import="com.bipowernode.oa.bean.Dept" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门列表页面</title>
    <script type="text/javascript">
        function del(dno){
            if(window.confirm("确定要删除吗？")){
                document.location.href = "<%=request.getContextPath() %>/dept/delete?deptno="+dno;
            }
        }
    </script>
    <style>
        span{
        float:right
        }
    </style>
</head>
<body>
<h3>欢迎<%=session.getAttribute("username")%>访问！</h3>


    <h1 align="center">部门列表</h1>
    <hr>
    <table border="1px" align="center" width="50%">
        <tr>
            <th>序号</th>
            <th>部门编号</th>
            <th>部门名称</th>
            <th>操作</th>

        </tr>


        <%
            List<Dept> deptList = (List<Dept>)request.getAttribute("deptList");
            int i = 0;
            for (Dept dept : deptList) {
//                System.out.println(dept.getDname());
        %>

        <tr align="center">
            <td><%=++i%></td>
            <td><%=dept.getDeptno()%></td>
            <td><%=dept.getDname()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除</a>
                <a href="<%=request.getContextPath() %>/dept/detail?f=m&dno=<%=dept.getDeptno()%>">修改</a>
                <a href="<%=request.getContextPath() %>/dept/detail?f=d&dno=<%=dept.getDeptno()%>">详情</a>
            </td>
        </tr>

        <%
            }
        %>

    </table>
    <hr>
    <a href="<%=request.getContextPath() %>/old/add.jsp">新增部门</a>
<span>
      <a href="<%=request.getContextPath()%>/user/exit">[退出系统]</a>
</span>

</body>
</html>