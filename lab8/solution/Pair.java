class Pair<T, U> {
    private final T t;
    private final U u;

    private Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<T, U>(t, u);
    }

    T first() {
        return this.t;
    }

    U second() {
        return this.u;
    }

    @Override
    public String toString() {
        return "(" + this.t + ", " + this.u + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Pair) {
            Pair<?,?> other = (Pair<?,?>) obj;
            return this.first().equals(other.first()) &&
                this.second().equals(other.second());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(t, u);
    }
}
