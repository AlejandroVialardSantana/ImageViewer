package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.persistence.FileImageLoader;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        File file = new File(".\\images");
        FileImageLoader loader = new FileImageLoader(file);
        Image image = loader.load();
        
        MainFrame mainFrame = new MainFrame();
        mainFrame.getImageDisplay().show(image);
    }
}
