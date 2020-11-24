package vendas.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vendas.ApiErrors;
import vendas.Exception.PedidoNaoEncontradoException;
import vendas.Exception.RegrasNegocioException;


@RestControllerAdvice
public class ApplicationControllerAdvice {
    
	 @ExceptionHandler(RegrasNegocioException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ApiErrors hendleRegraNegocioException(RegrasNegocioException ex) {
    	  String menssagemErro = ex.getMessage();
    	  return new ApiErrors(menssagemErro);
     }
	 
	 @ExceptionHandler(PedidoNaoEncontradoException.class)
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
		 return new ApiErrors(ex.getMessage());
	 }
	 
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	 public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
			List<String> errors = ex.getBindingResult().getAllErrors()
			 .stream()
			 .map(erro -> erro.getDefaultMessage())
			 .collect(Collectors.toList());
		  return new ApiErrors(errors);
	 }

}













