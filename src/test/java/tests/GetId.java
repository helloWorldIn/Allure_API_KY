package tests;



public class GetId {

    private String queueType;
    private String items;
    private String query;
    private int width;
    private int height;
    private int num_steps;
    private int num_images;
    private int guidance_scale;

    public String getQueueType() {
        return queueType;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNum_steps() {
        return num_steps;
    }

    public void setNum_steps(int num_steps) {
        this.num_steps = num_steps;
    }

    public int getNum_images() {
        return num_images;
    }

    public void setNum_images(int num_images) {
        this.num_images = num_images;
    }

    public int getGuidance_scale() {
        return guidance_scale;
    }

    public void setGuidance_scale(int guidance_scale) {
        this.guidance_scale = guidance_scale;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    private String preset;

}