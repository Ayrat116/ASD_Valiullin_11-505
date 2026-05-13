package Semestrovka_2;

class BinomialTreeNode {
    int key;
    int degree;
    BinomialTreeNode parent;
    BinomialTreeNode child;
    BinomialTreeNode sibling;

    public BinomialTreeNode(int key) {
        this.key = key;
        this.degree = 0;
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }
}
