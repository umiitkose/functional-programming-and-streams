# Functional-Programming

## Bir dili işlevsel(Fonksiyonel) yapan nedir?

Nesne yönelimli, işlevsel veya prosedürel gibi programlama paradigmaları, dilleri sınıflandıran ve programlarınızı
belirli bir tarzda yapılandırmanın ve sorunları çözmek için farklı yaklaşımlar kullanmanın yollarını sağlayan sentetik
genel kavramlardır. Çoğu paradigma gibi, işlevsel programlamanın da üzerinde anlaşılmış tek bir tanımı yoktur

Bir dil, soyut fonksiyonlar yaratıp birleştirerek hesaplamaları ifade etmenin bir yolu olduğunda işlevsel olarak kabul
edilir. Bu kavram, mantıkçı **Alonzo Church** tarafından 1930'larda icat edilen resmi matematiksel sistem lambda
hesaplamasına dayanır. Bu, hesaplamaları soyut fonksiyonlarla ifade etmek ve bunlara değişkenleri nasıl
uygulayacağınızı gösteren bir sistemdir. "Lambda hesaplaması" adı, sembolü için seçilen Yunan harfi "lambda"dan
gelmektedir: λ

Nesne yönelimli bir geliştirici olarak, imperative programlamaya alışkınsınız: Bir dizi ifade tanımlayarak, bilgisayara
bir dizi ifadeyle belirli bir görevi başarmak için ne yapması gerektiğini söylüyorsunuz.

Bir programlama dilinin fonksiyonel olarak kabul edilebilmesi için, hesaplamaların mantığını gerçek kontrol akışlarını
tanımlamadan ifade eden bir declarative stilin elde edilebilir olması gerekir. Böyle bir declarative programlama ,
sonucu ve programınızın (expression) ifadelerle nasıl çalışması gerektiğini tanımlarsınız, ifadelerle (statement) ne
yapması
gerektiğini değil.

**Expression :** Java'da bir ifade, bir hesaplamayı tanımlayan ve tek bir değere değerlendirilen bir dizi operatör,
işlenen ve yöntem çağrısıdır:

```
x * x
2 * Math.PI * radius
value == null ? true : false
```

**Statements :**
Öte yandan ifadeler, kodunuzun yürütmenin tam bir birimini oluşturmak için gerçekleştirdiği eylemlerdir; buna, geri
dönüş değeri olmayan yöntem çağrıları da dahildir. Bir değişkenin değerini atadığınızda veya değiştirdiğinizde, bir void
yöntemini çağırdığınızda veya if-else gibi kontrol akışı yapıları kullandığınızda ifadeler kullanıyorsunuz. Genellikle,
expression karıştırılırlar:

```
int totalTreasure = 0;

int newTreasuresFound = findTreasure(6);

totalTreasure = totalTreasure + newTreasuresFound;

if (totalTreasure > 10) {
System.out.println("You have a lot of treasure!");
} else {
System.out.println("You should look for more treasure!");
}
```

2si arasındaki temel ayrım, bir değerin döndürülüp döndürülmediğidir.

## Functional Programming Kavramları

Fonksiyonel programlama temel olarak soyut fonksiyonlara dayandığından, paradigmayı oluşturan pek çok kavram, emredici "
nasıl çözülür" yaklaşımının aksine, bildirimsel bir üslupla "ne çözülür" konusuna odaklanabilir.

### Pure Functions and Referential Transparency

Fonksiyonel programlama fonksiyonları iki kategoriye ayırır: (pure)saf ve (impure)saf olmayan.
Saf fonksiyonların iki temel garantisi vardır:

Aynı girdi her zaman aynı çıktıyı yaratacaktır
Saf bir fonksiyonun dönüş değeri yalnızca giriş argümanlarına bağlı olmalıdır.

Herhangi bir yan etki(side effect) olmaksızın kendi kendine yeterlidirler
Kod, argüman değerlerini değiştirmek veya herhangi bir I/O kullanmak gibi genel durumu etkileyemez.

Bu iki garanti, saf fonksiyonların paralel bir biçimde bile olsa herhangi bir ortamda güvenle kullanılabilmesini sağlar.
Aşağıdaki kod, bir metodun, bağlamının dışındaki hiçbir şeyi etkilemeden bir argüman kabul eden saf bir fonksiyon
olduğunu gösterir:

```Java
public String toLowercase(String str) {
    return str;
}
```

İki garantiden herhangi birini ihlal eden işlevler saf olmayan olarak kabul edilir. Aşağıdaki kod, mantığı için geçerli
zamanı kullandığı için saf olmayan bir işlevin örneğidir:

```Java
public String buildGreeting(String name) {
    var now = LocalTime.now();
    if (now.getHour() < 12) {
        return "Good morning " + name;
    } else {
        return "Hello " + name;
    }
}
```

### Immutability

Değiştirilemezlikle, veri yapıları başlatıldıktan sonra artık değişemez. Asla değişmedikleri için her zaman tutarlı, yan
etkisiz, tahmin edilebilir ve akıl yürütmesi daha kolaydır. Saf işlevler gibi, bunların kullanımı eş zamanlı ve paralel
ortamlarda, senkronize olmayan erişim veya kapsam dışı durum değişiklikleri gibi olağan sorunlar olmadan güvenlidir.

### Recursion

Özyineleme, aynı formdaki problemleri kısmen çözerek ve kısmi sonuçları birleştirerek orijinal problemi çözen bir
problem çözme tekniğidir. Basit bir ifadeyle, özyinelemeli fonksiyonlar kendilerini çağırır, ancak giriş argümanlarında
ufak bir değişiklik yaparak, bir son koşula ulaşana ve gerçek bir değer döndürene kadar.

Saf fonksiyonel programlama genellikle döngüler veya yineleyiciler yerine yinelemeyi kullanmayı tercih eder. Haskell
gibi bazıları bir adım daha ileri gider ve for veya while gibi döngülere hiç sahip olmaz.

The repeated function calls can be inefficient and even dangerous due to the risk of the stack overflowing. That’s why
many functional languages utilize optimizations like “unrolling” recursion into loops or tail-call optimization to
reduce the required stack frames. Java doesn’t support any of these optimization techniques.

### First-Class and Higher-Order Functions

