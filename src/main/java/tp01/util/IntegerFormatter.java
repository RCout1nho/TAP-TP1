package tp01.util;

import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class IntegerFormatter {
    protected static NumberFormatter formatter;

    public static NumberFormatter getFormatter() {
        formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        return formatter;
    }
}
