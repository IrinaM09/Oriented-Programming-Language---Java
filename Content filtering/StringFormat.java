
public class StringFormat {
	/**
	 * 
	 * @param string
	 *            - expresia curenta
	 * @return string - expresia va avea formatul: [ (, str, str, str/value, .. ) ]
	 */
	public static String convertStringFormat(String string) {
		/* inlocuieste sub forma '( ' */
		string = string.replaceAll("\\s*\\(\\s*", "( ");

		/* inlocuieste sub forma ' )' */
		string = string.replaceAll("\\s*\\)\\s*", " )");

		/* adauga spatiu dupa '|| / &&' */
		string = string.replaceAll("[|]{2}", " || ");
		string = string.replaceAll("[&]{2}", " && ");

		/* inlocuieste 'ID( (' cu 'ID ( (' */
		StringBuilder addSpace = new StringBuilder(string);
		int i;
		for (i = 0; i < string.length(); i++) {
			if (i + 1 < string.length())
				if ((string.charAt(i) >= '0' || string.charAt(i) <= '9') && string.charAt(i + 1) == '(') {
					addSpace.replace(i + 1, i + 2, " (");
					break;
				}
		}
		string = addSpace.toString();
		return string;
	}
}
