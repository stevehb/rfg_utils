package com.robotfriendgames.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FrameCombiner {
    private static final String USAGE = "usage: java -jar " + FrameCombiner.class.getSimpleName() + ".jar 'input_files*.png' out_file.png";
    private static final String FOUND_FILE = "\tfound %s [%d,%d]";
    private static final String SIZE_ERROR = "\nERROR: %s is [%d,%d], expected [%d,%d]\n";
    private static final String NEW_IMAGE = "copying data to new image [%d,%d]";
    private static final String WRITE_IMAGE = "wrote new image to %s";

    public static void main(String[] args) throws IOException {
        // get user input
        if(args.length != 2) {
            System.out.println(USAGE);
            return;
        }
        final String inputRegex = args[0].replace("?", ".?").replace("*", ".*?");
        String outputName = args[1];
        String dirPath = System.getProperty("user.dir");

        // find matching files
        System.out.println("scanning " + dirPath + " for files matching " + inputRegex);
        File dir = new File(dirPath);
        String[] matchingFiles = dir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.matches(inputRegex)) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        // sort filenames
        List<String> filenames = Arrays.asList(matchingFiles);
        Collections.sort(filenames);

        // read in images
        int width = -1, height = -1;
        BufferedImage[] imgs = new BufferedImage[filenames.size()];
        System.out.println("found:");
        for(int i = 0; i < filenames.size(); i++) {
            imgs[i] = ImageIO.read(new File(filenames.get(i)));
            if(width == -1 && height == -1) {
                width = imgs[i].getWidth();
                height = imgs[i].getHeight();
            }
            System.out.println(String.format(FOUND_FILE, filenames.get(i), imgs[i].getWidth(), imgs[i].getHeight()));

            if(imgs[i].getWidth() != width || imgs[i].getHeight() != height) {
                System.out.println(String.format(SIZE_ERROR, filenames.get(i), imgs[i].getWidth(), imgs[i].getHeight(), width, height));
                return;
            }
        }

        // copy image

        BufferedImage outImg = new BufferedImage(width * imgs.length, height, BufferedImage.TYPE_4BYTE_ABGR);
        System.out.println(String.format(NEW_IMAGE, outImg.getWidth(), outImg.getHeight()));
        for(int i = 0; i < imgs.length; i++) {
            BufferedImage img = imgs[i];
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    outImg.setRGB((width * i) + x, y, img.getRGB(x, y));
                }
            }
        }

        ImageIO.write(outImg, "png", new File(outputName));
        System.out.println(String.format(WRITE_IMAGE, outputName));
    }
}
