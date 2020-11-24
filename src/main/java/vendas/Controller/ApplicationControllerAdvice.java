package vendas.Controller;

import org.springframework.http.HttpStatus;
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
}
