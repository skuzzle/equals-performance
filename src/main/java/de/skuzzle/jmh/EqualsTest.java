package de.skuzzle.jmh;

import java.util.Objects;

public class EqualsTest {

    private static class AlwaysEquals {
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    private final int primitive;
    private final Object reference1;
    private final Object reference2;
    private final Object reference3;
    private final Object reference4;

    public EqualsTest(int primitive, Object reference1, Object reference2, Object reference3, Object reference4) {
        this.primitive = primitive;
        this.reference1 = reference1;
        this.reference2 = reference2;
        this.reference3 = reference3;
        this.reference4 = reference4;
    }

    public static EqualsTest eqInstance() {
        return new EqualsTest(1, new AlwaysEquals(), new AlwaysEquals(), new AlwaysEquals(), new AlwaysEquals());
    }

    public static EqualsTest randomInstance() {
        final double rnd = Math.random();
        final int r = (int) (rnd * 3.0);
        final String s = "s" + r;
        return new EqualsTest(r, s, s, s, s);
    }

    public boolean equals2Casts(Object obj) {
        return obj == this || primitive == ((EqualsTest) obj).primitive
                && Objects.equals(reference1, ((EqualsTest) obj).reference1)
                && Objects.equals(reference2, ((EqualsTest) obj).reference2)
                && Objects.equals(reference3, ((EqualsTest) obj).reference3)
                && Objects.equals(reference4, ((EqualsTest) obj).reference4);
    }

    public boolean equals1CastInline(Object object) {
        EqualsTest obj;
        return object == this || object instanceof EqualsTest
                && primitive == (obj = (EqualsTest) object).primitive
                && Objects.equals(reference1, obj.reference1)
                && Objects.equals(reference2, obj.reference2)
                && Objects.equals(reference3, obj.reference3)
                && Objects.equals(reference4, obj.reference4);
    }

    public boolean equals1CastClassic(Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof EqualsTest)) {
            return false;
        }
        final EqualsTest obj = (EqualsTest) object;
        return primitive == obj.primitive
                && Objects.equals(reference1, obj.reference1)
                && Objects.equals(reference2, obj.reference2)
                && Objects.equals(reference3, obj.reference3)
                && Objects.equals(reference4, obj.reference4);
    }

    public boolean equals1CastGetClass(Object object) {
        if (object == this) {
            return true;
        } else if (object.getClass() != EqualsTest.class) {
            return false;
        }
        final EqualsTest obj = (EqualsTest) object;
        return primitive == obj.primitive
                && Objects.equals(reference1, obj.reference1)
                && Objects.equals(reference2, obj.reference2)
                && Objects.equals(reference3, obj.reference3)
                && Objects.equals(reference4, obj.reference4);
    }

}