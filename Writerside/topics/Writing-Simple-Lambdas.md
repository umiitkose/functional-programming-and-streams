# Writing Simple Lambdas

Java kalbinde Object Oriented dildir. Functional programlama daha fazla declaratively kod yazmanın bir yoludur.
Nesnelerin durumuyla uğraşmak yerine ne yapmak istediğinizi belirtirsiniz. Döngülerden çok ifadelere odaklanırsınız.

Fonksiyonel programlama kod yazarken lambda ifadeler kullanılır.

## Looking at a Lambda Example

Bizim uygulamamız bazı kriterlere göre hayvan listesini yazdırmak olsun.

```Java
public record writing_simple_lambdas.com.umiitkose.functional.programming.Animal(String species, boolean canHop, boolean canSwim) {
}
```

writing_simple_lambdas.com.umiitkose.functional.programming.Animal record sınıfı 3 tane field sahip. Hayvan sınıfını zıplama yeteneği olanlara göre özelleştirme yapalım.

```Java
public interface CheckTrait {
    boolean test(writing_simple_lambdas.com.umiitkose.functional.programming.Animal a);
}
```

```Java
public class CheckIfHopper implements CheckTrait {
    public boolean test(writing_simple_lambdas.com.umiitkose.functional.programming.Animal a) {
        return a.canHop();
    }
}
```

```
import java.util.*;
public class TraditionalSearch {
public static void main(String[] args) {

// list of animals
var animals = new ArrayList<writing_simple_lambdas.com.umiitkose.functional.programming.Animal>();
animals.add(new writing_simple_lambdas.com.umiitkose.functional.programming.Animal("fish", false, true));
 animals.add(new writing_simple_lambdas.com.umiitkose.functional.programming.Animal("kangaroo", true, false));
 animals.add(new writing_simple_lambdas.com.umiitkose.functional.programming.Animal("rabbit", true, false));
 animals.add(new writing_simple_lambdas.com.umiitkose.functional.programming.Animal("turtle", false, true));

 // pass class that does check
 print(animals, new CheckIfHopper());
 }
 private static void print(List<writing_simple_lambdas.com.umiitkose.functional.programming.Animal> animals, CheckTrait checker) {
 for (writing_simple_lambdas.com.umiitkose.functional.programming.Animal animal : animals) {

 // General check
 if (checker.test(animal))
 System.out.print(animal + " ");
 }
 System.out.println();
 }
 }
```

Herşey gayet güzel. 6 satırda animal listemizi oluşturup eklemeler yapıp, print metotuyla yazdırıyoruz.

Eğer yüzme işi olsaydı CheckIfSwim adlı sınıfla bu işide halledecektik.

Önemsediğimiz mantığı neden burada belirtemiyoruz? Lambda ifadeleriyle bunu yapabileceğimiz ortaya çıktı. Tüm sınıfı
burada tekrarlayıp değişen tek satırı bulmanızı sağlayabiliriz. Bunun yerine, print() yöntem bildirimimizi değişmeden
tutabileceğimizi gösteriyoruz. 13. satırı, lambda kullanan aşağıdakiyle değiştirelim:

```
print(animals, a -> a.canHop());
```

Syntax biraz garip görünmesinden endişe etmeyin. Alışacaksınız ve bunu bir sonraki bölümde açıklayacağız. Ayrıca
sihir gibi görünen kısımları da açıklıyoruz. Şimdilik, okumasının ne kadar kolay olduğuna odaklanın. Java'ya yalnızca
bir Hayvanın zıplayabilmesini önemsediğimizi söylüyoruz. Yüzme yeteneğine sahip Hayvanları elde etmek için mantığı nasıl
ekleyeceğimizi anlamak için fazla hayal gücüne gerek yok. Sadece bir satır kod eklememiz gerekiyor; basit bir şey yapmak
için ekstra bir sınıfa gerek yok. İşte diğer satır:

```
print(animals, a -> a.canSwim());
```

Yüzme yeteneği olmayan hayvanlar içinse;

```
print(animals, a -> !a.canSwim());
```

Önemli olan, temelleri yerleştirdikten sonra lambda kullanan kod yazmanın gerçekten kolay olmasıdır. Bu kod, ertelenmiş
yürütme (Deferred execution) adı verilen bir kavram kullanır. Ertelenmiş yürütme (Deferred execution), kodun şimdi
belirtildiği ancak daha sonra çalışacağı anlamına gelir. Bu durumda, "daha sonra", yönteme geçirildiğinde değil, print()
yöntem gövdesinin içindedir.

## Learning Lambda Syntax

Az önce en basitinden bir lambda expression yazdık.

```
print(animals, a -> a.canSwim());
```

Lambdalar 1 soyut metota sahip interfaceler ile çalışır.Örneğimizdeki lambda, Java'nın a.canHop()'un sonucu olan bir
boolean değeri döndüren bir writing_simple_lambdas.com.umiitkose.functional.programming.Animal parametresine sahip bir yöntemi çağırması gerektiğini önerir. Tüm bunları biliyoruz
çünkü kodu biz yazdık. Peki Java bunu nasıl biliyor?

Java, lambda ifadelerinin ne anlama geldiğini anlamak için contexte güvenir. Context, lambdanın nerede ve nasıl
yorumlanacağını ifade eder. Örneğin, hayvanat bahçesine girmek için sırada bekleyen birini görürsek ve cüzdanını
çıkarmışsa, hayvanat bahçesi bileti almak istediğini varsaymak adildir. Alternatif olarak, cüzdanını çıkarmış bir
şekilde büfe sırasında bekliyorsa, muhtemelen açtır.

Yukarıdaki örneğe bakarsak 2. parametre olarak lambda geçtik. Bunun yerine bir lambda geçirdiğimiz için Java, lambdamızı
CheckTrait arayüzündeki soyut yöntem bildirimine eşlemeye çalışır.

Bu arayüzün yöntemi bir writing_simple_lambdas.com.umiitkose.functional.programming.Animal aldığından, lambda parametresi bir writing_simple_lambdas.com.umiitkose.functional.programming.Animal olmak zorundadır. Ve bu arayüzün yöntemi bir
boolean döndürdüğünden, lambdanın bir boolean döndürdüğünü biliyoruz.

Lambdaların sözdizimi aldatıcıdır çünkü birçok parça isteğe bağlıdır. Bu iki satır tam olarak aynı şeyi yapar:

```
a -> a.canHop()
(writing_simple_lambdas.com.umiitkose.functional.programming.Animal a) ->{ return a.canHop(); }
```

Burada neler olup bittiğine bir bakalım.
**Şekil 8.1'de gösterilen ilk örnek üç bölümden oluşur:**

* a adıyla belirtilen tek bir parametre
* Parametreyi ve gövdeyi ayırmak için ok operatörü (->)
* Tek bir yöntemi çağıran ve bu yöntemin sonucunu döndüren bir gövde

![Screenshot 2024-07-25 at 14.45.42.png](lambda_expression.png)

İkinci örnek, bir boolean döndüren bir lambda'nın en ayrıntılı biçimini gösterir.

* Adı a olan ve türün writing_simple_lambdas.com.umiitkose.functional.programming.Animal olduğunu belirten tek bir parametre
* Parametreyi ve gövdeyi ayırmak için ok operatörü (->)
* Noktalı virgül ve bir return ifadesi içeren bir veya daha fazla kod satırına sahip bir gövde

![Screenshot 2024-07-25 at 14.47.07.png](lambda_expression_2.png)

> Here’s a fun fact: s -> {} is a valid lambda. If there is no code on the right side of the expression, you don’t need
> the semicolon or return statement.

**Geçerli lambda ifadeleri:**

|           valid lambda expression            | # of parameters |
|:--------------------------------------------:|:---------------:|
|                  () -> true                  |        0        |
|          x -> x.startsWith("test")           |        1        |
|      (String x) -> x.startsWith("test")      |        1        |
|  (x, y) ->{ return x.startsWith("test"); }   |        2        |
| (String x, String y) -> x.startsWith("test") |        2        |

**Geçersiz lambda ifadeleri:**

|             Invalid lambda             |        Sebep         |
|:--------------------------------------:|:--------------------:|
|      x, y -> x.startsWith("fish")      |  solda parantez yok  |
|    x -> { x.startsWith("camel"); }     |   sağda return yok   |
| x ->{ return x.startsWith("giraffe") } |   en sonda ; eksik   |
|     String x ->x.endsWith("eagle")     | Solda parantez eksik |

> Bu kod satırının neden derlenmediğini düşünüyorsunuz?
>
> var gecersiz = (Hayvan a) -> a.canHop(); // DERLEMEZ
>
>Java'nın lambda hakkında bilgiyi bağlamdan çıkardığından bahsettiğimizi hatırlıyor musunuz? Eh, var da bağlama göre
> türü
> varsayar. Burada yeterli bağlam yok! Ne lambda ne de var, hangi tür işlevsel arayüzün kullanılması gerektiğini
> belirlemek için yeterli bilgiye sahip değil.


