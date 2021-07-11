package utilities;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Generator {

	// random ra usernam
	public String userName() {
		return "hongHanh" + createRandomNumber();
	}

	// random ra email
	public String email() {
		return randomIdentifier() + "@gmail.com";
	}

	public int getTime() {

		String hh = UUID.randomUUID().toString();

		System.out.println("HANHTH ==>" + hh);

		return (int) ZonedDateTime.now().toInstant().toEpochMilli();
	}

	/**
	 * Radom ra số từ 0-1000 (hàm ramdom của java)
	 * 
	 * @return
	 */
	public int createRandomNumber() {
		Random rd = new Random();
		int a = rd.nextInt(1000);
		return a;
	}

	final String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	Random rd = new Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being
	// used or not
	final Set<String> identifiers = new HashSet<String>();

	//
	public String randomIdentifier() {
		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rd.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(data.charAt(rd.nextInt(data.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}
}
