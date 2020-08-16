<%--
  Created by IntelliJ IDEA.
  User: 13275
  Date: 2020/4/28
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>员工列表</title>
   <%
       pageContext.setAttribute("APP_PATH",request.getContextPath());
   %>

    <%--
        不以/开始的相对路径，找资源，以当前资源的路径为标准，容易出错
        以/开始的相对路径找资源是以服务器的路径为标准（http：//localhost：8080）需要加上项目名
                如：http://localhost:8080/SsmCrud
    --%>
    <!-- 引入juuery -->
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery-2.0.0.min.js"></script>
    <!-- 引入bootstrap样式 -->
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
            <%--标题栏--%>
        <div class="row">
            <div class="col-md-12">
                <h1>SSM-CRUD</h1>
            </div>
        </div>
            <%--按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn btn-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
                <%--表格--%>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <tr>
                        <td>#</td>
                        <td>empName</td>
                        <td>gender</td>
                        <td>email</td>
                        <td>deptName</td>
                        <td>操作</td>
                    </tr>
                    <c:forEach items="${pageInfo.list}" var="emp">
                        <tr>
                            <td>${emp.empId}</td>
                            <td>${emp.empName}</td>
                            <td>${emp.gender=="M"?"男":"女"}</td>
                            <td>${emp.email}</td>
                            <td>${emp.department.deptName}</td>
                            <td>
                                <button class="btn btn-primary btn-sm">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    编辑
                                </button>
                                <button class="btn btn-danger btn-sm">
                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                    删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
                <%--分页信息--%>
        <div class="row">
            <%--分页信息--%>
                <div class="col-md-6">
                    当前${pageInfo.pageNum} 页,总${pageInfo.pages} 页,共${pageInfo.total} 条
                </div>
            <%--分页条--%>
                <div class="col-md-6">
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                            <li><a href="${APP_PATH}/emps?pn=1">首页</a></li>
                            <c:if test="${pageInfo.hasPreviousPage}">
                            <li>
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            </c:if>
                            <c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
                                <c:if test="${page_Num == pageInfo.pageNum}">
                                    <li class="active"><a href="#">${page_Num}</a></li>
                                </c:if>
                                <c:if test="${page_Num != pageInfo.pageNum}">
                                    <li><a href="${APP_PATH}/emps?pn=${page_Num}">${page_Num}</a></li>
                                </c:if>

                            </c:forEach>
                            <c:if test="${pageInfo.hasNextPage}">
                            <li>
                                <a href="${APP_PATH}/emps?pn=${pageInfo.pageNum+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                            </c:if>
                            <li><a href="${APP_PATH}/emps?pn=${pageInfo.pages}">尾页</a></li>
                        </ul>
                    </nav>
                </div>
        </div>
    </div>
</body>
</html>
