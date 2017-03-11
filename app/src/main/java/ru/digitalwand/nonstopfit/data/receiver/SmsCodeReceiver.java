package ru.digitalwand.nonstopfit.data.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.telephony.SmsMessage;

import ru.digitalwand.nonstopfit.BuildConfig;

/**
 * Created by Igor Goryainov
 * skype - glotemz
 * on 11.03.2017 17:11.
 */
public class SmsCodeReceiver extends BroadcastReceiver {

  private static final String SMS_ADDRESS = BuildConfig.SMS_ADDRESS;
  private static final String SEPARATOR = System.getProperty("line.separator");

  @Nullable
  protected SmsCodeReceiverCallback callback;

  public SmsCodeReceiver() {
    this(null);
  }

  public SmsCodeReceiver(@Nullable final SmsCodeReceiverCallback callback) {
    this.callback = callback;
  }

  @Override
  public void onReceive(final Context context, final Intent intent) {
    if (intent != null
        && intent.getAction() != null
        && Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equalsIgnoreCase(intent.getAction())) {
      final SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
      final StringBuilder stringBuilder = new StringBuilder();
      for (final SmsMessage message : messages) {
        if (SMS_ADDRESS.equalsIgnoreCase(message.getOriginatingAddress())) {
          stringBuilder.append(message.getMessageBody()).append(SEPARATOR);
        }
      }
      if (callback != null) {
        callback.onReceive(stringBuilder.substring(0, stringBuilder.lastIndexOf(SEPARATOR)));
      }
    }
  }

  public interface SmsCodeReceiverCallback {
    void onReceive(String code);
  }
}
