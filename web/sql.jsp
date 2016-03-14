<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

<!--        CREATE TABLE IF NOT EXISTS students (
        first_name VARCHAR(30),
        last_name VARCHAR(30),
        age INT      
        ) engine=InnoDB DEFAULT CHARSET=utf8;-->

        <form action="">
            <input type="text" name="firstName" value="" /><br>
            <input type="text" name="lastName" value="" /><br>
            <input type="text" name="age" value="" /><br>
            <input type="submit" value="Add" />
        </form>

        <sql:setDataSource var="db_source" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/universitydb"
                           user="root"  password="rostov01"/>

        <c:choose>
            <c:when test="${not empty param.firstName
                            && not empty param.lastName
                            && not empty param.age}">       
                <sql:update dataSource="${db_source}" var="newStudent">
                    INSERT INTO students(first_name, last_name, age) VALUES (?, ?, ?)  
                    <sql:param value="${param.firstName}" />
                    <sql:param value="${param.lastName}" />
                    <sql:param value="${param.age}" />
                </sql:update>
            </c:when>
            <c:otherwise>
                <font color="#cc0000">Please enter mandatory information.</font>
            </c:otherwise>
        </c:choose>

        <br/><br/>
        <sql:query dataSource="${db_source}" var="students">
            SELECT * from students;
        </sql:query>
        <table border="1">
            <c:forEach var="row" items="${students.rows}">
                <tr>
                    <td><c:out value="${row.first_name}" /></td>
                    <td><c:out value="${row.last_name}" /></td>
                    <td><c:out value="${row.age}" /></td>
                </tr>
            </c:forEach>

    </body>
</html>
