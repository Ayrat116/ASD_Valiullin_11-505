package CustomList;

public class Main {
    public static void main(String[] args){
        int[] arr = {1,2,3,4,5,6,7};
        CustomList customList = new CustomList(arr);
        customList.addLast(18);
        System.out.println(customList.size());
        customList.addRange();
        customList.deleteLast();
        customList.addIndex(3,78);
        customList.deleteIndex(5);
        customList.deleteAfterIndex(8);
        customList.reverse().print();
        System.out.println(customList.entryElement(3));
        customList.indexElement(2);
        customList.deleteSecond();
        customList.deleteElement(8);
        customList.print();
    }
}