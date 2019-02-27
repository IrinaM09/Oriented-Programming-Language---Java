
public class RoadCell extends Cell {

	// Se salveaza coordonatele. Celula poate fi vizitata
	RoadCell(int line, int col) {
		setI(line);
		setJ(col);
		setVisitable(true);
	}
}
