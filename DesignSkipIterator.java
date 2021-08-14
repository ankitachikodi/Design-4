class SkipIterator implements Iterator<Integer> {
    private  Iterator<Integer> it;
    private  Map<Integer, Integer> count;
    private Integer nextEl;

    public SkipIterator(Iterator<Integer> it) {
        this.it = it;
        this.count = new HashMap<>();
        advance();
    }

    public boolean hasNext() {
        return nextEl != null;
    }

    public Integer next() {
        Integer el = nextEl;
        advance();
        return el;
    }

    public void skip(int num) {
        if (nextEl == num) {
            advance();
        } else {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
    }

    private void advance() {
        nextEl = null;
        while (nextEl == null && it.hasNext()) {
            Integer el = it.next();
            if (!count.containsKey(el)) {
                nextEl = el;
            } else {
                count.put(el, count.get(el) - 1);
                count.remove(el, 0);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SkipIterator it = new SkipIterator(Arrays.asList(4, 2, 3, 5, 6).iterator());
        System.out.println(it.hasNext());
        it.skip(2);
        it.skip(3);
        it.skip(3);
        System.out.println(it.hasNext());
    }
}