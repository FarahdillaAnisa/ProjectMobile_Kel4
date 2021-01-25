package com.icha.projectmobile_kel4

class Pesanmenu (
    val id: String,
    val pesanmenu: String,
    val pesanharga: Int,
    val pesanjumlah: Int,
    val pesantotal: Int
){
    constructor() : this("", "", 0, 0, 0)
}