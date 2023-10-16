package com.akal.account.dto

import com.akal.account.model.Customer
import com.akal.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
    val id: String?,
    val balance: BigDecimal? = BigDecimal.ZERO,
    val creationDate: LocalDateTime,
    val customer: AccountCustomerDto?,
    val transactions: Set<TransactionDto>?
)
