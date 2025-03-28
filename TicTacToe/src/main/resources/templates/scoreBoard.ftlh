<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Список лидеров</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/scoreBoard.css">
</head>
<body>
<h1>Список лидеров</h1>
<div class="number">
    <form method="get" action="/scoreBoard">
        <label>
            Показать:
            <select name="limit" onchange="this.form.submit()" style="font-size: 18px;
                   padding: 12px 24px;
                   margin-left: 10px;
                   border: 2px solid #4CAF50;
                   border-radius: 8px;
                   background-color: #f8f8f8;
                   min-width: 150px;
                   height: 50px;
                   cursor: pointer;">

                <option value="1" <#if limit == 1>selected</#if>>1 игрок</option>
                <option value="5" <#if limit == 5>selected</#if>>5 игроков</option>
                <option value="10" <#if limit == 10>selected</#if>>10 игроков</option>
                <option value="15" <#if limit == 50>selected</#if>>15 игроков</option>
                <option value="20" <#if limit == 20>selected</#if>>20 игроков</option>
            </select>
        </label>
    </form>
</div>
<table>
    <tr>
        <th>Логин</th>
        <th>Всего игр</th>
        <th>Побед</th>
        <th>Проигрышей</th>
        <th>Ничьих</th>
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
    <button class="back">Назад</button>
</a>
</body>
<script>
    document.getElementById('limitSelect').addEventListener('change', function () {
        document.getElementById('limitForm').submit();
    });
</script>
</html>