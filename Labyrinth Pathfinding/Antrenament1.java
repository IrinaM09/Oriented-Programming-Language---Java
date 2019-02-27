import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

public class Antrenament1 {
	/**
	 * 
	 * @param matrix
	 * @param output
	 * @throws IOException
	 *             - cauta drumul cel mai scurt folosind metoda greedy
	 */
	void faraHarta(Maze matrix, BufferedWriter output) throws IOException {

		// Luam Start-ul maze-ului, din care Dubluve trebuie sa plece
		int i = matrix.getStartI();
		int j = matrix.getStartJ();
		matrix.maze[i][j].setNoVisited(1);
		List<Pair> neighbours = new ArrayList<Pair>();
		Pair pair = null;
		int noPositions = 1;
		int isExitCell = 0;
		char pos = 0;
		int dreapta[] = { 1, 0, 0, 1, -1, 0, 0, -1 };
		int stanga[] = { -1, 0, 0, -1, 1, 0, 0, 1 };
		int sus[] = { 0, 1, -1, 0, 0, -1, 1, 0 };
		int jos[] = { 0, -1, 1, 0, 0, 1, -1, 0 };
		int directii[] = new int[8];

		List<Cell> road = new ArrayList<Cell>();
		road.add(0, matrix.maze[i][j]);

		//Cat timp celula curenta nu este cea de iesire
		while (!(matrix.maze[i][j] instanceof OCell)) {

			//Daca este in afara labirintului 
			if (i == matrix.getHeight() || i < 0 || j == matrix.getWidth() || j < 0) {
				output.close();
				try {
					throw new HeroOutOfGroundException();
				} catch (HeroOutOfGroundException e) {
					e.printStackTrace();
				}
			}
			//Daca se afla pe o celula zid
			if (matrix.maze[i][j].getVisitable() == false) {
				output.close();
				try {
					throw new CannotMoveIntoWallsException();
				} catch (CannotMoveIntoWallsException e) {
					e.printStackTrace();
				}
			}

			if (matrix.getOrientare() == 'B') {
				directii = sus;
			}
			if (matrix.getOrientare() == 'A') {
				directii = dreapta;
			}
			if (matrix.getOrientare() == 'C') {
				directii = stanga;
			}
			if (matrix.getOrientare() == 'D') {
				directii = jos;
			}

			//Ne uitam la cel din dreapta
			if (i + directii[0] < matrix.getHeight() && i + directii[0] >= 0 && j + directii[1] >= 0
					&& j + directii[1] < matrix.getWidth())
				// daca e celula valida, o adaugam in lista de vecini
				if (matrix.maze[i + directii[0]][j + directii[1]].getVisitable() == true) {
					pair = new Pair(matrix.maze[i + directii[0]][j + directii[1]].getNoVisited(), 'A');
					neighbours.add(0, pair);
					// verificam daca este iesirea
					if (i + directii[0] == matrix.getEndI() && j + directii[1] == matrix.getEndJ()) {
						isExitCell = 1;
						pos = neighbours.get(0).getPosition();
					}
				}

			//Ne uitam la cel de deasupra
			if (i + directii[2] < matrix.getHeight() && i + directii[2] >= 0 && j + directii[3] >= 0
					&& j + directii[3] < matrix.getWidth())
				if (matrix.maze[i + directii[2]][j + directii[3]].getVisitable() == true) {
					pair = new Pair(matrix.maze[i + directii[2]][j + directii[3]].getNoVisited(), 'B');
					neighbours.add(0, pair);
					if (i + directii[2] == matrix.getEndI() && j + directii[3] == matrix.getEndJ()) {
						isExitCell = 1;
						pos = neighbours.get(0).getPosition();
					}
				}

			//Ne uitam la cel din stanga
			if (i + directii[4] < matrix.getHeight() && i + directii[4] >= 0 && j + directii[5] >= 0
					&& j + directii[5] < matrix.getWidth())
				if (matrix.maze[i + directii[4]][j + directii[5]].getVisitable() == true) {
					pair = new Pair(matrix.maze[i + directii[4]][j + directii[5]].getNoVisited(), 'C');
					neighbours.add(0, pair);
					if (i + directii[4] == matrix.getEndI() && j + directii[5] == matrix.getEndJ()) {
						isExitCell = 1;
						pos = neighbours.get(0).getPosition();
					}
				}

			//Ne uitam la cel de dedesubt
			if (i + directii[6] < matrix.getHeight() && i + directii[6] >= 0 && j + directii[7] >= 0
					&& j + directii[7] < matrix.getWidth())
				if (matrix.maze[i + directii[6]][j + directii[7]].getVisitable() == true) {
					pair = new Pair(matrix.maze[i + directii[6]][j + directii[7]].getNoVisited(), 'D');
					neighbours.add(0, pair);
					if (i + directii[6] == matrix.getEndI() && j + directii[7] == matrix.getEndJ()) {
						isExitCell = 1;
						pos = neighbours.get(0).getPosition();
					}
				}

			//Sortam; daca este iesire, nu compara
			if (neighbours.size() > 1 && isExitCell == 0) {
				Collections.sort(neighbours, new CompareNeighbours());
				pos = neighbours.get(0).getPosition();
			}
			//Daca nu este iesirea si nu avem ce sorta
			if (neighbours.size() == 1 && isExitCell == 0)
				pos = neighbours.get(0).getPosition();

			//Schimbam vederea in functie de cea curenta si urmatoarea miscare
			// si incrementam numarul de vizite a celulei
			//Daca urmatoarea miscare este dreapta
			if (pos == 'A') {
				road.add(noPositions, matrix.maze[i + directii[0]][j + directii[1]]);
				matrix.maze[i + directii[0]][j + directii[1]]
						.setNoVisited(matrix.maze[i + directii[0]][j + directii[1]].getNoVisited() + 1);
				i = i + directii[0];
				j = j + directii[1];
				if (matrix.getOrientare() == 'A')
					matrix.setOrientare('D');
				else if (matrix.getOrientare() == 'B')
					matrix.setOrientare('A');
				else if (matrix.getOrientare() == 'C')
					matrix.setOrientare('B');
				else
					matrix.setOrientare('C');
			}
			//Daca urmatoarea miscare este sus
			else if (pos == 'B') {
				road.add(noPositions, matrix.maze[i + directii[2]][j + directii[3]]);
				matrix.maze[i + directii[2]][j + directii[3]]
						.setNoVisited(matrix.maze[i + directii[2]][j + directii[3]].getNoVisited() + 1);
				i = i + directii[2];
				j = j + directii[3];

			}
			//Daca urmatoarea miscare este stanga
			else if (pos == 'C') {
				road.add(noPositions, matrix.maze[i + directii[4]][j + directii[5]]);
				matrix.maze[i + directii[4]][j + directii[5]]
						.setNoVisited(matrix.maze[i + directii[4]][j + directii[5]].getNoVisited() + 1);
				i = i + directii[4];
				j = j + directii[5];
				if (matrix.getOrientare() == 'A')
					matrix.setOrientare('B');
				else if (matrix.getOrientare() == 'B')
					matrix.setOrientare('C');
				else if (matrix.getOrientare() == 'C')
					matrix.setOrientare('D');
				else
					matrix.setOrientare('A');
			}
			//Daca urmatoarea miscare este jos
			else {
				road.add(noPositions, matrix.maze[i + directii[6]][j + directii[7]]);
				matrix.maze[i + directii[6]][j + directii[7]]
						.setNoVisited(matrix.maze[i + directii[6]][j + directii[7]].getNoVisited() + 1);
				i = i + directii[6];
				j = j + directii[7];
				if (matrix.getOrientare() == 'A')
					matrix.setOrientare('C');
				else if (matrix.getOrientare() == 'C')
					matrix.setOrientare('A');
				else if (matrix.getOrientare() == 'B')
					matrix.setOrientare('D');
				else
					matrix.setOrientare('B');
			}
			noPositions++;

			while (neighbours.size() != 0)
				neighbours.remove(0);
		}

		//Scriem in fisier
		output.write(Integer.toString(noPositions));
		output.newLine();
		for (Cell c : road) {
			output.write(c.getI() + " " + c.getJ());
			output.newLine();
		}
		output.close();
	}
}
