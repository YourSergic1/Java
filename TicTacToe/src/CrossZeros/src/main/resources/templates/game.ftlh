<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/game.css">
</head>
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
                    <h2 class="text1" style="color: mediumblue">Your move!</h2>
                    <#if playerFigure==1>
                        <h2 class="text2" style="color: mediumblue">You are X.</h2>
                    <#else>
                        <h2 class="text2" style="color: mediumblue">You are O.</h2>
                    </#if>
                <#else>
                    <h2 class="text1" style="color: mediumblue">Opponent's turn...</h2>
                </#if>
            <#else>
                <h2 class="text1" style="color: mediumblue">Waiting for opponent...</h2>
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
                <h2 class="text3" style="color: green">You Win!</h2>
            <#elseif isWin == -1>
                <h2 class="text3" style="color: red">You Lose!</h2>
            <#elseif isWin == 0>
                <h2 class="text3" style="color: grey">Draw!</h2>
            </#if>
        </#if>
    </table>
</form>
<a href="/menu">
    <button class="back">Back to menu</button>
</a>
</body>
</html>