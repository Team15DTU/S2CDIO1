package data.dto;

import data.dao.UserDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserDTO implements Serializable{

	// ---------------------- Fields ----------------------

	private static final long serialVersionUID = 4545864587995944260L;
	private int	userId;                     
	private String userName;                
	private String ini;                 
	private List<String> roles;
	private String cpr;
	private String password;

	// ---------------------- Constructor ----------------------

	public UserDTO() {
		this.roles = new ArrayList<>();
	}

	public UserDTO (int userId, String userName, String ini, String cpr, String role) {
		this.userId = userId;
		this.userName = userName;
		this.ini = ini;
		this.cpr = cpr;

		// Generates a random password of minimum length 10 with numbers, upper- and lowercase letters.
		this.password = passwordGenerator();

		//Adds role.
		this.roles = new ArrayList<>();
		addRole(role);

	}

	public UserDTO (int userId, String userName, String ini, String cpr, String password, String role){
		this.userId = userId;
		this.userName = userName;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;

		this.roles = new ArrayList<>();
		addRole(role);
	}

	// ---------------------- Getters and Setters ----------------------

	// region Getters and Setters
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIni() {
		return ini;
	}
	public void setIni(String ini) {
		this.ini = ini;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// endregion

	// ---------------------- Public Method ----------------------

	public boolean passwordChecker () {
		boolean passwordBoolean;
		int[] noOfCharsPassed = checkAndEachTypeOfCharInPassword(password);

		if (noOfCharsPassed[0] != 0 && noOfCharsPassed[1] != 0 && noOfCharsPassed[2] != 0) {
			passwordBoolean = true;
		} else {
			passwordBoolean = false;
		}

		return passwordBoolean;
	}

	/**
	 * Adds role
	 * @param role
	 */

	public void addRole(String role){
		this.roles.add(role);
	}

	/**
	 * 
	 * @param role
	 * @return true if role existed, false if not
	 */
	public void removeRole(String role){
		roles.remove(role);
	}


	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName + ", ini=" + ini + ", roles=" + roles + "]";
	}

	// ------------------- SUPPORT METHODS --------------------

	/**
	 * Generates a String as a Password of length 12 Charters.
	 * @return String password.
	 */
	private String passwordGenerator () {

		Random generator = new Random();

		StringBuilder passwordBuilder = new StringBuilder();

		for (int i = 0; i < 12; i++) {
			int randomGroup = generator.nextInt(3)+1;

			if (randomGroup == 1) {
				int tempNumberASCII = generator.nextInt(10) + 48;
				passwordBuilder.append(((char) tempNumberASCII));
			} else if (randomGroup == 2) {
				int tempUpperCaseLetterACSII = generator.nextInt(26) + 65;
				passwordBuilder.append(((char) tempUpperCaseLetterACSII));
			} else if (randomGroup == 3) {
				int tempLowerCaseLetterACSII = generator.nextInt(26) + 97;
				passwordBuilder.append(((char) tempLowerCaseLetterACSII));
			}
		}

		int[] charTypeCounter = checkAndEachTypeOfCharInPassword(passwordBuilder.toString());

		// Hvis der ikke er nogle tal, så tilføjes et tal.
		if (charTypeCounter[0] == 0) {
			int tempNumberASCII = generator.nextInt(10) + 48;
			passwordBuilder.append(((char) tempNumberASCII));

		// Hvis der ikke nogle store bogstaver, så tilføjes et stort bogstav.
		} else if (charTypeCounter[1] == 0) {
			int tempUpperCaseLetterACSII = generator.nextInt(26) + 65;
			passwordBuilder.append(((char) tempUpperCaseLetterACSII));

		// Hvis der ikke er nogle små bogstaver, så tilføjes et småt bogstav.
		} else if (charTypeCounter[2] == 0) {
			int tempLowerCaseLetterACSII = generator.nextInt(26) + 97;
			passwordBuilder.append(((char) tempLowerCaseLetterACSII));
		}

		return passwordBuilder.toString();
	}

	private int[] checkAndEachTypeOfCharInPassword (String password) {
		int[] charTypeCounter = {0,0,0};

		for (int i = 0; i < password.length(); i++) {
			int tempACSIIValueOfChar = password.charAt(i);
			if (tempACSIIValueOfChar >= 48 && tempACSIIValueOfChar <= 57) {
				charTypeCounter[0]++;
			} else if (tempACSIIValueOfChar >=65 && tempACSIIValueOfChar <= 90) {
				charTypeCounter[1]++;
			} else if (tempACSIIValueOfChar >= 97 && tempACSIIValueOfChar <= 122) {
				charTypeCounter[2]++;
			}
		}
		return charTypeCounter;
	}

}
