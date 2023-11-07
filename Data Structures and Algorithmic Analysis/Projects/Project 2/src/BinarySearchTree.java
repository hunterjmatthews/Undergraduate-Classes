/**
 * @author Hunter Matthews - HJM210004
 * @date 10/20/23
 */

// replace package name with your netid
// package hjm210004;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    // define stack
    ArrayDeque<Entry<T>> stack;

    public BinarySearchTree() {
        root = null;
        size = 0;
        stack = new ArrayDeque<>();
    }

    /**
     * Checks to see if the user specified element 'x' is in the tree.
     *
     * @param x user specified element to search for.
     * @return 'true' if the user specified element 'x' is found in the tree and its value is equal to 'x';
     *         'false' for all else.
     */
    public boolean contains(T x) {
        // Find the user specified element 'x'.
        Entry<T> findX = seek(root, x);
        // Checks to see if 'findX' is not equal to null and is equal to user specified element 'x'
        return findX != null && x.compareTo(findX.element) == 0;
    }

    /**
     * Adds user specified element to tree.
     *
     * @param x
     * @return 'true' if the user specififed element 'x' has been added to the tree;
     *         'false' if 'x' was in the tree already and the value was replaced.
     */
    public boolean add(T x) {
        // Creates a new entry that contains user specified element 'x' w/ null left and right children.
        Entry<T> newEntryToAdd = new Entry<>(x, null,null);
        // Calls the 'add' method which then adds the new entry.
        return add(newEntryToAdd);
    }

    /**
     * Used to remove with user specified element 'x' from the tree
     *
     * @param x the user specified element that we want to remove from the tree.
     * @return 'x' if the element was found and discarded from the tree;
     *         'null' if the element 'x' cannot be found in the tree.
     */
    public T remove(T x) {
        // Find the entry containing the user speciifed element 'x' using the seek method.
        Entry<T> entryToFind = seek(root, x);

        // Base Case: Attempt to find element 'x', if 'x' is not found in the tree, then we return null.
        if(entryToFind == null || entryToFind.element.compareTo(x) != 0) { return null; }

        // Next, we store the parent node.
        Entry<T> parent = stack.peek();

        // Call our remove helper function which will remove the node.
        deleteNode(entryToFind, parent);

        // Return element 'x' that we managed to remove from the tree.
        return x;
    }

    // #### Helper Methods ####
    /**
     * Helper method that adds the entry to the BST.
     *
     * @param node Entry that contains desired element to be added to the tree.
     * @return 'true' if provided element 'e' was added to the tree;
     *         'false' if provided element 'e' already exist inside the tree, and its value was changed.
     */
    public boolean add(Entry<T> node) {
        // Used to find the 'parent' node for the entry 'node' using our seek method.
        Entry<T> parent = seek(root, node.element);

        // If we cannot find the parent node (i.e. null), then we set 'node' to be the root of the tree.
        if(parent == null) {
            root = node;
        } else {
            // If 'node' contains the same key as its parent element, we update the parent element.
            if(node.element.compareTo(parent.element) == 0) {
                parent.element = node.element;
                return false;
            }
            // If 'node' is less than its parent element, then we add it to the left of the parent element.
            if(node.element.compareTo(parent.element) < 0)
                parent.left = node;
            // If 'node' is greater than its parent element, then we add it to the right of the parent element.
            if(node.element.compareTo(parent.element) > 0)
                parent.right = node;
        }
        // Increases the size of the tree adjusting for new added element.
        // Trivially, we can do size++; but I like the readability of this more!
        size = size + 1;
        // Now, we return true since a new element was added.
        return true;
    }

    /**
     * Helper function that removes/deletes node from the tree.
     *
     * @param
     * @return
     */
    private void deleteNode(Entry<T> node, Entry<T> parent) {
        // Choice #1: If the node is a leaf node.
        //        #a: The node does not have a parent.
        //        #b: The node is a left child.
        //        #c: The node is a right child.
        if(node.left == null && node.right == null) {
            if(parent == null) { root = null; }
            else if(node.element.compareTo(parent.element) < 0) { parent.left = null; }
            else if(node.element.compareTo(parent.element) > 0) { parent.right = null; }
            size = size - 1;
        }
        // Choice #2: If the node has a left child.
        //        #a: The node does not have a parent.
        //        #b: The node is a left child.
        //        #c: The node is a right child.
        else if(node.right == null) {
            if(parent == null) { root = root.left; }
            else if(node.element.compareTo(parent.element) < 0) { parent.left = node.left; }
            else if(node.element.compareTo(parent.element) > 0) { parent.right = node.left; }
            size = size - 1;
        }
        // Choice #3: If the node has a right child.
        //        #a: The node does not have a parent.
        //        #b: The node is a left child.
        //        #c: The node is a right child.
        else if(node.left == null) {
            if(parent == null) { root = root.right; }
            else if(node.element.compareTo(parent.element) < 0) { parent.left = node.right; }
            else if(node.element.compareTo(parent.element) > 0) { parent.right = node.right; }
            size = size - 1;
        }
        // Choice #4: If the node has two children.
        //        #a: The node does not have a parent.
        //        #b: The node is itself a left or right child.
        else {
            // If given node has 2 children, then we need to find the in-order sucessor (i.e. in-order successor of a node is the next node in in-order traversal of the tree)
            Entry<T> successor = seek(node.right, node.element);
            // Now, we delete the successor.
            remove(successor.element);
            if(parent == null) { root.element = successor.element; }
            else { node.element = successor.element; }
        }
    }

    /**
     * Finds desired user specified element 'x' in the tree.
     * 
     * @param beginningNode start the search from.
     * @param x user specified element that we want to find.
     * @return entry that contains desired element; null if not found in tree.
     */
    public Entry<T> seek(Entry<T> beginningNode, T x) {
        // Clear the stack for new search.
        stack.clear();
        // Base Case: If tree is empty, then we return null as there is nothing to search for.
        if (root == null) return null;
        // Initialize beginning node.
        Entry<T> curr = beginningNode;
        // Traverse until we find desired element.
        while (curr != null) {
            // Element is found!
            if (x.compareTo(curr.element) == 0) {
                stack.push(curr);
                break;
            } // Element is smaller, so we move left.
            else if (x.compareTo(curr.element) < 0) {
                stack.push(curr);
                curr = curr.left;
            } // Element is larger, so we move right.
            else if (x.compareTo(curr.element) > 0) {
                stack.push(curr);
                curr = curr.right;
            }
        }
        // This pops and returns the element that we were looking for from the stack.
        return stack.pop();
    }


// Removed all optional problems that I did not implement.
// #### Begin Optional Problems ####

    /**
     * Optional problem : Iterate elements in sorted order of keys
     * Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
        return null;
    }

    // Optional problem
    public T min(Entry<T> node) {
        while(node.left != null) { node = node.left; }
        return node.element;
    }

    public T max(Entry<T> node) {
        while(node.right != null) { node = node.right; }
        return node.element;
    }

// #### End Optional Problems ####

public static void main(String[] args) throws FileNotFoundException {
    BinarySearchTree<Long> bst = new BinarySearchTree<>();
    Scanner sc;
    if (args.length > 0) {
        File file = new File(args[0]);
        sc = new Scanner(file);
    } else {
        sc = new Scanner(System.in);
    }
    String operation = "";
    long operand = 0;
    int modValue = 999983;
    long result = 0;
    // Initialize the timer
    Timer timer = new Timer();

    while (!((operation = sc.next()).equals("End"))) {
        switch (operation) {
            case "Add": {
                operand = sc.nextInt();
                if (bst.add(operand)) {
                    result = (result + 1) % modValue;
                }
                break;
            }
            case "Remove": {
                operand = sc.nextInt();
                if (bst.remove(operand) != null) {
                    result = (result + 1) % modValue;
                }
                break;
            }
            case "Contains": {
                operand = sc.nextInt();
                if (bst.contains(operand)) {
                    result = (result + 1) % modValue;
                }
                break;
            }
        }
    }

    // End Time
    timer.end();

    System.out.println(result);
    System.out.println(timer);
}
    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }
}