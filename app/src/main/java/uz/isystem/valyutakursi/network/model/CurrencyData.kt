package uz.isystem.valyutakursi.network.model

data class CurrencyData(
    val id: Long,
    val Code: String,
    val Ccy: String,
    val CcyNm_UZ: String,
    val Rate: String,
    val Nominal: String,
    val Diff: String,
    val Date: String
)