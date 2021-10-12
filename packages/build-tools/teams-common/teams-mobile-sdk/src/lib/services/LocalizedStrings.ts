/**
 * Copyright © Microsoft Corporation. All rights reserved.
 */

import each = require('lodash/each');
import isNumber = require('lodash/isNumber');
import isString = require('lodash/isString');
import mapValues = require('lodash/mapValues');
import MessageFormat = require('messageformat');
import { Platform } from 'react-native';
import { ArgumentsValidator } from '../../common/utilities/ArgumentsValidator';

/*
 * Basic framework for string localization that abstracts the localization implementation
 * away from the UI/UX designs.
 *
 * When adding new strings to be localized, make sure to add them to the /rawstrings/LocalizedStrings_en.json file.
 * If you add it to the /strings/ version of the file (or any of the other languages), they will be overwritten by
 * the build system and/or the translation system.
 *
 * In addition, you must add a dev comment to your string as well, which gives the translators context for the key
 * that they are translating (since they are not using/looking at the app when translating, all they get is your
 * input english string and the dev comment to work off.)
 */
export interface LocalizedStringsProvider {
  getSupportedLocaleStrings (): { [key: string]: () => Object };
  getDefaultLocaleStrings (): Object;
}

enum PlatformType {
  iOS = 1,
  Android = 2,
  Unknown = 999
}

interface LocaleWithTable {
  newLocale: string;
  // Note: TypeScript cannot really help us here. We generally get the table from parsing a file so there is no type-checking there.
  // Also, we need casts to use the structure. It is a dictionary while traversing, but a string at the end.
  localizedStringTable: any;
}

interface ConfigModel {
  // Converts user input into a proper locale string. Could just pass through or call toLower.
  normalizeLocale: (inputLocale: string, platform: PlatformType) => string;

  // Gets the string table for the given locale. Note: This can return a new locale in case multiple locales map to the same table.
  getLocaleInfoFromLocale: (normalizedLocale: string) => LocaleWithTable;

  // The table with all known strings, used to fallback in case the current locale is missing that key.
  defaultStringTable: any;
}

// The difference between this and locale:
//   - userInputLocaleNormalized is what the user tried to set. It is also normalized (lowercased,
//   trimmed, convert to dash separated, etc).
//   - locale is the locale we support that is closest to what the user intended to set. This also
//   respect the standard format: ab-BC
let userInputLocaleNormalized: string;
let locale = 'en-US';
let language = 'en';

// If you set this locale, then all string building functions in the Localizer will simply return the path string.
// Used for automation, so they can always find the right string by ID.
const AutomationLocale = 'auto-auto';

let messageFormat: MessageFormat;

// Lookup of raw strings that do not require any compilation (replacements)
let rawStringCache: { [key: string]: string } = {};

// Lookup of strings that require compilation (replacements)
let compiledStringCache: { [key: string]: (params: { [name: string]: string | number }) => string } = {};

let localizedStringTable: any;

let localizedStringsProvider: LocalizedStringsProvider;
let initialized = false;

function normalizeLocale (locale: string, platform: PlatformType): string {
  let normalized = locale;

  // Android represents locales like: language + "_" + country + "_" + (variant + "_#" | "#") + script + "-" + extensions
  // https://developer.android.com/reference/java/util/Locale.html#toString()
  if (platform === PlatformType.Android) {
    normalized = normalized.replace(/_/g, '-').replace(/#.*/, '').replace(/-$/, '');
  }

  return normalized.toLowerCase();
}

// Change the active locale to something else -- will invalidate existing string package for the next access.
// @param {string} inputLocale New locale code to use
export function setLocale (inputLocale: string): void {
  let platform = PlatformType.Unknown;
  if (Platform.OS === 'ios') {
    platform = PlatformType.iOS;
  }
  if (Platform.OS === 'android') {
    platform = PlatformType.Android;
  }

  const activeConfig = getActiveConfig();
  const newNormalizedLocale = activeConfig.normalizeLocale(inputLocale, platform);
  if (newNormalizedLocale === userInputLocaleNormalized) {
    return;
  }
  userInputLocaleNormalized = newNormalizedLocale;

  // Clear the string cache
  clearStringCache();

  // Load the strings for the new locale
  // Note: this is allowed to change the locale string
  const info = activeConfig.getLocaleInfoFromLocale(userInputLocaleNormalized);
  const newLocale = info.newLocale;
  localizedStringTable = info.localizedStringTable;

  // Load up a new messageformat object with the right locale
  messageFormat = new MessageFormat(
    (userInputLocaleNormalized === AutomationLocale ||
      (userInputLocaleNormalized.length > 3 && userInputLocaleNormalized.substr(0, 3) === 'qps'))
      ? 'en'
      : newLocale
  );

  if (newLocale !== locale) {
    locale = newLocale;
    // Given a locale of the format "{a}-{b}" the first part before the dash character is the language code.
    const newLanguage = locale && locale.split('-')[0];

    if (language !== newLanguage) {
      language = newLanguage;
    }
  }
}

function clearStringCache () {
  rawStringCache = {};
  compiledStringCache = {};
}

// Strings in the localization dictionary are formatted as nested dictionaries.  You can nest the keys as deep as you desire,
// and when you call getString to get the string back again, you simply separate the parent keys with periods.
// Example localization file: { "Hub": { "Panel" : "Hi" } }
// Example query mechanism: Localizer.getString('Hub.Panel')
//
// You can also use MessageFormat-formatted strings for embedding values/numbers/conditionals in your strings.
// References:
//   https://www.npmjs.com/package/messageformat
//   http://userguide.icu-project.org/formatparse/messages
//   http://icu-project.org/apiref/icu4j/com/ibm/icu/text/MessageFormat.html
//
// There are several examples at the sites above, but you can use them from the UI like a normal .getString, but instead pass
// in the dictionary of parameters (i.e. Localized.getString('Hub.Panel', { numUsers: 4 })).
export function getString (path: string, paramData: { [name: string]: string | number } = {}): string {
  // Does the string require any replacements or other conditional formatting?
  if (paramData) {
    if (!compiledStringCache[path]) {
      // Compile and cache the string
      const rawStr = _getRawString(path);
      compiledStringCache[path] = messageFormat.compile(rawStr);
    }

    return compiledStringCache[path](paramData);
  } else {
    if (!rawStringCache[path]) {
      rawStringCache[path] = _getRawString(path);
    }
    return rawStringCache[path];
  }
}

export function getLocale (): string {
  return locale;
}

function _getRawString (path: string): string {
  if (userInputLocaleNormalized === AutomationLocale) {
    return path;
  }

  const sections = path.split('.');
  let base = localizedStringTable;
  let englishBase = getActiveConfig().defaultStringTable;
  each(sections, section => {
    const next = base[section];
    const nextEnglishBase = englishBase[section];
    englishBase = nextEnglishBase;

    // Fall back to english base if nothing else exists
    base = next || englishBase;
  });

  return base;
}

const UniqueStart = '!%&^^';
const UniqueEnd = '!^^&%';

// Localizes just like getString, but non-string/non-number placeholders are 'passed through' instead of inserting into the string.
// Returns an array for the string split on the 'pass through' placeholders (e.g. [a, " called ", b, "."]).
// E.g.
//   Localization strings: {
//   "MissedCall": "You missed a call from {person}",
//   "PeopleTyping": "{ peopleTyping, plural, =2{{firstTyper} and {secondTyper} are typing} other {# people are typing} }"
//   }
//
//   const person1 = new Person('Tom');
//   const person2 = 'Bob';
//
//   const missedCallParts = getParts("MissedCall", { person: person1 });
//   // -> ["You missed a call from ", person1]
//   // -> [person1, " さんから不在着信がありました"]
//   // Preserves language ordering.
//
//   const twoPeopleTypingParts = getParts("PeopleTyping", { firstTyper: person1, secondTyper: person2, peopleTyping: 2 });
//   // -> [person1, " and Bob are typing"]
//   // -> [person1, " と Bob が入力中"]
//   // Numbers and strings are interpreted/inserted correctly (e.g. {firstTyper} and {peopleTyping, 'plural', ...}).
//
//   const manyPeopleTypingParts = getParts("PeopleTyping", { firstTyper: person1, secondTyper: person2, peopleTyping: 4 });
//   // -> ["4 people are typing"]
//   // -> ["4 人が入力中"]
//   // Numbers are inserted correctly.
export function getParts<T> (path: string, paramData: { [name: string]: T | string | number })
  : Array<T | string> {
  if (userInputLocaleNormalized === AutomationLocale) {
    return [path];
  }

  const paramPlaceholders = mapValues(paramData, (value, key) =>
    isString(value) || isNumber(value) ? '' + value : UniqueStart + key + UniqueEnd);

  // Localize the string like normal, except some of the placeholders are special strings.
  // E.g.
  //   getParts("MissedCall", { person: new Date })
  //   -> "You missed a call from !%&^^person!^^&%"
  //   The value of the 'person' parameter is NOT inserted in the resulting string.
  const localizedWithPlaceholders = getString(path, paramPlaceholders);

  const result: Array<T | string> = [];
  let start = 0;
  let prevStart = 0;
  // Loop to find each placeholder in the localized string, pushing its value into the array.
  // tslint:disable-next-line
  while ((start = localizedWithPlaceholders.indexOf(UniqueStart, start)) !== -1) {
    // assert.ok(prevStart <= start, 'Localizer internal error: Loop is not making progress?');
    if (prevStart < start) {
      // Output the string between the placeholders.
      result.push(localizedWithPlaceholders.substring(prevStart, start));
    }

    const end = localizedWithPlaceholders.indexOf(UniqueEnd, start + UniqueStart.length);
    // assert.notDeepEqual(end, -1, 'Localizer internal error: Found start-delimiter without end-delimiter');
    // assert.ok(start < end, 'Localizer internal error: Checked substring before start-delimiter?');

    // Note: the placeholder embeds the key. Extract it.
    const key = localizedWithPlaceholders.substring(start + UniqueStart.length, end);
    // assert.ok(paramData.hasOwnProperty(key), 'Localizer internal error: Invalid key in param data: ' + key);
    // assert.ok(!isString(paramData[key]) && !isNumber(paramData[key]),
    // 'Localizer internal error: Should not pass through string/number parameters');

    // Output the actual value.
    result.push(paramData[key] as T);

    // Move our 'pointers'.
    start = end + UniqueEnd.length;
    prevStart = start;
  }
  // Output the string (if any) after the last placeholder.
  if (prevStart < localizedWithPlaceholders.length) {
    result.push(localizedWithPlaceholders.substring(prevStart));
  }
  return result;
}

// Returns the 'closest' supported locale for requested locale.
// 1. If locale is supported return it.
// 2. If it is not supported but fallback exists use fallback and go back to 1.
// 3. If it is not supported and no fallback exists, or fallback is stuck on unsupported return default locale.
// Do all checks on lowercase but preserve original cases for return value.
//
// E.g.
//   supported locales: ['en-gb', 'es-es', 'en-us', 'de-de']
//   default locale: 'en-us'
//   requested  ->   returned
//  'de-de'   ->   'de-de' // 'de-de' is supported
//  'ar-sa'   ->   'en-us' // fallback to default 'en-us'
//  'en-ca'   ->   'en-gb' // fallback to 'en-gb'
//  'es-gt'   ->   'es-es' // fallback to 'es-mx' (not supported) then fallback to 'es-es'
function fallbackLocale (locale: string, supportedLocales: string[], defaultLocale: string): string {
  const supportedLocalesKeys: string[] = []; // array containing original keys as per supportedLocales
  const normSupportedLocales = Object.keys(supportedLocales).map(l => {
    supportedLocalesKeys.push(l);
    return l.toLowerCase();
  });
  const normDefaultLocale = defaultLocale.toLowerCase();

  let newLocale = locale.toLowerCase();
  const missedLocales: { [key: string]: boolean } = {};
  do {
    const index = normSupportedLocales.indexOf(newLocale);
    if (index !== -1) {
      return supportedLocalesKeys[index];
    } else {
      missedLocales[newLocale] = true;
      newLocale = getLocaleFallback(newLocale, normSupportedLocales, defaultLocale);
    }
  } while ((newLocale !== normDefaultLocale) && (!missedLocales[newLocale]));

  return defaultLocale;
}

function getLocaleFallback (locale: string, _: string[], defaultLocale: string) {
  switch (locale) {
    // Locales where "British" is preferred fallback.
    case 'en-ca':
    case 'en-au':
    case 'en-in':
    case 'en-nz':
    case 'en-hk':
    case 'en-ie':
    case 'en-pk':
    case 'en-sg':
      return 'en-gb';
    // For all Latin America, use Spanish(Mexico).
    case 'es-co':
    case 'es-ar':
    case 'es-cl':
    case 'es-pe':
    case 'es-ec':
    case 'es-ve':
    case 'es-cr':
    case 'es-do':
    case 'es-uy':
    case 'es-pa':
    case 'es-gt':
    case 'es-bo':
    case 'es-ev':
    case 'es-hn':
    case 'es-ni':
    case 'es-pu':
    case 'es-br':
    case 'es-pr':
    case 'es-419':
      return 'es-mx';
    case 'zh-hk':
    case 'zh-mo':
      return 'zh-tw';
  }

  // Now handling only the <ll> part of <ll-CC> [note: special handling for zh-Hans\Hant in iOS].
  if (locale.substr(0, 7) === 'zh-hant') {
    return 'zh-tw';
  }

  locale = locale.substr(0, 2);
  switch (locale) {
    case 'ar':
      return 'ar-sa';
    case 'de':
      return 'de-de';
    case 'en':
      return 'en-us';
    // Galician, Basque fallback to Spanish.
    case 'es':
    case 'gl':
    case 'eu':
      return 'es-es';
    case 'et':
      return 'et-ee';
    case 'fr':
      return 'fr-fr';
    // 'iw' is an obsolete language-code for Hebrew, but still widely used on Android platforms. It just falls-back to he.
    case 'he':
    case 'iw':
      return 'he-il';
    case 'hi':
      return 'hi-in';
    case 'it':
      return 'it-it';
    case 'ja':
      return 'ja-jp';
    case 'nl':
      return 'nl-nl';
    case 'pt':
      return 'pt-br';
    case 'tr':
      return 'tr-tr';
    case 'ru':
      return 'ru-ru';
    case 'zh':
      return 'zh-cn';
    case 'bg':
      return 'bg-bg';
    case 'ca':
      return 'ca-es';
    case 'hr':
      return 'hr-hr';
    case 'cs':
      return 'cs-cz';
    case 'da':
      return 'da-dk';
    case 'fi':
      return 'fi-fi';
    case 'el':
      return 'el-gr';
    case 'hu':
      return 'hu-hu';
    case 'in':
    case 'id':
      return 'id-id';
    case 'ko':
      return 'ko-kr';
    case 'lv':
      return 'lv-lv';
    case 'lt':
      return 'lt-lt';
    case 'ms':
      return 'ms-my';
    case 'nb':
      return 'nb-no';
    case 'pl':
      return 'pl-pl';
    case 'ro':
      return 'ro-ro';
    case 'sr':
      return 'sr-latn-rs';
    case 'sk':
      return 'sk-sk';
    case 'sl':
      return 'sl-si';
    case 'sv':
      return 'sv-se';
    case 'th':
      return 'th-th';
    case 'uk':
      return 'uk-ua';
    case 'vi':
      return 'vi-vn';
  }

  return defaultLocale;
}

export function initialize (localizedStringsProviderValue: LocalizedStringsProvider) {
  ArgumentsValidator.assertNotNullOrUndefined('localizedStringsProviderValue', localizedStringsProviderValue);

  localizedStringsProvider = localizedStringsProviderValue;
  initialized = true;
}

function getLocaleInfoFromLocale (userInputLocaleNormalized: string): LocaleWithTable {
  ensureInitialized();

  const supportedLocales = localizedStringsProvider.getSupportedLocaleStrings();
  const supportedLocalesArray: string[] = [];
  Object.keys(supportedLocales).forEach(key => {
    supportedLocalesArray[key] = supportedLocales[key];
  });

  const newLocale = fallbackLocale(userInputLocaleNormalized, supportedLocalesArray, 'en-US');
  let localizedStringTable: any = supportedLocales[newLocale];
  // to resolve synchronous function giving stringtable
  localizedStringTable = typeof localizedStringTable === 'function' ? localizedStringTable() : localizedStringTable;
  return {
    newLocale,
    localizedStringTable
  };
}

function ensureInitialized (): void {
  if (!initialized) {
    throw new Error('LocalizedStrings not initialized. Please initialize before using it.');
  }
}

function getActiveConfig (): ConfigModel {
  ensureInitialized();
  return {
    normalizeLocale: normalizeLocale,
    getLocaleInfoFromLocale: getLocaleInfoFromLocale,
    defaultStringTable: localizedStringsProvider.getDefaultLocaleStrings()
  };
}
