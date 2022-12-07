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

  constructor(private deviceService: DeviceService,private route: Router) { }

  ngOnInit(): void {
  }

  getDevices() {
    const accountNo: string = this.accountInpEl!.nativeElement.value;
    console.log(accountNo);
    this.deviceService.getMobileNO(accountNo).subscribe(
      (response) => {                           //next() callback
        console.log(response)
        this.devices = response;
      },
      (error) => {                              //error() callback
        alert(error);
      },
      () => { });                                //complete() callback

    //console.log(this.devices);
  }

  sendToLogIn() {
    this.route.navigate(['/login']);
  }

}
