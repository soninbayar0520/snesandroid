package www.irlee.snesreceipts.Models;

public class Category {

    public String categoryId;
    public String categoryName;
    public String categoryPicture;
    public String categoryDetails;
    public String createdOn;
    public  String updatedOn;

    public Category(String categoryId, String categoryName, String categoryPicture, String categoryDetails, String createdOn, String updatedOn) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryPicture = categoryPicture;
        this.categoryDetails = categoryDetails;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Category() {
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCategoryPicture() {
        return categoryPicture;
    }

    public void setCategoryPicture(String categoryPicture) {
        this.categoryPicture = categoryPicture;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }
}
