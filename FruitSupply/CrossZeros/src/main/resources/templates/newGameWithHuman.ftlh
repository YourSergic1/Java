<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Выбор сторон</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    a {
        text-decoration: none;
    }

    html, body {
        font-family: 'TildaSans', Arial, sans-serif;
        background-color: rgb(74, 58, 0);
        height: 100%;
        display: flex;
        justify-content: center; /* Горизонтальное центрирование */
        align-items: center; /* Вертикальное центрирование */
        margin: 0;
    }

    table {
        border-collapse: collapse;
        width: 300px; /* Увеличиваем размер таблицы */
        height: 200px;
    }

    td {
        border: 2px solid white; /* Белые границы */
        width: 100px;
        height: 100px;
        text-align: center;
    }

    .back {
        position: absolute;
        top: 90%; /* Центрируем текст по вертикали */
        left: 10%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        z-index: 2;
        color: white; /* Белый текст */
        border: 2px solid white; /* Белая обводка */
        background: transparent; /* Прозрачный фон */
        padding: 10px 20px; /* Отступы */
        font-size: 2rem; /* Размер текста */
        cursor: pointer; /* Курсор в виде указателя */
        transition: 0.3s; /* Плавный переход */
    }

    .back:hover {
        background: white; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }

    .text {
        font-weight: 300;
        position: absolute;
        top: 35%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 1.5rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
        width: 40%;
        line-height: 1.5;
    }

</style>
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