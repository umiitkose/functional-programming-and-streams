import com.umiitkose.events.example.functional_interface.Consumer;

;

void main() {
    Consumer<String> newConsumer = System.out::println;
    Consumer<String> newConsumer2 = test();
    Consumer<String> stringConsumer = newConsumer.andThen(newConsumer2);

    stringConsumer.accept("HelloWord");

}



private Consumer<String> test() {
    return (String s) -> System.out.println("Log Basılıyor.");
}
