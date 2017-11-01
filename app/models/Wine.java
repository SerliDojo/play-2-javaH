package models;


import java.util.List;

public class Wine {

    /*
    "id": "cheval-noir",
  "name": "Cheval Noir",
  "type": "Rouge",
  "appellation": {"name": "Saint-Emilion", "region": "Bordeaux"},
  "grapes": ["Cabernet Sauvignon", "Merlot", "Cabernet Franc"]
     */
    private String id;
    private String name;
    private String type;
    private Appellation appellation;
    private List<String> grapes;

    public Wine(String name) {
        this.name = name;
    }

    public Wine(String id, String name, String type, Appellation appellation, List<String> grapes) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.appellation = appellation;
        this.grapes = grapes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Appellation getAppellation() {
        return appellation;
    }

    public void setAppellation(Appellation appellation) {
        this.appellation = appellation;
    }

    public List<String> getGrapes() {
        return grapes;
    }

    public void setGrapes(List<String> grapes) {
        this.grapes = grapes;
    }
}
