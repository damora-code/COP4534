package Prog16.Prog16_03;

public class Prog16_03 {
    public static void main(String[] args) {
        new Prog16_03();
    }

    public Prog16_03(){
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(5);
        bst.add(7);
        bst.add(8);
        bst.add(99);
        bst.add(23);
        bst.add(17);
        bst.add(6);
        bst.add(4);
        bst.add(2);

        bst.printAllPathsFromRootToLeaves();

    }
}
