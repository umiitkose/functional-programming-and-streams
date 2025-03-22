# Working with Built-in Functional Interfaces

Bir lambda yazmak istediğinizde kendi işlevsel arayüzünüzü yazmak elverişsiz olacaktır. Neyse ki, sizin için çok sayıda
genel amaçlı işlevsel arayüz sağlanmıştır. Bunları bu bölümde ele alacağız.

java.util.function paketi altında sağlanan temel functional interfaceler verilmiştir.

| Functional Interface | Return type  | Method name | # of parameters |
|----------------------|--------------|-------------|-----------------|
| Supplier - T         | T            | get()       | 0               |
| Consumer - T         | void         | accept(T)   | 1(T)            |
| BiConsumer<T,U>      | void         | accept(T,U) | 2(T,U)          |
| Predicate -T         | boolean      | test(T)     | 1(T)            |
| BiPredicate          | boolean(T,U) | test(T,U)   | 2(T,U)          |
| Function<T,R>        | R            | apply(T)    | 1(T)            |
| BiFunction<T,U,R>    | R            | apply(T,U)  | 2(T,U)          |
| UnaryOperator - T    | T            | apply(T)    | 1(T)            |
| BinaryOperator - T   | T            | apply(T,T)  | 2(T,T)          |

> Kitabın ilerleyen kısımlarında birkaç işlevsel arayüz daha öğreneceksiniz. Bir sonraki bölümde Comparator'ı ele
> alacağız. 13. Bölüm "Concurrency"de Runnable ve Callable'ı ele alacağız. Bunlar, işlevsel arayüzleri tanımanız
> istendiğinde sınavda karşınıza çıkabilir

## Implementing Supplier

```Java

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

Bir LocalDate nesnesini now() fabrika metodunu kullanarak oluşturabilirsiniz. Bu örnek, bu fabrikayı çağırmak için bir
Supplier'ın nasıl kullanılacağını gösterir:

```
Supplier<LocalDate> s1 = LocalDate::now;
Supplier<LocalDate> s2 = () -> LocalDate.now();
LocalDate d1 = s1.get();
LocalDate d2 = s2.get();
System.out.println(d1); // 2022-02-20
System.out.println(d2); // 2022-02-20
```

Bu örnek bir tarihi iki kez yazdırır. Ayrıca statik yöntem referanslarını gözden geçirmek için de iyi bir fırsattır.
LocalDate::now yöntem referansı, ara değişken s1'e atanacak bir Tedarikçi oluşturmak için kullanılır. Bir Tedarikçi
genellikle yeni nesneler oluştururken kullanılır. Örneğin, iki boş StringBuilder nesnesi yazdırabiliriz:

```
Supplier<StringBuilder> s1 = StringBuilder::new;
Supplier<StringBuilder> s2 = () -> new StringBuilder();
System.out.println(s1.get()); // Empty string
System.out.println(s2.get()); // Empty string
```

Bu sefer, nesneyi oluşturmak için bir oluşturucu referansı kullandık. Kullandığımız Tedarikçi türünü bildirmek için
jenerikler kullandık. Bunu okumak biraz uzun olabilir. Aşağıdakinin ne yaptığını çözebilir misiniz? Adım adım ilerleyin:

```
Supplier<StringBuilder> s1 = StringBuilder::new;
Supplier<StringBuilder> s2 = () -> new StringBuilder();
System.out.println(s1.get()); // Empty string 
System.out.println(s2.get()); // Empty string
```

Bu sefer, nesneyi oluşturmak için bir oluşturucu referansı kullandık. Kullandığımız Supplier türünü bildirmek için
jenerikler kullandık. Bunu okumak biraz uzun olabilir. Aşağıdakinin ne yaptığını çözebilir misiniz? Adım adım ilerleyin:

```
Supplier<ArrayList<String>> s3 = ArrayList::new;
ArrayList<String> a1 = s3.get();
System.out.println(a1); // []
```

Belirli bir türde bir Supplierımız var. Bu tür ArrayList-String olur. Sonra get()i çağırmak ArrayList- String'in yeni
bir örneğini oluşturur, bu Supplier'ın genel türüdür—başka bir deyişle, başka bir genel türü içeren genel bir türdür. Bu
tür bir şey ortaya çıktığında koda dikkatlice baktığınızdan emin olun. get()'i işlevsel arayüzde nasıl çağırdığımıza
dikkat edin. S3'ün kendisini yazdırmaya çalışırsak ne olur?

```
System.out.println(s3);
The code prints something like this
functionalinterface.BuiltIns$$Lambda$1/0x0000000800066840@4909b8da
```

Bu, lambda üzerinde toString() çağırmanın sonucudur. İğrenç. Bu gerçekten bir şey ifade ediyor. Test sınıfımızın adı
BuiltIns ve oluşturduğumuz functionalinterface adlı bir pakette. Sonra $$ geliyor, bu da sınıfın dosya sistemindeki bir
sınıf dosyasında bulunmadığı anlamına geliyor. Sadece bellekte var. Gerisi hakkında endişelenmenize gerek yok.

## Implementing Consumer and BiConsumer

Bir parametreyle bir şey yapmak istediğinizde ancak hiçbir şey döndürmediğinizde bir Consumer kullanırsınız. BiConsumer
aynı şeyi yapar, ancak iki parametre alır. Arayüzler aşağıdaki gibi tanımlanır:

```Java

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
// omitted default method
}

@FunctionalInterface
public interface BiConsumer<T, U> {
    void accept(T t, U u);
// omitted default method
}
```

Consumer interfacein genel kullanılmında yazdırma işlemi çokça kullanılır.

```
Consumer<String> c1 = System.out::println;
Consumer<String> c2 = x -> System.out.println(x);

c1.accept("Annie"); // Annie
c2.accept("Annie"); // Annie
```

```
var map = new HashMap<String, Integer>();
BiConsumer<String, Integer> b1 = map::put;
BiConsumer<String, Integer> b2 = (k, v) -> map.put(k, v);
b1.accept("chicken", 7);
b2.accept("chick", 1);

System.out.println(map); // {chicken=7, chick=1}
```

Çıktı {chicken=7, chick=1}'dir ve bu her iki BiConsumer uygulamasının da çağrıldığını gösterir. b1'i bildirirken, yerel
değişken haritasında bir yöntemi çağırmak istediğimizden bir nesne üzerinde bir örnek yöntem referansı kullandık. b1'i
örnekleyen kod, b2 için olan koddan epeyce kısadır. Muhtemelen sınavın yöntem referanslarına bu kadar düşkün olmasının
nedeni budur.

```
var map = new HashMap<String, String>();
BiConsumer<String, String> b1 = map::put;
BiConsumer<String, String> b2 = (k, v) -> map.put(k, v);

b1.accept("chicken", "Cluck");
b2.accept("chick", "Tweep");
System.out.println(map); // {chicken=Cluck, chick=Tweep}
```

## Implementing Predicate and BiPredicate

Predicate genellikle filtreleme veya eşleştirme sırasında kullanılır. Her ikisi de yaygın işlemlerdir. BiPredicate tıpkı
Predicate gibidir, tek farkı bir yerine iki parametre almasıdır. Arayüzler aşağıdaki gibi tanımlanır:

```Java

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
// omitted default and static methods
}

@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean test(T t, U u);
// omitted default methods
}
```

```
Predicate<String> p1 = String::isEmpty;
Predicate<String> p2 = x -> x.isEmpty();
System.out.println(p1.test("")); // true
System.out.println(p2.test("")); // true
```

```
BiPredicate<String, String> b1 = String::startsWith;
BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
System.out.println(b1.test("chicken", "chick")); // true
System.out.println(b2.test("chicken", "chick")); // true
```

Yöntem referansı hem örnek değişkeni hem de startsWith() için parametreyi içerir. Bu, yöntem referanslarının nasıl
epeyce yazma tasarrufu sağladığına dair iyi bir örnektir. Olumsuz tarafı, daha az açık olmaları ve gerçekten ne olup
bittiğini anlamanız gerekmesidir!

## Implementing Function and BiFunction

Bir Fonksiyon, bir parametreyi potansiyel olarak farklı bir türdeki bir değere dönüştürmekten ve onu döndürmekten
sorumludur. Benzer şekilde, bir BiFunction iki parametreyi bir değere dönüştürmekten ve onu döndürmekten sorumludur.
Arayüzler aşağıdaki gibi tanımlanır:

```Java

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
// omitted default and static methods
}

@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
// omitted default method
}
```

```
Function<String, Integer> f1 = String::length;
Function<String, Integer> f2 = x -> x.length();

System.out.println(f1.apply("cluck")); // 5
System.out.println(f2.apply("cluck")); // 5
```

Bu fonksiyon bir String'i bir Integer'a dönüştürür. Aslında teknik olarak String'i bir int'e dönüştürür, bu da otomatik
olarak bir Integer'a dönüştürülür. Türlerin farklı olması gerekmez. Aşağıdaki iki String nesnesini birleştirir ve başka
bir String üretir:

```
BiFunction<String, String, String> b1 = String::concat;
BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);

System.out.println(b1.apply("baby ", "chick")); // baby chick
System.out.println(b2.apply("baby ", "chick")); // baby chick
```

BiFunction'daki ilk iki tür girdi türleridir. Üçüncüsü sonuç türüdür. Yöntem referansı için, ilk parametre concat()'ın
çağrıldığı örnektir ve ikincisi concat()'a geçirilir.

## Implementing UnaryOperator and BinaryOperator

UnaryOperator ve BinaryOperator, bir Function'ın özel durumlarıdır. Tüm tip parametrelerinin aynı tipte olmasını
gerektirirler. Bir UnaryOperator, değerini aynı tipteki bir değere dönüştürür. Örneğin, birer birer artırmak, tekli bir
işlemdir. Aslında, UnaryOperator, Function'ı genişletir. Bir BinaryOperator, iki değeri aynı tipteki bir değere
birleştirir. İki sayıyı toplamak, ikili bir işlemdir. Benzer şekilde, BinaryOperator, BiFunction'ı genişletir. Arayüzler
aşağıdaki gibi tanımlanmıştır:

```Java

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
// omitted static method
}

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T> {
// omitted static methods
}
```

```
T apply(T t); // UnaryOperator
T apply(T t1, T t2); // BinaryOperator
```

Javadoc'ta, bu yöntemlerin Function/BiFunction üst sınıfından miras alındığını fark edeceksiniz. Alt sınıftaki genel
bildirimler, türün aynı olmasını zorlayan şeydir. Tekli örnek için, dönüş türünün parametreyle aynı tür olduğuna dikkat
edin.

```
UnaryOperator<String> u1 = String::toUpperCase;
UnaryOperator<String> u2 = x -> x.toUpperCase();

System.out.println(u1.apply("chirp")); // CHIRP
System.out.println(u2.apply("chirp")); // CHIRP
```

```
BinaryOperator<String> b1 = String::concat;
BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);

System.out.println(b1.apply("baby ", "chick")); // baby chick
System.out.println(b2.apply("baby ", "chick")); // baby chick
```

## Checking Functional Interfaces

Kafanda her zaman aşağıdaki gibi soruların çözümleri olsun.

* Returns a String without taking any parameters
* Returns a Boolean and takes a String
* Returns an Integer and takes two Integers

İlki Supplier içerisinde String alır. 2. aklına predicate gelsede, predicate boolean primitive döner. Bundan
dolayı Function -> String,Boolean olmalıdır. 3. ise Binary bir operatör olmalıdır. BiFunction iş görecektir.

Liste her zaman aklında olmalıdır..

## Using Convenience Methods on Functional Interfaces

Tanımı gereği, tüm işlevsel arayüzlerin tek bir soyut yöntemi vardır. Ancak bu, yalnızca bir yönteme sahip
olabilecekleri anlamına gelmez. Yaygın işlevsel arayüzlerin birçoğu, bir dizi yararlı default arayüz metotu sağlar

Bunların hepsi aynı türdeki işlevsel arayüzleri değiştirmeyi veya birleştirmeyi kolaylaştırır. Tablo 8.5'in yalnızca ana
arayüzleri gösterdiğine dikkat edin. BiConsumer, BiFunction ve BiPredicate arayüzleri benzer yöntemlere sahiptir

| Interface instance | Method return type | Method name | Method parameters |
|--------------------|--------------------|-------------|-------------------|
| Consumer           | Consumer           | andThen()   | Consumer          |
| Function           | Function           | andThen()   | Function          |
| Function           | Function           | compose()   | Function          |
| Predicate          | Predicate          | and()       | Predicate         |
| Predicate          | Predicate          | negate()    | -                 |
| Predicate          | Predicate          | or()        | Predicate         |

```
Predicate<String> egg = s -> s.contains("egg");
Predicate<String> brown = s -> s.contains("brown");
```

```
Predicate<String> brownEggs = s -> s.contains("egg") && s.contains("brown");
Predicate<String> otherEggs = s -> s.contains("egg") && !s.contains("brown");
```

Kısa hali :

```
Predicate<String> brownEggs = egg.and(brown);
Predicate<String> otherEggs = egg.and(brown.negate());
```

```
Consumer<String> c1 = x -> System.out.print("1: " + x);
Consumer<String> c2 = x -> System.out.print(",2: " + x); 
Consumer<String> combined = c1.andThen(c2);
combined.accept("Annie"); // 1: Annie,2: Annie
```

```
Function<Integer, Integer> before = x -> x + 1;
Function<Integer, Integer> after = x -> x * 2;
Function<Integer, Integer> combined = after.compose(before);
System.out.println(combined.apply(3)); // 8
```

## Learning the Functional Interfaces for Primitives

Birçok lambda içerisinde primitive typeleride destekliyor.

### Functional Interfaces for boolean

```Java

@FunctionalInterface
public interface BooleanSupplier {
    boolean getAsBoolean();
}
```

```
12: BooleanSupplier b1 = () -> true;
13: BooleanSupplier b2 = () -> Math.random()> .5;
14: System.out.println(b1.getAsBoolean()); // true
15: System.out.println(b2.getAsBoolean()); // false
```

### Functional Interfaces for double, int, and long

Birçok functional interface içerisinde double,int ve long primitive tipleri destekler.

| Functional interfaces                                      | Return type      | Single abstract method                | # of parameters                                |
|------------------------------------------------------------|------------------|---------------------------------------|------------------------------------------------|
| DoubleSupplier IntSupplier LongSupplier                    | double int  long | getAsDouble getAsInt  getAsLong       | 0                                              |
| DoubleConsumer IntConsumer  LongConsumer                   | void             | accept                                | 1 (double) 1 (int) 1 (long)                    |
| DoublePredicate IntPredicate  LongPredicate                | boolean          | test                                  | 1 (double) 1 (int) 1 (long)                    |
| DoubleFunction<R> IntFunction<R> LongFunction<R>           | R                | apply                                 | 1 (double) 1 (int) 1 (long)                    |
| DoubleUnaryOperator IntUnaryOperator  LongUnaryOperator    | double int  long | applyAsDouble applyAsInt  applyAsLong | 1 (double) 1 (int) 1 (long)                    |
| DoubleBinaryOperator IntBinaryOperator  LongBinaryOperator | double int  long | applyAsDouble applyAsInt  applyAsLong | 2 (double, double) 2 (int, int) 2 (long, long) |

Yukarıdaki tablo ile primitive olmayan genel functional interfaceler arasında farklar vardır.

Bazı arayüzlerden jenerikler kaldırıldı ve bunun yerine tür adı bize hangi ilkel türün dahil olduğunu söylüyor.
IntFunction gibi diğer durumlarda, ilkel bir int'i bir nesneye dönüştürdüğümüz için yalnızca geri dönüş türü olan
generic'e ihtiyaç duyulur.

İlkel bir tür döndürüldüğünde, tek soyut yöntem genellikle yeniden adlandırılır.

