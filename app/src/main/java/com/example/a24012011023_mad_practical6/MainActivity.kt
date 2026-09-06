package com.example.a24012011023_mad_practical6

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextClock
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    lateinit var alarmFrameAnimation: AnimationDrawable
    lateinit var alarmImg: ImageView
    lateinit var heartFrameAnimation: AnimationDrawable
    lateinit var heartImg: ImageView
    lateinit var textAlarm: TextView
    lateinit var cardAlarm: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        alarmImg=findViewById<ImageView>(R.id.imageView)
        alarmImg.setBackgroundResource(R.drawable.alarm_animation_list)
        alarmFrameAnimation=alarmImg.background as AnimationDrawable

        heartImg=findViewById<ImageView>(R.id.heart)
        heartImg.setBackgroundResource(R.drawable.heart_animation_list)
        heartFrameAnimation=heartImg.background as AnimationDrawable

        textAlarm=findViewById<TextClock>(R.id.time)
        cardAlarm=findViewById<MaterialCardView>(R.id.createAlarmCard)

        findViewById<MaterialButton>(R.id.btnSetAlarm).setOnClickListener {
            showTimedialog()
        }
        findViewById<MaterialButton>(R.id.btnCancelAlarm).setOnClickListener {
            setAlarm(-1, AlarmBroadcastReceiver.STOP_VAL)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        alarmFrameAnimation.start()
        heartFrameAnimation.start()
    }

    private fun showTimedialog(){
        val cldr: Calendar= Calendar.getInstance()
        val h: Int=cldr.get(Calendar.HOUR_OF_DAY)
        val m: Int=cldr.get(Calendar.MINUTE)
        val picker= TimePickerDialog(
            this,
            {tp,sHour,sMinute->sendDialogDataToActivity(sHour,sMinute)},
            h,m,false
        )
        picker.show()
    }

    private fun setAlarm(modifyTime:Long,str:String): Boolean{
        val intent= Intent(this, AlarmBroadcastReceiver::class.java)
        intent.putExtra(AlarmBroadcastReceiver.SERVICE_KEY,str)
        val pendingIntent= PendingIntent.getBroadcast(applicationContext,159159222,intent,
            PendingIntent.FLAG_IMMUTABLE    )
        val alarmManager=getSystemService(ALARM_SERVICE) as AlarmManager
        if (str== AlarmBroadcastReceiver.START_VAL) {
            if (alarmManager.canScheduleExactAlarms()){
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,modifyTime,pendingIntent)
                return true
            }
            else
                return false
        }
        else if (str== AlarmBroadcastReceiver.STOP_VAL){
            sendBroadcast(intent)
            alarmManager.cancel(pendingIntent)
            return true
        }
        return false
    }

    fun sendDialogDataToActivity(hour:Int,minute:Int){
        val cldr: Calendar= Calendar.getInstance()
        cldr.set(Calendar.HOUR_OF_DAY,hour)
        cldr.set(Calendar.MINUTE,minute)
        cldr.set(Calendar.SECOND,0)
        setAlarm(cldr.timeInMillis, AlarmBroadcastReceiver.START_VAL)
    }
}