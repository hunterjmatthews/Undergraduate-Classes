# Problem:
There are two parts in this project

1. Implement binary search trees.  Starter code BinarySearchTree.java is in the shared folder.
   The methods you need to implement are: contains(), add(), remove(). Other methods are optional.
   Do not define parent field in Entry class. Implement the single pass algorithm discussed in the class.


2. Extend BST to AVL tree.  Starter code: AVLTree.java is in the shared folder. Driver code is also provided.
   The methods you need to implement are: add(), verify.
   Implement remove() for extra credits (5 points will be added to Theory part).
   Do not rewrite the code for find(), add(), and remove(). Inherit the methods from BinarySearchTree class.
   
   The number of rotations required during deletion could be O(log n). We may have to do rotation at every level.
   For more details, check this https://webdocs.cs.ualberta.ca/~holte/T26/tree-deletion.html
