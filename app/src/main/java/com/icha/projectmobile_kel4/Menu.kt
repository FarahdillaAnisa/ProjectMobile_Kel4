package com.icha.projectmobile_kel4

class Menu (
    val id: String,
    val menu: String,
    val harga: Int,
    val kategori: String
){
    constructor() : this("", "", 0, "")
}