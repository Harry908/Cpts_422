package TestFiles;

public class CS422Halstead {

	int sort(int x[], int n) {
		int i, j, save, im1;
		/* This function sorts array x in ascending order */
		if (n < 2)
			return 1;
		for (i = 2; i <= n; i++) {
			im1 = i - 1;
			for (j = 1; j <= im1; j++)
				if (x[i] < x[j]) {
					save = x[i];
					x[i] = x[j];
					x[j] = save;
				}
		}
		return 0;
	}
}