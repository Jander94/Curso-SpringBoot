package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.exception.PedidoNaoEncontradoException;
import curso.springboot.Vendas.exception.RegraNegocioException;
import curso.springboot.Vendas.rest.ApiErrors;
import static org.springframework.http.HttpStatus.*;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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


//  Escuta os erros de validação do Bean validation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(BAD_REQUEST)
  public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
    List<String> errors = ex.getBindingResult().getAllErrors()
            .stream()
            .map(erro -> erro.getDefaultMessage())
            .collect(Collectors.toList());

    return new ApiErrors(errors);
  }
}
