<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Authorization</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/auth.css">
</head>
<body>
<form id="login-form">
    <label class="emailLabel" for="email">Email</label>
    <input class="emailInput" type="email" id="email" name="email" placeholder="example@mail.com" required/>
    <label class="passLabel" for="password">Password</label>
    <input class="passInput" type="password" id="password" name="password" placeholder="Enter your password" required/>
    <button type="submit" id="login-btn" class="authentication">Login</button>
    <div id="error-message" class="alert alert-danger" style="display: none; color: red; font-size: 2rem;">Login error</div>
</form>

<a href="/">
    <button type="submit" class="back">Back</button>
</a>

</body>
    <script src="scripts/auth.js"></script>
</html>