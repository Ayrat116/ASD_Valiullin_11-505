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
        int currentIndex = 0;
        Node next;

        if (index < 0){
            System.out.println("Добавление на позицию " + "<" + index + ">" + " невозможно");
            return;
        }

        if (index == 0){
            node.nextNode = pointer;
            head = node;
            return;
        }

        if (head == null){
            System.out.println("Добавление на позицию " + "<" + index + ">" + " невозможно. (Список пуст)");
            return;
        }

        while (pointer != null && currentIndex < index-1){
            pointer = pointer.nextNode;
            currentIndex++;
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

        if (head.nextNode == null){
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
        if (head.nextNode == null){
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
        int currentIndex = 0;
        Node next;

        if (head == null){
            System.out.println("Список пуст");
        }

        if (index < 0){
            System.out.println("Удаление элемента под индексом " + "<" + index + ">" + " невозможно");
            return;
        }

        if (index == 0){
            head = pointer.nextNode;
            return;
        }

        if (head.nextNode == null){
            System.out.println("Удаление элемента под индексом " + "<" + index + ">" + " невозможно");
            return;
        }


        while (currentIndex < index-1 && pointer != null){
            currentIndex++;
            pointer = pointer.nextNode;
        }

        if (currentIndex == index-1){
            pointer.nextNode = pointer.nextNode.nextNode;
            return;
        } else {
            System.out.println("Удаление элемента под индексом " + "<" + index + ">" + " невозможно");
            return;
        }
    }

    public void deleteAfterIndex(int index){
        Node pointer = head;
        int currentIndex = 0;

        if (index < 0){
            System.out.println("Удаление элемента под индексом <" + index + "> невозможно");
            return;
        }

        if (index == 0){
            head = null;
            return;
        }

        if (head == null){
            System.out.println("Список пуст, удаление невозможно");
            return;
        }

        while (pointer != null && currentIndex < index){
            pointer = pointer.nextNode;
            currentIndex++;
        }

        if (currentIndex == index){
            pointer.nextNode = null;
            return;
        } else {
            System.out.println("Удаление элемента под индексом <" + index + "> невозможно");
            return;
        }
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
        int index = 0;

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

        if (head.nextNode == null){
            System.out.println("Удаление второго элемента невозможно. (Список состоит из одного элемента)");
            return;
        }
        pointer.nextNode = pointer.nextNode.nextNode;
    }

    public void deleteElement(int element){
        Node pointer = head;
        Node next;
        int a = 0;

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
                a++;
            } else {
                pointer = pointer.nextNode;
            }
        }

        if (a==0){
            System.out.println("Удаление элемента <" + element + "> невозможно. (Элемент не найден)");
        }
    }
}