package com.icha.projectmobile_kel4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter(val mCtx: Context, val layoutResId: Int, val list: List<Menu> )
    : ArrayAdapter<Menu>(mCtx,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(layoutResId,null)

        val textNamaMenu = view.findViewById<TextView>(R.id.textMenu)
        val textHargaMenu = view.findViewById<TextView>(R.id. textHarga)
        val textKategoriMenu = view.findViewById<TextView>(R.id. textKategori)

        val menu = list[position]

        textNamaMenu.text = menu.NamaMenu
        textHargaMenu.text = menu.HargaMenu.toString()
        textKategoriMenu.text = menu.Kategori

        return view

    }
}