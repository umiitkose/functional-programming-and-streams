package com.umiitkose.functional.programming.introduction;

/**
 * Saf fonksiyonlar, aynı girdilerle her zaman aynı sonucu üreten ve yan etkileri olmayan fonksiyonlardır.
 */
public class PureFunctions {
    // Saf fonksiyon - her zaman aynı girdi için aynı çıktı, yan etki yok
    public static int sum(int a, int b) {
        return a + b;
    }

    void main() {
        // sum(3, 4) her zaman 7 döndürür (referential transparency)
        System.out.println(sum(3, 4)); // 7
    }
}
