import {Component, Input} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Apartment} from "../../models/apartment.model";
import {ApartmentsService} from "../../services/apartments.service";

@Component({
  selector: 'app-apartment-details',
  templateUrl: './apartment-details.component.html',
  styleUrls: ['./apartment-details.component.css']
})
export class ApartmentDetailsComponent {
  @Input() apartment?: Apartment;

  constructor(
    private route: ActivatedRoute,
    private apartmentServices: ApartmentsService
  ) {}


  ngOnInit(): void {
    this.getApartment();
  }

  getApartment(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.apartmentServices.getApartment(id)
      .subscribe(apartment => this.apartment = apartment);
  }
}
