
public class OCell extends Cell {

	// Se salveaza coordonatele. Celula poate fi vizitata
	OCell(int line, int col) {
		setI(line);
		setJ(col);
		setVisitable(true);
	}
}
