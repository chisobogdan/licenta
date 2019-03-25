package org.mycompany;

import org.apache.camel.RecipientList;

public class MessageRouter {

	@RecipientList
	public String routeTo(String destinations, String cod) {
		String queueList = "";

		String[] list = destinations.split(",");
		if (cod.equals("ROSU")) {
			return destinations;
		} else if (cod.equals("PORTOCALIU")) {
			for (String s : list) {
				if (!s.contains("JANDARMERIE")) {
					queueList += s + ",";
				}
			}
		} else {
			for (String s : list) {
				if (!s.contains("JANDARMERIE") && !s.contains("ISU")) {
					queueList += s + ",";
				}
			}
		}
		return queueList;

	}
}