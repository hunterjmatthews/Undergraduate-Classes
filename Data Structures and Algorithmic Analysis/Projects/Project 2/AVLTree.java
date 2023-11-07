/**
 * @author Hunter Matthews - HJM210004
 * @date 10/20/23
 */

// replace package name with your netid
// package hjm210004;

public class AVLTree<T extends Comparable<? super T>> extends BinarySearchTree<T> {
    static class Entry<T> extends BinarySearchTree.Entry<T> {
        int height;
        Entry(T x, Entry<T> left, Entry<T> right) {
            super(x, left, right);
            height = 0;
        }
    }

    AVLTree() {
        super();
    }

    /**
     * Add a new user specified element into the AVL tree.
     *
     * @param x the user specified element that we want to add to the AVL tree.
     * @return 'true' if the element was added.
     *         'false' if the element was not added.
     */
    @Override
    public boolean add(T x) {
        // Initializers
        Entry<T> curr;
        int leftHeight, rightHeight, heightLeftChild, heightRightChild;

        boolean returnValue = super.add(new Entry<>(x, null, null));
        // Look for 'x'
        seek(root, x);
        while (stack.peek() != null) {
            curr = (Entry<T>) stack.pop();

            // Height adjustment
            leftHeight = curr.left == null ? -1 : ((Entry<T>) curr.left).height;
            rightHeight = curr.right == null ? -1 : ((Entry<T>) curr.right).height;

            // Left & Right Imbalance
            if (leftHeight - rightHeight > 1) {
                heightLeftChild = curr.left.left == null ? -1 : ((Entry<T>) curr.left.left).height;
                heightRightChild = curr.left.right == null ? -1 : ((Entry<T>) curr.left.right).height;

                // Rotation: LL
                if (heightLeftChild > heightRightChild) { leftRotation(curr); }
                // Rotation: LR
                else { leftRightRotation(curr); }
            }
            if (rightHeight - leftHeight > 1) {
                heightLeftChild = curr.right.left == null ? -1 : ((Entry<T>) curr.right.left).height;
                heightRightChild = curr.right.right == null ? -1 : ((Entry<T>) curr.right.right).height;

                // Rotation: RR
                if (heightRightChild > heightLeftChild) { rightRotation(curr); }
                // Rotation: RL
                else { rightLeftRotation(curr); }
            }
        }
        return returnValue;
    }

    /**
     *  Remove user specified element 'x' from the AVL tree.
     *
     * @param x the user specified element that we want to remove from the tree.
     * @return element that was removed (if element was removed).
     *         null if element was not removed.
     */
   @Override
   public T remove(T x) {
       // Preform removal
       T removedElement = super.remove(x);

       // If element was removed, we must rebalance the tree.
       if (removedElement != null) { balanceTree(); }

       // Return either the removed element or null if not removed.
       return removedElement;
   }

    // #### Inner Class: Verification and Associated Methods ####

    /**
     * Inner class for storing results of verification.
     */
    private class Verification {
        // The minimum value found while verifying.
        T minValue;
        // The maximum value found while verifying.
        T maxValue;
        // The height of our verified result.
        int height;
        // Boolean status indicating the result of our verification.
        boolean status;

        /**
         *  Constructor that is responsible for creating Verification object.
         *
         * @param minValue Minimum value during verification process.
         * @param maxValue Maximum value during verification process.
         * @param height Height value during verification process.
         * @param status Result of the verification process.
         */
        public Verification(T minValue, T maxValue, int height, boolean status) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.height = height;
            this.status = status;
        }
    }

    /**
     *  Verifies the AVL tree.
     *
     * @return 'true' if the tree is valid.
     *         'false' if the tree is not valid.
     */
    boolean verify() {
        // If root is equal to null, then it will be considered a valid tree so return 'true'.
        if(root == null) { return true; }

        // Verifies the AVL tree recursively and stores the result in 'isValid'.
        Verification isValid = verify((Entry<T>) root);

        // Returns 'true' if the flag property matches the root's properties and if the height property matches the root's properties.
        return isValid.status && isValid.height == ((Entry<T>) root).height;
    }

    /**
     *  Verifies the subtree recursively.
     *
     * @param curr The node to which we want to verify.
     * @return The results of our verification.
     */
    Verification verify(Entry<T> curr) {
        Verification leftVerification = null, rightVerification = null;

        // First, we verify the left subtree.
        if(curr.left != null) {
            leftVerification = verify((Entry<T>) curr.left);
            /* Check if left subtree is invalid or if the max value in the left subtree is >= the current element.
               or if the height of the left subtree and current left child are currently not balanced.
             */
            if(!leftVerification.status || leftVerification.maxValue.compareTo(curr.element) >= 0 || leftVerification.height != ((Entry<T>) curr.left).height) {
                return new Verification(leftVerification.minValue, leftVerification.maxValue, leftVerification.height, false);
            }
        }

        // Next, we need to verify the right subtree.
        if(curr.right != null) {
            rightVerification = verify((Entry<T>) curr.right);
            /* Check if right subtree is invalid or if the max value in the right subtree is >= the current element.
               or if the height of the right subtree and current right child are currently not balanced.
             */
            if(!rightVerification.status || rightVerification.maxValue.compareTo(curr.element) >= 0 || rightVerification.height != ((Entry<T>) curr.right).height) {
                return new Verification(rightVerification.minValue, rightVerification.maxValue, rightVerification.height, false);
            }
        }

        // Now, we verify the height balance between both the right and left subtrees.
        if(Math.abs((leftVerification == null ? -1 : leftVerification.height) - (rightVerification == null ? -1 : rightVerification.height)) > 1) {
            return new Verification(curr.element, curr.element,Math.max(leftVerification == null ? -1 : leftVerification.height,
                    rightVerification == null ? -1 : rightVerification.height) + 1,false);
        }

        // If verification gives green light (i.e. pass), we want to return result w/ new height.
        return new Verification(curr.element, curr.element,Math.max(leftVerification == null ? -1 : leftVerification.height,
                rightVerification == null ? -1 : rightVerification.height) + 1, true);
    }



    // #### Helper Methods ####

    /**
     * Helper method that preforms the left rotation to balance AVL tree.
     *
     * @param node Entry where left rotation is preformed.
     */
    private void leftRotation(Entry<T> node) {
        // Root of the tree needs to be rotated so update the root.
        if(stack.peek() == null) { root = node.left; }
        // If not, then we need to update the parent's child element reference.
        else {
            if(stack.peek().element.compareTo(node.element) > 0) { stack.peek().left = node.left; }
            else { stack.peek().right = node.left; }
        }
        // Now, we need to reevaluate the height of 'node', so preform height calculations.
        node.height = Math.max(node.left.right == null ? -1 : ((Entry<T>) node.left.right).height, node.right == null ? -1 : ((Entry<T>) node.right).height) + 1;

        // Preform the actual left rotation
        Entry<T> tmp = (Entry<T>) node.left.right;
        node.left.right = node;
        node.left = tmp;
    }

    /**
     * Helper method that preforms the left-right rotation to balance AVL tree.
     *
     * @param node Entry where left-right rotation is preformed.
     */
    private void leftRightRotation(Entry<T> node) {
        // Root of the tree needs to be rotated so update the root.
        if(stack.peek() == null) { root = node.left.right; }
        // If not, then we need to update the parent's child element reference.
        else {
            if(stack.peek().element.compareTo(node.element) > 0) { stack.peek().left = node.left.right; }
            else { stack.peek().right = node.left.right; }
        }
        // Now, we need to reevaluate the height of 'node', so preform height calculations.
        // Calculate the height of the current entry 'node' after tree rotation.
        node.height = Math.max(node.right == null ? -1 : ((Entry<T>) node.right).height, node.left.right.right == null ? -1 : ((Entry<T>) node.left.right.right).height) + 1;
        // Calculate the height of the left child of 'node' after tree rotation.
        ((Entry<T>) node.left).height = Math.max(node.left.left == null ? -1 : ((Entry<T>) node.left.left).height, node.left.right.left == null ? -1 : ((Entry<T>) node.left.right.left).height) + 1;
        // Calculate the height of the 'node.left.right' after tree rotation.
        ((Entry<T>) node.left.right).height = Math.max(node.height, ((Entry<T>) node.left).height) + 1;

        // Preform the actual left-right rotation
        Entry<T> leftTmpEntry = (Entry<T>) node.left.right.left;
        Entry<T> rightTmpEntry = (Entry<T>) node.left.right.right;

        node.left.right.left = node.left;
        node.left.right.right = node;
        node.left.right = leftTmpEntry;
        node.left = rightTmpEntry;
    }

    /**
     * Helper method that preforms the right rotation to balance AVL tree.
     *
     * @param node Entry where right rotation is preformed.
     */
    private void rightRotation(Entry<T> node) {
        // Root of the tree needs to be rotated so update the root.
        if(stack.peek() == null) { root = node.right; }
        // If not, then we need to update the parent's child element reference.
        else {
            if(stack.peek().element.compareTo(node.element) > 0) { stack.peek().left = node.right; }
            else { stack.peek().right = node.right; }
        }
        // Now, we need to reevaluate the height of 'node', so preform height calculations.
        // Calculate the height of the current entry 'node' after tree rotation.
        node.height = Math.max(node.right.left == null ? -1 : ((Entry<T>) node.right.left).height, node.left == null ? -1 : ((Entry<T>) node.left).height) + 1;

        // Calculate the height to the right of the child of 'node' after tree rotation.
        ((Entry<T>) node.right).height = Math.max(node.height, node.right.right == null ? -1 : ((Entry<T>) node.right.right).height) + 1;

        // Preform the actual left rotation
        Entry<T> tmp = (Entry<T>) node.right.left;
        node.right.left = node;
        node.right = tmp;
    }

    /**
     * Helper method that preforms the right-left rotation to balance AVL tree.
     *
     * @param node Entry where right-left rotation is preformed.
     */
    private void rightLeftRotation(Entry<T> node) {
        // Root of the tree needs to be rotated so update the root.
        if(stack.peek() == null) { root = node.right.left; }
        // If not, then we need to update the parent's child element reference.
        else {
            if(stack.peek().element.compareTo(node.element) > 0) { stack.peek().left = node.right.left; }
            else { stack.peek().right = node.right.left; }
        }
        // Now, we need to reevaluate the height of 'node', so preform height calculations.
        // Calculate the height of the current entry 'node' after tree rotation.
        node.height = Math.max(node.left == null ? -1 : ((Entry<T>) node.left).height, node.right.left.left == null ? -1 : ((Entry<T>) node.right.left.left).height) + 1;
        // Calculate the height of the right child of 'node' after tree rotation.
        ((Entry<T>) node.right).height = Math.max(node.right.right == null ? -1 : ((Entry<T>) node.right.right).height, node.right.left.right == null ? -1 : ((Entry<T>) node.right.left.right).height) + 1;
        // Calculate the height of the 'node.right.left' after tree rotation.
        ((Entry<T>) node.right.left).height = Math.max(node.height, ((Entry<T>) node.right).height) + 1;

        // Preform the actual right-left rotation.
        Entry<T> leftTmpEntry = (Entry<T>) node.right.left.left;
        Entry<T> rightTmpEntry = (Entry<T>) node.right.left.right;

        node.right.left.right = node.right;
        node.right.left.left = node;
        node.right.left = rightTmpEntry;
        node.right = leftTmpEntry;
    }

    /**
     * Helper method that balances the AVL tree by preforming rotations (when element is removed).
     */
    private void balanceTree() {
        while (!stack.isEmpty()) {
            // Pops entry from the stack in preparation of balancing.
            Entry<T> curr = (Entry<T>) stack.pop();

            // Now, we want to update the height of the entry 'curr'.
            updateHeight(curr);

            // Calculates balance factor for entry 'curr'.
            int bal = getBalance(curr);

            // Is the 'curr' entry left-side heavy? If so, rotate.
            if (bal > 1) {
                // Preform left heavy operations.
                if (getBalance((Entry<T>) curr.left) >= 0) {
                    // Left rotation on 'curr'.
                    leftRotation(curr);
                } else {
                    // Left-right rotation on 'curr'.
                    leftRightRotation(curr);
                }
            }
            // Is the 'curr' entry right-side heavy? If so, rotate.
            else if (bal < -1) {
                // Preform right heavy operations.
                if (getBalance((Entry<T>) curr.right) <= 0) {
                    // Right rotation on 'curr'.
                    rightRotation(curr);
                } else {
                    // Right-left rotation on 'curr'.
                    rightLeftRotation(curr);
                }
            }
        }
    }

    /**
     * Helper method that updates the height of a tree node based on left and right subtrees.
     *
     * @param node The node for which we want to update the height of.
     */
    private void updateHeight(Entry<T> node) {
        // Calculates the height of the left subtree (or -1 if it DNE).
        int heightLeft = node.left == null ? -1 : ((Entry<T>) node.left).height;

        // Calculates the height of the right subtree (or -1 if it DNE).
        int heightRight = node.right == null ? -1 : ((Entry<T>) node.right).height;

        // Sets the height of the node to the max height of 1 + left and right subtrees.
        node.height = Math.max(heightLeft, heightRight) + 1;
    }

    /**
     * Helper method that calculates the balance factor of a particular tree node.
     *
     * @param node The node we want to calculate the balance factor.
     * @return the balance factor of the node.
     */
    private int getBalance(Entry<T> node) {
        // Calculates the height of the left subtree (or -1 if it DNE).
        int heightLeft = node.left == null ? -1 : ((Entry<T>) node.left).height;

        // Calculates the height of the right subtree (or -1 if it DNE).
        int heightRight = node.right == null ? -1 : ((Entry<T>) node.right).height;

        // Initializes and sets the balFactor to the heightLeft - heightRight.
        int balFactor = heightLeft - heightRight;

        // Returns the 'balFactor' calculation.
        return balFactor;
    }
}