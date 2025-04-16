import com.umiitkose.events.example.functional_interface.Consumer;
import com.umiitkose.events.example.functional_interface.StringFunction;


void main() {
    Consumer<String> newConsumer = System.out::println;
    Consumer<String> newConsumer2 = test();
    Consumer<String> stringConsumer = newConsumer.andThen(newConsumer2);

    stringConsumer.accept("HelloWord");

    StringFunction stringFunction = (String s) -> System.out.println("String: " + s);
    stringFunction.convert("HelloWorld");

}



public Consumer<String> test() {
    return (String s) -> System.out.println("Log Basılıyor.");
}
