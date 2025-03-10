<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>

<style>

    * {
        margin: 0;
        padding: 0;
    }

    html, body {
        font-family: 'TildaSans', Arial, sans-serif;
        background-color: rgb(74, 58, 0);
        height: 100%;
        display: flex;
        justify-content: center; /* Горизонтальное центрирование */
        align-items: center; /* Вертикальное центрирование */
        margin: 0;
    }

    .registration {
        position: absolute;
        top: 57%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

    .back {
        position: absolute;
        top: 90%; /* Центрируем текст по вертикали */
        left: 10%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }


    button {
        text-align: center;
        color: white; /* Белый текст */
        border: 2px solid white; /* Белая обводка */
        background: transparent; /* Прозрачный фон */
        padding: 10px 20px; /* Отступы */
        font-size: 2rem; /* Размер текста */
        cursor: pointer; /* Курсор в виде указателя */
        transition: 0.3s; /* Плавный переход */
    }

    button:hover {
        background: white; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }

    label {
        font-size: 2rem; /* Размер текста */
        color: white; /* Белый текст */
    }

    input {
        font-size: 2rem; /* Размер текста */
        color: black; /* Белый текст */
    }

    .passLabel {
        position: absolute;
        top: 40%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

    .passInput {
        position: absolute;
        top: 45%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

    .emailLabel {
        position: absolute;
        top: 30%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

    .emailInput {
        position: absolute;
        top: 35%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

</style>

<body>
<form action="/registration" method="post">
    <label class="emailLabel" for="email">Email</label>
    <input class="emailInput" type="email" id="email" name="login" placeholder="example@mail.com"
           value="${user.login!''}">
    <label class="passLabel" for="password">Пароль</label>
    <input class="passInput" type="password" id="password" name="password" placeholder="Введите пароль"
           value="${user.password!''}">

    <#if registrationError == true>
        <label class="registrationError" for="password" style="color: red">Ошибка регистрации</label>
    </#if>
    <#if registrationSuccess == true>
        <label class="registrationSuccess" for="password" style="color: forestgreen">Успешная регистрация</label>
    </#if>

    <button type="submit" class="registration">Зарегистрироваться</button>
</form>
<a href="/">
    <button type="submit" class="back">Назад</button>
</a>
</body>
</html>