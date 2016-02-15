package com.gnostrenoff.cdb.page;

public class Page {

	public static int nbPages;
	public static int nbTotalElement;
	public static int nbElementPerPage = 10;
	public static int currentPageIndex = 1;

	public static int computeOffset() {
		int offset;
		offset = (currentPageIndex - 1) * nbElementPerPage;
		return offset;
	}

	public static void updateValues(int total) {
		nbTotalElement = total;
		nbPages = nbTotalElement / nbElementPerPage;
		if (nbTotalElement % nbElementPerPage != 0) {
			nbPages++;
		}
	}
}
