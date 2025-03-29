<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Fruits supply</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/chooseInfoForReport.css">
</head>
<body>
<div class="wrapper">
    <form action="/chooseInfoForReport" method="post">
        <label for="companyName">Select company</label>
        <select id="companyName" name="companyName" required>
            <option value="" disabled selected>Company</option>
            <!-- Перебираем все компании и создаем элементы option -->
            <#list companies as company>
                <option value="${company.name}">${company.name}</option>
            </#list>
        </select>
        <label for="dateStart">Choose start date</label>
        <input type="date" id="dateStart" name="dateStart" required>

        <label for="dateEnd">Choose ending date</label>
        <input type="date" id="dateEnd" name="dateEnd" required>

        <button type="submit">Next</button>
    </form>
    <a href="/home">
        <button type="submit" class="back">Back</button>
    </a>
</div>
</body>
</html>
