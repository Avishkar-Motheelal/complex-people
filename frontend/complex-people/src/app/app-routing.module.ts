import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MaintenanceComponent} from "./components/maintenance/maintenance.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {ApartmentsComponent} from "./components/apartments/apartments.component";
import {ResidentsComponent} from "./components/residents/residents.component";
import {ResidentDetailsComponent} from "./components/resident-details/resident-details.component";
import {ApartmentDetailsComponent} from "./components/apartment-details/apartment-details.component";
import {VisitorsComponent} from "./components/visitors/visitors.component";
import {VisitorsDetailsComponent} from "./components/visitors-details/visitors-details.component";
import {AddApartmentComponent} from "./components/add-apartment/add-apartment.component";

const routes: Routes = [
  {path: "", redirectTo: "dashboard", pathMatch: 'full'},
  {path: "dashboard", component: DashboardComponent, pathMatch: 'full'},
  {path: "maintenance", component: MaintenanceComponent, pathMatch: 'full'},
  { path: 'apartments', component: ApartmentsComponent },
  { path: 'residents', component: ResidentsComponent },
  { path: 'residents/:id', component: ResidentDetailsComponent},
  { path: 'apartments/:id', component: ApartmentDetailsComponent},
  {path: 'visitors', component: VisitorsComponent},
  {path: 'visitors/:id', component: VisitorsDetailsComponent},
  {path: 'new-apartment', component: AddApartmentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
