package com.example.bhakti.ModelClass

class CharVedModel {
    private lateinit var img: String
    private lateinit var purl: String


    constructor()


    constructor(img: String, purl: String ) {
        this.img = img
        this.purl = purl
    }

    fun getImg(): String {
        return img
    }

    fun getPurl(): String {
        return purl
    }

    fun setImg(img: String){
        this.img = img
    }

    fun setPurl(purl: String){
        this.purl = purl
    }
}