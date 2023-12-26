package vk.demo.checkers;

import org.openqa.selenium.By;
import static java.lang.String.format;

public enum StepDirection {

	FWD_LEFT_DIAGONAL("forward left diagonally"),
	FWD_RIGHT_DIAGONAL("forward right diagonally"),
	
	BWD_LEFT_DIAGONAL("backward left diagonally"),
	BWD_RIGHT_DIAGONAL("backward right diagonally"),
	
	JUMP_FWD_LEFT_DIAGONAL("jump over forward left diagonally"),
	JUMP_FWD_RIGHT_DIAGONAL("jump over forward right diagonally"),
	
	JUMP_BWD_LEFT_DIAGONAL("jump over backward left diagonally"),
	JUMP_BWD_RIGHT_DIAGONAL("jump over backward right diagonally");
	
	private String name;
    private By locator;
    
    private StepDirection(String name){
        this.name = name;
        locator = By.xpath(format("//div[@name='%s']", name));
    }
 
    public String getName(){
        return name;
    }
 
    public By getLocator(){
        return locator;
    }
	
}
