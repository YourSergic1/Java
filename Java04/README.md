# Project 03 — Java_Bootcamp  

Резюме: в этом проекте ты научишься создавать веб-приложение на языке **Java** с использованием Spring. 

💡 [Нажми сюда](https://new.oprosso.net/p/4cb31ec3f47a4596bc758ea1861fb624), **чтобы поделиться с нами обратной связью на этот проект**. Это анонимно и поможет нашей команде сделать обучение лучше. Рекомендуем заполнить опрос сразу после выполнения проекта.

## Содержание
 1. [Chapter I](#chapter-i)   
     - [Инструкция](#инструкция)   
 2. [Chapter II](#chapter-ii)  
     - [Общая информация](#общая-информация)  
 3. [Chapter III](#chapter-iii)      
     - [Задание 0. Создание проекта](#задание-0-создание-проекта)  
     - [Задание 1. Создать структуру проекта](#задание-1-cоздать-структуру-проекта)  
     - [Задание 2. Реализация domain-слоя](#задание-2-реализация-domain-слоя)  
     - [Задание 3. Реализация datasource-слоя](#задание-3-реализация-datasource-слоя)  
     - [Задание 4. Реализация web-слоя](#задание-4-реализация-web-слоя)  
     - [Задание 5. Реализация di-слоя](#задание-5-реализация-di-слоя) 



## Chapter I
## Инструкция

1. На протяжении всего курса тебя будет сопровождать чувство неопределенности и острого дефицита информации — это нормально. Не забывай, что информация в репозитории и Google всегда с тобой. Как и пиры, и Rocket.Chat. Общайся. Ищи. Опирайся на здравый смысл. Не бойся ошибиться.
2. Будь внимателен к источникам информации. Проверяй. Думай. Анализируй. Сравнивай. 
3. Внимательно читай задания. Перечитай несколько раз. 
4. Читать примеры тоже лучше внимательно. В них может быть что-то, что не указано в явном виде в самом задании.
5. Тебе могут встретиться несоответствия, когда что-то новое в условиях задачи или примере противоречит уже известному. Если встретилось такое — попробуй разобраться. Если не получилось — запиши вопрос в открытые вопросы и выясни в процессе работы. Не оставляй открытые вопросы неразрешенными. 
6. Если задание кажется непонятным или невыполнимым — так только кажется. Попробуй его декомпозировать. Скорее всего, отдельные части станут понятными. 
7. На пути тебе встретятся самые разные задания. Те, что помечены звездочкой (\*) — подходят для более дотошных. Они повышенной сложности и необязательны к выполнению. Но если ты их сделаешь, то получишь дополнительный опыт и знания.
8. Не пытайся обмануть систему и окружающих. В первую очередь ты обманешь себя.
9. Есть вопрос? Спроси соседа справа. Если это не помогло — соседа слева.
10. Когда пользуешься помощью — всегда разбирайся до конца: почему, как и зачем. Иначе помощь не будет иметь смысла.
11. Всегда делай push только в ветку develop! Ветка master будет проигнорирована. Работай в директории src.
12. В твоей директории не должно быть иных файлов, кроме тех, что обозначены в заданиях.

## Chapter II
## Общая информация

**Веб-приложение** – это клиент-серверное приложение, в котором клиент взаимодействует с веб-сервером при помощи браузера. Логика веб-приложения распределена между сервером и клиентом, хранение данных осуществляется преимущественно на сервере, обмен информацией происходит по сети.

**Spring** — один из самых популярных фреймворков для создания веб-приложений на Java. Преимущества **Spring**:

- Инверсия управления (IoC) и внедрение зависимостей (Dependency Injection, DI): Spring предоставляет механизмы IoC и DI, которые позволяют управлять зависимостями между компонентами приложения. Благодаря этим механизмам код становится более модульным, уменьшается связанность и улучшается тестируемость приложения.

- Модульность: Spring состоит из различных модулей, таких как Spring Core, Spring MVC, Spring Security, Spring Data и другие. Они позволяют разработчикам выбирать только необходимое для их приложений, что способствует легкости и гибкости разработки.

- Поддержка аспектно-ориентированного программирования (Aspect-Oriented Programming, AOP): Spring предоставляет возможности для работы с аспектами, что позволяет выносить кросс-режимные функции (например, логирование, транзакции) из основной логики приложения, улучшая его модульность и управляемость.

- Поддержка транзакций: Spring обеспечивает поддержку декларативного управления транзакциями, что позволяет управлять транзакциями без прямых вызовов API уровня доступа к данным.

- Удобство тестирования: Благодаря DI и IoC, приложения, построенные с использованием Spring, легче тестируются, поскольку зависимости могут быть легко заменены на фиктивные объекты (mock objects) для упрощения модульного тестирования.

- Поддержка различных типов приложений: Spring предоставляет возможности для разработки широкого спектра приложений, включая веб-приложения (Spring MVC), RESTful API (Spring WebFlux), микросервисы, обработку данных, интеграцию с базами данных и многое другое.

### Темы для изучения:
- Веб-приложение;
- Spring;
- API;
- Алгоритм «Минимакс»;
- MVC.

## Chapter III
### Проект: Крестики-Нолики
Рассматриваются основы построения веб-приложений и использование фреймворка Spring.
Проект создаётся один раз и используется для всех последующих заданий.

## Задание 0. Создание проекта
В IntelliJ Idea создай новый проект: 
- Выбери язык Java.
- Выбери систему сборки Gradle.
- Выбери JDK 18, если такого нет, то загрузи любой JDK 18 версии.
- Выбери для Gradle DSL — Kotlin.

## Задание 1. Создание структуры проекта
- Каждый слой является отдельным пакетом.
- Структура проекта должна иметь следующие слои: web, domain, datasource, di.
- Слой web должен включать в себя как минимум пакеты model, controller, mapper для взаимодействия с клиентом.
- Слой domain должен включать в себя как минимум пакеты model, service для реализации бизнес-логики приложения.
- Слой datasource должен включать в себя как минимум пакеты model, repository, mapper для реализации работы с данными (например, с базой данных).
- В слое di описываются конфигурации для внедрения зависимостей.

## Задание 2. Реализация domain-слоя
- Опиши модель игрового поля в виде целочисленной матрицы.
- Опиши модель текущей игры, у которой есть UUID и игровое поле.
- Опиши интерфейс сервиса, у которого есть методы:
    - метод получения следующего хода текущей игры алгоритмом Минимакс;
    - метод валидации игрового поля текущей игры (проверь, что не изменены предыдущие ходы);
    - метод проверки окончания игры.
- Модели, интерфейсы, реализации должны находиться в разных файлах.

## Задание 3. Реализация datasource-слоя
- Реализуй класс-хранилище для хранения текущих игр.
- В качестве средства хранения использовать потокобезопасных коллекций.
- Опиши модели игрового поля, текущей игры.
- Реализуй мапперы domain<->datasource.
- Реализуй репозиторий для работы с классом-хранилищем, у которого есть методы:
    - метод сохранения текущей игры;
    - метод получения текущей игры.
- Создай класс, реализующий интерфейс сервиса и принимающий в качестве параметра репозиторий для работы с классом-хранилищем.
- Модели, интерфейсы, реализации должны находиться в разных файлах.

## Задание 4. Реализация web-слоя
- Опиши модели игрового поля, текущей игры.
- Реализуй мапперы domain<->web.
- Реализуй контроллер с использованием Spring, имеющий метод POST /game/{UUID текущей игры}, который отправляет текущую игру с обновленным игровым полем пользователем и получает в ответ текущую игру с обновленным игровым полем компьютером.
- Если отправлена некорректная текущая игра с обновленным полем, должна вернуться ошибка с описанием.
- Должна быть поддержка нескольких игр одновременно.
- Модели, интерфейсы, реализации должны находиться в разных файлах.

## Задание 5. Реализация di-слоя
- Реализуй класс Spring Configuration, в котором описывается граф зависимостей. 
- Он должен содержать как минимум: 
   - Класс-хранилище в качестве singleton.
   - Репозиторий для работы с классом-хранилищем.
   - Сервис для работы с репозиторием.
