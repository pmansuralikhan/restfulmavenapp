package com.mansur.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SampleTest {

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < 20; i++) {
			int index = (int) (Math.random() * 10) % 7 + 1;
			// System.out.printf("%d ", index - 1);
		}

		List<String> problemList = getProblemPods("UppercasePods.txt");
		List<String> affectedList = getWaveAffectedPods("UpgradeJan28.txt", problemList);
		affectedList.sort((x, y) -> x.compareTo(y));
		System.out.println(affectedList);

	}

	public static List<String> getWaveAffectedPods(String wavePodFilename, List<String> problemPods) throws Exception {
		File wavePodList = new File("UpgradeJan28.txt");
		BufferedReader reader = null;
		List<String> affectedWeekList = new ArrayList<String>();

		try {
			reader = new BufferedReader(new FileReader(wavePodList));
			String line = "";
			while ((line = reader.readLine()) != null) {
				// System.out.println("-> " + line);
				String[] linePods = line.split("[ ,]");
				for (String pod : linePods) {
					if (!pod.trim().isEmpty()) {
						// System.out.println("'" + pod + "'");
						if (problemPods.contains(pod) || problemPods.contains(pod.toLowerCase())
								|| problemPods.contains(pod.toUpperCase())) {
							// System.out.println(pod);
							affectedWeekList.add(pod);
						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null)
				reader.close();
		}
		return affectedWeekList;
	}

	public static List<String> getProblemPods(String filename) throws Exception {
		List<String> problemList = new ArrayList<String>();
		File podList = new File(filename);
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(podList));
			String line = "";
			while ((line = reader.readLine()) != null) {
				// System.out.println("-> " + line);
				String[] linePods = line.split("[ ,]");
				for (String pod : linePods) {
					if (!pod.trim().isEmpty()) {
						problemList.add(pod);
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (reader != null)
				reader.close();
		}
		return problemList;

	}

}
