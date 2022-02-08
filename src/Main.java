import Arrays.DynamicArray;


public class Main {
    // Main class to call instances of subclasses and work accordingly
    public static void main(String[] args) {
        try {
            DynamicArray<Integer> arr = new DynamicArray<Integer>(2);
            arr.add(0);
            arr.add(1);
            arr.add(0,15);
            System.out.println(arr);
            arr.set(1, 12);
            System.out.println(arr);
            arr.remove(2);
            System.out.println(arr);
            arr.remove(Integer.valueOf(12));
            System.out.println(arr);
            System.out.println(arr.isEmpty());
            System.out.println(arr.size());
            arr.clear();
            System.out.println(arr);
        } catch (Exception obj) {
            System.out.println(obj.getMessage());
        } finally {
            System.out.println("Hello World");
        }
    }
}
