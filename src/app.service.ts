import { HttpService, Injectable } from '@nestjs/common';
import { Observable } from 'rxjs';
import { AxiosResponse } from 'axios';

import { InsultDto } from './insult.dto';

@Injectable()
export class AppService {
  isoLanguages = new Set(['en', 'de', 'cn', 'el', 'es', 'fr', 'ru', 'sw']);
  insultServiceURL = 'https://evilinsult.com/generate_insult.php';

  constructor(private httpService: HttpService) {}

  public getInsults(lang): Promise<AxiosResponse<InsultDto>> {
    if (!this.isoLanguages.has(lang)) {
      throw new Error(`${lang} is not a supported language`);
    }

    return this.httpService
      .get(this.insultServiceURL, {
        params: { lang, type: 'json' },
      })
      .toPromise();
  }
}
