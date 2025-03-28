<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/registration.css">
</head>
<body>
<form id="registration-form">
    <label class="emailLabel" for="email">Email</label>
    <input class="emailInput" type="email" id="email" name="email" placeholder="example@mail.com" required>
    <label class="passLabel" for="password">Password</label>
    <input class="passInput" type="password" id="password" name="password" placeholder="Enter your password" required>
    <button type="submit" id="register-btn" class="registration">Register</button>
</form>
<div id="error-message" class="alert alert-danger" style="display: none; color: red; font-size: 2rem;">
    Registration error. Username is already taken.
</div>
<div id="success-message" class="alert alert-success" style="display: none; color: green; font-size: 2rem;">
    Registration completed successfully!
</div>
<a href="/">
    <button type="submit" class="back">Back</button>
</a>
</body>
<script src="scripts/registration.js"></script>
</html>