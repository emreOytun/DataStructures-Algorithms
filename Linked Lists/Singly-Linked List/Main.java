public class Main {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();
        
        System.out.println("------------");
        names.insertBeginning("Emre");
        names.insertBeginning("Can");
        names.insertBeginning("Necdet");
        names.insertEnd("Ali");
        names.insertEnd("Veli");
        names.traverse();
        System.out.println("Middle element: " + names.getMiddleNode());
    
        System.out.println("------------");
        names.reverseList();
        names.traverse();

        System.out.println("------------");
        names.remove("Emre");
        names.traverse();

    }
}
