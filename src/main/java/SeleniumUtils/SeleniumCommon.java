package SeleniumUtils;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCommon {
	public static void sleepInHalfSec(int halfSec) {
		try {
			Thread.sleep(halfSec * 500);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void waitUntilClickableThenClick(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
				webElement.click();
				return;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static String waitUntilVisibleThenGetText(WebDriver driver, WebElement element) {
		String textString = null;
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
				textString = webElement.getText();
				return textString;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static void waitUntilClickableThenSentKeys(WebDriver driver, WebElement element, String textToSend) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
				webElement.clear();
				webElement.sendKeys(textToSend);
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static WebElement waitUtilVisible(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
				webElement.getText(); // Using getText method to test if element
										// is stale or not
				return webElement;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static WebElement waitUtilClickable(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
				webElement.getText(); // Using getText method to test if element
										// is stale or not
				return webElement;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static Object tryCatchWraper4NonPrimitive(Object classInstance, String method, Object... paraValueList) {
		Class clazz = classInstance.getClass();
		Method methodToInvoke = null;
		int length = paraValueList.length;

		Class<?>[] parameterType = new Class<?>[length];
		try {
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					parameterType[i] = paraValueList[i].getClass();
				}
				methodToInvoke = clazz.getDeclaredMethod(method, parameterType);
			} else {
				methodToInvoke = clazz.getDeclaredMethod(method);
			}
			return methodToInvoke.invoke(classInstance, paraValueList);
		} catch (Throwable t) {
			return "";
		}
	}

	public static Object tryCatchWrapper(Object classInstance, String method, Class[] paraType, Object[] paraValue) {
		// if(paraType.length!=paraType.length){
		// System.out.println("Each values provided have to provide its Class
		// type as well");
		// }
		Class clazz = classInstance.getClass();
		Method methodToInvoke = null;
		try {
			methodToInvoke = clazz.getDeclaredMethod(method, paraType);
			return methodToInvoke.invoke(classInstance, paraValue);
		} catch (Throwable t) {
			return "";
		}

	}

	@Test
	public void test4TryCatchWrapper() {
		assertEquals(tryCatchWraper4NonPrimitive("abc", "concat", "XYZ"), "abcXYZ");
		assertEquals(tryCatchWraper4NonPrimitive("abc", "toUpperCase"), "ABC");
		assertEquals(tryCatchWrapper("abcde", "substring", new Class[] { int.class, int.class }, new Object[] { 1, 3 }),
				"bc");
		assertEquals(tryCatchWrapper("abc", "toUpperCase", new Class[] {}, new Object[] {}), "ABC");
		assertEquals(tryCatchWrapper("abc", "toUpperCase", null, null), "ABC");

		System.out.println(tryCatchWraper4NonPrimitive("abc", "concat", "XYZ"));
		System.out.println(tryCatchWraper4NonPrimitive("abc", "toUpperCase"));
		System.out.println(
				tryCatchWrapper("abcdefghi", "substring", new Class[] { int.class, int.class }, new Object[] { 1, 3 }));
		System.out.println(tryCatchWrapper("abc", "toUpperCase", new Class[] {}, new Object[] {}));
		System.out.println(tryCatchWrapper("abc", "toUpperCase", null, null));

	}

}
