package com.akal.account.model

import javax.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Transaction(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        val id: String?,
        val transacitonType: TransacitonType?=TransacitonType.INITIAL,
        val amount: BigDecimal?,
        val transacitonDate:LocalDateTime?,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name="account_id", nullable = false)
        val account: Account


){
    constructor(amount: BigDecimal,account: Account):this(
        id= null,
        amount = amount,
        transacitonDate = LocalDateTime.now(),
        transacitonType = TransacitonType.INITIAL,
        account = account
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (id != other.id) return false
        if (transacitonType != other.transacitonType) return false
        if (amount != other.amount) return false
        if (transacitonDate != other.transacitonDate) return false
        if (account != other.account) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (transacitonType?.hashCode() ?: 0)
        result = 31 * result + (amount?.hashCode() ?: 0)
        result = 31 * result + (transacitonDate?.hashCode() ?: 0)

        return result
    }
}
enum class TransacitonType{
        INITIAL,TRANSFER
}

