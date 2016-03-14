<%@page import="entities.Student"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="entities.University"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%--@page errorPage="error.jsp"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>University Project</title>
    </head>
    <body>
        <%--<%= Integer.parseInt(request.getParameter("gotoerror")) %>--%>
        <%--<jsp:include page="date_fmt.jsp" />--%>
        <h1>University Project</h1>
        <!--action: request URL-->
        <form action="main_servlet" method="GET">
            <select name="format">
                <option>text</option>
                <option>html</option>
                <option>xml</option>
                <option>json</option>
                <option>sql</option>
                <option selected>jsp</option>
            </select>
            <br>
            <table border=0>
                <tr>
                    First Name:  
                <input type="text" name="firstname" value="" placeholder="Enter First Name" /> 
                </tr>
                <tr>
                    <td>Last Name: </td>
                    <td><input type="text" name="lastname" value="" placeholder="Enter Last Name"/></td> 
                </tr>
                <tr>
                    <td>Age: </td>
                    <td><input type="text" name="age" value="" placeholder="Enter Age"/> </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /> </td>
                </tr>
                <table/>
        </form>

        <p>University Name: <h1><c:out value="${university.name}"/></h1>
        <c:out value="${university.address.city}"/><br>
        <c:out value="${university.testscore}"/><br>
        <c:out value="${university.listofstudents}"/><br>           
    </p>

    <%
        if (request.getAttribute("university") != null) {
            University university = (University) request.getAttribute("university");%>
    <h1>University Address:</h1>
    <%=university.getAddress()%>
    <% }  %>

    <%
        if (request.getAttribute("university") != null) {
            University university = (University) request.getAttribute("university");
            out.println(university.getAddress().getCity());
            out.println(university.getTestscore());
        }
    %>

    <!--import sql page to work with a database-->
    <%--<jsp:include page="sql.jsp" />--%>
    
    <% 
        if (request.getAttribute("university") != null) {
            University university = (University) request.getAttribute("university");
       %>  
       <h1>University Address: <%= university.getAddress()%></h1>
       
       <table border="1">
           <% Map listOfStudents = university.getListofstudents();
           Iterator<Student> iterator = listOfStudents.values().iterator();
           while(iterator.hasNext()){
               Student student = iterator.next();
               out.println("<tr><td>"+
               student.getFirstname() + 
                       "</td><td>" +
               student.getLastname() +
                       "</td><td>" +
               student.getAge() +
                       "</td></tr>"
               );
           }
           %>
       </table>
       
       <% }%>
    
    <c:if test="${not empty xml_out}" >
    <h1>XML Output</h1>
    <x:parse xml="${xml_out}" var="data" />
            
            <x:out select="$data/university/name" /> <br>
            <x:out select="$data/university/address/city" /> <br>

            <x:out select="$data/university/listofstudents/entry[1]/value/firstname" /> <br>
            <x:out select="$data/university/listofstudents/entry[1]/value/address/city" /> <br>

     </c:if>       
</body>
</html>
