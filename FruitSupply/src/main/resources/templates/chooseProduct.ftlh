<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Fruits supply</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/chooseProduct.css">
</head>
<body>
<div class="wrapper">
    <form action="/addDelivery/chooseProduct" method="post">
        <input type="hidden" name="deliveryID" value="${deliveryID}">
        <label for="productName">Select product</label>
        <select id="productName" name="productName" required>
            <option value="" disabled selected>Product</option>
            <!-- Перебираем все компании и создаем элементы option -->
            <#list products as product>
                <option value="${product.name}" data-id="${product.id}">${product.name}</option>
            </#list>
        </select>

        <label for="productWeight">Input product weight</label>
        <input type="number" id="productWeight" name="productWeight" placeholder="Product weight" required>
        <button type="submit" name="action" value="addMore">Add more</button>

        <!-- Кнопка для сохранения -->
        <button type="submit" name="action" value="save">Save</button>
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
