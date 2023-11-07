/**
 * @author Hunter Matthews - HJM210004
 * @date 10/20/23
 */

// replace package name with your netid
// package hjm210004;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Driver program for AVL tree implementation.

public class AVLTreeDriver {
    public static void main(String[] args) throws FileNotFoundException {
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
        boolean VERIFY = true;
        AVLTree<Long> avlTree = new AVLTree<>();
        // Initialize the timer
        Timer timer = new Timer();

        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    operand = sc.nextLong();
                    if(avlTree.add(operand)) {
                        result = (result + 1) % modValue;
                        if(VERIFY && !avlTree.verify())
                            System.out.println("Invalid AVL tree ");

                    }
                    break;
                }
                case "Remove": {
                    operand = sc.nextLong();
                    if (avlTree.remove(operand) != null) {
                        result = (result + 1) % modValue;
                        if(VERIFY && !avlTree.verify())
                            System.out.println("Invalid AVL tree ");
                    }
                    break;
                }
                case "Contains":{
                    operand = sc.nextLong();
                    if (avlTree.contains(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
            }
        }

        // End Time
        timer.end();

        System.out.println(result);
        System.out.println("Is valid AVL tree? " +  avlTree.verify());
        System.out.println(timer);
    }
}
