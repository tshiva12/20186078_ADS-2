import java.awt.Color;
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
		return picture;
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
		if (x == 0 || y == 0 || picture.width() - 1 == x || picture.height() - 1 == y) {
			return 1000;
		}
		double energy = Math.sqrt(Horizontal(x, y) + Vertical(x, y));
		return energy;
	}
	private double Horizontal(int x, int y) {
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		int red = right.getRed() - left.getRed();
		int green = right.getGreen() - left.getGreen();
		int blue = right.getBlue() - left.getBlue();
		int horizontal = (red * red) + (green * green) + (blue * blue);
		return horizontal;
	}
	private double Vertical(int x, int y) {
		Color top = picture.get(x, y - 1);
		Color bottom = picture.get(x, y + 1);
		int redV = top.getRed() - bottom.getRed();
		int greenV = top.getGreen() - bottom.getGreen();
		int blueV = top.getBlue() - bottom.getBlue();
		int vertical = (redV * redV) + (greenV * greenV) + (blueV * blueV);
		return vertical;
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