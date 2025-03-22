# Optional

Java da bilmiyorumu nasıl derim? diye sorarsanız aslında bunu Optional ile diyebiliyorsunuz.
Bir değer ile birlikte sunabilir yada boş olarak geriye döndürebiliriz.

Bazen bir değeri döndürmek zorunda olmadığımız durumlar olabilir. Bu durumda null dönmek yerine Optional kullanabiliriz.

![Optional_ornek_kullanım](Screenshot_2025-02-06_at_11.24.41.png)

## Optional Oluşturma

```Java
public class CreatingOptional {

    public static void main(String[] args) {
        average(90, 100)
                .ifPresent(System.out::println);

        System.out.println(average());
    }

    public static Optional<Double> average(int... scores) {
        if (scores.length == 0) return Optional.empty();
        int sum = 0;
        for (int score : scores) sum += score;
        return Optional.of((double) sum / scores.length);
    }
}

//Çıktı 

// - 95.0
// - Optional.empty
```

eğer herhangi bir hesaplama yapmazsak boş bir Optional nesnesi dönecektir.

```
Optional<Double> opt = average(90, 100);
if (opt.isPresent())
    System.out.println(opt.get()); // 95.0
```

ifPresent ile Optional nesnesi içindeki değer olup olmadığını kontrol ederek yazdırabilirsiniz. Consumer parametresi
alır. isPresent ile değerin boş olup olmadığını kontrol edebilirsiniz. Eğer Optional boş değilse get ile değere
erişebilirsiniz.

Eğer boş ise get ile değeri alamazsınız. Bu durumda **NoSuchElementException** hatası alırsınız.

- java.util.NoSuchElementException: No value present

Eğer direkt atama yaparak Optional nesnesine dönüştürmek isterseniz aşağıdaki gibi kullanabilirsiniz.

```
Optional o = Optional.ofNullable(value);
```

Aşağıdaki tabloda sık kullanılan static metotlar bulunmaktadır.

| Method                  | When Optional is empty                       | When Optional contains value |
|-------------------------|----------------------------------------------|------------------------------|
| get()                   | Throws exception                             | Returns value                |
| ifPresent(Consumer c)   | Does nothing                                 | Calls Consumer with value    |
| isPresent()             | Returns false                                | Returns true                 |
| orElse(T other)         | Returns other parameter                      | Returns value                |
| orElseGet(Supplier s)   | Returns result of calling Supplier           | Returns value                |
| orElseThrow()           | Throws NoSuchElementException                | Returns value                |
| orElseThrow(Supplier s) | Throws exception created by calling Supplier | Returns value                |

Yukarıdaki diğer metotları incelersek;

```
 Optional<Double> opt = average();
 System.out.println(opt.orElse(Double.NaN));
 System.out.println(opt.orElseGet(() -> Math.random()));
 System.out.println(opt.orElseThrow());
 System.out.println(opt.orElseThrow(() -> new IllegalStateException()));
 System.out.println(opt.orElseGet(() -> new IllegalStateException())); // DOES NOT COMPILE
 // - NaN
 // 0.145352212476465
 // Exception in thread "main" java.util.NoSuchElementException: No value present
 // Exception in thread "main" java.lang.IllegalStateException
 
 
 
Optional<Double> opt1 = average(90, 100);
System.out.println(opt1.orElse(Double.NaN));
System.out.println(opt1.orElseGet(() -> Math.random())); 
System.out.println(opt1.orElseThrow());

// 3'üde aynı sonucu vericektir. 95.0
```

Optional'a alternatif olarak null döndürebilirsiniz. Bu yaklaşımın birkaç eksikliği vardır. Bunlardan biri, null'ın özel
bir değer olabileceğini açık bir şekilde ifade etmenin bir yolu olmamasıdır. Buna karşılık, Optional döndürmek, API'de
bir değer olmayabileceğini açıkça belirtir.

Optional'ın bir diğer avantajı, ifPresent() ve diğer yöntemlerle fonksiyonel programlama stilini if ifadesine ihtiyaç
duymadan kullanabilmenizdir,. Son olarak, bölümün sonunda Optional çağrılarını zincirleyebileceğinizi göreceksiniz.