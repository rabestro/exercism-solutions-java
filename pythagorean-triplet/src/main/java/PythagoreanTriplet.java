import java.util.ArrayList;
import java.util.List;

record PythagoreanTriplet(int a, int b, int c) {

    static TripletListBuilder makeTripletsList() {
        return new TripletListBuilder();
    }

    static class TripletListBuilder {
        private int sum;
        private int maxFactor;

        TripletListBuilder thatSumTo(int sum) {
            this.sum = sum;
            return this;
        }

        TripletListBuilder withFactorsLessThanOrEqualTo(int maxFactor) {
            this.maxFactor = maxFactor;
            return this;
        }

        List<PythagoreanTriplet> build() {
            List<PythagoreanTriplet> triplets = new ArrayList<>();

            for (int a = 2; a <= sum / 3; ++a) {
                for (int b = a + 1; b <= 1 + (sum - a) / 2; ++b) {
                    int c = sum - a - b;
                    if (maxFactor > 0 && c > maxFactor) {
                        continue;
                    }
                    if (a * a + b * b == c * c) {
                        triplets.add(new PythagoreanTriplet(a, b, c));
                    }
                }
            }
            return triplets;
        }
    }
}
