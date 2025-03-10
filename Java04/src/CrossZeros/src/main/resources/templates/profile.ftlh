<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Мой профиль</title>
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

    button {
        text-align: center;
        color: white; /* Белый текст */
        border: 2px solid white; /* Белая обводка */
        background: transparent; /* Прозрачный фон */
        padding: 10px 20px; /* Отступы */
        font-size: 2rem; /* Размер текста */
        cursor: pointer; /* Курсор в виде указателя */
        transition: 0.3s; /* Плавный переход */
        position: absolute;
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
    }

    .delete:hover {
        background: red; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }

    .exit {
        top: 50%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .exit:hover, .back:hover {
        background: white; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }

    .delete {
        top: 40%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .back {
        top: 60%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

</style>
<body>
<form action="/delete" method="post">
    <button type="submit" class="delete">Удалить профиль</button>
</form>
<form action="/logout" method="post">
    <button type="submit" class="exit">Выход</button>
</form>
<a href="/menu">
    <button class="back">Назад</button>
</a>
</body>
</html>