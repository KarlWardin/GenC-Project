import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { DeviceService } from 'src/app/device/device.service';

@Component({
  selector: 'app-imei',
  templateUrl: './imei.component.html',
  styleUrls: ['./imei.component.css']
})
export class ImeiComponent implements OnInit {

  @ViewChild('accountInpEl') accountInpEl: ElementRef | undefined;
  devices: string[] = [];
  accountNo: string = localStorage.getItem("mpCurrentAccNo") || "";

  constructor(private deviceService: DeviceService, private route: Router) { }

  ngOnInit(): void {
  }

  getDevices() {
    console.log(this.accountNo);
    this.deviceService.getMobileNO(this.accountNo).subscribe(
      (response) => {                           //next() callback
        console.log(response);
        this.devices = response;
      },
      (error) => {                              //error() callback
        console.log(error);
      },
      () => {
        if (this.devices.length === 0) {
          alert("Recheck the Account no or Login again");
        }
      });                                //complete() callback
  }

  sendToLogIn() {
    this.route.navigate(['/login']);
  }

}
