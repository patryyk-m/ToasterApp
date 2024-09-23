package toasteritem;
import exceptions.UnrecognizedItemException;

public class ToasterItemCreator {

    // mthod to create a ToasterItem which depends on the string
    public static ToasterItem createItem(String itemName) throws UnrecognizedItemException {
        switch (itemName.toLowerCase()) {
            case "waffle":
                return new Waffle();
            case "toast":
                return new Toast();
            case "poptart":
                return new PopTart();
            default:
                throw new UnrecognizedItemException("Unrecognized toaster item: " + itemName);
        }
    }
}
