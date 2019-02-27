
public class ICell extends Cell {

	// Se salveaza coordonatele. Celula poate fi vizitata
	ICell(int line, int col) {
		setI(line);
		setJ(col);
		setVisitable(true);
	}
}
