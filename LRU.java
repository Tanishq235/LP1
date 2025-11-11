package page;
import java.util.*;

public class LRU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();
        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter page reference string: ");
        for (int i = 0; i < n; i++) pages[i] = sc.nextInt();

        Integer[] frame = new Integer[frames];
        int[] lastUsed = new int[frames];
        Arrays.fill(lastUsed, -1);
        int faults = 0;

        System.out.println("\nLRU Page Replacement Simulation");
        System.out.printf("%-6s %-8s ", "Page", "Status");
        System.out.print("Frames\n");

        for (int time = 0; time < n; time++) {
            int page = pages[time];
            boolean hit = false;
            for (int j = 0; j < frames; j++) {
                if (frame[j] != null && frame[j] == page) {
                    hit = true;
                    lastUsed[j] = time;
                    break;
                }
            }

            String status;
            if (hit) status = "HIT";
            else {
                status = "FAULT";
                faults++;
                boolean placed = false;
                for (int j = 0; j < frames; j++) {
                    if (frame[j] == null) {
                        frame[j] = page;
                        lastUsed[j] = time;
                        placed = true;
                        break;
                    }
                }
                if (!placed) {
                    int lruIndex = 0;
                    for (int j = 1; j < frames; j++)
                        if (lastUsed[j] < lastUsed[lruIndex]) lruIndex = j;
                    frame[lruIndex] = page;
                    lastUsed[lruIndex] = time;
                }
            }

            String pageCol = page + " ->";
            System.out.printf("%-6s %-8s ", pageCol, status);
            for (Integer f : frame) System.out.printf("%-4s", (f == null ? "-" : f));
            System.out.println();
        }

        int hits = n - faults;
        System.out.println("\nTotal Page Faults: " + faults);
        System.out.println("Total Hits: " + hits);
        sc.close();
    }
}
