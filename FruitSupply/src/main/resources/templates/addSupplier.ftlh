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
    </style>
</head>
<body>
<div class="wrapper">
    <form action="/addSupplier" method="post">
    <label for="companyName">Введите название компании</label>
    <input type="text" id="companyName" name="companyName" placeholder="Название компании">

    <label for="companyAddress">Введите адрес компании</label>
    <input type="text" id="companyAddress" name="companyAddress" placeholder="Адрес компании">

    <label for="companyPhone">Введите телефон компании</label>
    <input type="text" id="companyPhone" name="companyPhone" placeholder="Телефон компании">

    <button type="submit">Сохранить</button>
    </form>
    <a href="/home">
        <button type="submit" class="back">Назад</button>
    </a>
</div>
</body>
</html>
