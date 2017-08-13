package com.lei.wallpaper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;

public class Files {
    private static final Logger LOGGER = LoggerFactory.getLogger(Files.class);

    public static boolean writeFile(File source, File target) {
        if (!source.exists() || source.isDirectory()) {
            return false;
        }
        if (target.exists()) {
            target.delete();
        }
        try {
            target.createNewFile();
        } catch (IOException e) {
            LOGGER.error("创建文件失败, target:{}", target, e);
            return false;
        }

        return nioTransferCopy(source, target);
    }

    private static boolean nioTransferCopy(File source, File target) {
        FileChannel in = null;
        FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            LOGGER.error("nioTransferCopy#IOException, source:{}, target:{}", source, target);
            return false;
        } finally {
            close(inStream);
            close(in);
            close(outStream);
            close(out);
        }
        return true;
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            LOGGER.error("close fail, closeable:{}", closeable, e);
        }
    }

}
