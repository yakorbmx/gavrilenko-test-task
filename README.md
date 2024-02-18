# Mayflower(Stripchat) test task
## Task:
Используя любой язык программирования необходимо написать следующие автотесты для сайта https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all
1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName равной 'Giovanni Rovelli' имеет Address = 'Via Ludovico il Moro 22'.
2. Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.
3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.
4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customersи проверить, что изменения записались в базу.
5. Придумать собственный автотест и реализовать (тут все ограничивается только вашей фантазией).
   Заполнить поле ввода можно с помощью js кода, используя объект window.editor.

**Требования**:
- Для реализации обязательно использовать **Selenium WebDriver**
- Тесты должны запускаться **в docker контейнере**
- Код автотестов нужно выложить в любой git-репозиторий

## What wasn't done in current version:
1. Not fixed issues with docker run of tests
2. Not fixed issues with passing the UPDATE/CREATE/DELETE tests (looks like there's a problem with browser version, as latest versions of chrome doesn't support Web SQL)
3. Not added logging
4. There are a lot of weird hacks in the code
5. Not all assertions are made 'correctly' (I'd like to do it better way, but have no time)

## Run tests
Tests might be run using 'mvn clean test' command in terminal (located in project package)
Allure report might be generated using 'mvn allure:serve' command in same terminal


## Pre-conditions (not necessary, as selenide.remote parameter commented in pom.xml):
1. Install the docker
2. Run the docker
## Steps:
1. **Clone current repository**
2. **Install Selenoid**: docker pull selenoid/chrome:latest 
3. **In project directory** run the command: **docker-compose up**


