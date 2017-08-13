package com.lei.wallpaper;

public class Wallpaper {
    private String name;
    private String suffix = ".jpg";
    private Long size;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Wallpaper{" +
                "name='" + name + '\'' +
                ", suffix='" + suffix + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}
