package ru.digitalwand.nonstopfit.ui.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.DatePicker;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 25.03.2017 20:26.
 */
public class DateEditText extends android.support.v7.widget.AppCompatEditText {

  @Nullable
  private OnDateChangedListener dateChangeListener;
  private String dateFormat = "dd.MM.yyyy";

  public DateEditText(Context context) {
    this(context, null);
  }

  public DateEditText(Context context, AttributeSet attrs) {
    this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
  }

  public DateEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  public interface OnDateChangedListener {
    void onDateChanged(final Date date);
  }

  private void init() {
    setFocusable(false);
    setFocusableInTouchMode(false);
    setCursorVisible(false);
    setOnClickListener(v -> showDatePicker());
  }

  public void setDateFormat(@NonNull final String dateFormat) {
    this.dateFormat = dateFormat;
  }

  public void setOnDateChangeListener(@Nullable final OnDateChangedListener dateChangeListener) {
    this.dateChangeListener = dateChangeListener;
  }

  public void showDatePicker() {
    final int[] date = getYearMouthDay();
    new DatePickerDialog(getContext(), this::onDateSet, date[0], date[1], date[2]).show();
  }

  private void onDateSet(final DatePicker view, final int year, final int month, final int day) {
    final Calendar calendar = getDate(year, month, day);
    setText(DateFormatUtils.format(calendar, dateFormat));
    if (dateChangeListener != null) {
      dateChangeListener.onDateChanged(calendar.getTime());
    }
  }

  private static int[] getYearMouthDay() {
    final int[] date = new int[3];
    final Calendar c = Calendar.getInstance();
    date[0] = c.get(Calendar.YEAR);
    date[1] = c.get(Calendar.MONTH);
    date[2] = c.get(Calendar.DAY_OF_MONTH);
    return date;
  }

  private static Calendar getDate(final int year, final int month, final int day) {
    final Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, day);
    return c;
  }
}
