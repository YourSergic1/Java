<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>My profile</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>
<p class="id">ID: ${login}</p>
<p class="allGames">All games: ${static.allGamesCounter}</p>
<p class="wins">Wins: ${static.winningCounter}</p>
<p class="loses">Losses: ${static.lostCounter}</p>
<p class="draws">Draws: ${static.drawsCounter}</p>
<form action="/delete" method="post">
    <button type="submit" class="delete">Delete profile</button>
</form>
<form action="/exit" method="post">
    <button type="submit" class="exit">Exit</button>
</form>
<a href="/menu">
    <button class="back">Back</button>
</a>
</body>
</html>