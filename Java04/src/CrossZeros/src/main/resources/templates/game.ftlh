<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Игра с компьютером</title>

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

    .text1 {
        font-weight: 300;
        position: absolute;
        top: 5%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 1.5rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
        width: 40%;
        line-height: 1.5;
    }

    .text2 {
        font-weight: 300;
        position: absolute;
        top: 10%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 1.5rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
        width: 40%;
        line-height: 1.5;
    }

    .text3 {
        font-weight: 300;
        position: absolute;
        top: 90%; /* Центрируем текст по вертикали */
        left: 50%; /* Центрируем текст по горизонтали */
        transform: translate(-50%, -50%); /* Сдвигаем на 50% по обоим осям для точного центрирования */
        text-align: center;
        color: white;
        font-size: 1.5rem; /* Размер шрифта (по желанию) */
        z-index: 2; /* Текст будет поверх затемняющего слоя */
        width: 40%;
        line-height: 1.5;
    }

    table {
        border-collapse: collapse;
        width: 600px; /* Увеличиваем размер таблицы */
        height: 600px;
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
</style>
<script>
    function updateGameBoard() {
        fetch(window.location.href)
            .then(response => response.text())
            .then(html => {
                let tempDiv = document.createElement("div");
                tempDiv.innerHTML = html;

                // Обновляем игровую таблицу
                document.querySelector("table.game").innerHTML =
                    tempDiv.querySelector("table.game").innerHTML;

                // Обновляем блок информации о ходе
                document.querySelector(".turnInfo").innerHTML =
                    tempDiv.querySelector(".turnInfo").innerHTML;
            })
            .catch(error => console.error("Ошибка обновления:", error));
    }

    // Запускаем обновление сразу
    updateGameBoard();
    // Повторяем обновление каждую секунду
    setInterval(updateGameBoard, 2000);
    // Запускаем обновление сразу
    updateGameBoard();
    // Повторяем обновление каждую секунду
    setInterval(updateGameBoard, 2000);
</script>

<body>
<div class="turnInfo">
    <#if inGame >
        <#if human>
            <#if waitingForEnemy=false>
                <#if isPlayerTurn>
                    <h2 class="text1" style="color: mediumblue">Ваш ход.</h2>
                    <#if playerFigure==1>
                        <h2 class="text2" style="color: mediumblue">Вы ходите крестиком.</h2>
                    <#else>
                        <h2 class="text2" style="color: mediumblue">Вы ходите ноликом.</h2>
                    </#if>
                <#else>
                    <h2 class="text1" style="color: mediumblue">Ход противника.</h2>
                </#if>
            <#else>
                <h2 class="text1" style="color: mediumblue">Ожидание противника.</h2>
            </#if>
        </#if>
    </#if>
</div>
<form action="/game/${gameId}" method="post">
    <table class="game">
        <tr>
            <td data-cell>
                <button type="submit" value="0" name="num"
                        style="background: none; border: none; padding: 0; width: 100%; height:100%;">
                    <#if gameBoard[0][0] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[0][0] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="1" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[0][1] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[0][1] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="2" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[0][2] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[0][2] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
        </tr>
        <tr>
            <td data-cell>
                <button type="submit" value="3" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[1][0] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[1][0] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="4" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[1][1] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[1][1] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="5" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[1][2] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[1][2] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
        </tr>
        <tr>
            <td data-cell>
                <button type="submit" value="6" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[2][0] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[2][0] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="7" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[2][1] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[2][1] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
            <td data-cell>
                <button type="submit" value="8" name="num"
                        style="background: none; border: none; padding: 0;width: 100%; height:100%;">
                    <#if gameBoard[2][2] == 1>
                        <img src="/images/x.png" alt="Крестик" style="width: 70px;">
                    <#elseif gameBoard[2][2] == 0>
                        <img src="/images/o.png" alt="Нолик" style="width: 70px;">
                    </#if>
                </button>
            </td>
        </tr>
        <#if inGame == false>
            <#if isWin == 1>
                <h2 class="text3" style="color: green">Вы выиграли!</h2>
            <#elseif isWin == -1>
                <h2 class="text3" style="color: red">Вы проиграли!</h2>
            <#elseif isWin == 0>
                <h2 class="text3" style="color: grey">Ничья!</h2>
            </#if>
        </#if>
    </table>
</form>
<a href="/menu">
    <button class="back">Меню</button>
</a>
</body>
</html>