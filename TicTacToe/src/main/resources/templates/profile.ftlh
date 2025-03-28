<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Мой профиль</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
<p class="id">ID: ${login}</p>
<p class="allGames">Всего игр: ${static.allGamesCounter}</p>
<p class="wins">Побед: ${static.winningCounter}</p>
<p class="loses">Проигрышей: ${static.lostCounter}</p>
<p class="draws">Ничьих: ${static.drawsCounter}</p>
<form action="/delete" method="post">
    <button type="submit" class="delete">Удалить профиль</button>
</form>
<form action="/exit" method="post">
    <button type="submit" class="exit">Выход</button>
</form>
<a href="/menu">
    <button class="back">Назад</button>
</a>
</body>
</html>