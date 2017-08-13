package com.lei.wallpaper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String SOURCE_PATH = "C:\\Users\\lilei\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets";
    private static final String TARGET_PATH = "C:\\Users\\lilei\\Pictures\\Saved Pictures";

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        List<Wallpaper> wallpapers = null;
        try {
            wallpapers = getAllWallpaper(SOURCE_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = writeWallpaper(wallpapers, TARGET_PATH);
        System.out.println(count);
    }

    private static int writeWallpaper(List<Wallpaper> wallpapers, String targetPath) {
        int count = 0;
        File source, target;
        for (Wallpaper wallpaper : wallpapers) {
            source = new File(wallpaper.getPath());
            target = new File(targetPath, System.currentTimeMillis() + wallpaper.getSuffix());
            if (Files.writeFile(source, target)) {
                count++;
            }
        }
        return count;
    }

    private static List<Wallpaper> getAllWallpaper(String path) throws FileNotFoundException {
        File root = new File(path);
        if (!root.exists()) {
            throw new FileNotFoundException("文件目录不存在");
        }
        File[] files = root.listFiles();

        List<Wallpaper> wallpapers = new ArrayList<>();
        Wallpaper wallpaper;

        assert files != null;
        for (File file : files) {
            wallpaper = new Wallpaper();
            wallpapers.add(wallpaper);

            wallpaper.setName(file.getName());
            wallpaper.setPath(file.getPath());
            wallpaper.setSize(file.length());
        }
        return wallpapers;
    }

}
