<!-- this is login page where the user can enter their password and name  -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="loginPgStyle.css">
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        
        <!--this will call (loginServlet)  -->
        
        <form action="loginServlet" method="post">     
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="name" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <div class="register-link">
            <a href="register.html" class="btn">Register</a>
        </div>
    </div>
</body>
</html>

</html>

