import employee.Employee;
import tree.BinaryTree;
//import tree.BinaryTree.TraversalOrder;

public class BinaryTreeTest{
    public static void main(String[] args){
        BinaryTree<Integer> tree = new BinaryTree<>();

        Integer treeElements[] = {40, 30, 50, 20, 35, 45, 60, 25, 33, 37, 47, 70};

        for(Integer i:treeElements){
            tree.add(i);
        }

        System.out.println(tree.contains(20));
        System.out.println(tree.contains(48));
        System.out.println(tree.contains(37));

        System.out.println(tree.getTraversalList(BinaryTree.TraversalOrder.ROOT_LEFT_RIGHT));
        System.out.println();
        System.out.println(tree.getTraversalList(BinaryTree.TraversalOrder.LEFT_ROOT_RIGHT));
        System.out.println();
        System.out.println(tree.getTraversalList(BinaryTree.TraversalOrder.LEFT_RIGHT_ROOT));
        System.out.println();

        while(!tree.isEmpty()){
            System.out.println(tree.getTop());
            tree.delete();
        }

        System.out.println("**********************************************");

        BinaryTree<Employee> treeE = new BinaryTree<>();

        treeE.add(new Employee("Katy", 18));
        treeE.add(new Employee("Evgenia", 7));
        treeE.add(new Employee("Leonid", 33));
        treeE.add(new Employee("Katy", 35));
        treeE.add(new Employee("Pavel", 11));
        treeE.add(new Employee("Evgen", 10));



        System.out.println(treeE.contains(new Employee("Katy", 18)));
        System.out.println(treeE.contains(new Employee("Fyodor", 40)));


        System.out.println(treeE.getTraversalList(BinaryTree.TraversalOrder.ROOT_LEFT_RIGHT));
        System.out.println();
        System.out.println(treeE.getTraversalList(BinaryTree.TraversalOrder.LEFT_ROOT_RIGHT));
        System.out.println();
        System.out.println(treeE.getTraversalList(BinaryTree.TraversalOrder.LEFT_RIGHT_ROOT));
        System.out.println();

        while(!treeE.isEmpty()){
            System.out.println(treeE.getTop());
            treeE.delete();
        }
    }
}