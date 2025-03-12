<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Подключиться к игре по сети</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        html, body {
            font-family: 'TildaSans', Arial, sans-serif;
            background-color: rgb(74, 58, 0);
        }

        h1 {
            font-weight: 300;
            position: absolute;
            top: 5%; /* Центрируем текст по вертикали */
            left: 50%; /* Центрируем текст по горизонтали */
            transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
            text-align: center;
            color: white;
            font-size: 2rem; /* Размер шрифта (по желанию) */
            z-index: 2; /* Текст будет поверх затемняющего слоя */
            width: 40%;
            line-height: 1.5;
        }

        a {
            text-decoration: none;
        }

        table {
            position: absolute;
            top: 100px; /* Фиксированный отступ сверху в пикселях */
            left: 50%; /* Центрируем по горизонтали */
            transform: translateX(-50%); /* Сдвигаем на 50% по горизонтали для центрирования */
            width: 80%;
            border-collapse: collapse; /* Убирает двойные границы между ячейками */
            color: white;
            font-size: 1.5rem; /* Размер шрифта (по желанию) */
            z-index: 2; /* Текст будет поверх затемняющего слоя */
            line-height: 1.5;
        }

        th, td {
            border: 1px solid white; /* Устанавливает белую границу */
            padding: 8px; /* Отступы внутри ячеек */
            text-align: center; /* Выравнивание текста по центру */
        }

        .start {

            background: transparent;
            border: none;
            color: green;
            font-size: 1.5rem;
            cursor: pointer;
            padding: 0;
            text-decoration: underline;
        }

        .back {
            position: absolute;
            top: 5%; /* Центрируем текст по вертикали */
            left: 5%; /* Центрируем текст по горизонтали */
            transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
            text-align: center;
            z-index: 2;
            color: white; /* Белый текст */
            border: 2px solid white; /* Белая обводка */
            background: transparent; /* Прозрачный фон */
            padding: 10px 20px; /* Отступы */
            font-size: 2rem; /* Размер текста */
            cursor: pointer; /* Курсор в виде указателя */
            transition: 0.3s; /* Плавный переход */
        }

    </style>
</head>

<body>
<a href="/menu">
    <button class="back">Назад</button>
</a>
<h1>Выберите игру</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Enemy login</th>
        <th>Figure</th>
        <th>Подключиться</th>
    </tr>
    <#list allGamesWithLogin as game, login>
        <tr>
            <td style="color: black;">
                ${game.id}
            </td>
            <td style="color: deepskyblue;">
                ${login}
            </td>
            <#if game.user2Figure == 1>
                <td style="color: red;">X</td>
            <#else>
                <td style="color: red;">O</td>
            </#if>
            <td>
                <form action="/connectToTheGame" method="post">
                    <button type="submit" class="start" value=${game.id} name="gameId">Войти</button>
                </form>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>