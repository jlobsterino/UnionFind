public class Runner {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(5, 6);

        System.out.println("Find(3): " + uf.find(3));
        System.out.println("Find(7): " + uf.find(7));

        uf.print();
    }
}
