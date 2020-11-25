package vendas.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

 public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<?>>{

	public boolean isValid(List<?> list,
			ConstraintValidatorContext constraintValidatorContext) {
 		return list != null && !list.isEmpty();
	}
	
	@Override
	public void initialize (NotEmptyList ConstraintAnnotation ) {
		
	}

}
