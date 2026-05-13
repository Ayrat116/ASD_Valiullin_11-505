package Semestrovka_2;

import java.util.*;


public class BinomialHeap {
    private BinomialTreeNode head;
    private int size;
    private long operationSteps; // Счётчик итераций для анализа сложности

    public BinomialHeap() {
        head = null;
        size = 0;
        operationSteps = 0;
    }

    // --- Базовые операции (вставка, поиск минимума, удаление минимума, слияние) ---
    public void insert(int key) {
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.head = new BinomialTreeNode(key);
        tempHeap.size = 1;
        this.union(tempHeap);
    }

    public int findMin() {
        if (head == null) throw new NoSuchElementException("Heap is empty");
        BinomialTreeNode minNode = head;
        BinomialTreeNode current = head.sibling;
        while (current != null) {
            if (current.key < minNode.key) minNode = current;
            current = current.sibling;
        }
        return minNode.key;
    }

    public int extractMin() {
        if (head == null) throw new NoSuchElementException("Heap is empty");
        BinomialTreeNode prev = null;
        BinomialTreeNode curr = head;
        BinomialTreeNode minPrev = null;
        BinomialTreeNode minNode = head;

        while (curr != null) {
            if (curr.key < minNode.key) {
                minNode = curr;
                minPrev = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }

        if (minPrev == null) head = minNode.sibling;
        else minPrev.sibling = minNode.sibling;

        BinomialHeap childHeap = new BinomialHeap();
        BinomialTreeNode child = minNode.child;
        BinomialTreeNode nextChild;
        while (child != null) {
            nextChild = child.sibling;
            child.sibling = childHeap.head;
            child.parent = null;
            childHeap.head = child;
            child = nextChild;
        }

        this.union(childHeap);
        size--;
        return minNode.key;
    }

    public void union(BinomialHeap otherHeap) {
        this.head = mergeHeaps(this.head, otherHeap.head);
        otherHeap.head = null;
        if (this.head == null) return;

        BinomialTreeNode prev = null;
        BinomialTreeNode curr = this.head;
        BinomialTreeNode next = curr.sibling;

        while (next != null) {
            if ((curr.degree != next.degree) || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    linkTrees(curr, next);
                } else {
                    if (prev == null) this.head = next;
                    else prev.sibling = next;
                    linkTrees(next, curr);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
    }

    private BinomialTreeNode mergeHeaps(BinomialTreeNode h1, BinomialTreeNode h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;

        BinomialTreeNode head;
        BinomialTreeNode tail;
        if (h1.degree <= h2.degree) {
            head = h1;
            h1 = h1.sibling;
        } else {
            head = h2;
            h2 = h2.sibling;
        }
        tail = head;

        while (h1 != null && h2 != null) {
            if (h1.degree <= h2.degree) {
                tail.sibling = h1;
                h1 = h1.sibling;
            } else {
                tail.sibling = h2;
                h2 = h2.sibling;
            }
            tail = tail.sibling;
        }
        tail.sibling = (h1 != null) ? h1 : h2;
        return head;
    }

    private void linkTrees(BinomialTreeNode root, BinomialTreeNode child) {
        child.parent = root;
        child.sibling = root.child;
        root.child = child;
        root.degree++;
    }

    // --- Операции для анализа сложности (с подсчётом итераций) ---
    public void insertWithCounting(int key) {
        long startSteps = operationSteps;
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.head = new BinomialTreeNode(key);
        tempHeap.size = 1;
        this.unionWithCounting(tempHeap);
        operationSteps += (operationSteps - startSteps);
    }

    public int findMinWithCounting() {
        if (head == null) throw new NoSuchElementException("Heap is empty");
        BinomialTreeNode minNode = head;
        BinomialTreeNode current = head.sibling;
        operationSteps++;
        while (current != null) {
            operationSteps++;
            if (current.key < minNode.key) minNode = current;
            current = current.sibling;
        }
        return minNode.key;
    }

    public int extractMinWithCounting() {
        long startSteps = operationSteps;
        if (head == null) throw new NoSuchElementException("Heap is empty");
        BinomialTreeNode prev = null;
        BinomialTreeNode curr = head;
        BinomialTreeNode minPrev = null;
        BinomialTreeNode minNode = head;

        while (curr != null) {
            operationSteps++;
            if (curr.key < minNode.key) {
                minNode = curr;
                minPrev = prev;
            }
            prev = curr;
            curr = curr.sibling;
        }

        if (minPrev == null) head = minNode.sibling;
        else minPrev.sibling = minNode.sibling;

        BinomialHeap childHeap = new BinomialHeap();
        BinomialTreeNode child = minNode.child;
        BinomialTreeNode nextChild;
        while (child != null) {
            operationSteps++;
            nextChild = child.sibling;
            child.sibling = childHeap.head;
            child.parent = null;
            childHeap.head = child;
            child = nextChild;
        }

        this.unionWithCounting(childHeap);
        size--;
        operationSteps += (operationSteps - startSteps);
        return minNode.key;
    }

    public void unionWithCounting(BinomialHeap otherHeap) {
        this.head = mergeHeaps(this.head, otherHeap.head);
        otherHeap.head = null;
        if (this.head == null) return;

        BinomialTreeNode prev = null;
        BinomialTreeNode curr = this.head;
        BinomialTreeNode next = curr.sibling;

        while (next != null) {
            operationSteps++;
            if ((curr.degree != next.degree) || (next.sibling != null && next.sibling.degree == curr.degree)) {
                prev = curr;
                curr = next;
            } else {
                if (curr.key <= next.key) {
                    curr.sibling = next.sibling;
                    linkTrees(curr, next);
                } else {
                    if (prev == null) this.head = next;
                    else prev.sibling = next;
                    linkTrees(next, curr);
                    curr = next;
                }
            }
            next = curr.sibling;
        }
    }

    public void resetOperationSteps() { operationSteps = 0; }
    public long getOperationSteps() { return operationSteps; }

    // --- Обходы дерева (BFS и DFS) ---
    public void breadthFirstTraversal() {
        if (head == null) {
            System.out.println("Heap is empty");
            return;
        }
        Queue<BinomialTreeNode> queue = new LinkedList<>();
        BinomialTreeNode root = head;
        while (root != null) {
            queue.add(root);
            root = root.sibling;
        }

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            BinomialTreeNode node = queue.poll();
            System.out.print(node.key + " ");
            if (node.child != null) {
                BinomialTreeNode child = node.child;
                while (child != null) {
                    queue.add(child);
                    child = child.sibling;
                }
            }
        }
        System.out.println();
    }

    public void depthFirstTraversal() {
        if (head == null) {
            System.out.println("Heap is empty");
            return;
        }
        System.out.print("DFS: ");
        BinomialTreeNode root = head;
        while (root != null) {
            dfsRecursive(root);
            root = root.sibling;
        }
        System.out.println();
    }

    private void dfsRecursive(BinomialTreeNode node) {
        if (node == null) return;
        System.out.print(node.key + " ");
        BinomialTreeNode child = node.child;
        while (child != null) {
            dfsRecursive(child);
            child = child.sibling;
        }
    }

    // --- Вспомогательные методы для тестирования и отладки ---
    public static BinomialHeap heapSort(int[] arr) {
        BinomialHeap heap = new BinomialHeap();
        for (int num : arr) heap.insert(num);
        return heap;
    }

    public static BinomialHeap heapSortWithCounting(int[] arr) {
        BinomialHeap heap = new BinomialHeap();
        for (int num : arr) heap.insertWithCounting(num);
        return heap;
    }

    public void printHeap() {
        System.out.println("Binomial Heap:");
        BinomialTreeNode root = head;
        while (root != null) {
            printTree(root, 0);
            root = root.sibling;
        }
    }

    private void printTree(BinomialTreeNode node, int level) {
        if (node == null) return;
        for (int i = 0; i < level; i++) System.out.print("  ");
        System.out.println("Node: " + node.key + " (degree=" + node.degree + ")");
        BinomialTreeNode child = node.child;
        while (child != null) {
            printTree(child, level + 1);
            child = child.sibling;
        }
    }
}