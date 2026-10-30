<p align="center">
  Проект по автоматизации тестирования сайта <br>
  <b><a href="https://12storeez.com/">12STOREEZ</a></b>
</p>

<p align="center">
  <a href="https://12storeez.com/" title="12 STOREEZ — официальный сайт">
    <img src="images/logo/HeaderLogotype.png" alt="12 STOREEZ" width="100" height="100" style="border-radius: 50%;"/>
  </a>
</p>

> **12 STOREEZ** — российский fashion-бренд одежды и аксессуаров для женщин и мужчин.  
> Современный гардероб с акцентом на качество, чистый силуэт и повседневный стиль.  
> Официальный интернет-магазин: [12storeez.com](https://12storeez.com/).

____

## Содержание

* <a href="#tools">Технологии и инструменты</a>
* <a href="#cases">Тест-план автоматизированных тест-кейсов</a>
* <a href="#cases">Тест-план ручных тест-кейсов (ТестОпс)</a>
* <a href="#console">Запуск тестов из терминала</a>
* <a href="#jenkins">Сборка в Jenkins</a>
* <a href="#allure">Allure-отчёт</a>
* <a href="#allure-testops">Интеграция с Allure ТестОпс</a>
* <a href="#jira">Интеграция с Jira</a>
* <a href="#video">Видео прохождения тестов</a>
* <a href="#telegram">Уведомления в Telegram</a>
____

<a id="tools"></a>
## Технологии и инструменты

<p align="center">
  <a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50" title="IntelliJ IDEA — IDE для разработки автотестов" alt="IntelliJ IDEA"/></a>
  <a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50" title="Java — язык автотестов" alt="Java"/></a>
  <a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50" title="Gradle — система сборки проекта" alt="Gradle"/></a>
  <a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50" title="JUnit 5 — фреймворк запуска тестов" alt="JUnit 5"/></a>
  <a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50" title="Selenide — UI-фреймворк поверх Selenium" alt="Selenide"/></a>
  <a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50" title="Selenoid — удалённый запуск браузеров в Docker" alt="Selenoid"/></a>
  <a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure_Report.svg" width="50" height="50" title="Allure Report — отчёты по прогонам" alt="Allure Report"/></a>
  <a href="https://qameta.io/"><img src="images/logo/AllureTestOps.svg" width="50" height="50" title="Allure ТестОпс — хранение и аналитика тест-кейсов" alt="Allure ТестОпс"/></a>
  <a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50" title="Jenkins — CI/CD и удалённый запуск тестов" alt="Jenkins"/></a>
  <a href="https://www.atlassian.com/software/jira/"><img src="images/logo/Jira.svg" width="50" height="50" title="Jira — задачи и связь с тест-кейсами" alt="Jira"/></a>
  <a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50" title="GitHub — хранение исходного кода" alt="GitHub"/></a>
  <a href="https://web.telegram.org/"><img src="images/logo/Telegram.svg" width="50" height="50" title="Telegram — уведомления о результатах сборки" alt="Telegram"/></a>
</p>

- Автотесты написаны на **Java** с использованием **Selenide** и **JUnit 5**.
- Сборка проекта — **Gradle**.
- Удалённый запуск браузеров — **Selenoid** (VNC + video).
- CI — **Jenkins** с формированием **Allure Report**.
- Результаты и тест-кейсы — в **Allure ТестОпс**, интеграция с **Jira**.
- Уведомления о прогоне — в **Telegram** через бота.

Содержание Allure-отчёта:
* шаги теста;
* скриншот на последнем шаге;
* Page Source;
* логи браузерной консоли;
* видео выполнения теста (Selenoid).

____

<a id="cases"></a>
## Тест-план автоматизированных тест-кейсов
 
- Переход на экран «Установите приложение» и проверка формы экрана "Установите приложение";
- Поиск по слову: в карточках есть соответствующие результаты;
- Поиск через предложенные тэги: в карточках есть соответствующие результаты;
- Добавление товара в корзину и проверка добавленного товара в корзине;
- Удаление товара из корзины;
- Пустая корзина;
- Добавление в вишлист для неавторизованного пользователя (pop-up "Вход и регистрация");
- Левое меню: проверка списка ссылок коллекции с вкладки «Женщинам»;
- Левое меню: проверка списка ссылок коллекции с вкладки «Мужчинам».

## Тест-план ручных тест-кейсов (ТестОпс)
- "Оплатить заказ" (СБП), корректно заполнены все поля на форме;
- Валидация незаполненных полей по кнопке "Оплатить заказ";
- Валидация корректности заполнения полей;
- Отображение формы оплаты товара - пустая корзина.
____

<a id="console"></a>
## Запуск тестов из терминала

### Локальный запуск

```bash
gradlew clean test
```

### Удалённый запуск

***Удалённый запуск через Jenkins:***
```bash  
clean X5Group_test
"-Dbrowser=${browser}"
"-DbrowserVersion=${browserVersion}"
"-DbrowserSize=${browserSize}"
"-DbaseUrl=${baseUrl}"
"-Dremote=${remoteUrl}"
"-Dheadless=${HEADLESS}"
```
### Параметры запуска

| Параметр | Описание |
|---|---|
| `remote` | URL удалённого WebDriver / Selenoid |
| `browser` | Браузер |
| `browserVersion` | Версия браузера |
| `browserSize` | Размер окна |
| `baseUrl` | Базовый URL приложения |
| `headless` | Headless-режим |

____

<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/> Сборка в Jenkins


<p align="center">
  <a href="https://jenkins.qa.guru/view/java-students/job/ukolovaA-12STOREEZ/">
    <img title="Jenkins — страница сборки проекта" src="images/screenshots/jenkins_build.png" alt="Jenkins build" width="950"/>
  </a>
</p>

____

<a id="allure"></a>
## <img alt="Allure Report" height="25" src="images/logo/Allure_Report.svg" width="25"/> Allure-отчёт

### Overview

<p align="center">
  <img title="Allure Report — Overview Dashboard" src="images/screenshots/allure_overview.png" alt="Allure Overview" width="950"/>
</p>

### Результаты выполнения теста

<p align="center">
  <img title="Allure Report — результат выполнения теста" src="images/screenshots/allure_test_result.png" alt="Allure test result" width=" 950"/>
</p>

____

<a id="allure-testops"></a>
## <img alt="Allure ТестОпс" height="25" src="images/logo/AllureTestOps.svg" width="25"/> Интеграция с Allure ТестОпс

Выполнена интеграция сборки Jenkins с **Allure ТестОпс**.  

### Запуски

<p align="center">
  <img title="Allure ТестОпс — Dashboard со статистикой прогонов" src="images/screenshots/testops_test_runs.png" alt="Allure ТестОпс Dashboard" width="950"/>
</p>

### Тест-кейсы в ТестОпс

<p align="center">
  <img title="Allure ТестОпс — авто- и ручные тест-кейсы" src="images/screenshots/testops_cases.png" alt="Allure ТестОпс Cases" width="950"/>
</p>

____

<a id="jira"></a>
## <img alt="Jira" height="25" src="images/logo/Jira.svg" width="25"/> Интеграция с Jira

Реализована интеграция **Allure ТестОпс** с **Jira**: в тикете отображаются связанные тест-кейсы и результаты прогонов.

### Запуски
<p align="center">
  <img title="Jira — тест-кейсы и результаты прогона в задаче" src="images/screenshots/jira_runs.png" alt="Jira integration" width="950"/>
</p>

### Тест-кейсы
<p align="center">
  <img title="Jira — тест-кейсы и результаты прогона в задаче" src="images/screenshots/jira_tests.png" alt="Jira integration" width="950"/>
</p>

____
<a id="video"></a>
## <img alt="Selenoid" height="25" src="images/logo/Selenoid.svg" width="25"/> Видео прохождения тестов в Selenoid

К каждому тесту в отчёте прилагается видео прогона.

Кейс: "Удаление товара из корзины"
<p align="center">
  <img title="Selenoid — пример видео прохождения автотеста" src="images/screenshots/selenoid_video.gif" alt="Selenoid video" width="850" height="550"/>
</p>

____

<a id="telegram"></a>
## <img alt="Telegram" height="25" src="images/logo/Telegram.svg" width="25"/> Уведомления в Telegram

После завершения сборки бот в Telegram отправляет сообщение с результатом прогона.

<p align="center">
  <img title="Telegram — уведомление о результате сборки" src="images/screenshots/telegram_notification.png" alt="Telegram notification" width="550"/>
</p>

____



