<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Крестики нолики</title>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    body {
        font-family: 'TildaSans', Arial, sans-serif;
    }

    a {
        text-decoration: none;
    }

    .wrapper {
        position: relative;
        background-image: url("../images/bcg.jpg");
        background-size: cover; /* Фон растягивается, сохраняя пропорции */
        background-position: center; /* Центрируем фон */
        background-repeat: no-repeat; /* Фон не будет повторяться */
        height: 100vh; /* Высота блока будет 100% от высоты экрана */
    }

    .wrapper::before {
        content: ''; /* Создаем псевдоэлемент */
        position: absolute; /* Позиционируем его относительно родительского блока */
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.7); /* Черный с 30% прозрачностью */
        z-index: 1; /* Помещаем затемняющий слой поверх фона */
    }

    .name {
        font-weight: 600;
        position: absolute;
        top: 25%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 6rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
    }

    .text {
        font-weight: 300;
        position: absolute;
        top: 50%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 1.5rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
        width: 40%;
        line-height: 1.5;
    }

    .start {
        position: absolute;
        top: 79%; /* Центрируем текст по вертикали */
        left: 48%; /* Центрируем текст по горизонтали */
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

    .start:hover {
        background: white; /* Белый фон при наведении */
        color: black; /* Черный текст при наведении */
    }
</style>
<body>
<div class="wrapper">
    <h1 class="name">КРЕСТИКИ НОЛИКИ</h1>
    <h2 class="text">Это классическая стратегическая игра для двух игроков, которая сочетает в себе простоту правил и
        глубину тактического мышления. Игра ведется на поле 3x3, где каждый игрок по очереди ставит свой знак: крестик
        (X) или нолик (O). Цель игры — первым выстроить линию из трех своих знаков по горизонтали, вертикали или
        диагонали. Если все клетки поля заполнены, но ни один из игроков не смог создать линию, игра заканчивается
        ничьей.</h2>
    <form action="/" method="post">
        <button type="submit" class="start">Let's go!</button>
    </form>
</div>
</body>
</html>