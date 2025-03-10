<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Меню</title>
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

    button:hover {
        background: white; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }

    .newGameWithComputer {
        top: 10%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .newGameWithHuman {
        top: 20%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .chooseGameWithHuman {
        top: 30%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .allGamesWithComputer {
        top: 40%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .allGamesWithHuman {
        top: 50%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }

    .profile {
        top: 60%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
    }


</style>

<body>
<a href="/newGameWithComputer">
    <button class="newGameWithComputer">Играть с компьютером</button>
</a>
<a href="/newGameWithHuman">
    <button class="newGameWithHuman">Создать игру по сети</button>
</a>
<a href="/chooseGameWithHuman">
    <button class="chooseGameWithHuman">Подключиться к игре по сети</button>
</a>
<a href="/allGamesWithComputer">
    <button class="allGamesWithComputer">Мои игры с компьютером</button>
</a>
<a href="/allGamesWithHuman">
    <button class="allGamesWithHuman">Мои игры по сети</button>
</a>
<a href="/profile">
    <button class="profile">Мой профиль</button>
</a>
</body>
</html>