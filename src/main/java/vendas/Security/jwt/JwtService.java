package vendas.Security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import vendas.VendasApplication;
import vendas.Entity.Usuario;

@Service
public class JwtService {
    
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${spring.jwt.chave-Assinatura}")
	private String chaveAssinatura;
	
	
	public String getExpiracao() {
		return expiracao;
	}

	public void setExpiracao(String expiracao) {
		this.expiracao = expiracao;
	}

	public String getChaveAssinatura() {
		return chaveAssinatura;
	}

	public void setChaveAssinatura(String chaveAssinatura) {
		this.chaveAssinatura = chaveAssinatura;
	}
	
	public String gerarToken(Usuario usuario) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
  		
		return Jwts
				.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(data)
 				.signWith( SignatureAlgorithm.ES512,chaveAssinatura)
				.compact();
  	}
	
	
      private Claims ObterClaims(String token) throws ExpiredJwtException {
    	  return Jwts
    			  .parser()
    			  .setSigningKey(chaveAssinatura)
    			  .parseClaimsJwt(token)
    			  .getBody();
      }
      
      
      public boolean Tokenvalido(String token) {
    	  try {
    		  Claims claims = ObterClaims(token);
    		  Date dataExpiracao = claims.getExpiration();
    		  LocalDateTime data = dataExpiracao.toInstant()
    				        .atZone(ZoneId.systemDefault()).toLocalDateTime();
    		  return !LocalDateTime.now().isAfter(data);
    		  
    	  }catch (Exception e) {
			 return false;
		}
      }
      
      public String obterUsuario(String token) throws ExpiredJwtException {
    	  return (String) ObterClaims(token).getSubject();
      }
	
	 public static void main(String[] args) {
		   
		  ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
		  
 		  JwtService service = contexto.getBean(JwtService.class);
		  Usuario usuario = Usuario.builder().login("fulano").build();
		  String token = service.gerarToken(usuario);
		  System.out.println(token);
		  
		  boolean isTokenValido = service.Tokenvalido(token);
		  System.out.println("O teken setá válido "+ isTokenValido);
		  
		  System.out.println(service.obterUsuario(token));
 	}
}
	
	

