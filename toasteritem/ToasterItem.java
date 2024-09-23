package toasteritem;
import logging.AssignmentLogger;
public abstract class ToasterItem {
    private String name;
    private int cookTime; 

    public ToasterItem(String name, int cookTime) {
        AssignmentLogger.logConstructor(this);
        this.name = name;
        this.cookTime = cookTime;
        
    }

    public String getName() {
        return name;
    }

    public int getCookTime() {
        return cookTime;
    }

    public abstract String getImagePath();
}
