<!DOCTYPE html>
<html lang="rus,en">
<head>
    <meta charset="UTF-8">
    <title>My games vs Ai</title>
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/css/allGamesWithComputer.css">
</head>
<body>
<a href="/menu">
    <button class="back">Back</button>
</a>
<h1>My games vs Ai</h1>

<table>
    <tr>
        <th>Game ID</th>
        <th>Creation date</th>
        <th>Status</th>
    </tr>
    <#list gameStatuses as key, value>
        <tr>
            <td>
                <a href="/game/${key.id}" style="color: green; text-decoration: underline;">${key.id}</a>
            </td>
            <td style="color: black;">
                ${key.creationDate}
            </td>
            <#assign status = value>
            <#if status?is_number>
                <#if status == 10>
                    <td style="color: red;">Loss</td>
                <#elseif status == -10>
                    <td style="color: green;">Win</td>
                <#elseif status == 0>
                    <td style="color: dodgerblue;">Draw</td>
                <#else>
                    <td style="color: white;">Game in progress</td>
                </#if>
            <#else>
                Unknown status
            </#if>
        </tr>
    </#list>
</table>
</body>
</html>