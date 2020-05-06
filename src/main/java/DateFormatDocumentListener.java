
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DateFormatDocumentListener implements DocumentListener {

    boolean automaticChange = false;
    JTextField textField;

    public DateFormatDocumentListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!automaticChange) {
            formatDateString();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (!automaticChange) {
            formatDateString();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (!automaticChange) {
            formatDateString();
        }
    }

    public void formatDateString() {
        automaticChange = true;
        Runnable doAssist = new Runnable() {
            @Override
            public void run() {
                boolean validDate;
                String text = textField.getText();

                // keep only digits
                text = text.replaceAll("[^\\d.]", "");
                
                // maximum 6 digits
                if (text.length() > 6) {
                    text = text.substring(0, 6);
                }
                
                // format digits as dd/mm/yy
                if (text.length() > 4) {
                    String first = text.substring(0, 2);
                    String second = text.substring(2, 4);
                    String third = text.substring(4, text.length());
                    validDate = isValidDateString(first, second, text.length() == 6 ? third : null);
                    text = String.format("%s/%s/%s", first, second, third);
                } else if (text.length() > 2) {
                    String first = text.substring(0, 2);
                    String second = text.substring(2, text.length());
                    validDate = isValidDateString(first, text.length() == 4 ? second : null, null);
                    text = String.format("%s/%s", first, second);
                } else {
                    String first = text.substring(0, text.length());
                    validDate = isValidDateString(text.length() < 2 ? null : first, null, null);
                }
                textField.setText(text);
                if (validDate) {
                    textField.setForeground(Color.BLACK);
                } else {
                    textField.setForeground(Color.RED);
                }
                automaticChange = false;
            }
        };
        SwingUtilities.invokeLater(doAssist);
    }

    private static boolean isValidDateString(String day, String month, String year) {
        return (day == null || isValidDayString(day))
                && (month == null || isValidMonthString(month))
                && (year == null || isValidYearString(year));
    }

    private static boolean isValidDayString(String s) {
        try {
            int n = Integer.parseInt(s);
            if (n <= 0 || n > 31) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean isValidMonthString(String s) {
        try {
            int n = Integer.parseInt(s);
            if (n <= 0 || n > 12) {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean isValidYearString(String s) {
        try {
            int n = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
