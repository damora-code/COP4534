package Prog16.Prog16_03;

public class Prog16_04 {

    public Prog16_04(){
        BinarySearchTree bst = new BinarySearchTree();

        bst.add(21);
        bst.add(15);
        bst.add(34);
        bst.add(10);
        bst.add(17);
        bst.add(32);
        bst.add(46);
        bst.add(13);
        bst.add(28);
        bst.add(43);
        bst.add(30);
        bst.add(31);



        //bst.printAllPathsFromRootToLeaves();
        bst.maxSumPathFromRootToLeaves();
    }

    public static void main(String[] args) {
        new Prog16_04();
    }
}
