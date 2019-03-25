package org.mycompany;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyBean {
	public String beanMethod(List<Map<?, ?>> argument) {

		String result = null;
		Iterator it = null;
		for (int i = 0; i < argument.size(); i++) {
			it = argument.get(i).entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				if (result == null) {
					result = "jms:queue:";
					result += (String) entry.getValue();
				} else {
					result += ",";
					result += "jms:queue:";
					result += entry.getValue();
				}

			}
		}
		if (result == null) {
			return "";
		}
		return result;
	}

	public String getHeader(String zona) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("output/" + zona));
		String everything = null;
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
//		        sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} finally {
			br.close();
		}

		return everything;
	}

}
