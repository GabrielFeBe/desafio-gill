package com.transacoes.transacoes.dto;

import java.util.Date;

public record TransactionsReturnDto(Integer id, Double value, Date transactiondate, String category) {

}
