# Coding Functional Interfaces

Bir önceki bölümde CheckTrait 'i yazarken tek bir metota sahip bir interface yazdık. Gerçekte bu interface fonksiyonel
interface (functional interface) olarak geçer. Tek bir abstract metota sahip interfacetir. SAM (Single Abstract Method)
kuralı olarak aklında bulunsun.

## Defining a Functional Interface

Bir örnekle bunu anlatalım.

```Java

@FunctionalInterface
public interface Sprint {
    public void sprint(int speed);
}
```

```Java
public class Tiger implements Sprint {
    public void sprint(int speed) {
        System.out.println("writing_simple_lambdas.com.umiitkose.functional.programming.Animal is sprinting fast! " + speed);
    }
}
```

Tiger classı Sprint ismine sahip tek metotlu functional interface uygular.

```Java

@FunctionalInterface
public interface Dance { // DOES NOT COMPILE
    void move();

    void rest();
}
```

Eğer functional interface birden fazla soyut metota sahipse hata verecektir.

Bu anatosyon kullanan geliştirici bu interface'in gelecekte lambda işlemlerinde kullanacağını taahhüt eder.

```Java
public interface Dash extends Sprint {
}

public interface Skip extends Sprint {
    void skip();
}

public interface Sleep {
    private void snore() {
    }

    default int getZzz() {
        return 1;
    }
}

public interface Climb {
    void reach();

    default void fall() {
    }

    static int getBackUp() {
        return 100;
    }

    private static boolean checkHeight() {
        return true;
    }
}
```

Yukarıdaki 4 interfaceten hangileri functional interface'tir?

İlki tek metot olduğu için (Dash) fonksiyonel interfacetir. Skip sınıfı 2 tane arayüze sahip oldğundan değildir.
Sleep arayüzüde geçerli bir fonksiyonel interface değildir. Çünkü default ve private geçerli bir arayüz sağlamaz.

Climb ise geçerli olan bir fonksiyonel interface'tir.

## Adding Object Methods

Bütün sınıflar Object sınıfından belirli metotları kalıtım alır. Aşağıdakilerden bazıları;

* public String toString()
* public boolean equals(Object)
* public int hashCode()

Bunu şimdi gündeme getiriyoruz çünkü aşina olmanız gereken tek soyut yöntem kuralının bir istisnası var. Bir fonctional
interface, Object'te bulunan genel bir yöntemle aynı imzaya sahip soyut bir yöntem içeriyorsa, bu yöntemler tek soyut
yöntem testine dahil edilmez. Bu kuralın arkasındaki motivasyon, arayüzü uygulayan herhangi bir sınıfın, tüm sınıfların
yaptığı gibi Object'ten miras alacağı ve bu nedenle her zaman bu yöntemleri uygulayacağıdır.

> Java tüm sınıfların Object'ten uzandığını varsaydığından, Object ile uyumsuz bir arayüz yöntemi de bildiremezsiniz.
> Örneğin, bir arayüzde soyut bir int toString() yöntemi bildirmek derlenmez çünkü Object'in yöntem sürümü bir String
> döndürür.

```Java
public interface Soar {
    abstract String toString();
}
```

Bu geçerli değildir, Çünkü toString metotu Object sınıfında public olarak implemente edilmiştir.Burada ise bu
ezilmektedir.

```Java
public interface Dive {
    String toString();

    public boolean equals(Object o);

    public abstract int hashCode();

    public void dive();
}
```

Yukarıdaki dive interface'i ise single abstract bir metottur. Diğerleri ise Object sınıfında tanımlanan public metotlar
oldukları için sayılmazlar.

```
@FunctionalInterface
public interface AddingObject {

    void test();

    String toString();

    public abstract int hashCode();

    public boolean equals(Object o);

    //public boolean equals(String o); Eğer Object dışında geçersen compile error
}

```

Yukarıdakinde ise kod geçerlidir, yorum satırındaki kod aktif olursa compile error alınır. Object sınıfındaki metot
imzası ile aynı olması beklenir.

