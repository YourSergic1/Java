<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Top players</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/scoreBoard.css">
</head>
<body>
<h1>Top players</h1>
<div class="number">
    <form method="get" action="/scoreBoard">
        <label>
            Show:
            <select name="limit" onchange="this.form.submit()" style="font-size: 18px;
                   padding: 12px 24px;
                   margin-left: 10px;
                   border: 2px solid #4CAF50;
                   border-radius: 8px;
                   background-color: #f8f8f8;
                   min-width: 150px;
                   height: 50px;
                   cursor: pointer;">

                <option value="1" <#if limit == 1>selected</#if>>1 player</option>
                <option value="5" <#if limit == 5>selected</#if>>5 players</option>
                <option value="10" <#if limit == 10>selected</#if>>10 players</option>
                <option value="15" <#if limit == 50>selected</#if>>15 players</option>
                <option value="20" <#if limit == 20>selected</#if>>20 players</option>
            </select>
        </label>
    </form>
</div>
<table>
    <tr>
        <th>Login</th>
        <th>All Games</th>
        <th>Wins</th>
        <th>Losses</th>
        <th>Draws</th>
    </tr>
    <#list statisticAndLogin as key, value>
        <tr>
            <td>
                <a>${value}</a>
            </td>
            <td>
                ${key.allGamesCounter}
            </td>
            <td>
                ${key.winningCounter}
            </td>
            <td>
                ${key.lostCounter}
            </td>
            <td>
                ${key.drawsCounter}
            </td>
        </tr>
    </#list>
</table>

<a href="/menu">
    <button class="back">Back</button>
</a>
</body>
<script>
    document.getElementById('limitSelect').addEventListener('change', function () {
        document.getElementById('limitForm').submit();
    });
</script>
</html>