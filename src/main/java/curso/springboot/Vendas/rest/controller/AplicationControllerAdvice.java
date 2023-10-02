package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.exception.RegraNegocioException;
import curso.springboot.Vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplicationControllerAdvice {

  @ExceptionHandler(RegraNegocioException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiErrors handleRegraNegocioException(RegraNegocioException regraNegocioException) {
    String message = regraNegocioException.getMessage();
    return new ApiErrors(message);
  }
}
