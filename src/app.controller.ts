import { Body, Controller, Get, Post, Query } from '@nestjs/common';
import { AppService } from './app.service';
import { SlackInsultResponseDto } from './slackInsultResponse.dto';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Post('/insult')
  async insultRequest(
    @Body('text') language: string,
  ): Promise<SlackInsultResponseDto> {
    const lang = language != null && language != '' ? language : 'en';
    return await this.appService
      .getInsults(lang)
      .then((insultDTO) => ({
        text: insultDTO.insult,
        response_type: 'in_channel',
      }))
      .catch((err) => {
        console.error('Error occurred');
        console.error(err);

        throw new Error('Error occurred while generating insult');
      });
  }
}
