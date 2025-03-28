<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/auth.css">
</head>
<body>
<form id="login-form">
    <label class="emailLabel" for="email">Email</label>
    <input class="emailInput" type="email" id="email" name="email" placeholder="example@mail.com" required/>
    <label class="passLabel" for="password">Пароль</label>
    <input class="passInput" type="password" id="password" name="password" placeholder="Введите пароль" required/>
    <button type="submit" id="login-btn" class="authentication">Войти</button>
    <div id="error-message" class="alert alert-danger" style="display: none; color: red; font-size: 2rem;">Ошибка
        входа
    </div>
</form>

<a href="/">
    <button type="submit" class="back">Назад</button>
</a>

</body>
    <script src="scripts/auth.js"></script>
</html>