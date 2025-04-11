
void main() {
    Gatherer<Integer, ?, Integer> sc =
            Gatherers.scan(() -> 1,
                    (current, next) -> current + next);

    Gatherer<Integer, ?, String> fo =
            Gatherers.fold(() -> "",
                    (result, element) -> {
                        if (result.isEmpty()) return element.toString();
                        return result + ";" + element;
                    });

    var t = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .gather(sc.andThen(fo)) //same -> .gather(sc).gather(fo)
            .findFirst()
            .get();

    System.out.println(t);
}
