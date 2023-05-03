import { FormControl, ValidationErrors } from '@angular/forms';

export class FormValidators {
  // whitespace validation
  static notOnlyWhitespace(control: FormControl): ValidationErrors {
    if (control.value != null && control.value.trim().length === 0) {
      return { notOnlyWhitespace: true };
    } else {
      // valid, return null
      return null;
    }
  }
}
