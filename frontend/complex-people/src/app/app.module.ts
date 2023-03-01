import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MaintenanceComponent } from './components/maintenance/maintenance.component';
import {HttpClientModule} from "@angular/common/http";
import { TenantRequestsComponent } from './components/tenant-requests/tenant-requests.component';
import { NavigationWebComponent } from './components/navigation-web/navigation-web.component';

import { ApartmentsComponent } from './components/apartments/apartments.component';
import { ResidentsComponent } from './components/residents/residents.component';
import { ApartmentDetailsComponent } from './components/apartment-details/apartment-details.component';
import { ResidentDetailsComponent } from './components/resident-details/resident-details.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    NavigationWebComponent,
    DashboardComponent,
    MaintenanceComponent,
    TenantRequestsComponent,
    NavigationWebComponent,
    ApartmentsComponent,
    ResidentsComponent,
    ApartmentDetailsComponent,
    ResidentDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
