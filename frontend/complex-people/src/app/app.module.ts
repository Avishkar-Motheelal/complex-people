import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {HttpClientModule} from "@angular/common/http";
import {NavigationWebComponent} from './components/navigation-web/navigation-web.component';
import {ApartmentsComponent} from './components/apartments/apartments.component';
import {ResidentsComponent} from './components/residents/residents.component';
import {ApartmentDetailsComponent} from './components/apartment-details/apartment-details.component';
import {ResidentDetailsComponent} from './components/resident-details/resident-details.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {VisitorsComponent} from './components/visitors/visitors.component';
import {VisitorsDetailsComponent} from './components/visitors-details/visitors-details.component';
import {AccountComponent} from './components/account/account.component';
import {MaintenanceFormComponent} from './components/maintenance-form/maintenance-form.component';
import {TenantRequestsComponent} from "./components/tenant-requests/tenant-requests.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {MaintenanceComponent} from "./components/maintenance/maintenance.component";
import { AddMaintenanceComponent } from './components/add-maintenance/add-maintenance.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationWebComponent,
    DashboardComponent,
    MaintenanceComponent,
    TenantRequestsComponent,
    AccountComponent,
    MaintenanceFormComponent,
    NavigationWebComponent,
    ApartmentsComponent,
    ResidentsComponent,
    ApartmentDetailsComponent,
    ResidentDetailsComponent,
    VisitorsComponent,
    VisitorsDetailsComponent,
    AddMaintenanceComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
