<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Fruits supply</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/authentication.css">
</head>
<body>
<div class="wrapper">
    <form action="/authentication" method="post">
        <label class="emailLabel" for="email">Email</label>
        <input class="emailInput" id="email" name="username"/>
        <label class="passLabel" for="password">Password</label>
        <input class="passInput" type="password" id="password" name="password"/>
        <#if loginError == true>
            <label class="loginError" for="password" style="color: red">Login error</label>
        </#if>
        <button type="submit" class="authentication">Login</button>
    </form>

    <a href="/home">
        <button type="submit" class="back">Back</button>
    </a>
</div>
</body>
</html>
