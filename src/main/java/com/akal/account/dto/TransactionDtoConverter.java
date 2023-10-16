package com.akal.account.dto;


import com.akal.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(Transaction from) {
        return new TransactionDto(from.getId(),
                from.getTransacitonType(),
                from.getAmount(),
                from.getTransacitonDate());
    }
}