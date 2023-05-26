package com.example.bhakti.ModelClass

class RamModel
{
    private lateinit var name: String
    private lateinit var purl: String


    constructor()


    constructor(name: String, purl: String ) {
        this.name = name
        this.purl = purl
    }

    fun getName(): String {
        return name
    }

    fun getPurl(): String {
        return purl
    }

    fun setName(name: String){
        this.name = name
    }

    fun setPurl(purl: String){
        this.purl = purl
    }





}