import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { Location } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { DeviceService } from 'src/app/device/device.service';

import { ImeiComponent } from './imei.component';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

describe('ImeiComponent', () => {
  let component: ImeiComponent;
  let fixture: ComponentFixture<ImeiComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([]), HttpClientTestingModule,FormsModule],
      declarations: [ImeiComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(ImeiComponent);
    component = fixture.componentInstance;
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    router = TestBed.get(Router);
    fixture.detectChanges();
  });
  afterEach(() => {
    httpTestingController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('#sendToLogIn check', () => {
    const navigateSpy = spyOn(router, 'navigate');
    let button = fixture.debugElement.nativeElement.querySelector('#loginBtn');
    button.click();
    //component.sendToLogIn();
    expect(navigateSpy).toHaveBeenCalledWith(['/login']);
  });
});
