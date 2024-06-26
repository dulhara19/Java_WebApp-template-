
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="registerPgStyle.css">
    </head>
    <body>
        <form name="registerform" action="daoServlet" method="post">
            <table>
                <tr><td>Name :</td><td><input type="text" name="name"</td></tr>
                <tr><td>Password :</td><td><input type="password" name="password"</td></tr>
                <tr><td></td><td><input type="submit" value="submit"</td></tr>
            </table>
        </form>
    </body>
</html>
