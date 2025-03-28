<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Подключиться к игре по сети</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/chooseGameWithHuman.css">
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