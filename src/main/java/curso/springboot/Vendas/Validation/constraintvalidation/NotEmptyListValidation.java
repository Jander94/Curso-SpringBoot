package curso.springboot.Vendas.Validation.constraintvalidation;

import curso.springboot.Vendas.Validation.NotEmptyList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListValidation implements ConstraintValidator<NotEmptyList, List> {
  @Override
  public void initialize(NotEmptyList constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }

  @Override
  public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
    return list != null && !list.isEmpty();
  }
}
