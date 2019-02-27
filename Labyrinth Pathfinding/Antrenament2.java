import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Antrenament2 {
	/**
	 * 
	 * @param matrix
	 * @param output
	 * @throws IOException
	 *             - cauta cel mai scurt drum, stiind deja labirintul
	 */
	void cuHarta(Maze matrix, BufferedWriter output) throws IOException {

		// Cream o matrice cu -1 - unde e zid, 0 - unde e celula valida
		int[][] binaryMaze = new int[matrix.getHeight()][matrix.getWidth()];
		for (int i = 0; i < matrix.getHeight(); i++)
			for (int j = 0; j < matrix.getWidth(); j++) {
				if (matrix.maze[i][j] instanceof WallCell)
					binaryMaze[i][j] = -1;
				else
					binaryMaze[i][j] = 0;
			}

		// Cream o matrice pentru a stoca distanta unei celule valide fata de start
		int[][] bfsLevel = new int[binaryMaze.length][binaryMaze[0].length];
		for (int i = 0; i < binaryMaze.length; ++i)
			for (int j = 0; j < binaryMaze[0].length; ++j) {
				if (binaryMaze[i][j] == -1)
					bfsLevel[i][j] = -1;
				else
					bfsLevel[i][j] = 0;
			}

		List<Coordinate> road = bfsPath(binaryMaze, matrix.getStartI(), matrix.getStartJ(), matrix.getEndI(),
				matrix.getEndJ(), matrix.getOrientare(), matrix, bfsLevel, output);

		if (road == null) {
			System.out.println("Drum invalid");
			return;
		}

	}

	/**
	 * 
	 * @param maze
	 * @param istart
	 * @param jstart
	 * @param iend
	 * @param jend
	 * @param orientare
	 * @param matrix
	 * @param bfsLevel
	 * @param output
	 * @return
	 * @throws IOException
	 *             - algoritm BFS care cauta cel mai scurt drum folosind distanta
	 *             unei celule fata de start
	 */
	List<Coordinate> bfsPath(int[][] maze, int istart, int jstart, int iend, int jend, char orientare,
			Maze matrix, int[][] bfsLevel, BufferedWriter output) throws IOException {

		// Cream o lista cu drumul curent
		LinkedList<Coordinate> queue = new LinkedList<Coordinate>();
		// Adaugam startul
		Coordinate start = new Coordinate(istart, jstart);
		queue.add(start);

		bfsLevel[start.x][start.y] = 1;
		char startOrientation = 0;
		char orientation = orientare;
		char neighboursOrientation[] = new char[4];

		// Cat timp avem cel putin o miscare in drum
		while (!queue.isEmpty()) {

			int[] directionI = null, directionY = null;
			// Cream vectorii pentru schimbarea orientarii
			// E S V N
			if (orientation == 'A') {
				directionI = new int[] { 1, 0, -1, 0 };
				directionY = new int[] { 0, 1, 0, -1 };
			}
			if (orientation == 'B') {
				directionI = new int[] { 0, -1, 0, 1 };
				directionY = new int[] { 1, 0, -1, 0 };
			}
			if (orientation == 'C') {
				directionI = new int[] { -1, 0, 1, 0 };
				directionY = new int[] { 0, -1, 0, 1 };
			}
			if (orientation == 'D') {
				directionI = new int[] { 0, 1, 0, -1 };
				directionY = new int[] { -1, 0, 1, 0 };
			}
			// Luam o pereche de coordonate din drum
			Coordinate cell = queue.poll();

			// In afara labirintului -> arunca exceptia HeroOutOfGroundException
			if (cell.x == matrix.getHeight() || cell.x < 0 || cell.y == matrix.getWidth() || cell.y < 0) {
				try {
					throw new HeroOutOfGroundException();
				} catch (HeroOutOfGroundException e) {
					e.printStackTrace();
				}
			}
			// Celula zid -> arunca exceptia CannotMoveIntoWallsException
			if (maze[cell.x][cell.y] == -1) {
				try {
					throw new CannotMoveIntoWallsException();
				} catch (CannotMoveIntoWallsException e) {
					e.printStackTrace();
				}
			}
			// Daca am ajuns la iesire, ne oprim
			if (cell.x == iend && cell.y == jend)
				break;

			int level = bfsLevel[cell.x][cell.y];
			char currentOrientation = 0;

			// Verificam in toate cele 4 directii pentru coordonatele curente,
			// in functie de vederea E S V N
			for (int index = 0; index < 4; index++) {
				int x = cell.x + directionI[index];
				int y = cell.y + directionY[index];

				// Salvam orientarea curenta a coordonatelor curentE
				if (index == 0)
					currentOrientation = orientation;

				// Ne mutam orientarea in functie de vedere
				orientation = Orientation.rightOrientation(currentOrientation, index);

				// Daca iesirea e celula vecina, adaugam orientarea intr-un vector
				if (x == iend && y == jend) {
					if (orientation == 'A')
						neighboursOrientation[2] = orientation;
					else if (orientation == 'B')
						neighboursOrientation[3] = orientation;
					else if (orientation == 'C')
						neighboursOrientation[0] = orientation;
					else if (orientation == 'D')
						neighboursOrientation[1] = orientation;

				}
				// Daca incercam sa accesam in afara perimetrului, continuam cautarea
				if (x < 0 || y < 0 || x == maze.length || y == maze[0].length)
					continue;

				// Daca celula este valida, o adaugam la drum si salvam distanta ei
				// fata de start
				if (bfsLevel[x][y] == 0) {
					queue.add(new Coordinate(x, y));
					bfsLevel[x][y] = level + 1;
				}
			}
		}

		// Tratam exceptia : daca nivelul iesirii a ramas 0
		if (bfsLevel[iend][jend] == 0)
			return null;

		// Cream o noua lista de coordonate - drumul final minim.
		LinkedList<Coordinate> road = new LinkedList<Coordinate>();
		Coordinate cell = new Coordinate(iend, jend);

		// Distanta e data de nivelul celulei cu iesirea
		int distant = bfsLevel[iend][jend];

		output.write(Integer.toString(distant));
		output.newLine();

		orientation = 0;
		// Incepem de la iesire si luam directia prioritara dintre
		// E S V N
		for (int index = 0; index < 4; index++)
			if (neighboursOrientation[index] != 0) {
				startOrientation = neighboursOrientation[index];
				break;
			}

		// Orientarea va fi inversa fata de cea cu care s-a ajuns la iesire
		orientation = Orientation.reverseOrientation(startOrientation);

		// Cat timp nu am ajuns la celula start
		while (!cell.equals(start)) {
			// Adaugam celula curenta la drumul final
			road.push(cell);

			int level = bfsLevel[cell.x][cell.y];
			int[] directionI = null, directionY = null;
			// In functie de pozitia iesirii fata de start, ne miscam
			// V S E N sau E S V N
			if ((iend < istart && jend <= jstart) || (iend >= istart && jend < jstart)
					|| (iend < istart && jend > jstart)) {
				// Ne miscam V S E N
				if (orientation == 'B') {
					directionI = new int[] { 0, -1, 0, 1 };
					directionY = new int[] { -1, 0, 1, 0 };
				}
				if (orientation == 'A') {
					directionI = new int[] { -1, 0, 1, 0 };
					directionY = new int[] { 0, 1, 0, -1 };
				}
				if (orientation == 'C') {
					directionI = new int[] { 1, 0, -1, 0 };
					directionY = new int[] { 0, -1, 0, 1 };
				}
				if (orientation == 'D') {
					directionI = new int[] { 0, 1, 0, -1 };
					directionY = new int[] { 1, 0, -1, 0 };
				}
			}
			if ((iend >= istart && jend >= jstart)) {

				// Ne miscam E S V N
				if (orientation == 'B') {
					directionI = new int[] { 0, -1, 0, 1 };
					directionY = new int[] { 1, 0, -1, 0 };
				}
				if (orientation == 'A') {
					directionI = new int[] { 1, 0, -1, 0 };
					directionY = new int[] { 0, 1, 0, -1 };
				}
				if (orientation == 'C') {
					directionI = new int[] { -1, 0, 1, 0 };
					directionY = new int[] { 0, -1, 0, 1 };
				}
				if (orientation == 'D') {
					directionI = new int[] { 0, 1, 0, -1 };
					directionY = new int[] { -1, 0, 1, 0 };
				}
			}
			char currentOrientation = 0;
			// Verificam in toate cele 4 directii pentru coordonatele curente,
			// in functie de vedere
			for (int index = 0; index < 4; index++) {
				int x = cell.x + directionI[index];
				int y = cell.y + directionY[index];

				if (index == 0)
					currentOrientation = orientation;

				if (index == 0) {
					if ((iend < istart && jend <= jstart) || (iend >= istart && jend < jstart)
							|| (iend < istart && jend > jstart))
						// Luam orientarea pentru V S E N
						orientation = Orientation.leftOrientation(currentOrientation, index);

					if ((iend >= istart && jend >= jstart))
						// Luam orientarea pentru E S V N
						orientation = Orientation.rightOrientation(currentOrientation, index);

				}
				if (index == 1 || index == 3) {
					// Luam orientarea pentru E S V N
					orientation = Orientation.rightOrientation(currentOrientation, index);
				}
				if (index == 2) {
					if ((iend < istart && jend <= jstart) || (iend >= istart && jend < jstart)
							|| (iend < istart && jend > jstart))
						// Luam orientarea pentru V S E N
						orientation = Orientation.leftOrientation(currentOrientation, index);

					if ((iend >= istart && jend >= jstart))
						// Luam orientarea pentru E S V N
						orientation = Orientation.rightOrientation(currentOrientation, index);
				}

				if (x < 0 || y < 0 || x == maze.length || y == maze[0].length)
					continue;

				// Daca distanta celulei vecine fata de cea la care se verifica vecinii este
				// aceeasi, ne mutam pe acea celula
				if (bfsLevel[x][y] == level - 1) {
					cell = new Coordinate(x, y);
					break;
				}
			}
		}
		// Adaugam startul si scriem drumul final in fisier
		road.push(start);
		for (Coordinate celula : road) {
			output.write(celula.x + " " + celula.y);
			output.newLine();
		}
		output.close();
		return road;
	}

}

class Coordinate {
	public int x;
	public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object cell) {
		if (this == cell)
			return true;
		if ((cell == null) || (cell.getClass() != this.getClass()))
			return false;
		if (x == ((Coordinate) cell).x && y == ((Coordinate) cell).y)
			return true;
		return false;
	}
}
