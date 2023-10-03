package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.exception.PedidoNaoEncontradoException;
import curso.springboot.Vendas.exception.RegraNegocioException;
import curso.springboot.Vendas.rest.ApiErrors;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AplicationControllerAdvice {

  @ExceptionHandler(RegraNegocioException.class)
  @ResponseStatus(BAD_REQUEST)
  public ApiErrors handleRegraNegocioException(RegraNegocioException regraNegocioException) {
    String message = regraNegocioException.getMessage();
    return new ApiErrors(message);
  }

  @ExceptionHandler(PedidoNaoEncontradoException.class)
  @ResponseStatus(NOT_FOUND)
  public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
    return new ApiErrors(ex.getMessage());
  }
}
