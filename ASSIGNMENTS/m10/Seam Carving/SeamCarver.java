
public class SeamCarver {
	private Picture picture;
	// create a seam carver object based on the given picture
	public SeamCarver(Picture picture1) {
		if (picture1 == null) {
			throw new IllegalArgumentException("picture is null");
		}
		this.picture = picture1;
	}
	// current picture
	public Picture picture() {
		return null;
	}
	// width of current picture
	public int width() {
		return picture.width();
	}

	// height of current picture
	public int height() {
		return picture.height();
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return 0;
	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		return new int[0];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return new int[0];
	}

	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {

	}

	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {

	}
}