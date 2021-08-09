package com.alexwang.datastructure.tree;

import javax.swing.plaf.basic.BasicEditorPaneUI;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<T>> {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        while(scanner.hasNext()) {
            int op = scanner.nextInt();
            if(op == 0) {
                bst.insert(scanner.nextInt());
            } else if(op == 1) {
                bst.erase(scanner.nextInt());
            }
        }
        bst.print();
    }

    public void print() {
        print(this.root);
    }

    private void print(Node root) {
        if(root == nil) return;
        print(root.left);
        System.out.printf("%s ", root.value);
        print(root.right);
    }

    private Node nil = create(null);
    private Node root = nil;

    private class Node {

        public T value;
        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
            this.left = nil;
            this.right = nil;
        }
    }

    private Node create(T value) {
        return new Node(value);
    }

    public void clear(Node root) {
        root = nil;
        return;
    }

    public void insert(T value) {
        this.root = insert(root, value);
    }

    private Node insert(Node root, T value) {
        if(root == nil) return create(value);
        int i = value.compareTo(root.value);
        if(i == 0) {
            return root;
        } else if(i < 0) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    public void erase(T value) {
        this.root = earse(this.root, value);
    }

    private Node earse(Node root, T value) {
        if(root == nil) return nil;
        int i = value.compareTo(root.value);
        if(i < 0) {
            root.left = earse(root.left, value);
        } else if(i > 0) {
            root.right = earse(root.right, value);
        } else {
            if(root.left == nil || root.right == nil) {
                return root.left == nil ? root.right : root.left;
            } else {
                //寻找前驱节点，也可以找后继
                Node preNode = findPreNode(root);
                root.value = preNode.value;
                root.left = earse(root.left, preNode.value);
            }
        }
        return root;
    }

    private Node findPreNode(Node root) {
        Node temp = root.left;
        while(temp.right != nil) temp = temp.right;
        return temp;
    }
}
