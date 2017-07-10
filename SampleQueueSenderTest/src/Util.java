public final class Util {

	public static String createMessageForView(String text, String queueName) {
		String result = "";
		String[] array = queueName.split("\\.");
		result = "Destinatie: " + array[0] + "  ";
		result += "Sistem: " + array[1] + "  ";
		result += "\n";
		String[] array2 = text.split("<|>");
		result += "COD: " + array2[6] + "  ";
		result += "\n";
		result += "TIP: " + array2[14] + "  ";
		result += "\n";
		result += "Descriere:\n";
		int iteratii = array2[18].length() / 100;
		if ((array2[18].length() % 100) != 0)
			iteratii++;
		String aux = array2[18];
		for (int i = 0; i < iteratii; i++) {
			if (aux.length() < 100)
				result += aux.substring(0, aux.length());
			else
				result += aux.substring(0, 100);
			result += "\n";
			if (aux.length() > 100)
				aux = aux.substring(100, aux.length());
		}
		result += "\n";
		for (int i = 0; i < 95; i++)
			result += "_";
		result += "\n";

		return result;
	}
}
