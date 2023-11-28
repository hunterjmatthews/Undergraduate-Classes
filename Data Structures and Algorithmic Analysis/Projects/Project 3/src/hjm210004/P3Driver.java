/**
 * @author Hunter Matthews - HJM210004
 * @date Due: 11/13/23
 */

// Change to your net id
package hjm210004;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class P3Driver {
    public static void main(String[] args) throws Exception {
        Scanner in;
        String filePath = "/Users/huntermatthews/IdeaProjects/Project 3/src/Test Cases/p3-t5.txt";

        File file = new File(filePath);
        in = new Scanner(file);
        // Everything else is the same after this.

        boolean VERBOSE = false;
        if (args.length > 1) { VERBOSE = Boolean.parseBoolean(args[1]); }

        String operation = "";
        int lineno = 0;

        MDS mds = new MDS();
        Timer timer = new Timer();
        int id, result, total = 0, price;
        List<Integer> name = new LinkedList<>();

        whileloop:
        while (in.hasNext()) {
            lineno++;
            result = 0;
            operation = in.next();
            if(operation.charAt(0) == '#') {
                in.nextLine();
                continue;
            }
            switch (operation) {
                case "End":
                    break whileloop;
                case "Insert":
                    id = in.nextInt();
                    price = in.nextInt();
                    name.clear();
                    while(true) {
                        int val = in.nextInt();
                        if(val == 0) { break; }
                        else { name.add(val); }
                    }
                    result = mds.insert(id, price, name);
                    break;
                case "Find":
                    id = in.nextInt();
                    result = mds.find(id);
                    break;
                case "Delete":
                    id = in.nextInt();
                    result = mds.delete(id);
                    break;
                case "FindMinPrice":
                    result = mds.findMinPrice(in.nextInt());
                    break;
                case "FindMaxPrice":
                    result = mds.findMaxPrice(in.nextInt());
                    break;
                case "FindPriceRange":
                    result = mds.findPriceRange(in.nextInt(), in.nextInt(), in.nextInt());
                    break;
                case "RemoveNames":
                    id = in.nextInt();
                    name.clear();
                    while(true) {
                        int val = in.nextInt();
                        if(val == 0) { break; }
                        else { name.add(val); }
                    }
                    result = mds.removeNames(id, name);
                    break;
                default:
                    System.out.println("Unknown operation at line " + lineno + ": " + operation);
                    break;
            }
            total += result;
            if(VERBOSE) { System.out.println(lineno + "\t" + operation + "\t" + result + "\t" + total); }
        }
        System.out.println(total);
        System.out.println(timer.end());
    }

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;

        public Timer() {
            startTime = System.currentTimeMillis();
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public Timer end() {
            endTime = System.currentTimeMillis();
            elapsedTime = endTime-startTime;
            memAvailable = Runtime.getRuntime().totalMemory();
            memUsed = memAvailable - Runtime.getRuntime().freeMemory();
            return this;
        }

        public String toString() {
            return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
        }
    }
}