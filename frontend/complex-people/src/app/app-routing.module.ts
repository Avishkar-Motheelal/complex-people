import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MaintenanceComponent} from "./maintenance/maintenance.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ApartmentsComponent} from "./components/apartments/apartments.component";
import {ResidentsComponent} from "./components/residents/residents.component";
import {ResidentDetailsComponent} from "./components/resident-details/resident-details.component";
import {ApartmentDetailsComponent} from "./components/apartment-details/apartment-details.component";

const routes: Routes = [
  {path: "", redirectTo: "dashboard", pathMatch: 'full'},
  {path: "dashboard", component: DashboardComponent, pathMatch: 'full'},
  {path: "maintenance", component: MaintenanceComponent, pathMatch: 'full'}
];
const routes: Routes = [
  { path: 'apartments', component: ApartmentsComponent },
  { path: 'residents', component: ResidentsComponent },
  { path: 'residents/:id', component: ResidentDetailsComponent},
  { path: 'apartments/:id', component: ApartmentDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
