package com.umiitkose.streams.example.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesStream {

    /**
     * Stream'ler ve dosya I/O'si ile çalışmak oldukça basittir. Ancak, daha önce bahsettiğim üç sıra dışı yön var.
     * Bunlar büyük bir sorun değil ve Stream tabanlı dosya I/O'sini kullanmanın kullanılabilirliğini veya faydasını
     * azaltmaz, ancak bunların farkında olmanız gerekir:
     * • Stream'leri kapatmak gerekir
     * • Dizin içerikleri zayıf bir şekilde tutarlıdır
     * • Garanti edilmeyen öğe sırası
     * Bu yönler genel olarak I/O ile uğraşmaktan kaynaklanır ve yalnızca Stream pipeline değil, çoğu I/O ile ilgili kodda bulunur.
     */
    void main() {

        Path path = Paths.get("/Users/umitkose/Documents/MyProject/functionalProgrammingAndStream/src/main/java/com/umiitkose/streams/example/example");
        try (var stream = Files.walk(path)) {
            stream.map(Path::toFile).filter(file -> file.getName().endsWith(".java")).forEach(sourceFile -> {
                System.out.println(sourceFile.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
