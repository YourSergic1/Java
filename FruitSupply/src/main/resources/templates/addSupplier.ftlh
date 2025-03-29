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
    <form action="/addSupplier" method="post">
        <label for="companyName">Input the company name</label>
        <input type="text" id="companyName" name="companyName" placeholder="Name" required>

        <label for="companyAddress">Input the company address</label>
        <input type="text" id="companyAddress" name="companyAddress" placeholder="Address" required>

        <label for="companyPhone">Input the company phone</label>
        <input type="text" id="companyPhone" name="companyPhone" placeholder="Phone" required>

        <button type="submit">Save</button>
    </form>
    <a href="/home">
        <button type="submit" class="back">Back</button>
    </a>
</div>
</body>
</html>
