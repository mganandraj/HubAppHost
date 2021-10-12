---
uid: resources
---
# Resources
The Teams SDK currently supports images and localized strings.

## Getting Started
All app resources must be stored within a directory named `resources` within the app's root directory.

## Images
1. Create a directory named `images` inside the `resources` directory.
2. Create two directories named `dark` and `default` inside the `images` directory and place the images accordingly based on the theme.
2. Add image resources as SVG images with the `.svg` extension.
    * **Important:** PNG images are also supported but not recommended.

### Accessing image resources
Access image resources by using relative paths from the `images` directory.

For example: `Resource.image('dark/icon.svg')` 
             `Resource.image('dark/folder1/folder2/icon.svg')`
             `res://images/dark/icon.svg`

### Fluent icons
Fluent icons can be specified as app icons using the `fluent://` URI scheme.
Here's the expected format:
```text
fluent://<icon_symbol>/<icon_style>
```
- `icon_symbol` is the fluent icon name.
- `icon_style` is either `regular` or `filled`.

Examples:
```
fluent://notepad/filled
fluent://camera/regular
```

## Strings
### Add string resources
1. Create a directory named `strings` inside the `resources` directory.
    * All string defintion files will go here.
2. Create `strings_en.json` or English strings.
    * **Note:** Values in this file are used as defaults if a localized string is not found.
3. Create strings for other languages & locales inside the strings resources directory using the following file name format: `strings_<language|locale>.json`.

#### Example
This example also illustrates how to define comments for a string. Comments are useful to provide the context in which the string is used inside the app.

```json
{
  "app_name": "Hello World!",
  "_app_name.comment": "Display name of the app"
}
```

### Implement localized strings provider
All apps must create a localized strings provider by implementing the interface `LocalizedStringsProvider`. This provider must be passed as a parameter during `ApplicationContext` initialization.

### Accessing string resources
Access defined string resources by using `ApplicationContext`. 

#### Example
```typescript
ApplicationContext.getString('app_name');
```

### Parameterized string resources
String resources support parameters. 

#### Example
```json
{
  "get_device_contacts_result_message": "Got {contactsCount} contact(s).",
  "_get_device_contacts_result_message.comment": "Toast message to show when the request to get device contacts is complete. {contactsCount} is the count of the contacts found on the device.",
}
```

To access it:

```typescript
Resource.getLocalizedString('get_device_contacts_result_message', { 'contactsCount': 0 });
```

See [documentation for `Resource.getLocalizedString`](xref:teams-mobile-sdk.Resource.getLocalizedString) for more.

#### Example

##### `LocalizedStringsProvider` Implementation
```typescript
import { LocalizedStringsProvider } from 'teams-mobile-sdk';

export class LocalizedStringsProviderImpl implements LocalizedStringsProvider {
  private static instance: LocalizedStringsProviderImpl = new LocalizedStringsProviderImpl();
  private englishStringTable: any;
  private supportedLocales: { [key: string]: () => Object };

  constructor () {
    let self = this;
    self.englishStringTable = require('../../resources/strings/strings_en.json') as any;
    self.supportedLocales = {
      'ar-SA': () => require('../../resources/strings/strings_ar.json'),
      'bg-BG': () => require('../../resources/strings/strings_bg.json'),
      'ca-ES': () => require('../../resources/strings/strings_ca.json'),
      'cs-CZ': () => require('../../resources/strings/strings_cs.json'),
      'da-DK': () => require('../../resources/strings/strings_da.json'),
      'de-DE': () => require('../../resources/strings/strings_de.json'),
      'el-GR': () => require('../../resources/strings/strings_el.json'),
      'en-GB': () => require('../../resources/strings/strings_en-GB.json'),
      'en-US': () => self.englishStringTable,
      'es-ES': () => require('../../resources/strings/strings_es.json'),
      'es-MX': () => require('../../resources/strings/strings_es-MX.json'),
      'es-US': () => require('../../resources/strings/strings_es-US.json'),
      'et-EE': () => require('../../resources/strings/strings_et.json'),
      'fi-FI': () => require('../../resources/strings/strings_fi.json'),
      'fr-FR': () => require('../../resources/strings/strings_fr.json'),
      'fr-CA': () => require('../../resources/strings/strings_fr-CA.json'),
      'he-IL': () => require('../../resources/strings/strings_he.json'),
      'hi-IN': () => require('../../resources/strings/strings_hi.json'),
      'hr-HR': () => require('../../resources/strings/strings_hr.json'),
      'hu-HU': () => require('../../resources/strings/strings_hu.json'),
      'id-ID': () => require('../../resources/strings/strings_id.json'),
      'it-IT': () => require('../../resources/strings/strings_it.json'),
      'ja-JP': () => require('../../resources/strings/strings_ja.json'),
      'ko-KR': () => require('../../resources/strings/strings_ko.json'),
      'lt-LT': () => require('../../resources/strings/strings_lt.json'),
      'lv-LV': () => require('../../resources/strings/strings_lv.json'),
      'ms-MY': () => require('../../resources/strings/strings_ms.json'),
      'nb-NO': () => require('../../resources/strings/strings_nb.json'),
      'nl-NL': () => require('../../resources/strings/strings_nl.json'),
      'pl-PL': () => require('../../resources/strings/strings_pl.json'),
      'pt-BR': () => require('../../resources/strings/strings_pt-BR.json'),
      'pt-PT': () => require('../../resources/strings/strings_pt.json'),
      'ro-RO': () => require('../../resources/strings/strings_ro.json'),
      'ru-RU': () => require('../../resources/strings/strings_ru.json'),
      'sk-SK': () => require('../../resources/strings/strings_sk.json'),
      'sl-SI': () => require('../../resources/strings/strings_sl.json'),
      'sr-Latn-RS': () => require('../../resources/strings/strings_sr-Latn.json'),
      'sv-SE': () => require('../../resources/strings/strings_sv.json'),
      'th-TH': () => require('../../resources/strings/strings_th.json'),
      'tr-TR': () => require('../../resources/strings/strings_tr.json'),
      'uk-UA': () => require('../../resources/strings/strings_uk.json'),
      'vi-VN': () => require('../../resources/strings/strings_vi.json'),
      'zh-CN': () => require('../../resources/strings/strings_zh-CN.json'),
      'zh-TW': () => require('../../resources/strings/strings_zh-TW.json')
    };
  }

  public static getInstance (): LocalizedStringsProvider {
    return LocalizedStringsProviderImpl.instance;
  }

  getSupportedLocaleStrings (): any {
    return this.supportedLocales;
  }

  getDefaultLocaleStrings (): any {
    return this.englishStringTable;
  }
}
``` 

##### Registering your provider
It is recommended to register your localized strings provider in `app.js`. For example:
```typescript
// app.js
LocalizedStrings.initialize(LocalizedStringsProviderImpl.getInstance());
```
