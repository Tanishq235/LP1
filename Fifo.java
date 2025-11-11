package page;
import java.util.*;
public class Fifo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter page reference string: ");
        for (int i = 0; i < n; i++) {
            pages[i] = sc.nextInt();
        }

        Integer[] frame = new Integer[frames];
        Queue<Integer> q = new LinkedList<>();
        int faults = 0;
        int currentFrameIndex = 0;

        System.out.println("\nFIFO Page Replacement Simulation");
        System.out.println("Page\tStatus\t\tFrames");

        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean hit = false;

            for (int j = 0; j < frames; j++) {
                if (frame[j] != null && frame[j] == page) {
                    hit = true;
                    break;
                }
            }

            String status;
            if (hit) {
                status = "HIT";
            } else {
                status = "FAULT";
                faults++;

                if (currentFrameIndex < frames) {
                    frame[currentFrameIndex] = page;
                    q.add(currentFrameIndex);
                    currentFrameIndex++;
                } else {
                    int idx = q.poll();
                    frame[idx] = page;
                    q.add(idx);
                }
            }

            // Print a nicely formatted line
            System.out.printf("%d\t%-7s\t", page, status); // aligns status
            for (Integer f : frame) {
                System.out.print((f == null ? "-" : f) + "\t");
            }
            System.out.println();
        }

        int hits = n - faults;
        System.out.println("\nTotal Page Faults: " + faults);
        System.out.println("Total Hits: " + hits);

        sc.close();
    }
}
