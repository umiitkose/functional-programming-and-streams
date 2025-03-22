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



```
11: Stream<String> empty = Stream.empty(); // count = 0
12: Stream<Integer> singleElement = Stream.of(1); // count = 1
13: Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
```

