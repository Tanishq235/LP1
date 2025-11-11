package fc;
import java.util.*;

public class sjf {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int[] pid = new int[n];
        int[] at = new int[n];
        int[] bt = new int[n];
        int[] rt = new int[n]; // Remaining Time
        int[] ct = new int[n];
        int[] tat = new int[n];
        int[] wt = new int[n];
        boolean[] completed = new boolean[n];

        // Input
        for (int i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter Arrival Time and Burst Time for process " + pid[i] + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i];
        }

        int time = 0, completedCount = 0, shortest = -1;
        int minRt = Integer.MAX_VALUE;

        while (completedCount < n) {
            shortest = -1;
            minRt = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (at[i] <= time && !completed[i] && rt[i] < minRt) {
                    minRt = rt[i];
                    shortest = i;
                }
            }
            if (shortest == -1) {
                time++;
                continue;
            }

            rt[shortest]--;
            time++;

            if (rt[shortest] == 0) {
                completed[shortest] = true;
                completedCount++;
                ct[shortest] = time;
            }
        }

        for (int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
            wt[i] = tat[i] - bt[i];
        }
        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
                               ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        sc.close();
    }
}
