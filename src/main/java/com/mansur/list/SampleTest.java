<<<<<<< HEAD
package com.mansur.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class SampleTest {

	public static void main(String[] args) throws Exception {
		
		getResilientConnection(true);

		for (int i = 0; i < 20; /* i++ */) {
			int index = (int) (Math.random() * 10) % 7 + 1;
			// System.out.printf("%d ", index - 1);
			String message = String.format("Issue occured, retry attempt #%d will be attempted : %s",
					new Object[] { ++i, "Hello connection error" });
			System.out.println(message);
		}

		/*
		 * 
		 * List<String> problemList = getProblemPods("UppercasePods.txt"); List<String>
		 * affectedList = getWaveAffectedPods("UpgradeJan28.txt", problemList);
		 * affectedList.sort((x, y) -> x.compareTo(y));
		 * System.out.println(affectedList);
		 */

	}

	private static void getResilientConnection(boolean isThrow) {
		// retry 3 times with a gap of 30 sec
		for (int i = 0; i < 3;) {
			try {
				System.out.println("Get the connection...");
				if(isThrow) {
					throw new Exception("New JDBC connection error");
				}
				break;
			} catch (Exception e) {
				System.out.println(String.format("Issue occured, retry #%d will be attempted : %s",
						new Object[] { ++i, "Hello Error" }));
				try {
					System.out.println("Sleeping for a bit");
					TimeUnit.SECONDS.sleep(30);
					System.out.println("Now awake to retry");
				} catch (Exception innerException) {
					System.out.println(String.format("Sleep issue: {0}", new Object[] { "Sleep Error" }));
				}
			}
		}
		System.out.println("By this time, the connection should have been gotten..");
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
=======
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
>>>>>>> 7333725b9b4e9f8cdec46ac10aa6dc84de817ba0
