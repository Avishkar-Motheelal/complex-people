import { Component } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Apartment} from "../../models/apartment.model";
import {NewApartmentDto} from "../../models/new-apartment-dto.model";
import {ApartmentsService} from "../../services/apartments.service";
import {Router} from '@angular/router';
import {catchError, of} from "rxjs";




@Component({
  selector: 'app-add-apartment',
  templateUrl: './add-apartment.component.html',
  styleUrls: ['./add-apartment.component.css']
})
export class AddApartmentComponent {
  apartmentForm = new FormGroup({
    unitNumber: new FormControl(),
    bedrooms: new FormControl(),
    bathrooms: new FormControl(),
    parkingSpaces: new FormControl()
  });

  errorMessage: string = '';

  constructor(private apartmentsService: ApartmentsService, private router: Router) {
  }

  onSubmit() {
    // map to dto
    const newApartmentDto: NewApartmentDto = {
      unitNumber: this.apartmentForm.value.unitNumber,
      bedrooms: this.apartmentForm.value.bedrooms,
      bathrooms: this.apartmentForm.value.bathrooms,
      parkingSpaces: this.apartmentForm.value.parkingSpaces,
    };

    // subscribe
    this.apartmentsService.addNewApartment(newApartmentDto)
      .subscribe(
        data => void this.router.navigateByUrl('/apartments'),
        error => this.errorMessage = error.error.message
      );
  }


}
