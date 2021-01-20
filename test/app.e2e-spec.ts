import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { AppModule } from './../src/app.module';
import { query } from 'express';

describe('AppController (e2e)', () => {
  let app: INestApplication;

  beforeEach(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  it('/insult (POST)', () => {
    return request(app.getHttpServer())
      .post('/insult')
      .expect(201)
      .then((res) => {
        expect(res.body).toEqual(
          expect.objectContaining({
            response_type: 'in_channel',
            text: expect.any(String),
          }),
        );
      })
      .catch((err) => fail(err));
  });

  it('/insult (POST) different language', () => {
    return request(app.getHttpServer())
      .post('/insult')
      .query({ text: 'el' })
      .expect(201)
      .then((res) => {
        expect(res.body).toEqual(
          expect.objectContaining({
            response_type: 'in_channel',
            text: expect.any(String),
          }),
        );
      })
      .catch((err) => fail(err));
  });

  it('/insult (POST) wrong language', () => {
    return request(app.getHttpServer())
      .post('/insult')
      .query({ text: 'brainfuck' })
      .expect(500)
      .catch((err) => expect(err).not.toBeNull());
  });
});
