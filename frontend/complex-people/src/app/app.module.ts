import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {DashboardComponent} from './components/dashboard/dashboard.component';
import {MaintenanceComponent} from './components/maintenance/maintenance.component';
import {TenantRequestsComponent} from './components/tenant-requests/tenant-requests.component';
import {NavigationWebComponent} from './components/navigation-web/navigation-web.component';

import {ApartmentsComponent} from './components/apartments/apartments.component';
import {ResidentsComponent} from './components/residents/residents.component';
import {ApartmentDetailsComponent} from './components/apartment-details/apartment-details.component';
import {ResidentDetailsComponent} from './components/resident-details/resident-details.component';
import {FormsModule} from "@angular/forms";
import {VisitorsComponent} from './components/visitors/visitors.component';
import {VisitorsDetailsComponent} from './components/visitors-details/visitors-details.component';
import {AddApartmentComponent} from './components/add-apartment/add-apartment.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AddResidentComponent} from './components/add-resident/add-resident.component';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AlertComponent} from './alert/alert.component';
import {RegisterComponent} from './login/register.component';
import {AccountDetailsComponent} from './account-details/account-details.component';
import {ErrorInterceptor, JwtInterceptor} from "./_helpers";
import {LogoutComponent} from './components/logout/logout.component';

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
    VisitorsComponent,
    VisitorsDetailsComponent,
    AddApartmentComponent,
    AddResidentComponent,
    AppComponent,
    LoginComponent,
    AlertComponent,
    RegisterComponent,
    AccountDetailsComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}, //TODO enable after debugging
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
