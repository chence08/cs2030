import java.util.Optional;
import java.util.stream.Stream;

class Num extends AbstractNum<Integer> {
    static Num of(int i) {
        return new Num(Optional.of(i));
    }

    private Num(Optional<Integer> opt) {
        super(validate(opt));
    }

    static Optional<Integer> validate(Optional<Integer> opt) {
        return opt.filter(AbstractNum.valid);
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    private Num(AbstractNum<Integer> anum) {
        this(anum.opt);
    }

    static Num one() {
        return new Num(zero().opt.map(s));
    }

    static Num NaN() {
        return new Num(Optional.empty());
    }

    Num succ() {
        return new Num(opt.map(s));
    }

    Num add(Num otherNum) {
        if (!isValid() || !otherNum.isValid()) {
            return NaN();
        }
        /*
        Suppose we have 3 + 2.
        
        Set count = -2;
        We increment 3 by 1, and count by 1 in each iteration.
        The loop stops when count == 0.
        */
        Num currNum = this;
        Optional<Integer> count = otherNum.opt.map(n);
        Optional<Boolean> FALSE = Optional.of(false);
        while (count.map(x -> valid.test(x)).equals(FALSE)) {
            currNum = currNum.succ();
            count = count.map(s);
        }
        return currNum;
    }

    Num add_Streams(Num otherNum) {
        if (!isValid() || !otherNum.isValid()) {
            return NaN();
        }
        /*
        Suppose we have 3 + 2.

        create stream {0, 1}
        number of elements in stream = 2
        perform reduction:
        0: 3 -> 4
        1: 4 -> 5

        Stream implementation for other operations are left as an exercise.
        */
        return Stream.iterate(Num.zero(), x -> !x.equals(otherNum), x -> x.succ())
                     .reduce(this, (x, y) -> x.succ());
    }

    Num mul(Num otherNum) {
        if (!isValid() || !otherNum.isValid()) {
            return NaN();
        }
        /*
        Suppose we have 3 * 2.

        We start with 0;
        Set count = -2;
        So we will add 3 to 0 twice.
        */
        Num currNum = zero();
        Optional<Integer> count = otherNum.opt.map(n);
        Optional<Boolean> FALSE = Optional.of(false);
        while (count.map(x -> valid.test(x)).equals(FALSE)) {
            currNum = currNum.add(this);
            count = count.map(s);
        }
        return currNum;
    }

    Num sub(Num otherNum) {
        if (!isValid() || !otherNum.isValid()) {
            return NaN();
        }
        /*
        Suppose we have 3 - 2.

        Set second = -2;
        Set count = -3;
        */
        Optional<Integer> second = otherNum.opt.map(n);
        Optional<Integer> count = opt.map(n);
        Optional<Boolean> FALSE = Optional.of(false);
        while (count.map(x -> valid.test(x)).equals(FALSE)) {
            second = second.map(s);
            count = count.map(s);
        }
        return new Num(second);
    }

    /*
    HELPER FUNCTIONS FOR FRACTION

    Only addition and multiplication are commutative.
    */

    Optional<Num> add(Optional<Num> otherNum) {
        return otherNum.map(x -> x.add(this));
    }

    Optional<Num> mul(Optional<Num> otherNum) {
        return otherNum.map(x -> x.mul(this));
    }

    Optional<Num> sub(Optional<Num> otherNum) {
        return otherNum.map(x -> this.sub(x));
    }

    Num modulo(Num divisor) {
        if (divisor.equals(zero())) {
            return NaN();
        }
        if (!sub(divisor).isValid()) {
            return this;
        }
        Num previous = this;
        Num current = previous;
        while (current.isValid()) {
            previous = current;
            current = current.sub(divisor);
        }
        return previous;
    }

    static Num gcd(Num a, Num b) {
        if (b.equals(zero())) {
            return a;
        }
        return gcd(b, a.modulo(b));
    }

    /** 
     * only used for division by gcd, so input is always valid.
     */
    Num div(Num divisor) {
        Num quotient = zero();
        Num dividend = this;
        while(dividend.isValid()) {
            quotient = quotient.succ();
            dividend = dividend.sub(divisor);
        }
        return quotient.sub(one());
    }

    Optional<Num> div(Optional<Num> divisor) {
        return divisor.map(x -> this.div(x));
    }
}