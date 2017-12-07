Назовем связностью графа минимальное количество вершин, которые необходимо удалить для того чтобы граф стал несвязным. Также определим степень вершины как количество ребер выходящих из этой вершины. 

### Рассмотрим следующую задачу:
Постройте граф из *n* вершин со связностью *k* (*2 <= k <= n - 2*) в котором содержится наименьшее возможное количество ребер. 

Очевидно, что связность графа не может превышать минимальную степень вершины. Почему? Потому что мы можем удалить из графа все вершины соседствующие с этой вершиной и получить вершину от которой нельзя добраться до любой другой в графе.
Однако стоит заметить, что связность графа может быть меньше минимальной степени вершины.

Давайте рассмотрим искомый граф при k = 2. Граф представляет собой простой цикл. Для того чтобы граф стал несвязным необходимо удалить две несмежные вершины. Тогда в цикле получится две компоненты связности в форме цепочек. А что если мы построим такой граф, что каждая вершина принадлежит x циклам? Тогда связность графа будет 2 * x. Соответственно, если мы умеем строить такой граф мы можем получить ответ для всех четных k. Строить такой граф можно постепенно добавляя циклы с разным шагом.
```
for (int step = 1; step <= k / 2; step++) {
    memset(used, false, sizeof(used));
    for (int i = 1; i <= n; i++) {
        if (used[i]) continue;
        int j = i;
        while (!used[j]) {
            used[j] = true;
            int prev = j;
            j += it;
            if (j > n) j -= n;
            add_rib(prev, j);
        }
    }
}
```
Теперь давайте для нечетного k построим ответ для (k - 1). Очевидно что для того чтобы увеличить связность графа нужно добавить как минимум одно ребро к каждой вершине графа. Давайте для всех вершин *x <= (n + 1) / 2* соединим их ребром с вершиной *x + (n / 2)*. Таким образом, каждая вершина соединена  хотя бы одним ребром из цикла с шагом n / 2 и соответствующее ребро необходимо будет удалить в оптимальной стратегии получения несвязного графа. 
```
if (k % 2 == 1) {
    for (int i = 1; i <= (n + 1) / 2; i++) {
        add_rib(i, i + (n / 2));
    }
}
```

Одно из свойств полученного графа в том, что в нем содержится минимальное количество ребер для построения графа с минимальной степенью вершины k. То есть, данный алгоритм можно использовать и для построения графа у которого минимальная степень вершины k с использованием наименьшего количества ребер.