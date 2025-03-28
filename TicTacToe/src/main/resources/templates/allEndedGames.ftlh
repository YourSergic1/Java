<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Завершенные игры</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/allEndedGames.css">
</head>
<body>
<a href="/menu">
    <button class="back">Назад</button>
</a>
<h1>Завершенные игры</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Дата создания</th>
        <th>С кем игра</th>
        <th>Статус</th>
    </tr>
    <#list statuses as key, value>
        <tr>
            <td>
                <a href="/game/${key.id}" style="color: green; text-decoration: underline;">${key.id}</a>
            </td>
            <td style="color: black;">
                ${key.creationDate}
            </td>
            <td style="color: black;">
                <#if key.gameWithHuman == true>
            С человеком
            <#elseif key.gameWithHuman == false>
                С компьютером
            </#if>
            </td>
            <#if value == 10>
                <td style="color: red;">Проигрыш</td>
            <#elseif value == -10>
                <td style="color: green;">Выигрыш</td>
            <#elseif value == 0>
                <td style="color: dodgerblue;">Ничья</td>
            </#if>
        </tr>
    </#list>
</table>
</body>
</html>