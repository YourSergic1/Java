<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>Fruits supply</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/addProduct.css">
</head>
<body>
<div class="wrapper">
    <form action="/addProduct" method="post">
        <label for="companyName">Select company</label>
        <select id="companyName" name="companyName" required>
            <option value="" disabled selected>Company</option>
            <#list companies as company>
                <option value="${company.name}">${company.name}</option>
            </#list>
        </select>


        <label for="productType">Input the type of the product</label>
        <input type="text" id="productType" name="productType" placeholder="Type" required>

        <label for="productName">Input the name of the product</label>
        <input type="text" id="productName" name="productName" placeholder="Name" required>

        <label for="productPrice">Input the price of the product</label>
        <input type="number" id="productPrice" name="productPrice" placeholder="Price" required>

        <button type="submit">Save</button>
    </form>
    <a href="/home">
        <button type="submit" class="back">Back</button>
    </a>
</div>
</body>
</html>
