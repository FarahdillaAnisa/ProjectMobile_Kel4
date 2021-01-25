package com.icha.projectmobile_kel4

class Reservasi (
    val id: String,
    val namapemesan: String,
    val nohp: String,
    val jumlahmeja: Int

){
    constructor() : this("", "", "", 0)
}