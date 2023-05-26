package com.example.bhakti.ModelClass

class MandirModel {
    private lateinit var img: String
    private lateinit var name: String
    private lateinit var purl: String

    constructor()

    constructor(img: String, name: String, purl: String){
        this.img = img
        this.name = name
        this.purl = purl
    }

    fun getImg(): String{
        return img
    }

    fun getName(): String{
        return name
    }

    fun getPurl(): String{
        return purl
    }

    fun setImg(img: String){
        this.img = img
    }

    fun setName(name: String){
        this.name = name
    }

    fun setPurl(purl: String){
        this.purl = purl
    }
}