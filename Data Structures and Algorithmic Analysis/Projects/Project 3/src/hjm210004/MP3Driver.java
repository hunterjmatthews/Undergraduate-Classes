package hjm210004;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class P3Driver {
    public static void main(String[] args) throws Exception {
        Scanner in;
        PrintWriter writer = null;
        String filePath = "/path/to/testcase/p3-t5.txt";
        String outputFilePath = "output.txt"; // Define the output file path

        File file = new File(filePath);
        in = new Scanner(file);

        try {
            writer = new PrintWriter(outputFilePath, "UTF-8");
        } catch (IOException e) {
            System.out.println("An error occurred while opening the output file.");
            e.printStackTrace();
        }

        boolean VERBOSE = true;
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
            if (VERBOSE && writer != null) {
                writer.println(lineno + "\t" + operation + "\t" + result + "\t" + total);
            }
        }
        if (writer != null) {
            writer.println("Total: " + total);
            writer.println(timer.end());
            writer.close();
        } else {
            System.out.println("Total: " + total);
            System.out.println(timer.end());
        }
    }

    public static class Timer {
        long startTime, endTime, elapsedTime, memAvailable, memUsed;

        public Timer() {
            startTime = System.currentTimeMillis();
        }

        public void start() {
            startTime = System.currentTimeMillis();
        }

        public P3Driver.Timer end() {
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

