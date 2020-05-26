package com.example.ss

import com.google.gson.annotations.SerializedName

class JsonData {
    @SerializedName("ID")
    var ID: String? = null
    @SerializedName("Position")
    var Position: String? = null
    @SerializedName("EName")
    var EName: String? = null
    @SerializedName("X")
    var X: String? = null
    @SerializedName("Y")
    var Y: String? = null
    @SerializedName("CArea")
    var CArea: String? = null
    @SerializedName("EArea")
    var EArea: String? = null
    @SerializedName("CAddress")
    var CAddress: String? = null
    @SerializedName("EAddress")
    var EAddress: String? = null
    @SerializedName("AvailableCNT")
    var AvailableCNT: String? = null
    @SerializedName("EmpCNT")
    var EmpCNT: String? = null
    @SerializedName("UpdateTime")
    var UpdateTime: String? = null
}