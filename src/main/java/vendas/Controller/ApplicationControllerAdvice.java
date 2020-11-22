package vendas.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import vendas.ApiErrors;
import vendas.Exception.RegrasNegocioException;


@RestControllerAdvice
public class ApplicationControllerAdvice {
    
	 @ExceptionHandler(RegrasNegocioException.class)
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ApiErrors hendleRegraNegocioException(RegrasNegocioException ex) {
    	  String menssagemErro = ex.getMessage();
    	  return new ApiErrors(menssagemErro);
     }
}
