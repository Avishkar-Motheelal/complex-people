import { Component } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {VisitorsService} from "../../services/visitors.service";
import {NewVisitorDto} from "../../models/new-visitor-dto.model";
import {ApartmentsService} from "../../services/apartments.service";

@Component({
  selector: 'app-add-visitor',
  templateUrl: './add-visitor.component.html',
  styleUrls: ['./add-visitor.component.css']
})
export class AddVisitorComponent {

  apartmentNumber: number | any;
  apartmentBedrooms: number | any;
  apartmentBathrooms: number | any;
  apartmentParkingSpace: number | any;

  visitorForm = new FormGroup({
    firstName: new FormControl(),
    lastName: new FormControl(),
    phoneNumber: new FormControl(),
    emailAddress: new FormControl(),
    identificationNumber: new FormControl(),
    identificationType: new FormControl(),
    apartmentId: new FormControl()
  });

  errorMessage: string = '';

  constructor(private visitorService: VisitorsService, private router: Router,
              private apartmentService: ApartmentsService) {}

  onSubmit() {

    this.apartmentService.getApartment(this.visitorForm.value.apartmentId).subscribe((apartment) => {
      this.apartmentNumber = apartment.unitNumber;
      this.apartmentBathrooms = apartment.bathrooms;
      this.apartmentBedrooms = apartment.bedrooms;
      this.apartmentParkingSpace = apartment.parkingSpaces;

      const newVisitorDto: NewVisitorDto = {
        "firstName": this.visitorForm.value.firstName,
        "lastName": this.visitorForm.value.lastName,
        "phoneNumber": this.visitorForm.value.phoneNumber,
        "emailAddress": this.visitorForm.value.emailAddress,
        "identificationDocument": {
          "number": this.visitorForm.value.identificationNumber,
          "documentType": this.visitorForm.value.identificationType
        },
        "apartmentId": this.visitorForm.value.apartmentId,

        "role": {
          "roleType": "VISITOR",
        }
      };

      this.visitorService.addVisitor(newVisitorDto).subscribe(
        data => void this.router.navigateByUrl('/visitors'),
        error => this.errorMessage = error.error.message
      );


    });
  }

}
