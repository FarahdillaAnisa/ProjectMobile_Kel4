package com.icha.projectmobile_kel4
import android.app.AlertDialog
import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class ReservasiAdapter (
    val ReservasiContext: Context,
    val layoutResId: Int,
    val reservasiList: List<Reservasi>
): ArrayAdapter<Reservasi>(ReservasiContext, layoutResId, reservasiList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater= LayoutInflater.from(ReservasiContext)
        val view: View = layoutInflater.inflate(layoutResId, null)

        val o_nama: TextView = view.findViewById(R.id.ou_nama)
        val o_hp: TextView = view.findViewById(R.id.ou_hp)
        val o_meja: TextView = view.findViewById(R.id.ou_meja)
        val imgEdit: ImageView = view.findViewById(R.id.icn_edit)
        val confirm : Button = view.findViewById(R.id.btnConfirmReservasi)
        val cancel : Button = view.findViewById(R.id.btnCancelReservasi)

        val reservasi = reservasiList[position]
        imgEdit.setOnClickListener{
            updateDialog(reservasi)
        }

        o_nama.text = "Nama : " + reservasi.namapemesan
        o_hp.text = "No. Hp : " + reservasi.nohp
        o_meja.text = "Jumlah Meja : " + reservasi.jumlahmeja
        return view
    }

    private fun updateDialog(reservasi: Reservasi){
        val builder = AlertDialog.Builder(ReservasiContext)
        builder.setTitle("Update Data Reservasi")
        val inflater = LayoutInflater.from(ReservasiContext)
        val view = inflater.inflate(R.layout.update_reservasi_pelanggan, null)

        val edtNama = view.findViewById<EditText>(R.id.upNama)
        val edtNohp = view.findViewById<EditText>(R.id.upNoHp)
        val edtMeja = view.findViewById<EditText>(R.id.upMeja)

        edtNama.setText(reservasi.namapemesan)
        edtNohp.setText(reservasi.nohp)
        edtMeja.setText(reservasi.jumlahmeja)

        builder.setView(view)
        builder.setPositiveButton("Ubah"){
                p0,p1 ->
            val dbReservasi = FirebaseDatabase.getInstance().getReference("reservasi")
            val nama = edtNama.text.toString().trim()
            val noHp = edtNohp.text.toString()
            val editmeja = edtMeja.text.toString()
            val meja : Int = editmeja.toInt()

            if (nama.isEmpty() or noHp.isEmpty() or editmeja.isEmpty()){
                Toast.makeText(ReservasiContext, "Isi data secara lengkap tidak boleh kosong",
                    Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            val reservasi = Reservasi(reservasi.id, nama, noHp, meja)

            dbReservasi.child(reservasi.id).setValue(reservasi)
            Toast.makeText(ReservasiContext, "Data berhasil di Update", Toast.LENGTH_SHORT).show()
        }

        builder.setNeutralButton("Batal"){p0,p1 ->}
        builder.setNegativeButton("Hapus"){p0,p1 ->
            val dbReservasi = FirebaseDatabase.getInstance().getReference("reservasi")
                .child(reservasi.id)
            val dbPemesanan = FirebaseDatabase.getInstance().getReference("PemesananMenu")
                .child(reservasi.id)

            dbReservasi.removeValue()
            dbPemesanan.removeValue()

            Toast.makeText(ReservasiContext, "Data berhasil dihapus", Toast.LENGTH_SHORT)
                .show()
        }

        val alert = builder.create()
        alert.show()
    }
}