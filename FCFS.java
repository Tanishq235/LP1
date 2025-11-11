package fc;
import java.util.*;
public class fcfs {
	// FCFS (First Come First Serve) 
	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in); 

	        System.out.print("Enter number of processes: ");
	        int n = sc.nextInt(); 

	        int[] pid = new int[n];
	        int[] at = new int[n];
	        int[] bt = new int[n];
	        int[] ct = new int[n];
	        int[] tat = new int[n];
	        int[] wt = new int[n];

	        // Input section
	        for (int i = 0; i < n; i++) {
	            pid[i] = i + 1;
	            System.out.print("Enter Arrival Time and Burst Time for process " + pid[i] + ": ");
	            at[i] = sc.nextInt();
	            bt[i] = sc.nextInt();
	        }

	        // Sort processes by arrival time (simple bubble sort)
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (at[j] > at[j + 1]) {
	                    // Swap arrival times
	                    int temp = at[j]; at[j] = at[j + 1]; at[j + 1] = temp;
	                    // Swap burst times
	                    temp = bt[j]; bt[j] = bt[j + 1]; bt[j + 1] = temp;
	                    // Swap process IDs
	                    temp = pid[j]; pid[j] = pid[j + 1]; pid[j + 1] = temp;
	                }
	            }
	        }

	        int time = 0;

	        // Scheduling logic
	        for (int i = 0; i < n; i++) {
	            if (time < at[i]) {
	                time = at[i]; // CPU idle till this time
	            }

	            ct[i] = time + bt[i];   // Completion time
	            tat[i] = ct[i] - at[i]; // Turnaround time
	            wt[i] = tat[i] - bt[i]; // Waiting time

	            time = ct[i]; // Move time forward
	        }

	        // Display the table
	        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
	        for (int i = 0; i < n; i++) {
	            System.out.println(pid[i] + "\t" + at[i] + "\t" + bt[i] + "\t" +
	                               ct[i] + "\t" + tat[i] + "\t" + wt[i]);
	        }

	        sc.close();
	    }
	}


