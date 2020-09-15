package app.notification.missedcallapplication;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class CustomPhoneStateListener extends PhoneStateListener {
    int prev_state=0;

    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        switch(state){

            switch(state){
                case TelephonyManager.CALL_STATE_RINGING:
                    Log.d("TAG", "CALL_STATE_RINGING");
                    prev_state=state;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    Log.d("TAG", "CALL_STATE_OFFHOOK");
                    prev_state=state;
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    NumberDatabase database=new NumberDatabase(mContext);
                    if((prev_state==TelephonyManager.CALL_STATE_OFFHOOK)){
                        prev_state=state;
                        //Answered Call which is ended
                    }
                    if((prev_state==TelephonyManager.CALL_STATE_RINGING)){
                        prev_state=state;
                        //Rejected or Missed call
                    }
                    break;
            }
    }

}
