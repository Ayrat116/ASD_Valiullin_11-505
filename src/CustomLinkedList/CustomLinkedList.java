package CustomLinkedList;

public class CustomLinkedList {
    private LinkedNode head;

    public CustomLinkedList() {
    }

    public CustomLinkedList(int info) {
        head = new LinkedNode(info);
    }

    public CustomLinkedList(int[] arr) {
        for (int index : arr) {
            addLast(index);
        }
    }

    public void addToHead(int info) {
        LinkedNode linkedNode = new LinkedNode(info);
        if (head == null) {
            head = linkedNode;
            return;
        }
        linkedNode.nextNode = head;
        head.prevNode = linkedNode;
        head = linkedNode;
    }

    public void delete() {
        if (head == null) {
            System.out.println("Список пустой, удаление невозможно");
            return;
        }

        head = head.nextNode;
        if (head != null) {
            head.prevNode = null;
        }
    }

    public void addLast(int info) {
        if (head == null) {
            addToHead(info);
            return;
        }

        LinkedNode pointer = head;

        while (pointer.nextNode != null) {
            pointer = pointer.nextNode;
        }

        LinkedNode node = new LinkedNode(info);
        pointer.nextNode = node;
        node.prevNode = pointer;
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("Список пуст. Удаление невозможно");
            return;
        }

        if (head.nextNode == null) {
            head = null;
            return;
        }

        LinkedNode pointer = head;

        while (pointer.nextNode.nextNode != null) {
            pointer = pointer.nextNode;
        }

        pointer.nextNode.prevNode = null;
        pointer.nextNode = null;
    }

    public void addToIndex(int index, int info) {
        LinkedNode pointer = head;
        int ind = 0;

        if (index == 0) {
            addToHead(info);
            return;
        }

        if (head == null) {
            System.out.println("Список пуст. Добавление на индеск, не ранвный 0, невозможно");
            return;
        }

        while (ind < index - 1 && pointer.nextNode != null) {
            pointer = pointer.nextNode;
            ind++;
        }

        if (ind < index - 1) {
            System.out.println("Индекс превышает размер списка");
            return;
        }

        LinkedNode node = new LinkedNode(info);

        LinkedNode nextNode = pointer.nextNode;
        pointer.nextNode = node;
        node.prevNode = pointer;

        if (pointer.nextNode != null) {
            node.nextNode = nextNode;
            nextNode.prevNode = node;
        }
    }

    public void print() {
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }

        LinkedNode pointer = head;


        while (pointer.nextNode != null) {
            System.out.print(pointer.info + " -> ");
            pointer = pointer.nextNode;
        }

        System.out.println(pointer.info + " ");
    }

    public void deleteAfterIndex(int index) {
        if (head == null) {
            System.out.println("Список пуст. Удаление невозможно.");
            return;
        }

        LinkedNode pointer = head;
        int ind = 0;

        while (ind < index - 1 && pointer.nextNode != null) {
            pointer = pointer.nextNode;
            ind++;
        }

        if (ind < index - 1) {
            System.out.println("Удаление невозможно. Индекс выходит за пределы списка.");
            return;
        }

        if (pointer.nextNode != null) {
            pointer.nextNode.prevNode = null;
            pointer.nextNode = null;
        } else {
            System.out.println("Удаление невозможно. Индекс выходит за пределы списка.");
        }
    }

    public void deleteElement(int info) {
        if (head == null) {
            System.out.println("Список пуст. Удаление невозможно.");
            return;
        }

        LinkedNode pointer = head;

        while (pointer != null) {

            if (pointer.info == info) {
                if (pointer == head) {
                    head = pointer.nextNode;
                    head.prevNode = null;
                    return;
                }

                if (pointer.nextNode == null) {
                    pointer.prevNode.nextNode = null;
                    return;
                }

                pointer.prevNode.nextNode = pointer.nextNode;
                pointer.nextNode.prevNode = pointer.prevNode;
            }
            pointer = pointer.nextNode;
        }
    }

    public void deleteSecond() {
        if (head == null) {
            System.out.println("Список пуст. Удаление невозможно.");
            return;
        }

        if (head.nextNode == null) {
            System.out.println("Список состоит из одного элемента. Удаление невозможно.");
            return;
        }

        head.nextNode.prevNode = null;
        head.nextNode = head.nextNode.nextNode;
    }

    public CustomLinkedList reverse() {
        LinkedNode pointer = head;

        CustomLinkedList list = new CustomLinkedList();

        if (head == null){
            System.out.println("Список пуст. Перевернуть нельзя.");
            return list;
        }

        if (head.nextNode == null){
            list.addToHead(head.info);
            return list;
        }

        while (pointer != null){
            list.addToHead(pointer.info);
            pointer = pointer.nextNode;
        }
        return list;
    }

    public void addRange(int[] arr){
        LinkedNode pointer = head;
        if (arr.length == 0){
            return;
        }

        for (int i = 0; i < arr.length; i++){
            addLast(arr[i]);
        }
    }
}