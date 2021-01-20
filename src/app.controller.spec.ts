import { Test, TestingModule } from '@nestjs/testing';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { HttpModule } from '@nestjs/common';

describe('AppController', () => {
  let appController: AppController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      imports: [HttpModule],
      controllers: [AppController],
      providers: [AppService],
    }).compile();

    appController = app.get<AppController>(AppController);
  });

  describe('root', () => {
    it('Should return an insult', async () => {
      const insultResponse = await appController.insultRequest('en');

      expect(insultResponse).toMatchObject({
        response_type: 'in_channel',
        text: jasmine.anything(),
      });
    });

    it('Should fail with wrong language', async () => {
      await expect(
        appController.insultRequest('brainfuck'),
      ).rejects.toThrowError('brainfuck is not a supported language');
    });
  });
});
