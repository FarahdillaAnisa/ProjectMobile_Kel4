package com.icha.projectmobile_kel4
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import android.widget.Button
import java.util.*
import android.app.DatePickerDialog
import android.os.PersistableBundle
import android.widget.DatePicker
import java.text.SimpleDateFormat
import kotlinx.android.synthetic.main.update_reservasi_pelanggan.*

class update_reservasi_pelanggan : AppCompatActivity(){

    var button_date : Button? = null
    var textView_date : TextView? = null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_reservasi_pelanggan)

        //--------------elemen tanggal -------------------------
        //ambil elemen dari layout
        textView_date = this.uptglreservasi
        button_date = this.btnuptgl

        textView_date!!.text = "--/--/----"

        //buat onDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // pada saat klik akan show DatePickerDialog - OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@update_reservasi_pelanggan,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // format tanggal
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textView_date!!.text = sdf.format(cal.getTime())
    }
    //--------------elemen tanggal -------------------------
}
