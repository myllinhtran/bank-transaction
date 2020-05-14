package service.util;

import java.io.File;

public class Folder {

    public String createNewFolder(String path) {

        // Create a new directory object
        File dir = new File(path);

        if (dir.exists()) {
            System.out.printf(" Directory '%s' already exists. %n", path);
        } else if (dir.mkdir()) {
            System.out.printf(" Directory '%s' created successfully. %n", dir.getName());
            System.out.printf(" Directory path: %s. %n", path);
        } else {
            System.out.printf(" Sorry couldn't create specified directory. %n");
        }
        return dir.getAbsolutePath() + "/";
    }
}
