# Using Method References

Method references daha kolay bir şekilde kodu okumanın diğer bir yoludur. Lambdalar gibi yeni syntaxı kullanmak zaman
alabilir. Bu bölümde 4 farklı metot referansını göreceğiz. Metot references ile lambdaları birlikte kullanacağız.

Diyelim ki vaklamayı öğrenmeye çalışan bir ördek yavrusunu kodluyoruz. İlk olarak işlevsel bir arayüzümüz var:

```Java
public interface LearnToSpeak {
    void speak(String sound);
}
```

Sonra, ördek yavrumuzun şanslı olduğunu keşfediyoruz. Ördek yavrusunun çalışabileceği bir yardımcı sınıf var. Ördek
yavrusuna vaklamayı öğretmenin ayrıntılarını atladık ve işlevsel arayüzü çağıran kısmı bıraktık:

```Java
public class DuckHelper {
    public static void teacher(String name, LearnToSpeak trainer) {
        // Exercise patience (omitted)
        trainer.speak(name);
    }
}
```

Son olarak, her şeyi bir araya getirme ve küçük Ördek Yavrumuzla tanışma zamanı geldi. Bu kod, bir lambda kullanarak
fonksiyonel arayüzü uygular.

```Java
public class Duckling {
    public static void makeSound(String sound) {
        LearnToSpeak learner = s ->
                System.out.println(s);
        DuckHelper.teacher(sound, learner);
    }
}
```

Fena değil. Yine de biraz fazlalık var. Lambda, s adında bir parametre bildirir. Ancak, bu parametreyi başka bir yönteme
geçirmekten başka bir şey yapmaz. Bir yöntem referansı, bu fazlalığı kaldırmamızı ve bunun yerine şunu yazmamızı sağlar:

```
LearnToSpeak learner = System.out::println;
```

:: operatörü Java'ya println() metodunu daha sonra çağırmasını söyler. Sözdizimine alışmak biraz zaman alacaktır.
Alıştığınızda, çok fazla lambda yazmadan kodunuzun daha kısa ve daha az dikkat dağıtıcı olduğunu görebilirsiniz.

> ::'nin bir lambda gibi olduğunu ve functional interface ile ertelenmiş yürütme (deferred execution) için
> kullanıldığını unutmayın. Eğer size yardımcı olacaksa, metot reference bir lambda olarak bile hayal edebilirsiniz.

Bir metot referansı ve bir lambda çalışma zamanında aynı şekilde davranır. Derleyicinin metot referanslarınızı sizin
için lambdalara dönüştürdüğünü varsayabilirsiniz.

4 formatta metot referansları mevcuttur:

* static methods
* Instance methods on a particular object
* Instance methods on a parameter to be determined at runtime
* Constructors

## Calling static Methods

İlk örneğimizi double'dan long'a dönüştürme yapan bir fonksiyonel interface kullanarak yapıyoruz.

```Java
interface Converter {
    long round(double num);
}
```

Biz bu arayüzü Math sınıfındaki round() metotu ile kullanacağız.

```
 Converter methodRef = Math::round;
 Converter lambda = x ->Math.round(x);
 System.out.println(methodRef.round(100.1)); // 100
```

bir parametreli bir yönteme başvuruyoruz ve Java bunun bir parametreli bir lambda gibi olduğunu biliyor. Ek olarak, Java
bu parametreyi yönteme geçirmeyi biliyor.

round() yönteminin aşırı yüklendiğinin farkında olabilirsiniz; bir double veya float alabilir. Java, double'lı sürümü
çağırmak istediğimizi nasıl biliyor? Hem lambda'lar hem de yöntem referanslarıyla Java, bilgiyi bağlamdan çıkarır. Bu
durumda, double parametresi alan bir yöntemi olan bir Dönüştürücü bildirdiğimizi söyledik. Java, bu açıklamayla eşleşen
bir yöntem arar. Bunu bulamazsa veya birden fazla eşleşme bulursa, derleyici bir hata bildirir. İkincisine bazen
belirsiz tür hatası (ambiguous type error) denir.

## Calling Instance Methods on a Particular Object

```Java
interface StringStart {
    boolean beginningCheck(String prefix);
}
```

Belirli bir string ile ilgili kontrol yapıp yapmayacağımızı test edelim.

Uygun bir şekilde, String sınıfının bir parametre alan ve bir boolean döndüren bir startsWith() metodu vardır. Bu kodla
metot referanslarının nasıl kullanılacağına bakalım:

```
var str = "Zoo";
StringStart methodRef = str::startsWith;
StringStart lambda = s -> str.startsWith(s);

System.out.println(methodRef.beginningCheck("A")); // false
```

Hiç parametre almayan metot referanslarla da işlemler yapabiliriz.

```Java
interface StringChecker {
    boolean check();
}
```

Örneğin String boş olup olmadığı kontrolünü yapabiliriz.

```
var str = "";
StringChecker methodRef = str::isEmpty;
StringChecker lambda = () ->str.isEmpty();

System.out.print(methodRef.check()); // true
```

Bütün metotların referansları lambdalara dönüştürülebiliyorken, zıttı doğru değildir.

```
var str = "";
StringChecker lambda = () -> str.startsWith("Zoo");
```

```
StringChecker methodReference = str::startsWith; // DOES NOT COMPILE
StringChecker methodReference = str::startsWith("Zoo"); // DOES NOT COMPILE
```

Bunlardan hiçbiri işe yaramıyor! str'yi yöntem referansının bir parçası olarak geçirebilsek de, "Zoo" parametresini
onunla geçirmenin bir yolu yok. Bu nedenle, bu lambda'yı bir yöntem referansı olarak yazmak mümkün değil.

## Calling Instance Methods on a Parameter

```Java
interface StringParameterChecker {
    boolean check(String text);
}
```

```
StringParameterChecker methodRef = String::isEmpty;
StringParameterChecker lambda = s -> s.isEmpty();

System.out.println(methodRef.check("Zoo")); // false
```

## Calling Constructors

Bir constructor referansı, bir metot yerine new kullanan ve bir nesneyi örnekleyen özel bir metot referansı türüdür. Bu
örnek için, fonksiyonel arayüzümüz herhangi bir parametre almayacak ancak bir String döndürecektir:

```Java
interface EmptyStringCreator {
    String create();
}
```

```
30: EmptyStringCreator methodRef = String::new;
31: EmptyStringCreator lambda = () ->
new String();
32:
33: var myString = methodRef.create();
34: System.out.println(myString.equals("Snake")); // false
```

## Reviewing Method References

| Type                                    | Before Colon           | After Colon | Example         |
|-----------------------------------------|------------------------|-------------|-----------------|
| static methods                          | Class name             | Method name | Math::random    |
| instance methods on a particular object | Instance variable name | Method name | str::startsWith |
| instance methods on a parameter         | Class name             | Method name | String::isEmpty |
| Constructor                             | Class name             | new         | String::new     |


