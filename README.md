# Bugchain
---------------------------------
## Описание
Bugchain - платформа для реализации программы Bug Bounty на основе User-friendly смарт-контракта в Блокчеин.
## Алгоритм работы
### Для клиента
1. Подготовить тестовый сервер
2. Разместить задание
3. Получить результат - факт наличия уязвимости и эксплоит (при необходимости)
4. ????????
5. PROFIT!
### Для баг-хантера
1. Выбрать задание
2. Ввести номер своего кошелька
3. Достать "флаг"
4. Ввести флаг
5. Получить вознаграждение
### Что в это время делает наша инфраструктура
1. Веб-сервер принимает данные задания из интерфейса и вставляет их в смарт-контракт, который с этого момента действителен
2. Принимает от баг-хантера "флаг", вызывает сравнение найденного "флага" с реальным, лежащим в смарт-контракте в скрытом виде
3. В случае, если результат сравнения положителен ("флаг" верен), баг-хантеру переводится 2/3 от суммы вознаграждения
4. На нашем сервере производится тестовый запуск эксплоита (если есть). Результатом его выполнения должен быть тот же флаг, и если это так, то баг-хантеру переводится остаток (1/3) вознаграждения
