package se.iths.webshop.dto;

public class CategoryMenu {
    // change inputChoice to an Enum since we do not want to update the categories
    private String inputChoice;

    public CategoryMenu(){

    }
    public String getInputChoice(){
        return inputChoice;
    }
    public void setInputChoice(String inputChoice){
        this.inputChoice= inputChoice;
    }
}
