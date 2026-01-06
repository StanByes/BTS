package fr.riot.ap.controls;

import se.alipsa.ymp.YearMonthPicker;

import java.time.YearMonth;
import java.util.Locale;

public class ExpirationYearMonthPicker extends YearMonthPicker {
    public ExpirationYearMonthPicker() {
        this(YearMonth.now());
    }

    public ExpirationYearMonthPicker(YearMonth initial) {
        super(initial, initial.plusYears(10).withMonth(12), initial, Locale.getDefault(), "MMMM");
    }
}
