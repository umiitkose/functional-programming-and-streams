# Working with Variables in Lambdas

Artık fonksiyonel arayüzleri öğrendiğimize göre, bunları değişkenler için farklı yaklaşımları göstermek için
kullanacağız. Lambdalara göre üç yerde görünebilirler: parametre listesi, lambda gövdesi içinde bildirilen yerel
değişkenler ve lambda gövdesinden başvurulan değişkenler. Bunların üçü de sınavın sizi kandırması için fırsatlardır. Her
birini inceliyoruz, böylece hileler ortaya çıktığında tetikte olursunuz!

| Functional interfaces                                                                                                     | Return type                         | Single abstract method                                                      | # of parameters                                         |
|---------------------------------------------------------------------------------------------------------------------------|-------------------------------------|-----------------------------------------------------------------------------|---------------------------------------------------------|
| ToDoubleFunction`<`T`>` ToIntFunction`<`T`>` ToLongFunction`<`T`>`                                                        | double int  long                    | applyAsDouble applyAsInt  applyAsLong                                       | 1 (T)                                                   |
| ToDoubleBiFunction`<`T, U`>` ToIntBiFunction`<`T, U`>` ToLongBiFunction`<`T, U`>`                                         | double int  long                    | applyAsDouble applyAsInt  applyAsLong                                       | 2 (T, U)                                                |
| DoubleToIntFunction DoubleToLongFunction  IntToDoubleFunction  IntToLongFunction  LongToDoubleFunction  LongToIntFunction | int long  double  long  double  int | applyAsInt applyAsLong applyAsDouble applyAsLong  applyAsDouble  applyAsInt | 1 (double) 1 (double) 1 (int) 1 (int) 1 (long) 1 (long) |
| ObjDoubleConsumer`<`T`>` ObjIntConsumer`<`T`>` ObjLongConsumer`<`T`>`                                                     | void                                | accept                                                                      | 2 (T, double) 2 (T, int) 2 (T, long)                    |

## Listing Parameters

Bu bölümün önceki kısımlarında, parametre türünü belirtmenin isteğe bağlı olduğunu öğrendiniz. Ek olarak, var belirli
tür yerine kullanılabilir. Bu, bu üç ifadenin de birbirinin yerine geçebileceği anlamına gelir:

``
Predicate`<`String`>` p = x -`>`true;
Predicate`<`String`>` p = (var x) -`>` true;
Predicate`<`String`>` p = (String x) -`>` true;
``

## Using Local Variables Inside a Lambda Body

Çoğu lambda tek bir ifade iken, block olarakta tanımlamak legaldir. Local değişkenler içeren bu blocklar javada
geçerlidir.

```
(a, b) -> { int c = 0; return 5; }
(a, b) -> { int a = 0; return 5; } // DOES NOT COMPILE
```

İlk ifade legalken, ikinci ifade de java aynı değişken ismine izin vermez.

Aşağıdai ifadede kaç tane syntax hatası vardır?

```
11: public void variables(int a) {
12: int b = 1;
13: Predicate<Integer> p1 = a ->
{
14: int b = 0;
15: int c = 0;
16: return b == c; }
17: }
```

3 tane vardır. ilki 13 satırdaki değişken isminin kullanılmasıdır. 2. 14. satırdaki local değişkenin yeniden
kullanılmasındadır. Son ise 16. satırdadır.
p1 değişkeninin sonunda bir noktalı virgül eksik. }'den önce bir noktalı virgül var, ancak bu bloğun içinde. Normalde
eksik noktalı virgülleri aramanıza gerek kalmazken, lambdalar bu alanda aldatıcıdır, bu yüzden dikkatli olun!

## Referencing Variables from the Lambda Body

Lambda gövdeleri ile birden fazla değişkenli bir kod parçası yazmak mümkündür.

```Java
public class Crow {
    private String color;

    public void caw(String name) {
        String volume = "loudly";
        Consumer<String> consumer = s ->
                System.out.println(name + " says "
                        + volume + " that she is " + color);
    }
}
```

Bu, bir lambdanın belirli koşullar altında bir instance değişkenine, metot parametresine veya local değişkene
erişebileceğini gösterir. instance değişkenlere (ve sınıf değişkenlerine) her zaman izin verilir.
Lambdaların erişemediği tek şey, final olmayan veya effectively final olmayan değişkenlerdir. Etkili bir şekilde son
hakkında bir tekrara ihtiyacınız varsa, Bölüm 5, “Metotlara” bakın.

```
2: public class Crow {
3: private String color;
4: public void caw(String name) {
5: String volume = "loudly";
6: name = "Caty";
7: color = "black";
8:
9: Consumer<String> consumer = s ->
10: System.out.println(name + " says " // DOES NOT COMPILE
11: + volume + " that she is " + color); // DOES NOT COMPILE
12: volume = "softly";
13: }
14: }
```

**Kurallar:**

| Variable type     | Rule                                  |
|-------------------|---------------------------------------|
| Instance variable | Allowed                               |
| Static variable   | Allowed                               |
| Local variable    | Allowed if final or effectively final |
| Method parameter  | Allowed if final or effectively final |
| Lambda parameter  | Allowed                               |
