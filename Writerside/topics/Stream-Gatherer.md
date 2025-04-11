# Stream Gatherer

Gatherer interface Gatherer için temel sınıftır. Java streamler için özelleştirme de kullanılır.
Stream pipeline verileri mevcut yerleşik ara işlemlerle kolayca gerçekleştirilemeyen şekillerde dönüştürmesini sağlayan
özel ara işlemler oluşturmanıza olanak tanır.

Kısaca karmaşık veri işlemeyi kolaylaştıran ve verimli hale getiren birkaç temel yöntemden oluşur.

**Ne zaman Gatherer Kullanılmalı?**

Eğer iyi bir sebebiniz yoksa Gatherer API zoruluğundan dolayı kullanılması önerilmiyor. Stream içerinde olan kısımlar
için kullanılması önerilmiyor. Nerede kullanabiliriz derseniz;

* Streamden gelen ardışık öğelerin sabit boyutlu listelerinden oluşan bir streame ihtiyaç varsa kullanabilirsiniz. (
  window fixed)
* Öğeleri karşılaştırmanın özel bir yoluyla, Distinct benzeri bir işlem oluşturabilirsiniz.
* Kodunuzu daha okunabilir hale getirmek için, düzgün adlandırılmış, tek bir işlemde birleştirmek istediğiniz, belki
  biraz düz eşleme ve biraz isteğe bağlı işlemler içeren karmaşık bir map-filter işlemi oluşturabilirsiniz.

Bir Gatherer yazmak, fonksiyonel interface dönüşen Gatherer interface'i uygulamakla ilgilidir. Bunu bir lambda ile
uygulayabiliriz. Ayrıca bu interface'in sahip olduğu birkaç metot ile de bunu uygulayabiliriz.

Basit bir gatherer için Integrator parametresi alan Gatherer.of metotunu kullanabiliriz. Bir Integrator rolü, öğeleri
yukarıdaki streamden tüketmek ve öğeleri aşağıdaki streame aktarmaktır. Bu öğeler aynı, aynı tipte veya farklı olabilir.


----
src/main/java/com/umiitkose/streams/gathers/examples -> buradaki örnekleri aşağıdaki adımlarla incele.

src/main/java/com/umiitkose/streams/gathers/examples/BasicGather.java
src/main/java/com/umiitkose/streams/gathers/examples/DistinctByGatherer.java
src/main/java/com/umiitkose/streams/gathers/examples/FilterGatherers.java
src/main/java/com/umiitkose/streams/gathers/examples/FunctionGatherers.java
src/main/java/com/umiitkose/streams/gathers/examples/FlatmapGatherers.java
src/main/java/com/umiitkose/streams/gathers/examples/FusionGatherers.java

----

Gatherers Aşağıdaki işlemleri yapabilir.

* Elemanları 1-1, 1-N, N-1 ve N-N dönüşümleri için kullanılabilir.
* Daha sonraki öğelerin dönüşümünü etkilemek için daha önce görülen öğeleri takip edebilir.
* Streami paralel olarak işlemek için kullanılabilir.

Sonrada aşağıdaki durumu düşün.

Bu kod neden daha hızlı çalışabilir? Çünkü eşdeğer akış kodundan daha az nesne oluşturur. Her stream metot çağrısı, bir
ek yükü temsil eden yeni bir Stream nesnesi oluşturur. Bu kodda, yalnızca iki Stream nesnesi oluşturulmuştur ve bu bir
optimizasyon olabilir.

Gatherer<T,A,R> interface 3 parametreye sahiptir.

T -> gather işlemi için giriş elemanlarının tipi
A -> Toplayıcının daha önce görülen öğeleri izlemek ve daha sonraki öğelerin dönüşümünü etkilemek için kullanabileceği
toplayıcının özel durum nesnesinin türü.
R -> İşlem yapılan Elemanın çıkış tipi

**Initializer Method:**
Bu metot isteğe bağlıdır. Özellikle integrator veya finisher dahili bir durum kullanıldığında önemlidir. Bu
gibi durumlarda, Initializer Method herhangi bir veri işleme başlamadan önce bu dahili durumu düzgün bir şekilde
başlatmaktan sorumludur. Kısacası private state objenin (A) oluşturulmasıdır.

**Integrator Method:**
Her zaman uygulanmalıdır. Bu, gerçek veri işleme mantığının tanımlandığı ana metottur. Her Gatherer her stream
öğesinin nasıl işlendiğini ve geçerli duruma nasıl dahil edildiğini belirlemek esastır.

**Finisher Method:**
İsteğe bağlı olarak uygulanabilir. Tüm elemanlar entegre edildikten sonra son işleme ve dönüşümler için kullanılır.

**Combiner Method:**
Geçersiz kılma isteğe bağlıdır. Bu yöntem, farklı stream segmentlerinden gelen sonuçların nasıl birleştirileceğini
tanımladığı için paralel stream işlemleri için önemlidir. Akış paralel olarak işlendiğinde önemli hale gelir ve belirli
paralel işleme gereksinimlerinize göre uyarlanmalıdır.

**AndThen Method:**
Geçersiz kılma isteğe bağlıdır. Varsayılan uygulama, gatherer'ı diğer gatherer'lardan birleştirmeye izin verir. 

Integrator.greedy(): Bu metot, bir stream'in tüm öğelerini hemen işler ve tüketir. "Açgözlü" (greedy) olarak
adlandırılır çünkü stream'in tamamını hemen değerlendirir ve sonucu döndürmeden önce tüm işlemleri tamamlar.
Integrator.of(): Bu metot, daha çok "tembel" (lazy) bir değerlendirme yaklaşımı kullanır. Stream elemanlarını yalnızca
gerektiğinde işler ve tüketir, bu da bellek kullanımı ve performans açısından daha verimli olabilir.

Bu iki yaklaşım arasındaki seçim, uygulamanızın ihtiyaçlarına bağlıdır. Büyük veri kümeleriyle çalışırken veya işlemin
sonucunun tamamına hemen ihtiyaç duymadığınız durumlarda Integrator.of() daha uygun olabilir. Öte yandan, tüm işlemin
hemen tamamlanmasını istiyorsanız veya işlem sonucunun tamamına hemen ihtiyaç duyuyorsanız Integrator.greedy() tercih
edilebilir.

https://dev.java/learn/api/streams/gatherers/#intro --> Spliterator neyi yapamıyor?

