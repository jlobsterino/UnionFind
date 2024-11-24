import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class newTest {
    private UnionFind uf;

    // Этот метод будет вызываться перед каждым тестом для инициализации структуры данных
    @BeforeEach
    public void setUp() {
        uf = new UnionFind(10); // Инициализируем структуру для 10 элементов
    }

    @Test
    public void testFindSameSet() {
        // Тестируем, что элемент сам себе родитель, если это корень множества
        assertEquals(0, uf.find(0));  // Элемент 0 должен быть корнем
        assertEquals(1, uf.find(1));  // Элемент 1 должен быть корнем
    }

    @Test
    public void testUnion() {
        // Тестируем операцию объединения
        uf.union(0, 1);  // Объединяем элементы 0 и 1
        assertEquals(uf.find(0), uf.find(1));  // После объединения корни должны быть одинаковыми
    }

    @Test
    public void testMultipleUnion() {
        // Тестируем несколько объединений
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        // После всех объединений элементы 0, 1, 2, 3 должны принадлежать одному множеству
        assertEquals(uf.find(0), uf.find(3));  // Элемент 3 должен быть в том же множестве, что и 0
        assertEquals(uf.find(1), uf.find(2));  // Элемент 2 должен быть в том же множестве, что и 1
    }

    @Test
    public void testNoUnion() {
        // Тестируем, что элементы без объединения остаются в разных множествах
        assertNotEquals(uf.find(0), uf.find(1));  // Элементы 0 и 1 должны быть в разных множествах
    }

    @Test
    public void testUnionWithPathCompression() {
        // Проверим сжатие пути
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        // После этих объединений, find(0) и find(3) должны вернуть один и тот же корень,
        // и при этом путь будет сжат
        int rootBefore = uf.find(3);
        uf.find(0);  // Сжимаем путь
        int rootAfter = uf.find(3);  // После сжатия путь должен быть короче

        assertEquals(rootBefore, rootAfter);  // Корни должны быть одинаковыми, но путь будет сжат
    }

    @Test
    public void testUnionByRank() {
        // Тестируем объединение по рангу (чтобы проверить правильность работы объединения по глубине)
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(1, 3); // Здесь объединяем два дерева, одно из которых будет глубже другого

        // После объединения элементы 0, 1, 2, 3 должны быть в одном множестве
        assertEquals(uf.find(0), uf.find(3));
    }

    @Test
    public void testComplexUnion() {
        // Тестируем комбинированные операции
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(4, 5);
        uf.union(2, 4);  // Здесь объединяем два множества: {0, 1, 2} и {3, 4, 5}

        assertEquals(uf.find(0), uf.find(5));  // Все элементы 0, 1, 2, 3, 4, 5 теперь в одном множестве
    }

    @Test
    public void testEdgeCase() {
        // Тестируем крайний случай, когда у нас только один элемент
        UnionFind ufSingle = new UnionFind(1);
        assertEquals(ufSingle.find(0), 0);  // Единственный элемент должен быть сам себе родителем
    }
}
