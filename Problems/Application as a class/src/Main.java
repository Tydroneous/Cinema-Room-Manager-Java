class Application {

    String name;

    void run(String[] args) {
        System.out.println(this.name);
        for (String i : args) {
            System.out.println(i);
        }
    }
}