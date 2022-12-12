import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';
import { Location } from '@angular/common';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { LoginComponent } from './login.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, FormsModule],
      declarations: [LoginComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    router = TestBed.get(Router);
    fixture.detectChanges();
  });
  afterEach(() => {
    // After every test, assert that there are no more pending requests.
    httpTestingController.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('reset Button press', fakeAsync(() => {
    let button = fixture.debugElement.nativeElement.querySelector('#resetBtn');
    button.click();
    fixture.detectChanges();
    tick();
    let accountNoInput = fixture.debugElement.nativeElement.querySelector('#accountNo').value;
    let passwordInput = fixture.debugElement.nativeElement.querySelector('#password').value;
    expect(accountNoInput).toBe('');
    expect(passwordInput).toBe('');
  }));
  it('#onLogin check', fakeAsync(() => {
    const testData: string = "eyTest.Test.Test";
    let button = fixture.debugElement.nativeElement.querySelector('#submitBtn');
    let accountNoInput = fixture.debugElement.nativeElement.querySelector('#accountNo');
    let passwordInput = fixture.debugElement.nativeElement.querySelector('#password');
    accountNoInput.value = "test";
    passwordInput.value = "test";
    accountNoInput.dispatchEvent(new Event('input'));
    passwordInput.dispatchEvent(new Event('input'));
    fixture.detectChanges();
    button.click();
    const req = httpTestingController.expectOne('http://localhost:8888/login');
    expect(req.request.method).toEqual('POST');
    req.flush(testData);
  }));
  it('#sendToHome check', () => {
    const navigateSpy = spyOn(router, 'navigate');
    //let button = fixture.debugElement.nativeElement.querySelector('#backBtn');
    //button.click();
    component.sendToHome();
    expect(navigateSpy).toHaveBeenCalledWith(['/']);
  });
});
