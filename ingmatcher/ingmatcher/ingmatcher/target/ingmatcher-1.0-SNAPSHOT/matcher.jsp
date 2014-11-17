/**
 * Created by yevgeniy on 16/11/14.
 */
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ page import="com.google.appengine.api.users.User" %>
        <%@ page import="com.google.appengine.api.users.UserService" %>
            <%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
                <%@ page import="java.util.List" %>
                    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                        <html>
                            <head>
                                <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
                            </head>

                            <body>
                            %>

                                <p>Hello, please enter a ingredient word to match</p>


                                    <form action="/matcher.jsp" method="get">
                                        <div><input type="text" name="ingredientName" value="${fn:escapeXml(ingredientName)}"/></div>

                                    </form>

                                </body>
                            </html>