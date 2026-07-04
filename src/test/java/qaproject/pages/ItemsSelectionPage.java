package qaproject.pages;

public class ItemsSelectionPage {
	public static class ItemsModule {

	
		public static final String item1 = "add-to-cart-sauce-labs-backpack";
		public static final String item2 = "add-to-cart-sauce-labs-bike-light";
		public static final String cart = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/a[1]/span[1]";
		public static final String item1present = "//div[normalize-space()='Sauce Labs Backpack']";
		public static final String item2present = "//div[normalize-space()='Sauce Labs Bike Light']";
	}
}