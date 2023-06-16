class Product {
    private String barcode;
    private String productName;
    private String expiryDate;
    private String category;
    private String userId; 
    private boolean isUsed;

    public Product(String barcode, String productName, String expiryDate, String category, String userId, boolean isUsed) {
        this.barcode = barcode;
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.category = category;
        this.userId = userId;
        this.isUsed = isUsed;
    }


    public String getBarcode() {
        return barcode;
    }

    public String getProductName() {
        return productName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCategory() {
        return category;
    }
    public String getUserId() {
        return userId;
    }
    public void setUsed() {
        this.isUsed = true;
    }
    public boolean isUsed() {
        return isUsed;
    }

}
