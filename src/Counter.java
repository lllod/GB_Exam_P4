public class Counter implements AutoCloseable{
    static int count = 0;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }

    public void print() {
        System.out.println("Количество животных: " + count);
    }

    @Override
    public void close() {
        System.out.println("Счетчик закрыт");
    }

}