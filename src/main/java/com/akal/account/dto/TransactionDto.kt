package com.akal.account.dto

import com.akal.account.model.TransacitonType
import java.math.BigDecimal
import java.time.LocalDateTime

data class TransactionDto(
    val id: String?,
    val transacitonType: TransacitonType?= TransacitonType.INITIAL,
    val amount: BigDecimal?,
    val transacitonDate: LocalDateTime?,

    )
