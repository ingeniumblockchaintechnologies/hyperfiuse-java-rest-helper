package ro.ibt.hyperfiuse.resthelper.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusType {

	COMMITTED("COMMITTED"), PENDING("PENDING"), INVALID("INVALID"), NONE("NONE"), UNKNOWN("UNKNOWN");

	private String status;

	private StatusType(String status) {

		this.status = status;
	}

	@Override
	public String toString() {

		return this.status;
	}

	// ****** Reverse Lookup Implementation************//

	// Lookup table
	private static final Map<String, StatusType> lookup = new HashMap<>();

	// Populate the lookup table on loading time
	static {

		for (StatusType env : StatusType.values()) {

			lookup.put(env.toString(), env);
		}
	}

	// This method can be used for reverse lookup purpose
	public static StatusType get(String status) {

		return lookup.get(status);
	}
}
