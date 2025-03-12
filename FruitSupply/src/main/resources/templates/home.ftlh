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
    </style>
</head>
<body>
<div class="wrapper">
    <label>Добавить новую поставку</label>
    <a href="/addDelivery/chooseCompany">
        <button type="submit" >Добавить поставку</button>
    </a>
    <label>Создать отчет за период</label>
    <a href="/report">
        <button type="submit" >Создать отчет</button>
    </a>
    <label>Добавить поставщика</label>
    <a href="/addSupplier">
        <button type="submit">Добавить поставщика</button>
    </a>
    <label>Добавить продукт</label>
    <a href="/addProduct">
        <button type="submit">Добавить продукт</button>
    </a>
</div>
</body>
</html>