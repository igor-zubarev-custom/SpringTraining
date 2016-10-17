package home.zubarev.model;

import java.math.BigDecimal;

public class Phone {
    private Long id;
    private String model;
    private BigDecimal price;
    private String color;
    private String displaySize;
    private String camera;
    private Integer length;
    private Integer width;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getDisplaySize() {
        return displaySize;
    }
    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }
    public String getCamera() {
        return camera;
    }
    public void setCamera(String camera) {
        this.camera = camera;
    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }

    public Phone() {
    }

    public Phone(Long id, String model, BigDecimal price, String color, String displaySize, String camera, Integer length, Integer width) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.color = color;
        this.displaySize = displaySize;
        this.camera = camera;
        this.length = length;
        this.width = width;
    }
}
