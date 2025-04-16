# Streamlerle Çalışma

Streamler, Java 8 ile birlikte gelen bir özelliktir. Streamler, bir veri kümesi üzerinde işlem yapmamızı sağlar.

Stream bir veri dizisidir. Stream pipeline, bir sonuç üretmek için bir akış üzerinde çalışan işlemlerden oluşur. İlk
olarak, pipeline'ların
akışını kavramsal olarak inceleyeceğiz. Bundan sonra, kodun içine gireceğiz.

## Pipeline Flow Anlamak

Stream pipeline 3 aşamadan oluşur:

1. Source: Stream'in nereden geldiği.
2. Intermediate operations: Stream'i başka bir stream'e dönüştürür. İstediğiniz kadar ara işlem olabilir. Stream'ler
   lazy evaluation oldukları için terminal operation ile çalışmadıkları sürece ara işlemler çalışmaz.
3. Terminal operation: Sonuç üretir. Stream'ler yalnızca bir kez kullanılabilir, terminal işlem tamamlandıktan sonra
   stream artık geçerli değildir.

![Stream_pipeline](Screenshot_2025-02-07_at_20.52.18.png)

Aşağıdaki tabloda ara ve terminal işlemler arasındaki farkları görebilirsiniz:

| Scenario                              | Intermediate operation | Terminal operation |
|---------------------------------------|------------------------|--------------------|
| Required part of useful pipeline?     | No                     | Yes                |
| Can exist multiple times in pipeline? | Yes                    | No                 |
| Return type is stream type?           | Yes                    | No                 |
| Executed upon method call?            | No                     | Yes                |
| Stream valid after call?              | Yes                    | No                 |

Kısaca olay aşağıdaki gibi gerçekleşir;

Bir fabrikada genellikle işleri denetleyen bir ustabaşı bulunur. Java, akış boru hatlarıyla çalışırken ustabaşı rolünü
üstlenir. Bu, özellikle tembel değerlendirme ve sonsuz akışlarla uğraşırken çok önemli bir roldür. Akışı tanımlamayı,
ustabaşıya talimat vermek olarak düşünün. Ustabaşı ne yapılması gerektiğini öğrendiğinde, istasyonları kurar ve işçilere
görevlerinin ne olacağını söyler. Ancak işçiler, ustabaşı onlara başlamalarını söyleyene kadar başlamazlar. Ustabaşı,
işin başlaması için terminal işlemi görene kadar bekler. Ayrıca işi izler ve iş tamamlanır tamamlanmaz hattı durdurur.
Bunun birkaç örneğine bakalım. Bu örneklerde kod kullanmıyoruz çünkü akış boru hattı kavramını anlamak, kod yazmaya
başlamadan önce gerçekten önemlidir.

Ustabaşının bakış açısından neler olduğuna bir bakalım. İlk olarak, kaynağın kutudan işaretler çıkardığını görürler.
Ustabaşı, masada bir işçi kurar ve başlaması için bir sinyal beklemesini söyler. Sonra ustabaşı, işareti boyamak için
ara işlemi görür. Bir işçiyi boya ile kurar ve başlaması için bir sinyal beklemesini söyler. Son olarak, ustabaşı
işaretleri bir yığına koymak için terminal işlemi görür. Bunu yapmak için bir işçi kurar ve üç işçinin de başlamasını
bağırır. Kutuda iki işaret olduğunu varsayalım. Adım 1, ilk işçinin bir işareti kutudan çıkarıp ikinci işçiye
vermesidir. Adım 2, ikinci işçinin onu boyayıp üçüncü işçiye vermesidir. Adım 3, üçüncü işçinin onu yığına koymasıdır.
Adımlar 4-6, diğer işaret için aynı işlemdir. Sonra ustabaşı, işaret kalmadığını görür ve tüm işletmeyi kapatır.
Ustabaşı akıllıdır ve ne gerektiğine bağlı olarak işi en iyi şekilde nasıl yapacağına karar verebilir.

## Stream Oluşturma

Java'da, bahsettiğimiz streams **java.util.stream** paketinde tanımlanan **Stream<T>** arayüzü ile temsil edilir.

### Sonlu Streamler Oluşturma

Stream oluşturmanın birden fazla yöntemi vardır. Örnekleri aşağıdaki gibidir.

```
11: Stream<String> empty = Stream.empty(); // count = 0
12: Stream<Integer> singleElement = Stream.of(1); // count = 1
13: Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
```

11 satır empty stream oluşturmayı gösterir. 12 satır tek elemanlı stream oluşturma, 13. satır ise çoklu eleman
gösterimini sunar.

Java ayrıca Collectionlardan stream'e dönüştürmeyi de sağlar.

```
14: var list = List.of("a", "b", "c");
15: Stream<String> fromList = list.stream();
```

### Paralel Stream Oluşturma

Paralel streamlar aşağıdaki gibi olur.

```
24: var list = List.of("a", "b", "c");
25: Stream<String> fromListParallel = list.parallelStream();
```

## Sonsuz Streamler Oluşturma - Creating Infinite Streams

Stream'i güçlü yapan yönlerinden biri sonsuz streamler oluşturabiliyorz.

```
17: Stream<Double> randoms = Stream.generate(Math::random);
18: Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
```

17 satır random sayılar üretir, eğer bunu foreach ile çağırırsanız program kill edilene kadar yazdırılır.
18 satırda da seed başlangıç kısmıdır, ve her seferinde 2 ekler.

Eğer streami yazdırmayı denerseniz, aşağıdaki gibi bir yazı alırsınız.

```
java.util.stream.ReferencePipeline$Head@3b07d329
```

Eğer 100'den küçük tek sayıları yazdırmak istersek.

```
Stream<Integer> oddNumberUnder100 = Stream.iterate(1, // seed
n ->n < 100, // Predicate to specify when done
n ->n + 2); // UnaryOperator to get next value
```

## Stream oluşturma Metotları gözden geçirme

| Method                                       | Finite or infinite? | Notes                                                                                                                                                     |
|----------------------------------------------|---------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stream.empty()                               | Finite              | Creates Stream with zero elements.                                                                                                                        |
| Stream.of(varargs)                           | Finite              | Creates Stream with elements listed.                                                                                                                      |
| coll.stream()                                | Finite              | Creates Stream from Collection.                                                                                                                           |
| coll.parallelStream()                        | Finite              | Creates Stream from Collection where the stream can run in parallel.                                                                                      |
| Stream.generate(supplier)                    | Infinite            | Creates Stream by calling Supplier for each element upon request.                                                                                         |
| Stream.iterate(seed, unaryOperator)          | Infinite            | Creates Stream by using seed for first element and then calling UnaryOperator for each subsequent element upon request.                                   |
| Stream.iterate(seed,predicate,unaryOperator) | Finite or infinite  | Creates Stream by using seed for first element and then calling UnaryOperator for each subsequent element upon request. Stops if Predicate returns false. |

## Using Common Terminal Operations

Herhangi bir ara işlem olmadan bir terminal işlemi gerçekleştirebilirsiniz, ancak bunun tersi mümkün değildir. Bu yüzden
önce terminal işlemlerinden bahsediyoruz. Reductionlar, tüm akış içeriğinin tek bir ilkel veya Nesneye dönüştürüldüğü
özel bir terminal işlem türüdür. Örneğin, bir int veya bir Collection olabilir.

| Method                            | What happens for infinite streams | Return value | Reduction |
|-----------------------------------|-----------------------------------|--------------|-----------|
| count()                           | Does not terminate                | long         | Yes       |
| min() max()                       | Does not terminate                | Optional<T>  | Yes       |
| findAny() findFirst()             | Terminates                        | Optional<T>  | No        |
| allMatch() anyMatch() noneMatch() | Sometimes terminates              | boolean      | No        |
| forEach()                         | Does not terminate                | void         | No        |
| reduce()                          | Does not terminate                | Varies       | Yes       |
| collect()                         | Does not terminate                | Varies       | Yes       |

### Count

count() metodu, sonlu bir stream'deki elemanların sayısını belirler. Sonsuz bir stream için, asla sonlanmaz. Neden?
1'den sonsuza kadar sayın ve bittiğinde bize bildirin. Ya da daha doğrusu, bunu yapmayın, çünkü sınav için çalışmanızı
tercih ederiz, hayatınızın geri kalanını sayarak geçirmeyin. count() metodu bir reduction'dır çünkü stream'deki her bir
elemana bakar ve tek bir değer döner. Metod imzası şu şekildedir:

```
public long count()
```

```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
System.out.println(s.count()); // 3
```

### Minimum ve Maximum

min() ve max() metotları custom comparator geçilmesine izin verilen ve en küçük veya en büyük elemanları bulan
metotlardır. Tıpkı count metotu gibi, sonlu streamleri ele alırlar.

```
public Optional<T> min(Comparator<? super T> comparator)
public Optional<T> max(Comparator<? super T> comparator)
```

Metot imzasının Optional döndüğüne dikkat edin.

**Örnek :**

```
Stream<String> s = Stream.of("monkey", "ape", "bonobo");
Optional<String> min = s.min((s1, s2) -> s1.length()-s2.length());
min.ifPresent(System.out::println); // ape
```

```
Optional<?> minEmpty = Stream.empty().min((s1, s2) ->0);
System.out.println(minEmpty.isPresent()); // false
```

Boş bir streamde comporator asla çağrılmaz. Optionalda değer bulunmaz.

#### Note {collapsible="true"}

Aynı anda hem min hemde max metotlarını kullanmazsın. Bir stream yalnızca tek bir terminal operation ile işlem yapar.

### FindAny ve FindFirst

findAny() ve findFirst() metotları boş stream olmadıkça geriye değer döndürür. Eğer stream boşsa, boş bir optional
döner.
Bu, sonsuz bir stream ile sonlanabilen ilk metottur. Java yalnızca ihtiyaç duyduğunuz miktarda stream oluşturduğundan,
sonsuz stream yalnızca bir eleman üretmelidir.
Adından da anlaşılacağı gibi, findAny() metodu stream'in herhangi bir elemanını döndürebilir.
Şimdiye kadar gördüğünüz stream'lerde çağrıldığında, genellikle ilk elemanı döndürür, ancak bu davranış garanti edilmez.
Bölüm 13'te göreceğiniz gibi, findAny() metodu paralel stream'lerle çalışırken rastgele bir eleman döndürme olasılığı
daha yüksektir.
Bu metotlar terminal işlemleridir ancak reduction değildir. Bunun nedeni, bazen tüm elemanları işlemden geçirmeden
dönebilirler. Bu, stream'e dayalı bir değer döndürdükleri, ancak tüm stream'i tek bir değere indirmedikleri anlamına
gelir.

```
public Optional<T> findAny()
public Optional<T> findFirst()
```

```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
Stream<String> infinite = Stream.generate(() -> "chimp");
s.findAny().ifPresent(System.out::println); // monkey (usually)
infinite.findAny().ifPresent(System.out::println); // chimp
```

### AllMatch, AnyMatch ve NoneMatch

allMatch(), anyMatch() ve noneMatch() metotları bir stream'i arar ve stream'in predicate ile nasıl ilişkili olduğu
hakkında bilgi döner. Bu metotlar sonsuz stream'ler için sonlanabilir veya sonlanmayabilir. Bu, verilere bağlıdır. Find
metotları gibi, bunlar da reduction değildir çünkü tüm elemanlara bakmaları gerekmez.

```
public boolean anyMatch(Predicate <? super T> predicate)
public boolean allMatch(Predicate <? super T> predicate)
public boolean noneMatch(Predicate <? super T> predicate)
```

```
var list = List.of("monkey", "2", "chimp");
Stream<String> infinite = Stream.generate(() -> "chimp");
Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
System.out.println(list.stream().anyMatch(pred)); // true
System.out.println(list.stream().allMatch(pred)); // false
System.out.println(list.stream().noneMatch(pred)); // false
System.out.println(infinite.anyMatch(pred)); // true
```

Farklı streamler için aynı predicate'i kullanabiliriz.

## Iteration

streamlerdeki elemanlar üzerinde iterate yapmak genellikle kullanılır. Bunun içinde forEach() metotu çağırılır. Sonlu
bir
stream için, stream'i sonlandırmaz.

```
public void forEach(Consumer<? super T> action)
```

```
Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
s.forEach(System.out::print); // MonkeyGorillaBonobo
```

Geleneksel for loop burada kullanılmaz.

```
Stream<Integer> s = Stream.of(1);
for (Integer i : s) {} // DOES NOT COMPILE
```

Nedeni stream için terminal operation gerekmesidir.

## Reducing

reduce() metodu, stream'deki tüm elemanları tek bir değere indirger.

```
public T reduce(T identity, BinaryOperator<T> accumulator)

public Optional<T> reduce(BinaryOperator<T> accumulator)

public <U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
```

Bunları tek tek ele alalım. Bir indirgeme yapmanın en yaygın yolu, bir başlangıç değeriyle başlamak ve bunu bir sonraki
değerle birleştirmeye devam etmektir. Fonksiyonel programlama olmadan bir dizi String nesnesini tek bir String'e nasıl
birleştireceğinizi düşünün.

```
var array = new String[] { "w", "o", "l", "f" };
var result = "";
for (var s: array) result = result + s;
System.out.println(result); // wolf
```

Aşağıdaki ise örneğin kullanımıdır, İlk parametre başlangıç değeri ve accumalotr kısmını gösterir.

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
String word = stream.reduce("", (s, c) -> s + c); //method references stream.reduce("", String::concat);
System.out.println(word); // wolf
```

```
Stream<Integer> stream = Stream.of(3, 5, 6);
System.out.println(stream.reduce(1, (a, b) -> a*b)); // 90
```

* If the stream is empty, an empty Optional is returned.
* If the stream has one element, it is returned.
* If the stream has multiple elements, the accumulator is applied to combine them.
*

```
BinaryOperator<Integer> op = (a, b) -> a * b;
Stream<Integer> empty = Stream.empty();
Stream<Integer> oneElement = Stream.of(3);
Stream<Integer> threeElements = Stream.of(3, 5, 6);
empty.reduce(op).ifPresent(System.out::println); // no output
oneElement.reduce(op).ifPresent(System.out::println); // 3
threeElements.reduce(op).ifPresent(System.out::println); // 90
```

**(Buna Bakacağız..)**

```
Stream<String> stream = Stream.of("w", "o", "l", "f!");
int length = stream.reduce(0, (i, s) -> i+s.length(), (a, b) -> a+b);
System.out.println(length); // 5
```

İlk parametre (0) başlatıcı için değerdir. Eğer boş bir stream'imiz olsaydı, bu cevap olurdu. İkinci parametre
biriktiricidir. Önceki biriktiricilerden farklı olarak, bu karışık veri türlerini işler. Bu örnekte, ilk argüman, i, bir
Integer iken, ikinci argüman, s, bir String'dir. Mevcut String'in uzunluğunu toplamımıza ekler. Üçüncü parametre,
herhangi bir ara toplamı birleştiren combiner'dır. Bu durumda, a ve b her ikisi de Integer değerleridir.
Üç argümanlı reduce() işlemi, paralel stream'lerle çalışırken kullanışlıdır çünkü stream'in ayrı iplikler tarafından
ayrıştırılmasına ve yeniden birleştirilmesine izin verir. Örneğin, dört 100 karakterlik string'in uzunluğunu saymamız
gerekseydi, ilk iki değer ve son iki değer bağımsız olarak hesaplanabilirdi. Ara sonuç (200 + 200) daha sonra nihai
değere birleştirilirdi.

### Collecting

Mutable reduction olarak adlandırılan özel bir reduction tipidir. Normal reductiondan daha verimlidir çünkü hesaplanma
sürecindeyken aynı mutable object kullanılır.

```
public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
public <R,A> R collect(Collector<? super T, A,R> collector)
```

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
StringBuilder word = stream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
System.out.println(word); // wolf
```


