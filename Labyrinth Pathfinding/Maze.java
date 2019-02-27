import java.io.BufferedReader;
import java.io.IOException;

public class Maze {
	Cell[][] maze;
	private int width;
	private int height;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;
	private char orientare;

	/***
	 * 
	 * @param input
	 * @throws IOException
	 *             salveaza inputul intr-un obiect de tipul Maze
	 */
	Maze(BufferedReader input) throws IOException {
		String linie = input.readLine();
		String mazeInfo[] = linie.split(" ");
		this.height = Integer.parseInt(mazeInfo[0]);
		this.width = Integer.parseInt(mazeInfo[1]);
		maze = new Cell[height][width];
		this.setOrientare('B');
		
		for (int i = 0; i < this.height; i++) {
			String inputLine = input.readLine();

			for (int j = 0; j < this.width; j++) {
				if (inputLine.charAt(j) == '#') {
					maze[i][j] = new WallCell();
					continue;
				}
				if (inputLine.charAt(j) == '.') {
					maze[i][j] = new RoadCell(i, j);
					continue;
				}
				if (inputLine.charAt(j) == 'I') {
					maze[i][j] = new ICell(i, j);
					startI = i;
					startJ = j;
					continue;
				}
				maze[i][j] = new OCell(i, j);
				endI = i;
				endJ = j;
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getStartI() {
		return startI;
	}

	public void setStartI(int startI) {
		this.startI = startI;
	}

	public int getStartJ() {
		return startJ;
	}

	public void setStartj(int startJ) {
		this.startJ = startJ;
	}

	public int getEndJ() {
		return endJ;
	}

	public void setEndJ(int endJ) {
		this.endJ = endJ;
	}

	public int getEndI() {
		return endI;
	}

	public void setEndI(int endI) {
		this.endI = endI;
	}

	public char getOrientare() {
		return orientare;
	}

	public void setOrientare(char orientare) {
		this.orientare = orientare;
	}
}