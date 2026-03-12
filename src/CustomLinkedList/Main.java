package CustomLinkedList;

public class Main {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,2,2,67,7};

        CustomLinkedList customLinkedList = new CustomLinkedList(arr);
        //customLinkedList.addToIndex(3,1);
        //customLinkedList.deleteAfterIndex(6);
        //customLinkedList.deleteElement(2);
        //customLinkedList.deleteSecond();
        //customLinkedList.reverse().print();
        customLinkedList.addRange(arr);
        customLinkedList.print();
    }
}
