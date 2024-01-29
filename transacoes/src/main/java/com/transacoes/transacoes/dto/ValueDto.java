package com.transacoes.transacoes.dto;

import java.util.List;

public record ValueDto<T>(Integer numberOfInsertions,T value) {

}
