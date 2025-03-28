<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Join online game</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/chooseGameWithHuman.css">
</head>

<body>
<a href="/menu">
    <button class="back">Back</button>
</a>
<h1>Choose game</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Enemy login</th>
        <th>Figure</th>
        <th>Join</th>
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
                    <button type="submit" class="start" value=${game.id} name="gameId">Join</button>
                </form>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>