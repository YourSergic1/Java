    <!DOCTYPE html>
    <html lang="rus,en">
    <head>
        <meta charset="UTF-8">
        <title>Fruits supply</title>
        <style>
            /* Центрирование и стилизация */
                body {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh; /* Занимает всю высоту экрана */
                    margin: 0;
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f4; /* Фоновый цвет */
                }

                .wrapper {
                    text-align: center;
                    background-color: white;
                    padding: 20px;
                    border-radius: 10px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Тень для блока */
                }

                label {
                    display: block;
                    font-size: 1.5em; /* Увеличение размера текста */
                    margin: 15px 0; /* Отступы между элементами */
                }

                input {
                    font-size: 1em; /* Размер текста в поле ввода */
                    padding: 10px;
                    margin: 10px 0; /* Отступы между полями ввода */
                    width: 80%; /* Ширина поля ввода */
                    border: 1px solid #ccc;
                    border-radius: 5px;
                }

                button {
                    font-size: 1.2em; /* Увеличение размера текста кнопок */
                    padding: 10px 20px; /* Внутренние отступы кнопок */
                    margin: 10px 0; /* Отступы между кнопками */
                    border: none;
                    border-radius: 5px;
                    background-color: #007BFF; /* Цвет кнопок */
                    color: white;
                    cursor: pointer;
                    transition: background-color 0.3s ease; /* Плавное изменение цвета */
                }

                button:hover {
                    background-color: #0056b3; /* Цвет кнопок при наведении */
                }
            option {
                font-size: 1em; /* Размер текста в option */
                padding: 10px; /* Внутренние отступы */
                background-color: white; /* Фоновый цвет */
                color: #333; /* Цвет текста */
            }
            select {
                font-size: 1em; /* Размер текста в выпадающем списке */
                padding: 10px;
                margin: 10px 0; /* Отступы между элементами */
                width: 85%; /* Ширина выпадающего списка */
                border: 1px solid #ccc;
                border-radius: 5px;
                background-color: white; /* Фоновый цвет */
                appearance: none; /* Убирает стандартный стиль браузера */
                -webkit-appearance: none; /* Для Safari */
                -moz-appearance: none; /* Для Firefox */
                cursor: pointer;
            }

            /* Стили для option при наведении */
            option:hover {
                background-color: #007BFF; /* Цвет фона при наведении */
                color: white; /* Цвет текста при наведении */
            }
        </style>
    </head>
    <body>
    <div class="wrapper">
        <form action="/addDelivery/chooseProduct" method="post">
            <input type="hidden" name="deliveryID" value="${deliveryID}">
            <label for="productName">Выберите продукт</label>
            <select id="productName" name="productName" >
                <option value="" disabled selected>Выберите продукт</option>
                <!-- Перебираем все компании и создаем элементы option -->
                <#list products as product>
                    <option value="${product.name}" data-id="${product.id}">${product.name}</option>
                </#list>
            </select>

            <label for="productWeight">Введите вес продукта</label>
            <input type="number" id="productWeight" name="productWeight" placeholder="Вес продукта">
            <button type="submit" name="action" value="addMore">Добавить еще</button>

            <!-- Кнопка для сохранения -->
            <button type="submit" name="action" value="save">Сохранить</button>
        </form>
        <script>
            // Добавляем обработчик события для формы
            document.querySelector("form").addEventListener("submit", function (e) {
                var selectedOption = document.querySelector("#productName").selectedOptions[0];
                var productId = selectedOption.getAttribute("data-id"); // Получаем ID продукта из data-id

                // Создаем скрытое поле для передачи productId
                var hiddenInput = document.createElement("input");
                hiddenInput.type = "hidden";
                hiddenInput.name = "productId";  // Название параметра
                hiddenInput.value = productId;   // Значение ID продукта
                this.appendChild(hiddenInput);   // Добавляем скрытое поле в форму
            });
        </script>
    </div>
    </body>
    </html>
