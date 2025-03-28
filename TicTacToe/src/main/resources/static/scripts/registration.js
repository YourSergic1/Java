document.getElementById('registration-form').addEventListener('submit', function (event) {
    event.preventDefault(); // Останавливаем стандартную отправку формы

    // Получаем значения из полей
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Отправка данных на сервер
    fetch('/registration', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            login: email,
            password: password
        })
    }).then(response => {
        if (!response.ok) {
            document.getElementById('error-message').style.display = 'block';
            document.getElementById('success-message').style.display = 'none';
            throw new Error('Ошибка регистрации');
        }
        return response.json();
    })
        .then(data => {
            // Сохраняем токен в localStorage
            document.cookie = `accessToken=${data.accessToken}; path=/; max-age=${15 * 60}`; // срок жизни 15 минут
            document.cookie = `refreshToken=${data.refreshToken}; path=/; max-age=${24 * 60 * 60}`; // срок жизни 1 день

            // Показываем сообщение об успехе
            document.getElementById('success-message').style.display = 'block';
            document.getElementById('error-message').style.display = 'none';

            setTimeout(() => {
                window.location.href = '/menu';
            }, 1000);
        })
});