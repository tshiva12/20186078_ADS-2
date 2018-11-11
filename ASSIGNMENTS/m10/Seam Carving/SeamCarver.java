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
	private double[][] initEnergies() {
        double[][] energies = new double[height()][width()];
        for (int i = 0; i < height(); i++) {
            for (int j = 0; j < width(); j++) {
                energies[i][j] = energy(j, i);
            }
        }
        return energies;
    }
    // pass through an array and mark the shorthest distance from top to entry
    private void topologicalSort(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        for (int row = 1; row < h; row++) {
            for (int col = 0; col < w; col++) {
                double temp = energies[row - 1][col];
                double min = 0;
                if (col == 0) {
                    min = temp;
                } else {
                    min = Math.min(temp, energies[row - 1][col - 1]);
                }

                if (col != (w - 1)) {
                    min = Math.min(min, energies[row - 1][col + 1]);
                } else {
                	min = min;
                }
                energies[row][col] += min;
            }
        }
    }
    private double[][] transposeGrid(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        double[][] trEnergies = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                trEnergies[col][row] = energies[row][col];
            }
        }
        return trEnergies;
    }
    private int[] minVerticalPath(double[][] energies) {
        int h = energies.length, w = energies[0].length;
        int[] path = new int[h];
        topologicalSort(energies);
        // find the lowest element in last row
        path[h - 1] = 0;
        for (int i = 0; i < w; i++) {
            if (energies[h - 1][i] < energies[h - 1][path[h - 1]])
                path[h - 1] = i;
        }
        // trace path back to first row
        // assuming we need the cheapest upper neighboring entry
        for (int row = h - 2; row >= 0; row--) {
            int col = path[row + 1];
            // three neighboring, priority to center
            path[row] = col;
            if (col > 0 && energies[row][col - 1] < energies[row][path[row]])
                path[row] = col - 1;
            if (col < (w - 2) && energies[row][col + 1] < energies[row][path[row]])
                path[row] = col + 1;
        }
        return path;
    }
	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		double[][] transposeEnergies = transposeGrid(initEnergies());
		return minVerticalPath(transposeEnergies);
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		double[][] normalEnergies = initEnergies();
		return minVerticalPath(normalEnergies);
	}
	// remove horizontal seam from current picture
	public void removeHorizontalSeam(int[] seam) {
		if (height() <= 1 || !isValid(seam, width(), height() - 1))
            throw new IllegalArgumentException("IllegalArgumentException");
        Picture pic = new Picture(width(), height() - 1);
        for (int w = 0; w < width(); w++) {
            for (int h = 0; h < seam[w]; h++) {
                pic.set(w, h, this.picture.get(w, h));
            }

            for (int h = seam[w] + 1; h < height(); h++) {
                pic.set(w, h - 1, this.picture.get(w, h));
            }

        }
        this.picture = pic;
	}
	// remove vertical seam from current picture
	public void removeVerticalSeam(int[] seam) {
		if (width() <= 1 || !isValid(seam, height(), width()))
            throw new IllegalArgumentException("IllegalArgumentException");
        Picture pic = new Picture(width() - 1, height());
        for (int h = 0; h < height(); h++) {
            for (int w = 0; w < seam[h]; w++) {
                pic.set(w, h, this.picture.get(w, h));
            }
                

            for (int w = seam[h] + 1; w < width(); w++) {
                pic.set(w - 1, h, this.picture.get(w, h));
            }

        }
        this.picture = pic;
	}
	// return false if two consecutive entries differ by more than 1
    private boolean isValid(int[] a, int len, int range) {
        if (a == null) {
            return false;
        }
        if (a.length != len || a[0] < 0 || a[0] > range) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (a[i] < Math.max(0, a[i - 1] - 1) || a[i] > Math.min(range, a[i - 1] + 1))
                return false;
        }
        return true;
    }
}