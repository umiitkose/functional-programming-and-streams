package com.umiitkose.functional.programming.introduction;

import java.util.List;

public class EffectivelyFinal {

    public void processData(List<Integer> numbers) {
        int threshold = 10; // effectively final

        // Lambda içinde effectively final değişken kullanımı
        numbers.stream()
                .filter(n -> n > threshold)
                .forEach(System.out::println);

        // Eğer aşağıdaki satırı eklersek, threshold artık "effectively final" olmaz
        // ve yukarıdaki lambda ifadesinde kullanımı derleme hatası verir
        // threshold = 20;
    }

}
