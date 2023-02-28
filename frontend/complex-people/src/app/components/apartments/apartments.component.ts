import { Component } from '@angular/core';
import {ApartmentsService} from "../../services/apartments.service";
import {Apartment} from "../../models/apartment.model";

@Component({
  selector: 'app-apartments',
  templateUrl: './apartments.component.html',
  styleUrls: ['./apartments.component.css']
})
export class ApartmentsComponent {
  apartments: Apartment[] = [];

  constructor(private apartmentService: ApartmentsService) {}

  ngOnInit(): void {
    this.getApartments();
  }

  getApartments(): void {
    this.apartmentService.getApartments().subscribe((apartments) => {
      this.apartments = apartments;
    });
  }

}
