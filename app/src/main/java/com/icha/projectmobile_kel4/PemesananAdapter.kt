package com.icha.projectmobile_kel4
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PemesananAdapter (
    val pemesananContext: Context,
    val layoutResId: Int,
    val pemesananList: List<Pesanmenu>
): ArrayAdapter<Pesanmenu>(pemesananContext, layoutResId, pemesananList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(pemesananContext)
        val view: View = layoutInflater.inflate(layoutResId, null)
        val tvmenu = view.findViewById<TextView>(R.id.ou_menu)
        val tvharga = view.findViewById<TextView>(R.id.ou_harga)
        val tvjumlah = view.findViewById<TextView>(R.id.ou_jumlah)
        val tvtotal = view.findViewById<TextView>(R.id.ou_total)
        val pesan = pemesananList[position]
        tvmenu.text = pesan.pesanmenu
        tvharga.text = pesan.pesanharga.toString()
        tvjumlah.text = pesan.pesanjumlah.toString()
        tvtotal.text = pesan.pesantotal.toString()
        return view
    }
}