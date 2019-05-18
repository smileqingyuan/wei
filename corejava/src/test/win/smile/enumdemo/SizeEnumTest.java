package win.smile.enumdemo;

import org.junit.Test;

public class SizeEnumTest {

    @Test
    public void testSizeEnum(){
        System.out.println(SizeEnum.LARGE);
        System.out.println(SizeEnum.LARGE.getAbbreviation());
        System.out.println(SizeEnum.valueOf("LARGE"));
        SizeEnum[] values = SizeEnum.values();
        for (SizeEnum value : values) {
            System.out.println(value.getAbbreviation());
        }
        System.out.println(SizeEnum.LARGE.getEnSize());
    }

}
