import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Main {

    public static void main(String... args) {
        Path pathIni = Paths.get("C:\\Users\\gleiton.franco\\Pictures\\teste");
        Path pathEnd = Paths.get("C:\\Users\\gleiton.franco\\Pictures\\grid");
        System.out.println(pathEnd.toAbsolutePath());
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(pathIni);
            paths.forEach(a -> {
                System.out.println(a.getFileName());
                try {
                    Thumbnails.of(a.toFile())
                            .size(200,800)
                            .outputQuality(0.3f)
                            .outputFormat("jpg")
                            .toFile(new File(pathEnd.toAbsolutePath()
                                    + FileSystems.getDefault().getSeparator()
                                    + a.getFileName()
                            ));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
