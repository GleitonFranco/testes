//import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) {
        criaarq(args);
//        Path pathIni = Paths.get("C:\\Users\\gleiton.franco\\Pictures\\teste");
//        Path pathEnd = Paths.get("C:\\Users\\gleiton.franco\\Pictures\\grid");
//        System.out.println(pathEnd.toAbsolutePath());
//        try {
//            DirectoryStream<Path> paths = Files.newDirectoryStream(pathIni);
//            paths.forEach(a -> {
//                System.out.println(a.getFileName());
//                try {
//                    Thumbnails.of(a.toFile())
//                            .size(200,800)
//                            .outputQuality(0.3f)
//                            .outputFormat("jpg")
//                            .toFile(new File(pathEnd.toAbsolutePath()
//                                    + FileSystems.getDefault().getSeparator()
//                                    + a.getFileName()
//                            ));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static void criaarq(String[] nomes) {
        String[] nomesOrigem = {"EntityOrigem", "FiltroEntityOrigem", "entityOrigem", "entity_origem", "entityorigem", "ENTITYORIGEM"};
        String[] nomesFim    = {"EntidadeFim", "FiltroEntidadeFim", "entidadeFim", "entidade_fim", "entidadefim", "ENTIDADEFIM"};
        final String root = "/home/gleiton/git/testes/src/main/java/javerde/com/br";
        final String[] originals = {
                root + "/domain/%s.java",
                root + "/domain/Filtro%s.java",
                root + "/dao/%sDao.java"
        };
        Path path0 = null;
        Path path1 = null;
        String origem = "";
        String fim = "";
        List<String> newLines = new ArrayList<>();
        for (String strPath: originals) {
            origem = String.format(strPath, nomesOrigem[0]);
            fim = String.format(strPath, nomesFim[0]);
            System.out.println(origem);
            System.out.println(fim);
            path0 = Paths.get(origem);
            path1 = Paths.get(fim);
            try {
                newLines = new ArrayList<>();
                for (String l : Files.readAllLines(path0)) {
                    newLines.add(corrigirLinha(l, nomesOrigem, nomesFim));
                }
                Files.write(Paths.get(fim), newLines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            try {
//                Files.copy(path0.normalize(), path1.normalize());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println();
        }
    }

    public static String corrigirLinha(String linha, String[] nomesOrigem, String[] nomesFim) {
        String linhaResult = linha;
        for (int i=0; i < nomesOrigem.length; i++) {
            linhaResult = linhaResult.replace(nomesOrigem[i], nomesFim[i]);
        }
        return linhaResult;
    }

}
