import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = null;
		BufferedWriter output = null;
	
		try {
			input = new BufferedReader(new FileReader(args[1]));
			output = new BufferedWriter(new FileWriter(args[2]));
			} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		switch (Integer.valueOf(args[0])) {
		case 1: {
			Antrenament1 antreneaza = new Antrenament1();
			antreneaza.faraHarta(new Maze(input), output);
			return;
		}
		case 2: {
			Antrenament2 antreneaza = new Antrenament2();
			antreneaza.cuHarta(new Maze(input), output);
			return;
		}
		}
	}
}
