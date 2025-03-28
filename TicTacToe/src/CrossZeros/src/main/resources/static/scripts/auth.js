document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Предотвращаем стандартную отправку формы

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

// Отправляем запрос на сервер для получения токена
    fetch('/auth', {
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
            throw new Error('Неверный логин или пароль.');
        }
        return response.json();
    })
        .then(data => {
            document.cookie = `accessToken=${data.accessToken}; path=/; max-age=${15 * 60}`; // срок жизни 15 минут
            document.cookie = `refreshToken=${data.refreshToken}; path=/; max-age=${24 * 60 * 60}`; // срок жизни 1 день
            window.location.href = '/menu';
        })
});