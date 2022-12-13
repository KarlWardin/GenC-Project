import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { DeviceService } from 'src/app/device/device.service';

import { CardComponent } from './card.component';

describe('CardComponent', () => {
  let component: CardComponent;
  let fixture: ComponentFixture<CardComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [DeviceService],
      declarations: [CardComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(CardComponent);
    component = fixture.componentInstance;
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('#getImeiNo check', fakeAsync(() => {
    const testData: string = '86457993747854894';
    let button = fixture.debugElement.nativeElement.querySelector('#getImeiBtn');
    component.accountNo='test';
    component.mobileNo='1234567890';
    button.click();
    fixture.detectChanges();
    tick();
    const req = httpTestingController.expectOne('http://localhost:8080/getImeiNo?mobileNo=1234567890&accountNo=test');
    expect(req.request.method).toEqual('GET');
    req.flush(testData);
  }))
});
