import java.util.Optional;
import java.util.function.Predicate;

class Fraction extends AbstractNum<Frac> {
    static Fraction of(int n, int d) {
        return new Fraction(makeOpt(n, d));
    }

    static Optional<Frac> makeOpt(int n, int d) {
        return Optional.of(Frac.of(Num.of(n), Num.of(d)));
    }

    static Optional<Frac> validate(Optional<Frac> opt) {
        Predicate<Frac> valid = frac -> frac.first().isValid() && 
                                        frac.second().isValid() &&
                                        !frac.second().equals(Num.zero());
        return opt.filter(valid);
    }

    private Fraction(Optional<Frac> opt) {
        super(validate(opt));
    }

    static Fraction NaN() {
        return new Fraction(Optional.empty());
    }

    Optional<Num> getNumerator() {
        return opt.map(x -> x.first());
    }

    Optional<Num> getDenominator() {
        return opt.map(x -> x.second());
    }

    Fraction add(Fraction otherFraction) {
        Optional<Num> a = getNumerator();
        Optional<Num> b = getDenominator();
        Optional<Num> c = otherFraction.getNumerator();
        Optional<Num> d = otherFraction.getDenominator();
        Optional<Num> ad = a.flatMap(x -> x.mul(d));
        Optional<Num> bc = b.flatMap(x -> x.mul(c));
        Optional<Num> numerator = ad.flatMap(x -> x.add(bc));
        Optional<Num> denominator = b.flatMap(x -> x.mul(d));
        Optional<Frac> opt = numerator.flatMap(x -> denominator.map(y -> Frac.of(x, y)));
        return new Fraction(opt);
    }

    Fraction sub(Fraction otherFraction) {
        Optional<Num> a = getNumerator();
        Optional<Num> b = getDenominator();
        Optional<Num> c = otherFraction.getNumerator();
        Optional<Num> d = otherFraction.getDenominator();
        Optional<Num> ad = a.flatMap(x -> x.mul(d));
        Optional<Num> bc = b.flatMap(x -> x.mul(c));
        Optional<Num> numerator = ad.flatMap(x -> x.sub(bc));
        Optional<Num> denominator = b.flatMap(x -> x.mul(d));
        Optional<Frac> opt = numerator.flatMap(x -> denominator.map(y -> Frac.of(x, y)));
        return new Fraction(opt);
    }

    Fraction mul(Fraction otherFraction) {
        Optional<Num> a = getNumerator();
        Optional<Num> b = getDenominator();
        Optional<Num> c = otherFraction.getNumerator();
        Optional<Num> d = otherFraction.getDenominator();
        Optional<Num> numerator = a.flatMap(x -> x.mul(c));
        Optional<Num> denominator = b.flatMap(x -> x.mul(d));
        Optional<Frac> opt = numerator.flatMap(x -> denominator.map(y -> Frac.of(x, y)));
        return new Fraction(opt);
    }

    Fraction reduce() {
        if (equals(NaN())) {
            return this;
        }
        Optional<Num> numerator = getNumerator();
        Optional<Num> denominator = getDenominator();
        if (numerator.equals(denominator)) {
            return new Fraction(Optional.of(Frac.of(Num.one(), Num.one())));
        }
        Optional<Num> gcd = numerator.flatMap(x -> denominator.map(y -> Num.gcd(x, y)));
        Optional<Num> newNumerator = numerator.flatMap(x -> x.div(gcd));
        Optional<Num> newDenominator = denominator.flatMap(x -> x.div(gcd));
        Optional<Frac> opt = newNumerator.flatMap(x -> newDenominator.map(y -> Frac.of(x, y)));
        return new Fraction(opt);
    }
}