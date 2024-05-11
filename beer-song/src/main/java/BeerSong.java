class BeerSong {

    private static final int INITIAL_BOTTLES = 99;

    public String sing(int count, int repeat) {
        final var song = new StringBuilder();
        while (repeat-- > 0) {
            if (count == 0) {
                song.append("No more bottles of beer on the wall, no more bottles of beer.\n")
                        .append("Go to the store and buy some more, ");
                count = INITIAL_BOTTLES;
            } else {
                song.append(bottle(count)).append(" of beer on the wall, ")
                        .append(bottle(count)).append(" of beer.\n")
                        .append("Take ").append(count == 1 ? "it" : "one")
                        .append(" down and pass it around, ");
                --count;
            }
            song.append(bottle(count)).append(" of beer on the wall.\n\n");
        }
        return song.toString();
    }

    public String singSong() {
        return sing(INITIAL_BOTTLES, 100);
    }

    private String bottle(int count) {
        return switch (count) {
            case 0 -> "no more bottles";
            case 1 -> "1 bottle";
            default -> count + " bottles";
        };
    }
}
