package CustomList;

public class CustomList {
    Node head;

    public CustomList(){}

    public CustomList(int info){
        head = new Node(info);
    }

    public CustomList(int[] arr){
        for (int i = 0; i< arr.length; i++){
            addLast(arr[i]);
        }
    }
    public void add(int info){
        Node node = new Node(info);
        node.nextNode = head;
        head = node;
    }

    public void addLast(int info){
        if (head == null){
            head = new Node(info);
            return;
        }

        Node pointer = head;

        while (pointer.nextNode!=null){
            pointer = pointer.nextNode;
        }

        pointer.nextNode = new Node(info);
    }

    public void addIndex(int index, int info){
        Node node = new Node(info);
        Node pointer = head;
        int a = 1;
        Node next;

        if (index > size()+1){
            System.out.println("Добавление на позицию " + "<" + index + ">" + " невозможно");
            return;
        }
        if (head == null){
            pointer = new Node(info);
            return;
        }

        if (index == 1){
            node.nextNode = pointer;
            head = node;
            return;
        }

        while (a < index-1){
            a++;
            pointer = pointer.nextNode;
        }

        next = pointer.nextNode;
        pointer.nextNode = node;
        node.nextNode = next;


    }

    public void addRange(){
        int min = 0;
        int max = 0;

        Node pointer = head;

        if (head == null){
            System.out.println("Список пуст");
            return;
        }

        if (size() == 1){
            System.out.println("Список состоит из 1 значения - " + pointer.info);
            return;
        }

        min = pointer.info;

        while (pointer != null){

            if (pointer.info < min){
                min = pointer.info;
            }

            if (pointer.info > max){
                max = pointer.info;
            }

            pointer = pointer.nextNode;
        }

        System.out.println("Минимальное значение списка - " + min);
        System.out.println("Максимальное значение списка - " + max);
    }

    public int size(){
        Node pointer = head;
        int a = 0;

        if (head == null){
            return a;
        }

        a++;

        while (pointer.nextNode != null){
            pointer = pointer.nextNode;
            a++;
        }
        return a++;
    }


    public void deleteLast(){
        Node pointer = head;

        if (head == null){
            System.out.println("Список пуст, удаление невозможно");
            return;
        }
        if (size() == 1){
            head = null;
            return;
        }
        while (pointer.nextNode.nextNode != null){
            pointer = pointer.nextNode;
        }

        pointer.nextNode = null;
    }

    public void print(){
        Node pointer = head;

        if (head == null){
            System.out.println("Список пуст");
        }

        while (pointer != null){

            if (pointer.nextNode == null){
                System.out.println(pointer.info);
            } else {
                System.out.print(pointer.info + "->");
            }

            pointer = pointer.nextNode;
        }
    }

    public void deleteIndex(int index){
        Node pointer = head;
        int a = 1;
        Node next;

        if (head == null){
            System.out.println("Список пуст");
        }

        if (size() < index){
            System.out.println("Удаление элемента под индексом " + "<" + index + ">" + " невозможно");
            return;
        }

        if (size() == 1){
            deleteLast();
            return;
        }

        if (index == 1){
            head = pointer.nextNode;
        }
        while (a < index-1){
            a++;
            pointer = pointer.nextNode;
        }

        pointer.nextNode = pointer.nextNode.nextNode;
    }

    public void deleteAfterIndex(int index){
        int a = 1;
        Node pointer = head;

        if (index == 0){
            System.out.println("Удаление элемента под индексом <" + index + "> невозможно");
            return;
        }

        if (index > size()){
            System.out.println("Нет элемента под индексом <" + index + ">. Удаление невозможно");
        }

        if (head == null){
            System.out.println("Список пуст, удаление невозможно");
            return;
        }

        if (index == 1){
            head = null;
            return;
        }

        while (a < index-1){
            a++;
            pointer = pointer.nextNode;
        }

        pointer.nextNode = null;
    }

    public CustomList reverse(){
        CustomList reverseCustomList = new CustomList();
        Node pointer = head;

        if (head == null){
            System.out.println("Список пуст. Переворот списка невозможен");
        }

        while (pointer != null){
            reverseCustomList.add(pointer.info);
            pointer = pointer.nextNode;
        }

        return reverseCustomList;
    }

    public boolean entryElement(int a){
        Node pointer = head;

        if (head == null){
            System.out.println("Спиок пуст. Элемент не найден");
            return false;
        }

        while (pointer != null){
            if (pointer.info == a){
                return true;
            }
            pointer = pointer.nextNode;
        }

        return false;
    }

    public void indexElement(int element){
        Node pointer = head;
        CustomList customList = new CustomList();
        int index = 1;

        while (pointer != null){
            if (pointer.info == element){
                customList.add(index);
                System.out.print(index + " ");

            }
            index++;
            pointer = pointer.nextNode;
        }

        System.out.println();
    }

    public void deleteSecond(){
        Node pointer = head;

        if (head == null){
            System.out.println("Список пуст. Удаление второго элемента невозможно");
            return;
        }

        pointer.nextNode = pointer.nextNode.nextNode;
    }

    public void deleteElement(int element){
        Node pointer = head;
        Node next;

        if (head == null){
            System.out.println("Список пуст. Удаление невозможно");
            return;
        }

        while (head != null && head.info == element){
            head = head.nextNode;
        }

        if (head == null){
            return;
        }

        if (pointer.info == element && pointer.nextNode == null){
            pointer = null;
        }

        while (pointer.nextNode!= null){

            if (pointer.nextNode.info == element){
                pointer.nextNode = pointer.nextNode.nextNode;
            } else {
                pointer = pointer.nextNode;
            }
        }

    }
}