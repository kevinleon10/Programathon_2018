package codeSpitters.programathon_2018.service.module;

/**
 *
 * @author JorgeRemon
 */
public class Module {

    private String name;
    private String style;
    private String description;
    private String linkName;
    private String link;

    public Module(String name, String style, String description, String linkName, String link) {
        this.name = name;
        this.style = style;
        this.description = description;
        this.linkName = linkName;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
