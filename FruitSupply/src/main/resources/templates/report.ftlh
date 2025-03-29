<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Fruits supply</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/report.css">
</head>
<body>
<div class="wrapper">
    <label for="productWeight">Report</label>
    <label for="companyName">${res.companyName}</label>
    <label for="dateEnd">${res.startDate}</label>
    <label for="dateEnd">${res.endDate}</label>
    <table border="1">
        <thead>
        <tr>
            <th>Product</th>
            <th>Weight</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <#list res.products as name, info>  <!-- Итерируем Map -->
            <tr>
                <td>${name}</td>  <!-- Пример получения данных из Product -->
                <td>${info[0]}</td>  <!-- Пример получения данных из DeliveryProduct -->
                <td>${info[1]}</td>  <!-- Пример получения данных из DeliveryProduct -->
            </tr>
        </#list>
        </tbody>
    </table>
    <a href="/home">
        <button type="submit" class="back">Back</button>
    </a>
</div>
</body>
</html>
