<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>My ended games</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/allEndedGames.css">
</head>
<body>
<a href="/menu">
    <button class="back">Back</button>
</a>
<h1>My ended games</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Creation date</th>
        <th>Opponent</th>
        <th>Status</th>
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
            Human
            <#elseif key.gameWithHuman == false>
                Ai
            </#if>
            </td>
            <#if value == 10>
                <td style="color: red;">Loss</td>
            <#elseif value == -10>
                <td style="color: green;">Win</td>
            <#elseif value == 0>
                <td style="color: dodgerblue;">Draw</td>
            </#if>
        </tr>
    </#list>
</table>
</body>
</html>