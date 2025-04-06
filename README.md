
# Binary Trees library

***

Binary Trees lib - библиотека, реализующая базовый интерфейс для создания бинарных деревьев


## Технологии

* Kotlin 2.1.10
* JUnit 5
* Gradle Gradle 8.13
* Java 23

## Структура проекта


В основе библиотеки лежат абстрактные классы `Node` и `BinaryTree`, 
реализующие интерфейсы для узла дерева и самого дерева соответственно. 

Узлы деревьев хранят в себе данные в виде Ключ-Значение произвольного типа 
(с ограничением на сравнимость (реализацию интерфейса `Comparable`) типа Ключа)

Каждое дерево поддерживает функции:
* вставка элемента
* удаление элемента 
* поиск элемента 
* просмотр значения по ключу
* поиск предка элемента с данным ключом

Помимо этого класс `BinaryTree` содержит в себе реализацию итератора по ключам дерева
(через вложенный класс `Iterate`, реализующий интерфейс `Iterator`), а также функцию, которая возвращает 
строку, содержащую ключи дерева в обходе от корня до листьев по уровням слева на право

При этом для любого дерева в данной библиотеки должно выполняться правило: если метод данного класса возвращает ссылку 
на узел, то этот метод должен быть помечен `private`. 

Для каждого добавляемого в библиотеку дерева должны быть написаны Unit-тесты, добавленные в 
соответствующую папку проекта, а также описаны цели в файле `build.gradle.kts`

## Статус проекта

На данный момент библиотека содержит реализации следующих деревьев:
* Бинарное дерево поиска (`BSTree`/`BSNode`)
* AVL-дерево (`AVLTree`/`AVLNode`)
* Красно-черное дерево (`BRTree`/`BRNode`)

## Установка и запуск

* Чтобы скачать проект, необходимо ввести следующие команды:
```
/*via SSH*/
git clone git@github.com:spbu-coding-2024/trees-trees-team-1.git
/*via HTTPS*/
git clone https://github.com/spbu-coding-2024/trees-trees-team-1.git
```
* Чтобы собрать проект, необходимо ввести следующие команды:
```
    ./gradlew build
```
* Чтобы запустить тесты, необходимо ввести следующие команды:
```
    ./gradlew test
```
* Документация проекта доступна в папке ```docs/```: для ее просмотра откройте файл ```docs/index.html``` в браузере

## Лицензия

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)

## Авторы проекта

* [Михаил Свирюков](https://github.com/MikhailSvirukov)
* [Дарья Нечаева](https://github.com/DaryaNechaeva)
* [Анастасия Грицук](https://github.com/Nasty12121)







