package Pojo;

/**
 * Created by MALBEL on 12-12-2017.
 */

public class ProblemType {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDefaultImageUrl() {
        return defaultImageUrl;
    }

    public void setDefaultImageUrl(String defaultImageUrl) {
        this.defaultImageUrl = defaultImageUrl;
    }

    public String getTickedImageUrl() {
        return tickedImageUrl;
    }

    public void setTickedImageUrl(String tickedImageUrl) {
        this.tickedImageUrl = tickedImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    String displayName;
    String defaultImageUrl;
    String tickedImageUrl;
    String title;
    Boolean isSelected;

    public ProblemType(String name, String displayName, String defaultImageUrl, String tickedImageUrl, String title, Boolean isSelected) {
        this.name = name;
        this.displayName = displayName;
        this.defaultImageUrl = defaultImageUrl;
        this.tickedImageUrl = tickedImageUrl;
        this.title = title;
        this.isSelected = isSelected;
    }






}
