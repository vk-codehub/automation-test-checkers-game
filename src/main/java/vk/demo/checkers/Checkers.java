package vk.demo.checkers;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Checkers {

	WebDriver driver;
	Board board;

	@FindBy(xpath = "//div[@class='page']/h1")
	private WebElement txt_page_header;

	@FindBy(linkText = "Restart...")
	private WebElement lnk_restart;

	@FindBy(xpath = "//div[@class='gameWrapper']/p[@id='message']")
	private WebElement txt_gamewrap_msg;

	@FindBy(xpath = "//div[@id='board']/div")
	private List<WebElement> lst_board_row;
	
	private final String NEW_BOARD_STATUS = "Select an orange piece to move.";

	public Checkers(WebDriver driver) {
		this.driver = driver;
		this.board = new Board(driver);
		PageFactory.initElements(driver, this);
	}

	public Board getBoard() {
		return board;
	}

	public Board restartGameUsingRestartLink() {
		if (this.restart(false)) {
			board = new Board(driver);
		}

		return board;
	}

	private Boolean restart(boolean usePageRefresh) {
		boolean status = false;

		if (usePageRefresh) {
			driver.navigate().refresh();
		} else {
			lnk_restart.click();
		}

		if (board.getStatusMsg().equals(NEW_BOARD_STATUS)) {
			status = true;
		}

		return status && isBoardVisible();
	}

	public Boolean isBoardVisible() {
		return (lst_board_row.size() == 8) ? true : false;
	}

}
