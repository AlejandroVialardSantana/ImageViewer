package imageviewer.view.persistence;

import imageviewer.model.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileImageLoader implements ImageLoader {
       
    private final File[] files;

    public FileImageLoader(File folder) {
        this.files = folder.listFiles(imageType());
    }
    
    private FileFilter imageType() {
        return (File pathname) -> pathname.getName().endsWith(".jpg");
    }
    
    @Override
    public Image load() {
        return imageAt(0);
    }
    
    private Image imageAt(int index) {
        return new Image() {
            @Override
            public String name() {
                return files[index].getName();
            }
            
            @Override
            public InputStream stream() {
                try {
                    return new BufferedInputStream(new FileInputStream(files[index]));
                }
                catch (FileNotFoundException e) {
                    return null;
                }
            }
            
            @Override
            public Image prev() {
                return imageAt(index == 0 ? files.length - 1 : index - 1);
            }
            
            @Override
            public Image next() {
                return imageAt(index == files.length - 1 ? 0 : index + 1);
           }
        };
    }
}
