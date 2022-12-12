import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { DeviceService } from './device.service';

describe('DeviceService', () => {
  let service: DeviceService;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(DeviceService);
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
  it('#getImeiNo function test', () => {
    const testData: string = "86457993747854894";

    localStorage.setItem('mpCurrentToken', 'mpCurrentToken');

    service.getImeiNo("1234567890", "test").subscribe();

    const req = httpTestingController.expectOne(
      request => request.headers.has('Authorization')
        && request.url === "http://localhost:8080/getImeiNo?mobileNo=1234567890&accountNo=test"
    );

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    req.flush(testData);
  });
  it('#getMobileNO function test', () => {
    const testData = ["1234567890", "9673926301"];

    localStorage.setItem('mpCurrentToken', 'mpCurrentToken');

    service.getMobileNO("test").subscribe();

    const req = httpTestingController.expectOne(
      (request) => request.headers.has('Authorization')
        && request.url === "http://localhost:8080/getMobileNo?accountNo=test"
    );

    // Assert that the request is a GET.
    expect(req.request.method).toEqual('GET');

    req.flush(testData);
  });
});
