package zelvalea.tasks.oop;

public record Triangle (
        double a, double b, double c
) {

    public double computeBisector() {
        double t = b+c;
        return Math.sqrt(b*c*(a+t)*(t-a))/t;
    }
    public double computeMedian() {
        return Math.sqrt(2*(a*a+c*c)-b*b)/2;
    }

    public double computeHeight() {
        return (2 * square()) / a;
    }
    public double square() {
        double p = (a+b+c) / 2;

        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public Triangle scaleTriangle(double scale) {
        return new Triangle(a*scale,b*scale,c*scale);
    }

    public AngleData computeAngles(AngleUnit angleUnit) {
        return angleUnit.compute(a,b,c);
    }


    private enum AngleUnit {
        RADIANS {
            @Override
            public AngleData compute(
                    double a,
                    double b,
                    double c
            ) {
                double alpha = Math.acos((b*b + c*c - a*a) / (2*b*c));

                double beta = Math.acos((a*a + c*c - b*b) / (2*a*c));

                double gamma = Math.acos((a*a + b*b - c*c) / (2*a*b));

                return new AngleData(alpha, beta, gamma);
            }
        },
        DEGREES {
            @Override
            public AngleData compute(
                    double a,
                    double b,
                    double c
            ) {
                AngleData rad = RADIANS.compute(a,b,c);

                return new AngleData(
                        Math.toDegrees(rad.alpha),
                        Math.toDegrees(rad.beta),
                        Math.toDegrees(rad.gamma)
                );
            }
        };

        public abstract AngleData compute(
                double a,
                double b,
                double c
        );
    }
    public record AngleData(
            double alpha,
            double beta,
            double gamma
    ) {
        @Override
        public String toString() {
            return "alpha=" + alpha + ", beta=" + beta + ", gamma=" + gamma;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triangle triangle = (Triangle) o;

        return Double.compare(square(), triangle.square()) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
