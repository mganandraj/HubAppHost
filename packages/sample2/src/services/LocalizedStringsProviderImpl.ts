/**
 * Copyright © <author>. All rights reserved.
 */
import { LocalizedStringsProvider } from 'teams-mobile-sdk';

export class LocalizedStringsProviderImpl implements LocalizedStringsProvider {
  private static instance: LocalizedStringsProviderImpl = new LocalizedStringsProviderImpl();
  private englishStringTable: any;
  private supportedLocales: { [key: string]: () => Object };

  constructor () {
    const self = this;
    self.englishStringTable = require('../../resources/strings/strings_en.json');
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

  public getSupportedLocaleStrings (): any {
    return this.supportedLocales;
  }

  public getDefaultLocaleStrings (): any {
    return this.englishStringTable;
  }
}
