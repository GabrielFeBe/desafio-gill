package com.transacoes.transacoes.dto;

import java.util.List;

public record PersonDto(List<TransactionsReturnDto> transactions, String email, String name) {

}