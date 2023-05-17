public class Main {
    public static void main(String[] args) {
        testMyArrayList();


    }

    private static void testMyArrayList() {
        MyArrayListNoImpl<Integer> myArrayList = new MyArrayListNoImpl<>();
        for(int i = 0; i < 10; i++) myArrayList.add(i);
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.add(2, 10);
        System.out.println("myArrayList 2번인덱스에 10넣기 = " + myArrayList);
        System.out.println("myArrayList = " + myArrayList);

        boolean remove = myArrayList.remove(10);
        System.out.println("remove 10지우기 = " + remove);
        System.out.println("myArrayList = " + myArrayList);
        boolean contains = myArrayList.contains(10);
        System.out.println("contains 10 존재? = " + contains);

        System.out.println("myArrayList = " + myArrayList);
        Integer getInt = myArrayList.get(3);
        System.out.println("getInt index3가져오기 = " + getInt);

        myArrayList.removeIf(integer -> integer % 2 == 0);
        System.out.println("myArrayList 짝수지우기 = " + myArrayList);

        int size = myArrayList.size();
        System.out.println("size = " + size);

        Integer set = myArrayList.set(3, 33);
        System.out.println("set = " + set);
        System.out.println("myArrayList = " + myArrayList);
    }
}