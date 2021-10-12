import * as LocalizedStrings from '../src/lib/services/LocalizedStrings';

LocalizedStrings.initialize({
  getSupportedLocaleStrings (): any {
    let temp = [];
    temp['en-US'] = {};
    temp['en-CA'] = {};
    temp['es-MX'] = {};
    return temp;
  },
  getDefaultLocaleStrings (): any {
    return ['en-US'];
  }
});

it('should get default locale', () => {
  expect(LocalizedStrings.getLocale()).toMatch(/^en-US$/);
});

it('should set locale to \'en-CA\'', () => {
  LocalizedStrings.setLocale('en-CA');
  expect(LocalizedStrings.getLocale()).toMatch(/^en-CA$/);
});

it('should set locale as \'en-US\' as default for unsupported locale \'de-DE\'', () => {
  LocalizedStrings.setLocale('de-DE');
  expect(LocalizedStrings.getLocale()).toMatch(/^en-US$/);
});

it('should set locale to \'es-MX\' as the fallback for \'es-CO\'', () => {
  LocalizedStrings.setLocale('es-CO');
  expect(LocalizedStrings.getLocale()).toMatch(/^es-MX$/);
});
