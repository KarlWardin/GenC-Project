import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';

describe('AuthService', () => {

  let service: AuthService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule]
    });
    service = TestBed.inject(AuthService);
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
  });
  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpTestingController.verify();
  });



  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('#login function test', () => {
    const testData: string = "eyTest.Test.Test";

    service.login({accountNo:"test",password:"test"}).subscribe();

    const req = httpTestingController.expectOne('http://localhost:8888/login');

    // Assert that the request is a POST.
    expect(req.request.method).toEqual('POST');

    // Respond with mock data, causing Observable to resolve.
    // Subscribe callback asserts that correct data was returned.
    req.flush(testData);
  });

});
