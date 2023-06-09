//import net.coobird.thumbnailator.Thumbnails;

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
        String[] nomesOrigem = {"ValorTributoAtividade"
                , "FiltroValorTributoAtividade"
                , "valorTributoAtividade"
                , "valor_tributo_atividade"
                , "valortributoatividade"
                , "TBVALORTRIBUTOATIVIDADE"
                , "CTRIBUCODI"
                , "Valor Tributo Atividade"
        };
        String[] nomesFim = {"MotivoDevolucao"
                , "FiltroMotivoDevolucao"
                , "motivoDevolucao"
                , "motivo_devolucao"
                , "motivodevolucao"
                , "TBMOTIVODEVOLUCAODOCUMENTO"
                , "CMODEVCODI"
                , "Motivo Devolucao"
        };
        final String root = "C:\\rep\\cadm\\codigos\\cadm\\src\\main\\java\\br\\gov\\emprel\\cadm";
        final String rootView = "C:\\rep\\cadm\\codigos\\cadm\\src\\main\\webapp\\views";
        final String[] paths = {
                root + "\\domain\\%s.java",
                root + "\\domain\\filter\\Filtro%s.java",
                root + "\\domain\\filter\\historico\\Filtro%sHistorico.java",
                root + "\\domain\\historico\\%sHistorico.java",
                root + "\\domain\\historico\\%sHistoricoPK.java",
                root + "\\dao\\%sDao.java",
                root + "\\dao\\historico\\%sHistoricoDao.java",
                root + "\\manager\\%sManager.java",
                root + "\\manager\\historico\\%sHistoricoManager.java"
        };
        final String[] pathsViews = {
                root + "\\web\\%s\\%sSearchBean.java",
                root + "\\web\\%s\\%sEditBean.java",
                rootView + "\\%s\\%sSearch.xhtml",
                rootView + "\\%s\\%sEdit.xhtml",
                rootView + "\\%s\\aba\\aba%sHistorico.xhtml"
        };
        String origem = "";
        String fim = "";
        for (String strPath: paths) {
            origem = String.format(strPath, nomesOrigem[0]);
            fim = String.format(strPath, nomesFim[0]);
            criarDocumentoFim(origem, fim, nomesOrigem, nomesFim);
        }
        try {
            Files.createDirectories(Paths.get(root + "\\web\\" + nomesFim[4]));
            Files.createDirectories(Paths.get(rootView + "\\" + nomesFim[4] + "\\aba"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String strPath: pathsViews) {
            int i = strPath.endsWith("Historico.xhtml") || strPath.endsWith("Bean.java") ? 0 : 2;
            origem = String.format(strPath, nomesOrigem[4], nomesOrigem[i]);
            fim = String.format(strPath, nomesFim[4], nomesFim[i]);
            criarDocumentoFim(origem, fim, nomesOrigem, nomesFim);
        }
        System.out.println("**** DOCUMENTOS GERADOS COM SUCESSO EU ACHO ****");
    }

    public static void criarDocumentoFim(String origem, String fim, String[] nomesOrigem, String[] nomesFim) {
        Path pathOrigem = Paths.get(origem);
        Path pathFim = Paths.get(fim);
        List<String> newLines = new ArrayList<>();
        System.out.println(origem + "\n" + fim + "\n");
        try {
            for (String l : Files.readAllLines(pathOrigem)) {
                newLines.add(corrigirLinha(l, nomesOrigem, nomesFim));
            }
            Files.write(pathFim, newLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
