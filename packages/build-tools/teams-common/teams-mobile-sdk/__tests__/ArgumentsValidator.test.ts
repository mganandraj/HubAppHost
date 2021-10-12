import { ArgumentException } from '../src/common/errors/ArgumentException';
import { ArgumentsValidator } from '../src/common/utilities/ArgumentsValidator';

it('should throw exception if argumentValue is null', () => {
  expect(function () { ArgumentsValidator.assertNotNullOrUndefined('argumentName', null); }).toThrow(ArgumentException);
});
