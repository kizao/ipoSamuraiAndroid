package com.example.iposamurai

import java.io.Serializable

data class IpoData(
    var companyName: Any? = null,
    val assessment: Any? = null,
    val companyNameAssessment: Any? = null,
    var maxPrice: Any? = null,
    var minPrice: Any? = null,
    var offeringPrice: Any? = null,
    var initialPrice: Any? = null,
    var applicationStart: Any? = null,
    var applicationEnd: Any? = null,
    var purchaseStart: Any? = null,
    var purchaseEnd: Any? = null,
    var listingDate: Any? = null
): Serializable