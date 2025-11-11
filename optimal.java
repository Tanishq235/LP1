package page;
import java.util.*;

public class Optima {
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
        int faults = 0;

        System.out.println("\nOPTIMAL Page Replacement Simulation");
        System.out.printf("%-6s %-8s ", "Page", "Status");
        System.out.print("Frames\n");

        for (int i = 0; i < n; i++) {
            int page = pages[i];
            boolean hit = false;
            for (Integer f : frame) if (f != null && f == page) { hit = true; break; }

            String status;
            if (hit) {
                status = "HIT";
            } else {
                status = "FAULT";
                faults++;
                boolean placed = false;
                for (int j = 0; j < frames; j++) {
                    if (frame[j] == null) { frame[j] = page; placed = true; break; }
                }
                if (!placed) {
                    int replaceIndex = -1;
                    int farthest = -1;
                    for (int j = 0; j < frames; j++) {
                        int nextUse = Integer.MAX_VALUE;
                        for (int k = i + 1; k < n; k++) {
                            if (frame[j] == pages[k]) { nextUse = k; break; }
                        }
                        if (nextUse == Integer.MAX_VALUE) { replaceIndex = j; break; }
                        if (nextUse > farthest) { farthest = nextUse; replaceIndex = j; }
                    }
                    frame[replaceIndex] = page;
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
