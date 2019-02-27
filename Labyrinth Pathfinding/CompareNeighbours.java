import java.util.Comparator;

public class CompareNeighbours implements Comparator<Pair> {

	@Override
	/**
	 * @param p1
	 * @param p2
	 * @return -1 : nr. vizite first < nr. vizite second
	 * 		    1 : nr. vizite first > nr. vizite second
	 * 			first == second : comparara dupa pozitie 
	 */
	public int compare(Pair first, Pair second) {

		Integer noVisitedFirst = first.getNoVisited();
		Integer noVisitedSecond = second.getNoVisited();
		Character positionFirst = first.getPosition();
		Character positionSecond = second.getPosition();

		if (noVisitedFirst.compareTo(noVisitedSecond) == 0)
			return positionFirst.compareTo(positionSecond);
		return noVisitedFirst.compareTo(noVisitedSecond);

	}
}