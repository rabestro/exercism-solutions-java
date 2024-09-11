import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HandTest {

    @Test
    public void getValue() {
        var hand = new Hand("2S 4H 6S 4D JH");
        assertEquals("HKDIM", hand.getValue());
    }

}