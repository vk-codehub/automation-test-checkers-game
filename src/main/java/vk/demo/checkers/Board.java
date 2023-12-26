package vk.demo.checkers;

import static java.lang.String.format;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import vk.demo.utils.SeUtils;

public class Board {

	private WebDriver driver;
	private By locator;
	private BoardNav move;
	private final long TIME_OUT_IN_SEC = 5;

	public Board(WebDriver driver) {
		this.driver = driver;

	}

	public void select(String name) {
		// selection can only initiate a move
		this.move = new BoardNav(driver, name);
		this.locator = By.xpath(format("//img[@name='%s']", name));
		String msg = getStatusMsg();
		if (msg.equals("Make a move.") || msg.equals("Select an orange piece to move.")) {
			driver.findElement(locator).click();
		}
		
	}

	public void move(StepDirection direction) throws Exception {
		switch (direction) {

		case FWD_LEFT_DIAGONAL:
			move.moveDiagonallyFwdLeft();
			break;
		case FWD_RIGHT_DIAGONAL:
			move.moveDiagonallyFwdRight();
			break;

		case JUMP_FWD_LEFT_DIAGONAL:
			move.jumpOverFwdLeft();
			break;
		case JUMP_FWD_RIGHT_DIAGONAL:
			move.jumpOverFwdRight();
			break;
		default:
			// do nothing;
			break;
		}

	}
	
	public String getStatusMsg() {
		return SeUtils.waitForElement(driver, By.xpath("//div[@class='gameWrapper']/p[@id='message']"), Duration.ofSeconds(TIME_OUT_IN_SEC)).getText().trim();
	}

}
