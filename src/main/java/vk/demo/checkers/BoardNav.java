package vk.demo.checkers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DecimalFormat;

import static java.lang.String.format;

public class BoardNav {

	private WebDriver driver;
	private String pieceName;
	private By locator;

	public BoardNav(WebDriver driver, String name) {
		this.driver = driver;
		this.pieceName = name;
		this.locator = By.xpath(format("//img[@name='%s']", name));
	}

	public int getCurrentPosition() {
		return Integer.parseInt(pieceName.substring(pieceName.length() - 2));
	}

	public String getPieceColor() {
		String color = "None";
		String gif = driver.findElement(locator).getAttribute("src").toString().toLowerCase();
		if (gif.contains("you")) {
			color = "orange";
		} else if (gif.contains("me")) {
			color = "blue";
		}
		return color;
	}

	public void moveDiagonally(String direction) throws Exception {
		int newPosition = -1;
		String squareName = "space";
		By newLocator = this.locator; // initially setting to the current square

		if (getPieceColor().equals("orange")) {
			if (direction.equals("left")) {
				newPosition = getCurrentPosition() + 11;
			} else if (direction.equals("right")) {
				newPosition = getCurrentPosition() - 9;
			}
		} else { // blue
			if (direction.equals("left")) {
				newPosition = getCurrentPosition() - 9;
			} else if (direction.equals("right")) {
				newPosition = getCurrentPosition() - 11;
			}
		}

		if (newPosition != -1) {
			squareName = squareName + String.valueOf(newPosition);
			newLocator = By.xpath(format("//img[@name='%s']", squareName));

			if (driver.findElement(newLocator).getAttribute("src").contains("gray.gif")) {
				// The square is available for accepting a move.
				driver.findElement(newLocator).click();
				Thread.sleep(4000); //This is due to the defect with text 'Make a Move' display.
			}
		} else {
			throw new Exception("Incorrect move attempt.");
		}
	}

	public void jumpOver(String direction) throws Exception {
		String newPosition = "-1";
		String squareName = "space";
		By newLocator = this.locator; // initially setting to the current square

		if (getPieceColor().equals("orange")) {
			if (direction.equals("left")) {
				newPosition = new DecimalFormat("00").format(getCurrentPosition() + 11);
			} else if (direction.equals("right")) {
				newPosition = new DecimalFormat("00").format(getCurrentPosition() - 9);
			}
		} else { // blue
			if (direction.equals("left")) {
				newPosition = new DecimalFormat("00").format(getCurrentPosition() + 9);
			} else if (direction.equals("right")) {
				newPosition = new DecimalFormat("00").format(getCurrentPosition() - 11);
			}
		}

		if (Integer.valueOf(newPosition) != -1) {
			squareName = squareName + newPosition;
			newLocator = By.xpath(format("//img[@name='%s']", squareName));

			if (driver.findElement(newLocator).getAttribute("src").contains("me1.gif")) {
				// Opponent FOUND, so verify if the next diagonal square exists and is empty for
				// a jump over
				String tmpPosition = "-1";
				String tmpSquareName = "space";

				if (direction.equals("left")) {
					tmpPosition = new DecimalFormat("00").format(Integer.valueOf(newPosition) + 11);
				} else if (direction.equals("right")) {
					tmpPosition = new DecimalFormat("00").format(Integer.valueOf(newPosition) - 9);
				}

				tmpSquareName = tmpSquareName + tmpPosition;
				By tempLocator = By.xpath(format("//img[@name='%s']", tmpSquareName));

				if (Integer.valueOf(tmpPosition) >= 0 && Integer.valueOf(tmpPosition) <= 80) {
					// Look for an empty square
					if (driver.findElement(tempLocator).getAttribute("src").contains("gray.gif")) {
						// The square is available for accepting a move.
						driver.findElement(tempLocator).click();
						Thread.sleep(4000); //This is due to the defect with text 'Make a Move' display.
					}
				}
			} else {
				throw new Exception("Invalid jump attempt.");
			}
		}
	}

	public void moveDiagonallyFwdLeft() throws Exception {
		moveDiagonally("left");
	}

	public void moveDiagonallyFwdRight() throws Exception {
		moveDiagonally("right");
	}

	public void jumpOverFwdLeft() throws Exception {
		jumpOver("left");
	}

	public void jumpOverFwdRight() throws Exception {
		jumpOver("right");
	}
}
