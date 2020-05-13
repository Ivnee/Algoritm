import java.util.Random;
import java.util.Stack;

public class TreeImpl<E extends Object & Comparable<? super E>> implements Tree<E>{
    static Random rd = new Random();


    private Node<E> root;
    private int size;




    public Node<E> getRoot() {
        return root;
    }


    @Override
    public boolean add(E value) {
        Node<E> newNode = new Node<>(value);
        if (isEmpty()) {
            root = newNode;
            size++;
            return true;
        }

        Node<E> parent = findParent(value);
        if (parent == null) {
            return false;
        }
        else if (value.compareTo(parent.getValue()) > 0) {
            parent.setRightChild(newNode);
        }
        else {
            parent.setLeftChild(newNode);
        }

        size++;
        return true;

    }

    @Override
    public boolean contains(E value) {
        return doFind(value).current !=null;
    }
    private Node<E> findParent(E value){
        return doFind(value).parent;
    }

    @Override
    public boolean remove(E value) {
        NodeAndItsParent nodeAndItsParent = doFind(value);
        Node<E> removedNode = nodeAndItsParent.current;
        Node<E> parent  = nodeAndItsParent.parent;
        if(removedNode == null){
            return false;
        }
        if(removedNode.isLeaf()){
            removeLeafNode(removedNode, parent);
        }else if (hasOnlySingleChildNode(removedNode)){
            removeNodeWithSingleChild(parent,removedNode);
        }else{
            removeCommonNode(parent, removedNode);
        }
        size--;
        return true;
    }

    private void removeCommonNode(Node<E> parent, Node<E> removedNode) {
        Node<E> successor =  getSuccsessor(removedNode);
        if(removedNode == root){
            root = successor;
        }else if(parent.getLeftChild() == removedNode){
            parent.setLeftChild(successor);
        }else{
            parent.setRightChild(successor);
        }
        successor.setLeftChild(removedNode.getLeftChild());
    }

    private Node<E> getSuccsessor(Node<E> removedNode) {
        Node<E> successor = removedNode;
        Node<E> successorParent = null;
        Node<E> current = removedNode.getRightChild();

        while (current!= null){
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        if(successor != removedNode.getRightChild()&& successorParent!= null){
             successorParent.setLeftChild(successor.getRightChild());
             successor.setRightChild(removedNode.getRightChild());
        }
        return successor;
    }

    private void removeNodeWithSingleChild(Node<E> parent, Node<E> removedNode) {
        Node<E> childNode = removedNode.getLeftChild() != null?removedNode.getLeftChild(): removedNode.getRightChild();
        if(removedNode == root){
            root= childNode;
        }else if(parent.getLeftChild() == removedNode){
            parent.setLeftChild(childNode);
        }else{
            parent.setRightChild(childNode);
        }
    }

    private boolean hasOnlySingleChildNode(Node<E> removedNode) {
        return removedNode.getLeftChild() !=null ^ removedNode.getRightChild() != null;
    }

    private void removeLeafNode(Node<E> removedNode, Node<E> parent) {
        if(parent == null){
            root = null;
        }
        if(parent.getLeftChild() == removedNode){
            parent.setLeftChild(null);
        }else {
            parent.setRightChild(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");


    }

    @Override
    public void traverse(TraverseMode mode) {
        switch (mode){
            case IN_ORDER:
                inOrder(root);
                break;
            case PRE_ORDER:
                preOrder(root);
                break;
            case POST_ORDER:
                postOrder(root);
                break;
            default:
                try {
                    throw  new IllegalAccessException("Unknow mode: "+ mode);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
    }

    private void inOrder(Node<E> current) {
        if(current==null){
            return;
        }
        inOrder(current.getLeftChild());
        System.out.println(current.getValue());
        inOrder(current.getRightChild());
    }

    private void preOrder(Node<E> current) {
        if(current==null){
            return;
        }
        System.out.println(current.getValue());
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }

    private void postOrder(Node<E> current) {
        if(current == null){
            return;
        }
        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.println(current.getValue());
    }
    public static Tree<Integer> createRandomIntTree(int deep) {
        TreeImpl <Integer> tree = new TreeImpl<>();
        for (int i = 0; i <getDeep(deep) - 1; i++) {
            tree.add(rd.nextInt(50) - 25);
        }
        System.out.println("Tree balance is: " + checkBalance(tree.getRoot()));
        return tree;
    }


    private static boolean checkBalance(Node node){
        return  node == null ||
                checkBalance(node.getLeftChild())&&
                        checkBalance(node.getRightChild())&&
                        Math.abs(checkDeep(node.getLeftChild()) - checkDeep(node.getRightChild()))<=1;

    }
    private static int checkDeep(Node node){
        return node == null ? 0:1 + Math.max(checkDeep(node.getLeftChild()),checkDeep(node.getRightChild()));
    }

    private static int getDeep(int deep){
        return square(2,deep)-1;
    }
    private static int square(int num, int deep){
        if(deep<0){
            throw new NumberFormatException("Неверное значение глубины");
        }
        if(deep == 0){
            return 1;
        }
        if(deep == 1){
            return num;
        }
        return num * square(num,deep-1);
    }


    private NodeAndItsParent doFind (E value){

        Node<E> parent = null;
        Node<E> current = root;
        while(current!=null){

            if(current.getValue().equals(value)){
                return new NodeAndItsParent(current,parent);
            }
            parent = current;
            if (value.compareTo(current.getValue()) > 0){
                current = current.getRightChild();
            }else{
                current = current.getLeftChild();
            }
        }
        return new NodeAndItsParent(null,parent);
    }
    private class NodeAndItsParent{
        Node<E> current;
        Node<E> parent;

        public NodeAndItsParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }
}

