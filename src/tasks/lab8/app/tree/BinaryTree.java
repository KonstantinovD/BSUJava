package tree;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable> {

    private Node<T> root;

    public T getTop() {
        return root.obj;
    }

    public boolean isEmpty(){
        return root==null;
    }

    public void add(T obj){
        if(root == null){
            root = new Node<T>(obj);
        }else{
            root.add(obj);
        }
    }

    public void delete(){
        if (root == null) throw new NullPointerException("Binary Tree root is null");
        else{
            if(root.mLeft == null){
                if(root.mRight == null){
                    root = null;
                }else{
                    root = root.mRight;
                }
            }else{
                if(root.mLeft.mRight == null){
                    root.mLeft.mRight = root.mRight;
                    root = root.mLeft;
                }else{
                    Node<T> nextNode = root.mLeft;
                    while(nextNode.mRight.mRight != null) nextNode = nextNode.mRight;
                    Node<T> lastNode = nextNode.mRight;
                    nextNode.mRight = null;

                    lastNode.mRight = root.mRight;
                    lastNode.mLeft = root.mLeft;
                    root = lastNode;
                }
            }
        }
    }

    public boolean contains(T obj){
        if(root == null) return false;
        return find(root, obj);
    }
    private boolean find(Node<T> node, T obj){
        int compareResult = obj.compareTo(node.getObj());
        if(compareResult == 0) return true;
        if (compareResult > 0) {
            if(node.getRight() == null) return false;
            return find(node.getRight(), obj);
        }else { //compareResult < 0
            if(node.getLeft() == null) return false;
            return find(node.getLeft(), obj);
        }
    }



    public ArrayList<T> getTraversalList(TraversalOrder traversalType){
        Traversal tr = new Traversal();
        switch(traversalType){
            case ROOT_LEFT_RIGHT:{
                return tr.getRootLeftRight(this);
            }
            case LEFT_ROOT_RIGHT:{
                return tr.getLeftRootRight(this);
            }
            case LEFT_RIGHT_ROOT:{
                return tr.getLeftRightRoot(this);
            }
            default: return null;
        }
    }



    static class Node<T extends Comparable>{
        Node<T> mRight;
        Node<T> mLeft;
        T obj;

        public T getObj() {
            return obj;
        }

        public Node<T> getLeft() {
            final Node<T> node = mLeft;
            return node;
        }

        public Node<T> getRight() {
            return mRight;
        }


        public Node(Node<T> other){
            this.obj = other.obj;
            this.mRight = other.mRight;
            this.mLeft = other.mLeft;
        }

        private Node(T obj){
            this.obj = obj;
        }

        private void add(T inserted){
            Node<T> nodeToInsert = null;
            if(obj.compareTo(inserted)<0){ //this is less
                if(mRight != null){ mRight.add(inserted);
                }else{
                    mRight = new Node<T>(inserted);
                }return;
            }
            if(obj.compareTo(inserted)>0){//this is more
                if(mLeft != null){ mLeft.add(inserted);
                }else{
                    mLeft = new Node<T>(inserted);
                }return;
            }throw new IllegalArgumentException("Duplicates are not allowed!");
        }
    }

    public static enum TraversalOrder{
        ROOT_LEFT_RIGHT,
        LEFT_RIGHT_ROOT,
        LEFT_ROOT_RIGHT,
    }

    private class Traversal<T extends Comparable>{
        ArrayList<T> treeElements;

        /**
         * Pre-order
         * @param tree
         * @return
         */
        ArrayList<T> getRootLeftRight(BinaryTree<T> tree) {
            treeElements = new ArrayList<>();
            preOrder(tree.root);
            return treeElements;
        }
        void preOrder(Node<T> node){
            if (node == null) return;
            treeElements.add(node.getObj());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }

        /**
         * In-order
         * @param tree
         * @return
         */
        ArrayList<T> getLeftRootRight(BinaryTree<T> tree) {
            treeElements = new ArrayList<>();
            inOrder(tree.root);
            return treeElements;
        }
        void inOrder(Node<T> node){
            if (node == null) return;
            inOrder(node.getLeft());
            treeElements.add(node.getObj());
            inOrder(node.getRight());
        }

        /**
         * Post-order
         * @param tree
         * @return
         */
        ArrayList<T> getLeftRightRoot(BinaryTree<T> tree) {
            treeElements = new ArrayList<>();
            postOrder(tree.root);
            return treeElements;
        }
        void postOrder(Node<T> node){
            if (node == null) return;
            postOrder(node.getLeft());
            postOrder(node.getRight());
            treeElements.add(node.getObj());
        }
    }

}