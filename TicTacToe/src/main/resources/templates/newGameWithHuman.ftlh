<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Выбор сторон</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/newGameWithHuman.css">
</head>

<body>
<table class="sideChoose">
    <h2 class="text">Выбери сторону. Крестики начинают первыми.</h2>
    <form action="/startGameWithHuman" method="post">
        <tr>
            <td data-cell>
                <button type="submit" value=1 name="num"
                        style="background: none; border: none; padding: 0; width: 100%; height:100%;">
                    <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                </button>
            </td>
            <td data-cell>
                <button type="submit" value=0 name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                </button>
            </td>
        </tr>
    </form>
</table>
<a href="/menu">
    <button class="back">Назад</button>
</a>
</body>
</html>