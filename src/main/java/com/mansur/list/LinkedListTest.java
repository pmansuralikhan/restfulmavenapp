package com.mansur.list;

public class LinkedListTest {

	public static void main(String[] args) {
		LinkedList list = createList(new int[] { 3, 1, 7, 3, 1, 5, 7, 8, 9, 10, 4, 4 });
		printList(list);

		nthFromLastElement(list, 5);
		nthFromLastElement(list, 2);
		nthFromLastElement(list, 1);
		findDuplicates(list);
		LinkedList reverse = reverseList(list);

		partitionList(reverse, 5);
		removeDuplicates(reverse);
		printList(reverse);
	}

	private static LinkedList reverseList(LinkedList list) {
		// had to use 3 pointers
		LinkedList prev = list, curr = list.next, next = list.next.next;

		prev.next = null;
		while (curr.next != null) {
			curr.next = prev;
			prev = curr;
			curr = next;
			next = curr.next;
		}
		curr.next = prev;
		System.out.print("Reversed: ");
		printList(curr);
		return curr;

	}

	private static void partitionList(LinkedList list, int i) {
		if (list == null) {
			System.out.println("Nothing to partition");
		} else if (list.next == null) {
			System.out.println("No need to partition");
			printList(list);
		} else {
			LinkedList curr = list, firstList = null, secondList = null, firstListCurr = null, secondListCurr = null;
			while (curr.next != null) {
				if (curr.data <= i) {
					if (firstList == null) {
						firstList = curr;
					} else {
						firstListCurr.next = curr;
					}
					firstListCurr = curr;
				} else {
					if (secondList == null) {
						secondList = curr;
					} else {
						secondListCurr.next = curr;
					}
					secondListCurr = curr;
				}
				curr = curr.next;
			}
			if (firstListCurr != null) {
				firstListCurr.next = null;
			}
			if (secondListCurr != null) {
				secondListCurr.next = null;
			}

			System.out.print("First list: ");
			printList(firstList);
			System.out.print("Second list: ");
			printList(secondList);
		}
	}

	private static LinkedList createList(int[] a) {
		LinkedList head = null, curr = null;
		for (int ai : a) {
			LinkedList newNode = new LinkedList(ai, null);
			if (head == null) {
				curr = newNode;
				head = newNode;
			} else {
				curr.next = newNode;
				curr = newNode;
			}
		}
		return head;
	}

	private static void printList(LinkedList list) {
		if (list == null) {
			System.out.println("List is empty");
		} else {
			do {
				if (list.next != null) {
					System.out.print(list.data + "-->");
				} else {
					System.out.print(list.data);
				}
				list = list.next;
			} while (list != null);
		}
		System.out.println("");
	}

	private static void findDuplicates(LinkedList list) {
		LinkedList head = list, curr = list;
		while (head != null) {
			int occurence = 0;
			int lookup = head.data;
			// System.out.println("Looking up " + head.data);
			// curr = curr.next;
			while (curr != null) {
				if (lookup == curr.data) {
					occurence++;
					if (occurence > 1) {
						// System.out.println("Duplicate found: " + lookup);
						break;
					}
				}
				curr = curr.next;
			}
			curr = list;
			head = head.next;
		}
	}

	private static void removeDuplicates(LinkedList list) {
		LinkedList head = list, curr = list;
		while (head != null) {
			while (curr != null && curr.next != null) {
				if (head.data == curr.next.data) {
					curr.next = curr.next.next;
				}
				curr = curr.next;
			}
			// reset pointers
			head = head.next;
			curr = head;
		}
	}

	private static int nthFromLastElement(LinkedList list, int n) {
		LinkedList p1, p2;
		p1 = list;
		p2 = list;

		// move p2 n times
		for (int i = 0; i < n - 1; i++) {
			if (p2.next != null) {
				p2 = p2.next;
			}
		}

		// move till p2 ends
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		System.out.println(n + "th element from last: " + p1.data);
		return p1.data;

	}
}

class LinkedList {
	public int data;
	public LinkedList next;

	public LinkedList(int num, LinkedList next) {
		this.data = num;
		this.next = next;
	}

	public String toString() {
		return data + ": " + next;
	}
}
