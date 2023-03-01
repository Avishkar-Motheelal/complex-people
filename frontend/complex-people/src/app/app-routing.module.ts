import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./login/register.component";
import {AccountDetailsComponent} from "./account-details/account-details.component";
import {MaintenanceComponent} from "./components/maintenance/maintenance.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {ApartmentsComponent} from "./components/apartments/apartments.component";
import {ResidentsComponent} from "./components/residents/residents.component";
import {ResidentDetailsComponent} from "./components/resident-details/resident-details.component";
import {ApartmentDetailsComponent} from "./components/apartment-details/apartment-details.component";

const routes: Routes = [
  {path: "", redirectTo: "dashboard", pathMatch: 'full'},
  {path: "dashboard", component: DashboardComponent, pathMatch: 'full'},
  {path: "maintenance", component: MaintenanceComponent, pathMatch: 'full'},
  { path: 'apartments', component: ApartmentsComponent },
  { path: 'residents', component: ResidentsComponent },
  { path: 'residents/:id', component: ResidentDetailsComponent},
  { path: 'apartments/:id', component: ApartmentDetailsComponent},
];
const routes: Routes = [
  {path: "", redirectTo: "/login", pathMatch: "full"},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "account/details", component: AccountDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
