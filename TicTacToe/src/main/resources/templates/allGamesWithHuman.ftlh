<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Мои игры по сети</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/allGamesWithHuman.css">
</head>
<body>
<a href="/menu">
    <button class="back">Назад</button>
</a>
<h1>Игры по сети</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Дата создания</th>
        <th>Статус</th>
    </tr>
    <#list gameStatuses as key, value>
        <tr>
            <td>
                <a href="/game/${key.id}" style="color: green; text-decoration: underline;">${key.id}</a>
            </td>
            <td style="color: black;">
                ${key.creationDate}
            </td>
            <#assign status = value>
            <#if status?is_number>
                <#if status == 10>
                    <td style="color: red;">Проигрыш</td>
                <#elseif status == -10>
                    <td style="color: green;">Выигрыш</td>
                <#elseif status == 0>
                    <td style="color: dodgerblue;">Ничья</td>
                <#else>
                    <td style="color: white;">Игра идет</td>
                </#if>
            <#else>
                Статус неизвестен
            </#if>
        </tr>
    </#list>
</table>
</body>
</html>