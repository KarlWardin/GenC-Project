import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ImeiComponent } from './pages/imei/imei.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: ImeiComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
