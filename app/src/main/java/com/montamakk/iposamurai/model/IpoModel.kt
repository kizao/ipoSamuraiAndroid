package com.montamakk.iposamurai.model

import java.io.Serializable

data class IpoItem(
   val companyName: String = "",
   val assessment: String = "",
   val maxPrice: String = "",
   val minPrice: String = "",
   val offeringPrice: String = "",
   val applicationStart: String = "",
   val applicationEnd: String = "",
   val purchaseStart: String = "",
   val purchaseEnd: String = "",
   val listingDate: String = ""
): Serializable
