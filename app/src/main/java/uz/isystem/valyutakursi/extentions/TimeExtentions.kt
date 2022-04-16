package uz.isystem.valyutakursi.extentions

import android.os.CountDownTimer


class TimeExtentions(
    val millisInFuture: Long, val countDownTimer: Long,
    private val doOnTick: (time: Long) -> Unit = {},
    private val doOnFinish: () -> Unit = {},
) : CountDownTimer(millisInFuture, countDownTimer) {
    override fun onTick(p0: Long) {
        doOnTick(p0)
    }

    override fun onFinish() {
        doOnFinish()
    }

}