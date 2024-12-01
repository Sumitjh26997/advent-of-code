package day1;

import java.io.*;
import java.util.*;


public class P1 {
	private final List<Integer> listLeft;
	private final List<Integer> listRight;

	public P1(String filePath) {
		this.listLeft = new ArrayList<>();
		this.listRight = new ArrayList<>();
		this.readFile(filePath);
	}

	private void readFile(String filePath) {
			try {
					File file = new File(filePath);
					Scanner sc = new Scanner(file);
					while(sc.hasNextLine()) {
							String line = sc.nextLine();
							String[] values = line.split(";");
							this.listLeft.add(Integer.parseInt(values[0]));
							this.listRight.add(Integer.parseInt(values[1]));
					}
					sc.close();
			} catch (FileNotFoundException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
			}
	}

	private void printLists() {
			for(Integer l : this.listLeft) {
					System.err.println(l);
			}

			for(Integer r : this.listRight) {
					System.err.println(r);
			}
	}

  // part 1 solution
	private int getTotalDifference() {
			int totalDifference = 0;
			Collections.sort(this.listLeft);
			Collections.sort(this.listRight);

			for(int i = 0; i < this.listLeft.size(); i++) {
					totalDifference += Math.abs(this.listLeft.get(i) - this.listRight.get(i));
			}
			return totalDifference;
	}

  // part 2 solution
	private int similarityScore() {
			Map<Integer, Integer> map = new HashMap<>();
			int similarityScore = 0;

			for (Integer num : this.listRight) {
					map.put(num, map.getOrDefault(num, 0) + 1);
			}

			for (Integer n: this.listLeft) {
					if (map.containsKey(n)) {
							similarityScore += n * map.get(n);
					}
			}

			return similarityScore;
	}
	public static void main(String[] args) {
			P1 p1 = new P1("day1/input.txt");
			// p1.printLists();
			System.out.println(p1.getTotalDifference());
			System.out.println(p1.similarityScore());
	}
}